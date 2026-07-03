package com.lins.card.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0011"}, d2 = {"Lcom/lins/card/ui/viewmodel/CardViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "repository", "Lcom/lins/card/data/repository/CardRepository;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/lins/card/ui/viewmodel/CardListState;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "addCard", "", "card", "Lcom/lins/card/data/model/Card;", "deleteCard", "toggleFavorite", "updateCard", "app_debug"})
public final class CardViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.lins.card.data.repository.CardRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.lins.card.ui.viewmodel.CardListState> uiState = null;
    
    public CardViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.lins.card.ui.viewmodel.CardListState> getUiState() {
        return null;
    }
    
    public final void addCard(@org.jetbrains.annotations.NotNull()
    com.lins.card.data.model.Card card) {
    }
    
    public final void updateCard(@org.jetbrains.annotations.NotNull()
    com.lins.card.data.model.Card card) {
    }
    
    public final void deleteCard(@org.jetbrains.annotations.NotNull()
    com.lins.card.data.model.Card card) {
    }
    
    public final void toggleFavorite(@org.jetbrains.annotations.NotNull()
    com.lins.card.data.model.Card card) {
    }
}