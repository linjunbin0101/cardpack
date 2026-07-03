package com.lins.card.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lins.card.data.model.CardCategory
import com.lins.card.ui.theme.*

@Composable
fun CategoryFilter(
    selectedCategory: CardCategory?,
    onCategorySelected: (CardCategory?) -> Unit,
    showFavoritesOnly: Boolean,
    onFavoritesToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        item {
            FilterChip(
                selected = showFavoritesOnly,
                onClick = onFavoritesToggle,
                label = { Text("收藏") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
            )
        }
        
        item {
            FilterChip(
                selected = selectedCategory == null && !showFavoritesOnly,
                onClick = { onCategorySelected(null) },
                label = { Text("全部") }
            )
        }
        
        items(CardCategory.entries) { category ->
            FilterChip(
                selected = selectedCategory == category && !showFavoritesOnly,
                onClick = { onCategorySelected(category) },
                label = { Text(category.displayName) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(
            width = if (selected) 0.dp else 1.dp,
            color = if (selected) Primary else Outline
        ),
        color = if (selected) Primary else Color.Transparent,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if (leadingIcon != null) {
                leadingIcon()
            }
            label()
        }
    }
}
