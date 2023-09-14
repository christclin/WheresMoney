package com.gogolook.wheresmoney.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.gogolook.wheresmoney.ui.theme.LocalColors
import com.gogolook.wheresmoney.ui.theme.LocalShapes
import com.gogolook.wheresmoney.ui.theme.LocalTypography

private val buttonContentPadding = PaddingValues(16.dp, 11.dp, 16.dp, 11.dp)


@Preview
@Composable
fun FloatingActionButton(
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Outlined.Add,
    iconSize: Dp = 28.dp,
    size: Dp = 52.dp,
    onClick: () -> Unit = {},
) {
    FloatingActionButton(
        modifier = modifier
            .width(size)
            .height(size),
        onClick = onClick,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp,
        ),
        shape = CircleShape,
        containerColor = LocalColors.current.primary,
        contentColor = LocalColors.current.onSurfaceHighlightPrimary,
    ) {
        Icon(
            modifier = Modifier.size(iconSize),
            imageVector = icon,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
fun PrimaryStandardButton(
    modifier: Modifier = Modifier,
    text: String = "OK",
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    StandardButton(
        modifier = modifier,
        text = text,
        colors = ButtonDefaults.buttonColors(
            containerColor = LocalColors.current.primary,
            disabledContainerColor = LocalColors.current.surfaceSecondary,
            contentColor = LocalColors.current.onSurfaceHighlightPrimary,
            disabledContentColor = LocalColors.current.onSurfaceQuaternary,
        ),
        enabled = enabled,
        onClick = onClick
    )
}

@Preview
@Composable
fun SecondaryStandardButton(
    modifier: Modifier = Modifier,
    text: String = "CANCEL",
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    StandardButton(
        modifier = modifier,
        text = text,
        colors = ButtonDefaults.buttonColors(
            containerColor = LocalColors.current.surfaceSecondary,
            disabledContainerColor = LocalColors.current.surfaceSecondary,
            contentColor = LocalColors.current.onSurfaceQuaternary,
            disabledContentColor = LocalColors.current.onSurfaceQuaternary,
        ),
        enabled = enabled,
        onClick = onClick
    )
}

@Composable
fun StandardButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    colors: ButtonColors,
    onClick: () -> Unit = {}
) {
    TextButton(
        modifier = modifier,
        contentPadding = buttonContentPadding,
        shape = LocalShapes.current.button,
        enabled = enabled,
        onClick = onClick,
        colors = colors,
    ) {
        Text(
            text = text,
            style = LocalTypography.current.m4,
        )
    }
}

@Preview
@Composable
fun PrimaryOutlineButton(
    modifier: Modifier = Modifier,
    text: String = "OK",
    onClick: () -> Unit = {}
) {
    OutlineButton(
        modifier = modifier,
        text = text,
        borderColor = LocalColors.current.primary,
        textColor = LocalColors.current.primary,
        onClick = onClick
    )
}

@Preview
@Composable
fun SecondaryOutlineButton(
    modifier: Modifier = Modifier,
    text: String = "CANCEL",
    onClick: () -> Unit = {}
) {
    OutlineButton(
        modifier = modifier,
        text = text,
        borderColor = LocalColors.current.onSurfaceQuaternary,
        textColor = LocalColors.current.onSurfaceQuaternary,
        onClick = onClick
    )
}

@Composable
fun OutlineButton(
    modifier: Modifier = Modifier,
    text: String,
    borderColor: Color = LocalColors.current.primary,
    textColor: Color = LocalColors.current.primary,
    onClick: () -> Unit = {}
) {
    OutlinedButton(
        modifier = modifier,
        contentPadding = buttonContentPadding,
        shape = LocalShapes.current.button,
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = textColor,
            containerColor = Color.Transparent
        ),
        border = BorderStroke(2.dp, borderColor),
    ) {
        Text(
            text = text,
            style = LocalTypography.current.m4,
            color = textColor
        )
    }
}