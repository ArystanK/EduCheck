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
fun FromUsProgramPage(
    onBackButtonClick: () -> Unit,
    onGetConspectusClick: () -> Unit,
    onCheckEssayClick: () -> Unit,
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
                painter = painterResource(id = R.drawable.from_us_page_background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp, start = 50.dp),
                alignment = Alignment.Center
            )
        }
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = stringResource(id = R.string.learn_from_us),
                style = MaterialTheme.typography.titleLarge,
                fontSize = 36.sp,
                color = Color.White
            )
            TitleWithContentCard(
                title = "Получи конспект по статье, тексту",
                content = "Вставляй ссылку и получи краткий обзор важных моментов",
                image = painterResource(id = R.drawable.get_conspect_card),
                onClick = onGetConspectusClick,
                width = 270.dp,
                height = 180.dp,
                textAlign = TextAlign.Left,
                contentAlignment = Alignment.Start
            )
            TitleWithContentCard(
                title = "Проверь своё эссе",
                content = "Напиши эссе, а программа проверит его на ошибки и поможет улучшить, дополнить",
                image = painterResource(id = R.drawable.check_essay_card_background),
                onClick = onCheckEssayClick,
                width = 270.dp,
                height = 180.dp,
                textAlign = TextAlign.Left,
                contentAlignment = Alignment.Start
            )
        }
    }
}

@Composable
fun FromUsProgramScreen(navController: NavController) {
    FromUsProgramPage(
        onBackButtonClick = { navController.popBackStack() },
        onGetConspectusClick = { navController.navigate(Screens.CREATE_CONSPECTUS.name) },
        onCheckEssayClick = { navController.navigate(Screens.CHECK_ESSAY.name) }
    )
}

@Preview(showSystemUi = true, showBackground = true, locale = "ru-rKZ")
@Composable
fun FromUsProgramPagePreview() {
    EduCheckTheme {
        FromUsProgramPage(
            onBackButtonClick = {},
            onGetConspectusClick = {},
            onCheckEssayClick = {}
        )
    }
}