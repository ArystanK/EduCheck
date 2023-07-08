package kz.arctan.educheck.auth.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kz.arctan.educheck.R

@Composable
fun ProfilePage() {
    Image(painterResource(id = R.drawable.profile), "", modifier = Modifier.fillMaxSize())
}