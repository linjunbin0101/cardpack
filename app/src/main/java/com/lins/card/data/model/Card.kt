package com.lins.card.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class Card(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val category: String,
    val holderName: String = "",
    val note: String = "",
    val frontImagePath: String = "",
    val backImagePath: String = "",
    val imagePaths: String = "",
    val color: Long = 0L,
    val isFavorite: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val favoriteAt: Long = 0L
) {
    fun getImagePathList(): List<String> {
        if (imagePaths.isNotEmpty()) {
            return imagePaths.split("|||").filter { it.isNotEmpty() }
        }
        val list = mutableListOf<String>()
        if (frontImagePath.isNotEmpty()) list.add(frontImagePath)
        if (backImagePath.isNotEmpty()) list.add(backImagePath)
        return list
    }
    
    companion object {
        fun fromImagePathList(paths: List<String>): String {
            return paths.joinToString("|||")
        }
    }
}
