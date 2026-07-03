package com.lins.card.data.repository

import com.lins.card.data.local.CardDao
import com.lins.card.data.model.Card
import kotlinx.coroutines.flow.Flow

class CardRepository(private val cardDao: CardDao) {
    
    fun getAllCards(): Flow<List<Card>> = cardDao.getAllCards()
    
    fun getCardsByCategory(category: String): Flow<List<Card>> = 
        cardDao.getCardsByCategory(category)
    
    fun searchCards(query: String): Flow<List<Card>> = 
        cardDao.searchCards(query)
    
    fun getFavoriteCards(): Flow<List<Card>> = cardDao.getFavoriteCards()
    
    suspend fun getCardById(id: Long): Card? = cardDao.getCardById(id)
    
    suspend fun insertCard(card: Card): Long = cardDao.insertCard(card)
    
    suspend fun updateCard(card: Card) = cardDao.updateCard(card)
    
    suspend fun deleteCard(card: Card) = cardDao.deleteCard(card)
    
    suspend fun deleteCardById(id: Long) = cardDao.deleteCardById(id)
    
    fun getCardCount(): Flow<Int> = cardDao.getCardCount()
    
    fun getCardCountByCategory(category: String): Flow<Int> = 
        cardDao.getCardCountByCategory(category)
}
