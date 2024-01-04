package com.mun.bonecci.multiplebottomsheets.components

import androidx.compose.runtime.Composable
import com.mun.bonecci.multiplebottomsheets.CommentsBottomSheet
import com.mun.bonecci.multiplebottomsheets.ShareBottomSheet

/**
 * Enum class representing the type of content to be displayed in the bottom sheet.
 */
enum class BottomSheetType {
    COMMENTS, // Represents the comments content type
    SHARE     // Represents the share content type
}

/**
 * Composable function to render a bottom sheet layout based on the specified [bottomSheetType].
 *
 * @param bottomSheetType The type of content to be displayed in the bottom sheet.
 * @param closeSheet Callback to close the bottom sheet.
 */
@Composable
fun SheetLayout(
    bottomSheetType: BottomSheetType,
    closeSheet: () -> Unit
) {
    // Based on the provided type, invoke the corresponding bottom sheet composable.
    when (bottomSheetType) {
        BottomSheetType.COMMENTS -> CommentsBottomSheet(closeSheet)
        BottomSheetType.SHARE -> ShareBottomSheet(closeSheet)
    }
}