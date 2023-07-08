package kz.arctan.educheck.common.components

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kz.arctan.educheck.common.theme.EduCheckTheme

@Composable
fun CustomButton(
    textStyle: TextStyle,
    onClick: () -> Unit,
    text: String,
    width: Dp,
    enabled: Boolean = true,
) {
    Button(
        modifier = Modifier.size(width, 50.dp),
        onClick = {
            println("Everything is ok!")
            Log.d("CUSTOM_BUTTON", "Button was clicked")
            onClick()
        },
        shape = RoundedCornerShape(size = 8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A0C7F)),
        enabled = enabled
    ) {
        Text(
            text = text,
            style = textStyle
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AuthButtonPreview() {
    EduCheckTheme {
        CustomButton(
            MaterialTheme.typography.titleLarge,
            onClick = {},
            text = "Register",
            width = 340.dp
        )
    }
}