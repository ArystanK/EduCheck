package kz.arctan.educheck.program.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.arctan.educheck.R
import kz.arctan.educheck.common.theme.EduCheckTheme

@Composable
fun TitleWithContentCard(
    title: String,
    content: String,
    isLoginRequired: Boolean = false,
    image: Painter,
    onClick: () -> Unit,
    width: Dp,
    height: Dp,
    textAlign: TextAlign,
    contentAlignment: Alignment.Horizontal,
) {
    Box(
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(8.dp))
            .clickable { onClick() },
    ) {
        Box(modifier = Modifier.size(width, height)) {
            Box(modifier = Modifier.align(Alignment.BottomEnd)) {
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = contentAlignment,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 20.sp,
                    lineHeight = 20.sp,
                    textAlign = textAlign
                )
                Text(
                    text = content,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 20.sp,
                    textAlign = textAlign
                )
            }
            if (isLoginRequired) Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.login_required),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Preview
@Composable
fun ProgramOptionPreview() {
    EduCheckTheme {
        FromUsProgramOption {}
    }
}

@Composable
fun WithUsProgramOption(onClick: () -> Unit) {
    TitleWithContentCard(
        title = stringResource(id = R.string.learn_with_us),
        content = stringResource(id = R.string.here_you_can_use_additional_tools),
        image = painterResource(id = R.drawable.with_us_background),
        onClick = onClick,
        width = 200.dp,
        height = 200.dp,
        textAlign = TextAlign.Center,
        contentAlignment = Alignment.CenterHorizontally
    )
}

@Composable
fun FromUsProgramOption(onClick: () -> Unit) {
    TitleWithContentCard(
        title = stringResource(id = R.string.learn_from_us),
        content = stringResource(id = R.string.here_you_can_take_tests_and_more),
        image = painterResource(id = R.drawable.from_us_background),
        isLoginRequired = false,
        onClick = onClick,
        width = 200.dp,
        height = 200.dp,
        textAlign = TextAlign.Center,
        contentAlignment = Alignment.CenterHorizontally
    )
}