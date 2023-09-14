/*
 * Copyright 2021 GOGOLOOK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gogolook.wheresmoney.ui.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class WMShapes(
    val small: CornerBasedShape = RoundedCornerShape(16.dp),
    val medium: CornerBasedShape = RoundedCornerShape(24.dp),
    val large: CornerBasedShape = RoundedCornerShape(32.dp),
    val card: CornerBasedShape = small,
    val dialog: CornerBasedShape = small,
    val panel: CornerBasedShape = medium,
    val button: CornerBasedShape = large,
    val materialShapes: Shapes = Shapes(
        small = small,
        medium = medium,
        large = large
    )
)

@Deprecated("1.0")
val deprecatedShapes = Shapes(
    small = RoundedCornerShape(16.dp),
    medium = RoundedCornerShape(16.dp),//<- used by `DropdownMenu`
    large = RoundedCornerShape(0.dp)
)

open class PaddingCircleShape(private val paddingPx: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = Path().apply {
                addOval(
                    Rect(
                        left = paddingPx,
                        top = paddingPx,
                        right = size.width - paddingPx,
                        bottom = size.height - paddingPx
                    )
                )
            }
        )
    }
}
