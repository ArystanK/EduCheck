package kz.arctan.educheck.conspectus.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kz.arctan.educheck.common.components.CustomButton
import kz.arctan.educheck.common.theme.PurpleBackground
import kz.arctan.educheck.conspectus.domain.Questions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConspectusPage(
    articleLink: String,
    onArticleLinkChange: (String) -> Unit,
    generateConspectus: () -> Unit,
    conspectus: String,
    onStartTest: () -> Unit,
    generativeQuestions: Questions,
    extractiveQuestions: Questions,
    userGenerativeAnswers: List<String>,
    userExtractiveAnswers: List<String>,
    onUserGenerativeAnswerChange: (Int, String) -> Unit,
    onUserExtractiveAnswerChange: (Int, String) -> Unit,
    onCheckTest: () -> Unit,
    generativeScore: List<String>,
    extractiveScore: List<String>,
) {
    val scrollPageState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PurpleBackground)
            .verticalScroll(scrollPageState)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(color = Color(0xDE3D006C), RoundedCornerShape(bottomEnd = 114.dp))
                .padding(horizontal = 24.dp)
                .padding(top = 64.dp)
        ) {
            Text(
                text = "Выучи и пойми тему вместе с нами",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 40.sp,
                color = Color.White,
                lineHeight = 40.sp
            )
        }
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Ссылка на статью",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 24.sp
            )
            TextField(
                value = articleLink,
                onValueChange = onArticleLinkChange,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(4.dp),
            )
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                CustomButton(
                    onClick = generateConspectus,
                    textStyle = MaterialTheme.typography.labelSmall.copy(fontSize = 24.sp),
                    text = "Сгенерировать",
                    width = 340.dp
                )
            }
            Text(
                text = "Конспект",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
            val scrollableConspectusState = rememberScrollState()
            SelectionContainer(
                modifier = Modifier
                    .size(340.dp, 380.dp)
                    .background(Color.White, RoundedCornerShape(6.dp))
                    .padding(12.dp)
            ) {
                Text(
                    text = conspectus,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Justify
                    ),
                    modifier = Modifier.verticalScroll(
                        state = scrollableConspectusState,
                    )
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomButton(
                    textStyle = MaterialTheme.typography.labelSmall.copy(fontSize = 16.sp),
                    onClick = onStartTest,
                    text = "Пройти тест",
                    width = 340.dp
                )

            }
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = "Тестирование",
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp),
                    color = Color.White
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            ) {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(generativeQuestions.size) { id ->
                        Text(text = "${id + 1}. ${generativeQuestions.questions.getOrElse(id) { "" }}")
                        TextField(
                            value = userGenerativeAnswers.getOrElse(id) { "" },
                            onValueChange = { onUserGenerativeAnswerChange(id, it) })
                        Text(text = generativeScore.getOrElse(id) { "" })
                    }
                    items(extractiveQuestions.size) { id ->
                        Text(text = "${id + 1}. ${extractiveQuestions.questions.getOrElse(id) { "" }}")
                        TextField(
                            value = userExtractiveAnswers.getOrElse(id) { "" },
                            onValueChange = { onUserExtractiveAnswerChange(id, it) })
                        Text(text = extractiveScore.getOrElse(id) { "" })
                    }
                }
            }
            CustomButton(
                textStyle = MaterialTheme.typography.labelSmall,
                onClick = onCheckTest,
                text = "Проверить",
                width = 200.dp,
                enabled = (generativeQuestions + extractiveQuestions).size > 0
            )
        }
    }

}

@Composable
fun ConspectusScreen(navController: NavController, viewModel: ConspectusViewModel) {
    val state by viewModel.state.collectAsState()
    ConspectusPage(
        articleLink = state.articleLink,
        onArticleLinkChange = { viewModel.reduce(ConspectusIntent.ArticleLinkChangeIntent(it)) },
        generateConspectus = { viewModel.reduce(ConspectusIntent.GenerateConspectusIntent) },
        conspectus = state.conspectus,
        onStartTest = { viewModel.reduce(ConspectusIntent.StartTestIntent) },
        generativeQuestions = state.generativeQuestions,
        onCheckTest = { viewModel.reduce(ConspectusIntent.CheckAnswersIntent) },
        onUserGenerativeAnswerChange = { id, answer ->
            viewModel.reduce(ConspectusIntent.UserGenerativeAnswerChange(id, answer))
        },
        userGenerativeAnswers = state.userGenerativeAnswers,
        onUserExtractiveAnswerChange = { id, answer ->
            viewModel.reduce(ConspectusIntent.UserExtractiveAnswerChange(id, answer))
        },
        extractiveQuestions = state.extractiveQuestions,
        userExtractiveAnswers = state.userExtractiveAnswers,
        extractiveScore = state.extractiveTestResults,
        generativeScore = state.generativeTestResults
    )
}
