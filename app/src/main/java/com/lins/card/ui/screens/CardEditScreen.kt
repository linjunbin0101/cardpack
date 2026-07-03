package com.lins.card.ui.screens

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.asImageBitmap
import com.lins.card.CardApplication
import com.lins.card.data.model.Card
import com.lins.card.data.model.CardCategory
import com.lins.card.ui.theme.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

data class CardEditState(
    val id: Long = 0,
    val name: String = "",
    val category: CardCategory = CardCategory.OTHER,
    val holderName: String = "",
    val note: String = "",
    val imagePaths: List<String> = emptyList(),
    val isFavorite: Boolean = false
)

class CardEditViewModel : androidx.lifecycle.ViewModel() {
    private val repository = CardApplication.instance.repository
    
    var state by mutableStateOf(CardEditState())
        private set
    
    var isEditMode by mutableStateOf(false)
        private set
    
    suspend fun loadCard(id: Long) {
        if (id > 0) {
            repository.getCardById(id)?.let { card ->
                state = CardEditState(
                    id = card.id,
                    name = card.name,
                    category = CardCategory.fromString(card.category),
                    holderName = card.holderName,
                    note = card.note,
                    imagePaths = card.getImagePathList(),
                    isFavorite = card.isFavorite
                )
                isEditMode = true
            }
        }
    }
    
    fun updateName(value: String) { state = state.copy(name = value) }
    fun updateCategory(value: CardCategory) { state = state.copy(category = value) }
    fun updateHolderName(value: String) { state = state.copy(holderName = value) }
    fun updateNote(value: String) { state = state.copy(note = value) }
    
    fun addImage(path: String) {
        state = state.copy(imagePaths = state.imagePaths + path)
    }
    
    fun removeImage(index: Int) {
        state = state.copy(imagePaths = state.imagePaths.toMutableList().apply { removeAt(index) })
    }
    
    fun updateImage(index: Int, path: String) {
        state = state.copy(imagePaths = state.imagePaths.toMutableList().apply { set(index, path) })
    }
    
    suspend fun toggleFavoriteAndSave() {
        val newFavoriteState = !state.isFavorite
        state = state.copy(isFavorite = newFavoriteState)
        if (isEditMode && state.id > 0) {
            repository.getCardById(state.id)?.let { card ->
                repository.updateCard(card.copy(
                    isFavorite = newFavoriteState,
                    favoriteAt = if (newFavoriteState) System.currentTimeMillis() else 0L
                ))
            }
        }
    }
    
    fun toCard(): Card {
        return Card(
            id = state.id,
            name = state.name,
            category = state.category.name,
            holderName = state.holderName,
            note = state.note,
            imagePaths = Card.fromImagePathList(state.imagePaths),
            isFavorite = state.isFavorite
        )
    }
    
