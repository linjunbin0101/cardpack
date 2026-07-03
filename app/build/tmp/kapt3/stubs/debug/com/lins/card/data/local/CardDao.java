package com.lins.card.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\'J\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\fH\'J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\f2\u0006\u0010\u0012\u001a\u00020\u0013H\'J\u001c\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u0012\u001a\u00020\u0013H\'J\u0014\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\'J\u0016\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u0018\u001a\u00020\u0013H\'J\u0016\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u001a"}, d2 = {"Lcom/lins/card/data/local/CardDao;", "", "deleteCard", "", "card", "Lcom/lins/card/data/model/Card;", "(Lcom/lins/card/data/model/Card;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCardById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllCards", "Lkotlinx/coroutines/flow/Flow;", "", "getCardById", "getCardCount", "", "getCardCountByCategory", "category", "", "getCardsByCategory", "getFavoriteCards", "insertCard", "searchCards", "query", "updateCard", "app_debug"})
@androidx.room.Dao()
public abstract interface CardDao {
    
    @androidx.room.Query(value = "SELECT * FROM cards ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.lins.card.data.model.Card>> getAllCards();
    
    @androidx.room.Query(value = "SELECT * FROM cards WHERE category = :category ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.lins.card.data.model.Card>> getCardsByCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String category);
    
    @androidx.room.Query(value = "SELECT * FROM cards WHERE name LIKE \'%\' || :query || \'%\' OR note LIKE \'%\' || :query || \'%\' ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.lins.card.data.model.Card>> searchCards(@org.jetbrains.annotations.NotNull()
    java.lang.String query);
    
    @androidx.room.Query(value = "SELECT * FROM cards WHERE isFavorite = 1 ORDER BY favoriteAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.lins.card.data.model.Card>> getFavoriteCards();
    
    @androidx.room.Query(value = "SELECT * FROM cards WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCardById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.lins.card.data.model.Card> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertCard(@org.jetbrains.annotations.NotNull()
    com.lins.card.data.model.Card card, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateCard(@org.jetbrains.annotations.NotNull()
    com.lins.card.data.model.Card card, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteCard(@org.jetbrains.annotations.NotNull()
    com.lins.card.data.model.Card card, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM cards WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteCardById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM cards")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Integer> getCardCount();
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM cards WHERE category = :category")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Integer> getCardCountByCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String category);
}