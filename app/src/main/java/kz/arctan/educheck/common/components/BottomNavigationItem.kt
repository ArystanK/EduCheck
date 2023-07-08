package kz.arctan.educheck.common.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CustomBottomNavigationItem(
    icon: ImageVector,
    type: String,
) {
    Icon(
        imageVector = icon,
        contentDescription = type,
        tint = Color(0xFF4A0C7F),
        modifier = Modifier.size(32.dp)
    )
}

