package com.gogolook.wheresmoney.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.gogolook.wheresmoney.ui.theme.LocalColors
import com.gogolook.wheresmoney.ui.theme.LocalTypography

data class ToolbarAction(
    val image: ImageVector,
    val onClick: () -> Unit,
)

@Composable
fun MenuIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    color: Color = LocalColors.current.onSurfacePrimary,
    onClick: (() -> Unit)? = null,
) {
    Icon(
        imageVector = icon,
        contentDescription = "",
        tint = color,
        modifier = modifier
            .size(28.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(bounded = false)
            ) { onClick?.invoke() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    title: String,
    headerAction: ToolbarAction? = null,
    tailActions: List<ToolbarAction> = emptyList(),
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = LocalTypography.current.m3,
                color = LocalColors.current.onSurfacePrimary,
            )
        },
        navigationIcon = {
            Row {
                Spacer(modifier = Modifier.width(12.dp))
                headerAction?.let { action ->
                    MenuIcon(
                        icon = action.image,
                        onClick = action.onClick
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
            }
        },
        actions = {
            Spacer(modifier = Modifier.width(16.dp))
            tailActions.forEach { action ->
                 MenuIcon(
                    icon = action.image,
                    onClick = action.onClick
                 )
            }
            Spacer(modifier = Modifier.width(12.dp))
        }
    )
}