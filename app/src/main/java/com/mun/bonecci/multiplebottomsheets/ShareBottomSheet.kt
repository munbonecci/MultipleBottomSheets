package com.mun.bonecci.multiplebottomsheets

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mun.bonecci.multiplebottomsheets.utils.Constants.DARK_MODE
import com.mun.bonecci.multiplebottomsheets.utils.Constants.LIGHT_MODE
import com.mun.bonecci.multiplebottomsheets.components.CircleIconButton
import com.mun.bonecci.multiplebottomsheets.ui.theme.MultipleBottomSheetsTheme
import com.mun.bonecci.multiplebottomsheets.ui.theme.dimen_16dp
import com.mun.bonecci.multiplebottomsheets.utils.IconResource

@Composable
fun ShareBottomSheet(closeSheet: () -> Unit) {
    Column(
        modifier = Modifier.padding(dimen_16dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.share_label),
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .align(Alignment.Center)
                    .layoutId(SHARE_LABEL_ID)
            )
            CircleIconButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                layoutId = CLOSE_SHARE_BUTTON_ID,
                icon = IconResource.fromImageVector(Icons.Filled.Close).asPainterResource(),
                backGroundColor = Transparent,
                iconColor = Color.Black
            ) {
                closeSheet.invoke()
            }
        }
        Column(
            modifier = Modifier.padding(dimen_16dp)
        ) {
            Spacer(modifier = Modifier.height(dimen_16dp))
            Text(
                text = "Click outside the bottom sheet to hide it",
                style = MaterialTheme.typography.body1
            )
        }
    }
}

const val SHARE_LABEL_ID = "comments"
const val CLOSE_SHARE_BUTTON_ID = "close_share_button"

@Preview(name = LIGHT_MODE)
@Preview(name = DARK_MODE, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ShareScreenPreview() {
    MultipleBottomSheetsTheme {
        Surface {
            ShareBottomSheet(closeSheet = {})
        }
    }
}