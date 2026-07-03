package com.lins.card

import android.app.Application
import androidx.room.Room
import com.lins.card.data.local.CardDatabase
import com.lins.card.data.repository.CardRepository

class CardApplication : Application() {
    
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            CardDatabase::class.java,
            "card_database"
        ).fallbackToDestructiveMigration().build()
    }
    
    val repository by lazy {
        CardRepository(database.cardDao())
    }
    
    companion object {
        lateinit var instance: CardApplication
            private set
    }
    
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
