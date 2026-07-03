package com.lins.card.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.lins.card.data.model.Card
import com.lins.card.data.model.CardCategory
import com.lins.card.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItem(
    card: Card,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val category = CardCategory.fromString(card.category)
    val categoryColor = when (category) {
        CardCategory.BANK -> CategoryBank
        CardCategory.MEMBER -> CategoryMember
        CardCategory.ID -> CategoryId
        CardCategory.SOCIAL -> CategorySocial
        CardCategory.DRIVER -> CategoryDriver
        CardCategory.PASSPORT -> CategoryPassport
        CardCategory.OTHER -> CategoryOther
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Outline),
        colors = CardDefaults.cardColors(containerColor = Surface),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = categoryColor.copy(alpha = 0.1f))
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = category.displayName.first().toString(),
                            style = MaterialTheme.typography.titleLarge,
                            color = categoryColor
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = card.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (card.holderName.isNotEmpty()) {
                    Text(
                        text = card.holderName,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Outline
                    )
                }
                Text(
                    text = category.displayName,
                    style = MaterialTheme.typography.bodySmall,
                    color = categoryColor
                )
            }

            if (card.isFavorite) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "收藏",
                    tint = Color.Red,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
