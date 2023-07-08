package kz.arctan.educheck.program.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import kz.arctan.educheck.common.theme.PurpleSecondary

@Composable
fun WithUsProgramPage(
    onBackButtonClick: () -> Unit,
    onAskQuestionClick: () -> Unit,
    onCreateConspectusClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PurpleBackground),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(
                    PurpleSecondary,
                    RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp)
                )
        ) {
            IconButton(
                onClick = onBackButtonClick,
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Image(
                painter = painterResource(id = R.drawable.with_us_page_background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(),
                alignment = Alignment.Center
            )
        }
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = stringResource(id = R.string.learn_with_us),
                style = MaterialTheme.typography.titleLarge,
                fontSize = 36.sp,
                color = Color.White
            )
            TitleWithContentCard(
                title = "Задал вопрос - получил ответ!",
                content = "Задавай любые интересующие вопросы и получишь пояснение",
                image = painterResource(id = R.drawable.ask_question_card_background),
                onClick = onAskQuestionClick,
                width = 270.dp,
                height = 180.dp,
                textAlign = TextAlign.Left,
                contentAlignment = Alignment.Start

            )
            TitleWithContentCard(
                title = "Файл в конспект",
                content = "Загрузи любой файл(аудио, видео, PDF) и получи конспект",
                image = painterResource(id = R.drawable.create_conspectus_card_background),
                onClick = onCreateConspectusClick,
                width = 270.dp,
                height = 180.dp,
                textAlign = TextAlign.Left,
                contentAlignment = Alignment.Start
            )
        }
    }
}

@Composable
fun WithUsProgramScreen(navController: NavController) {
    WithUsProgramPage(
        onBackButtonClick = { navController.popBackStack() },
        onAskQuestionClick = { navController.navigate(Screens.QUESTION_ANSWER.name) },
        onCreateConspectusClick = { navController.navigate(Screens.INSTALL_AUDIO.name) }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun WithUsPagePreview() {
    EduCheckTheme {
        WithUsProgramPage(
            onBackButtonClick = { /*TODO*/ },
            onAskQuestionClick = { /*TODO*/ },
            onCreateConspectusClick = {}
        )
    }
}