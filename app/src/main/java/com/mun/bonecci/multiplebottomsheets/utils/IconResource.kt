package com.mun.bonecci.multiplebottomsheets.utils

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource

/**
 * Utility class for creating [Painter] instances from either a drawable resource ID or an [ImageVector].
 *
 * @property resID Drawable resource ID.
 * @property imageVector Image vector to be used as an alternative to drawable resource.
 */
class IconResource private constructor(
    @DrawableRes private val resID: Int?,
    private val imageVector: ImageVector?
) {

    /**
     * Converts the [IconResource] to a [Painter] resource.
     *
     * @return [Painter] resource based on the drawable resource ID or image vector.
     */
    @Composable
    fun asPainterResource(): Painter {
        resID?.let {
            return painterResource(id = resID)
        }
        return rememberVectorPainter(image = imageVector!!)
    }

    companion object {
        /**
         * Creates an [IconResource] instance from a drawable resource ID.
         *
         * @param resID Drawable resource ID.
         * @return [IconResource] instance with the specified drawable resource ID.
         */
        fun fromDrawableResource(@DrawableRes resID: Int): IconResource {
            return IconResource(resID, null)
        }

        /**
         * Creates an [IconResource] instance from an [ImageVector].
         *
         * @param imageVector Image vector to be used as an alternative to drawable resource.
         * @return [IconResource] instance with the specified image vector.
         */
        fun fromImageVector(imageVector: ImageVector?): IconResource {
            return IconResource(null, imageVector)
        }
    }
}
