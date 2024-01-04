package com.mun.bonecci.multiplebottomsheets

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mun.bonecci.multiplebottomsheets.components.BottomSheetType
import com.mun.bonecci.multiplebottomsheets.components.CircleIconButton
import com.mun.bonecci.multiplebottomsheets.components.SheetLayout
import com.mun.bonecci.multiplebottomsheets.ui.theme.MultipleBottomSheetsTheme
import com.mun.bonecci.multiplebottomsheets.ui.theme.dimen_16dp
import com.mun.bonecci.multiplebottomsheets.ui.theme.dimen_2dp
import com.mun.bonecci.multiplebottomsheets.ui.theme.dimen_50dp
import com.mun.bonecci.multiplebottomsheets.utils.Constants
import com.mun.bonecci.multiplebottomsheets.utils.IconResource
import kotlinx.coroutines.launch

/**
 * Composable function to demonstrate the use of multiple bottom sheets with Jetpack Compose.
 * It showcases two bottom sheets: Comments and Share, triggered by corresponding icons.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowBottomSheetsScreen() {
    // State to track the current type of bottom sheet being displayed.
    var currentBottomSheet: BottomSheetType? by remember { mutableStateOf(null) }

    // Modal bottom sheet state to control the visibility and behavior of the bottom sheet.
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded }
    )

    // Coroutine scope to launch and manage coroutines related to bottom sheet actions.
    val coroutineScope = rememberCoroutineScope()

    // Callback to close the bottom sheet.
    val closeSheet = { coroutineScope.launch { sheetState.hide() } }

    // Callback to open the bottom sheet.
    val openSheet = { coroutineScope.launch { sheetState.show() } }

    // BackHandler to automatically close the bottom sheet when the back button is pressed.
    BackHandler(sheetState.isVisible) { closeSheet() }

    // ModalBottomSheetLayout to define the layout and appearance of the bottom sheet.
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = dimen_16dp, topEnd = dimen_16dp),
        sheetContent = {
            // Display the current bottom sheet content based on the selected type.
            currentBottomSheet?.let {
                SheetLayout(
                    closeSheet = { closeSheet() },
                    bottomSheetType = it
                )
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
    ) {
        // Main content inside the bottom sheet layout.
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            // Box to contain the trigger icons for Comments and Share.
            Box(
                modifier = Modifier
                    .padding(dimen_2dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                // Column containing the trigger icons.
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = dimen_16dp, bottom = dimen_50dp, end = dimen_16dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.End
                ) {
                    // Trigger icon for Comments.
                    CircleIconButton(
                        layoutId = COMMENTS_ID,
                        icon = IconResource.fromDrawableResource(R.drawable.baseline_comment_24)
                            .asPainterResource(),
                        iconColor = Color.Black
                    ) {
                        coroutineScope.launch {
                            // Set the current bottom sheet type to Comments and open the sheet.
                            currentBottomSheet = BottomSheetType.COMMENTS
                            openSheet()
                        }
                    }

                    // Trigger icon for Share.
                    CircleIconButton(
                        layoutId = SHARE_ID,
                        icon = IconResource.fromImageVector(Icons.Filled.Share).asPainterResource(),
                        iconColor = Color.Black
                    ) {
                        // Set the current bottom sheet type to Share and open the sheet.
                        currentBottomSheet = BottomSheetType.SHARE
                        openSheet()
                    }
                }
            }
        }
    }
}

// Constants for identifying the types of bottom sheets.
const val COMMENTS_ID = "Comments"
const val SHARE_ID = "Share"

/**
 * Preview composable for the [ShowBottomSheetsScreen].
 */
@ExperimentalAnimationApi
@Preview(name = Constants.LIGHT_MODE)
@Preview(name = Constants.DARK_MODE, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ShowVideoPreview() {
    // Apply the theme and display the [ShowBottomSheetsScreen] in a Surface.
    MultipleBottomSheetsTheme {
        Surface {
            ShowBottomSheetsScreen()
        }
    }
}