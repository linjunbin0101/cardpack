package com.lins.card.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lins.card.CardApplication
import com.lins.card.data.model.Card
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class CardListState(
    val cards: List<Card> = emptyList()
)

class CardViewModel : ViewModel() {
    
    private val repository = CardApplication.instance.repository
    
    val uiState: StateFlow<CardListState> = repository.getAllCards()
        .map { cards -> 
            val sortedCards = cards.sortedWith(
                compareByDescending<Card> { it.isFavorite }
                    .thenByDescending { if (it.isFavorite) it.favoriteAt else it.createdAt }
            )
            CardListState(cards = sortedCards)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            CardListState()
        )
    
    fun addCard(card: Card) {
        viewModelScope.launch {
            repository.insertCard(card)
        }
    }
    
    fun updateCard(card: Card) {
        viewModelScope.launch {
            repository.updateCard(card.copy(updatedAt = System.currentTimeMillis()))
        }
    }
    
    fun deleteCard(card: Card) {
        viewModelScope.launch {
            repository.deleteCard(card)
        }
    }
    
    fun toggleFavorite(card: Card) {
        viewModelScope.launch {
            val newFavorite = !card.isFavorite
            repository.updateCard(card.copy(
                isFavorite = newFavorite,
                updatedAt = System.currentTimeMillis(),
                favoriteAt = if (newFavorite) System.currentTimeMillis() else 0L
            ))
        }
    }
}
