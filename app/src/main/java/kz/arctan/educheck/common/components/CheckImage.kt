package kz.arctan.educheck.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.arctan.educheck.R

@Composable
fun CheckImage() {
    Image(
        painter = painterResource(id = R.drawable.check),
        contentDescription = "Check",
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(40.dp, 32.dp)
    )
}

@Preview
@Composable
fun CheckImagePreview() {
    CheckImage()
}
