package com.lins.card.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aD\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\tH\u0007\u001a$\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000eH\u0003\u001a0\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0003\u001a>\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0003\u001a\u001e\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00112\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a.\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u001b\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u001dH\u0003\u001a(\u0010\u001e\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0011H\u0082@\u00a2\u0006\u0002\u0010$\u00a8\u0006%"}, d2 = {"CardEditScreen", "", "cardId", "", "onSave", "Lkotlin/Function0;", "onDelete", "onBack", "viewModel", "Lcom/lins/card/ui/screens/CardEditViewModel;", "CategorySelector", "selectedCategory", "Lcom/lins/card/data/model/CardCategory;", "onCategorySelected", "Lkotlin/Function1;", "ImagePicker", "label", "", "imagePath", "onClick", "modifier", "Landroidx/compose/ui/Modifier;", "ImagePickerWithDelete", "onPreview", "ImagePreviewDialog", "onDismiss", "LocalImage", "contentDescription", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "copyImageToPrivateStorage", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "prefix", "(Landroid/content/Context;Landroid/net/Uri;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class CardEditScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void CardEditScreen(long cardId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSave, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDelete, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    com.lins.card.ui.screens.CardEditViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void CategorySelector(com.lins.card.data.model.CardCategory selectedCategory, kotlin.jvm.functions.Function1<? super com.lins.card.data.model.CardCategory, kotlin.Unit> onCategorySelected) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ImagePickerWithDelete(java.lang.String label, java.lang.String imagePath, kotlin.jvm.functions.Function0<kotlin.Unit> onPreview, kotlin.jvm.functions.Function0<kotlin.Unit> onDelete, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ImagePicker(java.lang.String label, java.lang.String imagePath, kotlin.jvm.functions.Function0<kotlin.Unit> onClick, androidx.compose.ui.Modifier modifier) {
    }
    
    private static final java.lang.Object copyImageToPrivateStorage(android.content.Context context, android.net.Uri uri, java.lang.String prefix, kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @androidx.compose.runtime.Composable()
    private static final void LocalImage(java.lang.String imagePath, java.lang.String contentDescription, androidx.compose.ui.Modifier modifier, androidx.compose.ui.layout.ContentScale contentScale) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ImagePreviewDialog(java.lang.String imagePath, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
}