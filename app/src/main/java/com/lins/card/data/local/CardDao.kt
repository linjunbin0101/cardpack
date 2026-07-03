package com.lins.card.data.local

import androidx.room.*
import com.lins.card.data.model.Card
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Query("SELECT * FROM cards ORDER BY createdAt DESC")
    fun getAllCards(): Flow<List<Card>>

    @Query("SELECT * FROM cards WHERE category = :category ORDER BY createdAt DESC")
    fun getCardsByCategory(category: String): Flow<List<Card>>

    @Query("SELECT * FROM cards WHERE name LIKE '%' || :query || '%' OR note LIKE '%' || :query || '%' ORDER BY createdAt DESC")
    fun searchCards(query: String): Flow<List<Card>>

    @Query("SELECT * FROM cards WHERE isFavorite = 1 ORDER BY favoriteAt DESC")
    fun getFavoriteCards(): Flow<List<Card>>

    @Query("SELECT * FROM cards WHERE id = :id")
    suspend fun getCardById(id: Long): Card?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: Card): Long

    @Update
    suspend fun updateCard(card: Card)

    @Delete
    suspend fun deleteCard(card: Card)

    @Query("DELETE FROM cards WHERE id = :id")
    suspend fun deleteCardById(id: Long)

    @Query("SELECT COUNT(*) FROM cards")
    fun getCardCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM cards WHERE category = :category")
    fun getCardCountByCategory(category: String): Flow<Int>
}
