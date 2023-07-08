package kz.arctan.educheck.essay.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kz.arctan.educheck.common.components.CustomButton
import kz.arctan.educheck.common.theme.EduCheckTheme
import kz.arctan.educheck.common.theme.PurpleBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EssayCheckPage(
    essay: String,
    onEssayChange: (String) -> Unit,
    onTakePictureClick: () -> Unit,
    onCheckEssay: () -> Unit,
    result: String,
    feedback: String,
    isLoading: Boolean,
) {
    val scrollableState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollableState)
            .background(PurpleBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
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
                text = "Проверь своё эссе",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 40.sp,
                color = Color.White,
                lineHeight = 40.sp
            )
        }
        val essayScrollState = rememberScrollState()
        TextField(
            value = essay,
            onValueChange = onEssayChange,
            modifier = Modifier
                .size(340.dp, 230.dp)
                .verticalScroll(essayScrollState),
            placeholder = {
                Text(
                    text = "Введи текст",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                    )
                )
            },
            textStyle = MaterialTheme.typography.labelSmall.copy(
                fontSize = 16.sp,
                lineHeight = 16.sp
            ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp)
        )
        IconButton(
            onClick = onTakePictureClick,
            modifier = Modifier
                .size(70.dp)
                .background(Color(0xDE3D006C), CircleShape),
        ) {
            Icon(
                imageVector = Icons.Outlined.PhotoCamera,
                contentDescription = "Take a picture",
                modifier = Modifier.size(48.dp),
                tint = Color.White
            )

        }
        CustomButton(
            textStyle = MaterialTheme.typography.labelSmall.copy(fontSize = 24.sp),
            onClick = onCheckEssay,
            text = "Проверить",
            width = 200.dp
        )
        Text(
            text = "Результат",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            fontSize = 40.sp
        )
        Text(
            text = "$result%",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            fontSize = 36.sp
        )
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
        ) {
            if (isLoading) CircularProgressIndicator()
            Text(
                text = "Фидбэк",
                color = Color.White,
                fontSize = 24.sp,
                style = MaterialTheme.typography.titleLarge
            )
        }
        SelectionContainer(
            modifier = Modifier
                .size(340.dp, 230.dp)
                .padding(bottom = 25.dp)
                .background(Color.White, RoundedCornerShape(6.dp))
        ) {
            val scrollableFeedbackState = rememberScrollState()
            Text(
                text = feedback,
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 16.sp),
                modifier = Modifier
                    .padding(12.dp)
                    .verticalScroll(state = scrollableFeedbackState)
            )
        }
    }
}

@Composable
fun EssayCheckScreen(navController: NavController, viewModel: EssayCheckViewModel) {
    val state by viewModel.state.collectAsState()
    EssayCheckPage(
        essay = state.essay,
        onEssayChange = { viewModel.reduce(EssayCheckIntent.EssayChangeIntent(it)) },
        onTakePictureClick = { viewModel.reduce(EssayCheckIntent.TakePictureIntent) },
        onCheckEssay = { viewModel.reduce(EssayCheckIntent.CheckIntent) },
        result = state.result,
        feedback = state.feedback,
        isLoading = state.isLoading
    )
}

@Preview
@Composable
fun EssayCheckPagePreview() {
    EduCheckTheme() {
        EssayCheckPage(
            essay = "Ключевые моменты статьи:\n" +
                    "Фотосинтез - процесс, при котором растения используют солнечный свет, воду и углекислый газ для производства пищи.\n" +
                    "Солнечный свет поглощается листьями растения.\n\n\n\n" +
                    "\n" +
                    "Конспект:\n" +
                    "I. Фотосинтез: A. Процесс, при котором растения производят пищу. B. Необходимые компоненты: 1. Солнечный свет. 2. Вода. 3. Углекислый газ.\n" +
                    "II. Поставщики компонентов фотосинтеза: A. Солнечный свет: - Поглощается листьями растения. B. Вода: - Впитывается корнями растения. - Движение воды по стеблю осуществляется ксилемой.\n" +
                    "Конспект:\n" +
                    "I. Фотосинтез: A. Процесс, при котором растения производят пищу. B. Необходимые компоненты: 1. Солнечный свет. 2. Вода. 3. Углекислый газ.\n" +
                    "II. Поставщики компонентов фотосинтеза: A. Солнечный свет: - Поглощается листьями растения. B. Вода: - Впитывается корнями растения. - Движение воды по стеблю осуществляется ксилемой.",
            onCheckEssay = {},
            onEssayChange = {},
            onTakePictureClick = {},
            result = "70",
            feedback = "Ключевые моменты статьи:\n" +
                    "Фотосинтез - процесс, при котором растения используют солнечный свет, воду и углекислый газ для производства пищи.\n" +
                    "Солнечный свет поглощается листьями растения.\n\n\n\n" +
                    "\n" +
                    "Конспект:\n" +
                    "I. Фотосинтез: A. Процесс, при котором растения производят пищу. B. Необходимые компоненты: 1. Солнечный свет. 2. Вода. 3. Углекислый газ.\n" +
                    "II. Поставщики компонентов фотосинтеза: A. Солнечный свет: - Поглощается листьями растения. B. Вода: - Впитывается корнями растения. - Движение воды по стеблю осуществляется ксилемой.\n" +
                    "Конспект:\n" +
                    "I. Фотосинтез: A. Процесс, при котором растения производят пищу. B. Необходимые компоненты: 1. Солнечный свет. 2. Вода. 3. Углекислый газ.\n" +
                    "II. Поставщики компонентов фотосинтеза: A. Солнечный свет: - Поглощается листьями растения. B. Вода: - Впитывается корнями растения. - Движение воды по стеблю осуществляется ксилемой.",
            isLoading = false
        )
    }
}