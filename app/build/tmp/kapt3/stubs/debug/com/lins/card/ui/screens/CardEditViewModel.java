package com.lins.card.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0004J\u0016\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001bH\u0086@\u00a2\u0006\u0002\u0010\u001cJ\u000e\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u001fJ\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010#J\u000e\u0010$\u001a\u00020\u00152\u0006\u0010%\u001a\u00020&J\u000e\u0010\'\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u0017J\u0016\u0010(\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010)\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u0017J\u000e\u0010*\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u0017R+\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0013\u0010\n\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012\u00a8\u0006+"}, d2 = {"Lcom/lins/card/ui/screens/CardEditViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "<set-?>", "", "isEditMode", "()Z", "setEditMode", "(Z)V", "isEditMode$delegate", "Landroidx/compose/runtime/MutableState;", "repository", "Lcom/lins/card/data/repository/CardRepository;", "Lcom/lins/card/ui/screens/CardEditState;", "state", "getState", "()Lcom/lins/card/ui/screens/CardEditState;", "setState", "(Lcom/lins/card/ui/screens/CardEditState;)V", "state$delegate", "addImage", "", "path", "", "isValid", "loadCard", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeImage", "index", "", "toCard", "Lcom/lins/card/data/model/Card;", "toggleFavoriteAndSave", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateCategory", "value", "Lcom/lins/card/data/model/CardCategory;", "updateHolderName", "updateImage", "updateName", "updateNote", "app_debug"})
public final class CardEditViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.lins.card.data.repository.CardRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState state$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState isEditMode$delegate = null;
    
    public CardEditViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.lins.card.ui.screens.CardEditState getState() {
        return null;
    }
    
    private final void setState(com.lins.card.ui.screens.CardEditState p0) {
    }
    
    public final boolean isEditMode() {
        return false;
    }
    
    private final void setEditMode(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object loadCard(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void updateName(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void updateCategory(@org.jetbrains.annotations.NotNull()
    com.lins.card.data.model.CardCategory value) {
    }
    
    public final void updateHolderName(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void updateNote(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void addImage(@org.jetbrains.annotations.NotNull()
    java.lang.String path) {
    }
    
    public final void removeImage(int index) {
    }
    
    public final void updateImage(int index, @org.jetbrains.annotations.NotNull()
    java.lang.String path) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object toggleFavoriteAndSave(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.lins.card.data.model.Card toCard() {
        return null;
    }
    
    public final boolean isValid() {
        return false;
    }
}