    fun isValid(): Boolean {
        return state.name.isNotBlank()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardEditScreen(
    cardId: Long,
    onSave: () -> Unit,
    onDelete: () -> Unit,
    onBack: () -> Unit,
    viewModel: CardEditViewModel = viewModel()
) {
    val context = LocalContext.current
    val repository = CardApplication.instance.repository
    val scope = rememberCoroutineScope()
    
    LaunchedEffect(cardId) {
        viewModel.loadCard(cardId)
    }
    
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showCategoryPicker by remember { mutableStateOf(false) }
    var editingImageIndex by remember { mutableStateOf(-1) }
    var previewImagePath by remember { mutableStateOf<String?>(null) }
    
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            scope.launch {
                val savedPath = copyImageToPrivateStorage(context, it, "img_${System.currentTimeMillis()}")
                savedPath?.let { path ->
                    if (editingImageIndex >= 0 && editingImageIndex < viewModel.state.imagePaths.size) {
                        viewModel.updateImage(editingImageIndex, path)
                    } else {
                        viewModel.addImage(path)
                    }
                }
            }
        }
        editingImageIndex = -1
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onBack() }
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "返回")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(if (viewModel.isEditMode) "编辑卡片" else "添加卡片")
                    }
                },
                actions = {
                    if (viewModel.isEditMode) {
                        IconButton(onClick = { 
                            scope.launch { 
                                viewModel.toggleFavoriteAndSave() 
                            }
                        }) {
                            Icon(
                                imageVector = if (viewModel.state.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "收藏",
                                tint = if (viewModel.state.isFavorite) androidx.compose.ui.graphics.Color.Red else LocalContentColor.current
                            )
                        }
                        IconButton(onClick = { showDeleteDialog = true }) {
                            Icon(Icons.Default.Delete, contentDescription = "删除")
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = viewModel.state.name,
                onValueChange = viewModel::updateName,
                label = { Text("卡片名称 *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )
            
            CategorySelector(
                selectedCategory = viewModel.state.category,
                onCategorySelected = { viewModel.updateCategory(it) }
            )
            
            OutlinedTextField(
                value = viewModel.state.holderName,
                onValueChange = viewModel::updateHolderName,
                label = { Text("持卡人/卡号") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )
            
            OutlinedTextField(
                value = viewModel.state.note,
                onValueChange = viewModel::updateNote,
                label = { Text("备注") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                shape = RoundedCornerShape(12.dp)
            )
            
            viewModel.state.imagePaths.forEachIndexed { index, path ->
                ImagePickerWithDelete(
                    label = "照片 ${index + 1}",
                    imagePath = path,
                    onPreview = { previewImagePath = path },
                    onDelete = { viewModel.removeImage(index) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        editingImageIndex = -1
                        imagePickerLauncher.launch("image/*")
                    }
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outline
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "添加照片",
                    color = MaterialTheme.colorScheme.outline
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = {
                    if (viewModel.isValid()) {
                        val card = viewModel.toCard()
                        scope.launch {
                            if (viewModel.isEditMode) {
                                repository.updateCard(card)
                            } else {
                                repository.insertCard(card)
                            }
                            onSave()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                enabled = viewModel.isValid()
            ) {
                Text(if (viewModel.isEditMode) "保存修改" else "添加卡片")
            }
        }
    }
    
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("确认删除") },
            text = { Text("确定要删除这张卡片吗？此操作不可撤销。") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        scope.launch {
                            viewModel.state.id.let { id ->
                                repository.getCardById(id)?.let { card ->
                                    repository.deleteCard(card)
                                }
                            }
                            onDelete()
                        }
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("删除")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("取消")
                }
            }
        )
    }
    
    previewImagePath?.let { path ->
        ImagePreviewDialog(
            imagePath = path,
            onDismiss = { previewImagePath = null }
        )
    }
}

@Composable
private fun CategorySelector(
    selectedCategory: CardCategory,
    onCategorySelected: (CardCategory) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 56.dp)
                .clickable { expanded = true },
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedCategory.displayName,
                    style = MaterialTheme.typography.bodyLarge
                )
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }
        }
        
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            CardCategory.entries.forEach { category ->
                DropdownMenuItem(
                    text = { Text(category.displayName) },
                    onClick = {
                        onCategorySelected(category)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
private fun ImagePickerWithDelete(
    label: String,
    imagePath: String,
    onPreview: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline
            )
            IconButton(
                onClick = onDelete,
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "删除",
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.58f)
                .clickable(onClick = onPreview),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        ) {
            if (imagePath.isNotEmpty()) {
                LocalImage(
                    imagePath = imagePath,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.outline
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "点击上传",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        }
    }
}

@Composable
private fun ImagePicker(
    label: String,
    imagePath: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline
        )
        Spacer(modifier = Modifier.height(8.dp))
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.58f)
                .clickable(onClick = onClick),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        ) {
            if (imagePath.isNotEmpty()) {
                LocalImage(
                    imagePath = imagePath,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.outline
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "点击上传",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        }
    }
}

private suspend fun copyImageToPrivateStorage(
    context: Context,
    uri: Uri,
    prefix: String
): String? = withContext(Dispatchers.IO) {
    try {
        val imagesDir = File(context.filesDir, "card_images")
        if (!imagesDir.exists()) {
            imagesDir.mkdirs()
        }
        
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "${prefix}_${timeStamp}.jpg"
        val destFile = File(imagesDir, fileName)
        
        context.contentResolver.openInputStream(uri)?.use { input ->
            FileOutputStream(destFile).use { output ->
                input.copyTo(output)
            }
        }
        
        destFile.absolutePath
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

@Composable
private fun LocalImage(
    imagePath: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    val context = LocalContext.current
    var bitmap by remember { mutableStateOf<android.graphics.Bitmap?>(null) }
    
    LaunchedEffect(imagePath) {
        if (imagePath.isNotEmpty()) {
            withContext(Dispatchers.IO) {
                try {
                    val file = File(imagePath)
                    if (file.exists()) {
                        val options = BitmapFactory.Options().apply {
                            inSampleSize = 2
                        }
                        BitmapFactory.decodeFile(imagePath, options)
                    } else null
                } catch (e: Exception) {
                    null
                }
            }?.let { bitmap = it }
        }
    }
    
    bitmap?.let {
        Image(
            bitmap = it.asImageBitmap(),
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = contentScale
        )
    }
}

@Composable
private fun ImagePreviewDialog(
    imagePath: String,
    onDismiss: () -> Unit
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(androidx.compose.ui.graphics.Color.Black)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { onDismiss() })
                },
            contentAlignment = Alignment.Center
        ) {
            LocalImage(
                imagePath = imagePath,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, zoom, _ ->
                            scale = (scale * zoom).coerceIn(0.5f, 3f)
                            offset = Offset(
                                x = offset.x + pan.x,
                                y = offset.y + pan.y
                            )
                        }
                    }
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = offset.x,
                        translationY = offset.y
                    ),
                contentScale = ContentScale.Fit
            )
            
            IconButton(
                onClick = onDismiss,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "关闭",
                    tint = androidx.compose.ui.graphics.Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}
