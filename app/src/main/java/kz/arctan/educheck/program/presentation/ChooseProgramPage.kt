package kz.arctan.educheck.program.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kz.arctan.educheck.R
import kz.arctan.educheck.Screens
import kz.arctan.educheck.common.theme.EduCheckTheme
import kz.arctan.educheck.common.theme.PurpleBackground

@Composable
fun ChooseProgramPage(
    withUsProgramOptionClick: () -> Unit,
    fromUsProgramOptionClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(PurpleBackground),
    ) {
        Text(
            text = stringResource(id = R.string.choose_program),
            style = MaterialTheme.typography.titleLarge,
            fontSize = 36.sp,
            modifier = Modifier.padding(20.dp),
            color = Color.White,
            textAlign = TextAlign.Center
        )
        WithUsProgramOption(onClick = withUsProgramOptionClick)
        Spacer(modifier = Modifier.size(20.dp))
        FromUsProgramOption(onClick = fromUsProgramOptionClick)
    }
}

@Composable
fun ChooseProgramScreen(navController: NavController) {
    ChooseProgramPage(
        withUsProgramOptionClick = { navController.navigate(Screens.WITH_US.name) },
        fromUsProgramOptionClick = { navController.navigate(Screens.FROM_US.name) }
    )
}

@Preview(showSystemUi = true, showBackground = true, locale = "ru-rKZ")
@Composable
fun ChooseProgramPagePreview() {
    EduCheckTheme() {
        ChooseProgramPage(withUsProgramOptionClick = { /*TODO*/ }) {

        }
    }
}