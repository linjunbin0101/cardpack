package com.lins.card.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lins.card.data.model.Card

@Database(
    entities = [Card::class],
    version = 4,
    exportSchema = false
)
abstract class CardDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
}
