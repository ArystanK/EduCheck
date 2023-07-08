package kz.arctan.educheck.auth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kz.arctan.educheck.R
import kz.arctan.educheck.Screens
import kz.arctan.educheck.common.components.CustomButton
import kz.arctan.educheck.common.components.Title
import kz.arctan.educheck.common.theme.EduCheckTheme
import kz.arctan.educheck.common.theme.PurpleBackground

@Composable
fun WelcomePage(onRegisterButtonClick: () -> Unit, onLoginAsGuest: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(space = 120.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(PurpleBackground)
            .padding(top = 300.dp)
    ) {
        Title()
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CustomButton(
                onClick = onRegisterButtonClick,
                textStyle = MaterialTheme.typography.titleLarge,
                text = stringResource(id = R.string.register),
                width = 340.dp
            )
            Text(
                text = stringResource(id = R.string.login_as_guest),
                style = MaterialTheme.typography.labelSmall,
                fontSize = 20.sp,
                modifier = Modifier.clickable { onLoginAsGuest() }
            )
        }
    }
}

@Composable
fun WelcomeScreen(navController: NavController) {
    WelcomePage(
        onRegisterButtonClick = { },
        onLoginAsGuest = { navController.navigate(Screens.MAIN.name) }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomePagePreview() {
    EduCheckTheme {
        WelcomePage(onRegisterButtonClick = {}) {}
    }
}