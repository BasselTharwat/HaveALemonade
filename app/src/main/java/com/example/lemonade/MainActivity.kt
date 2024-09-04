package com.example.lemonade

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.Black
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                var mainClick by rememberSaveable { mutableStateOf(0) }
                var randomNumber by rememberSaveable { mutableStateOf( (2..4).random() ) }
                LemonadeApp(mainClick = mainClick, onClick = { mainClick++ },
                    onReset = { mainClick = 0
                            randomNumber = (2..4).random()},
                    onRandomClick = { randomNumber--
                            if(randomNumber == 0)
                                mainClick++
                    })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp(mainClick: Int, onClick: () -> Unit,
                onReset: () -> Unit,
                onRandomClick: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xfff9e44c),
                    titleContentColor = Color(0xff0b0a11),
                ),
                title = {
                    Text(
                        "Lemonade",
                        fontWeight = FontWeight.Medium
                    )
                }
            )
        },
        content = { padding ->
            when (mainClick) {
                0 -> ImageAndText(
                    imageId = R.drawable.lemon_tree,
                    textId = R.string.tap_lemon,
                    modifier = Modifier.padding(padding),
                    onClickClicked = onClick
                )
                1 -> ImageAndText(
                    imageId = R.drawable.lemon_squeeze,
                    textId = R.string.keep_tapping,
                    modifier = Modifier.padding(padding),
                    onClickClicked = onRandomClick
                )
                2 -> ImageAndText(
                    imageId = R.drawable.lemon_drink,
                    textId = R.string.drink_lemon,
                    modifier = Modifier.padding(padding),
                    onClickClicked = onClick
                )
                3 -> ImageAndText(
                    imageId = R.drawable.lemon_restart,
                    textId = R.string.tap_empty,
                    modifier = Modifier.padding(padding),
                    onClickClicked = onReset
                )
            }
        }
    )
}

@Composable
fun ImageAndText(imageId: Int, textId: Int, modifier: Modifier = Modifier,
                     onClickClicked: () -> Unit) {
    val image = painterResource(id = imageId)
    Column(
        modifier = Modifier
            .background(Color(0xfffffbff))
            .fillMaxSize()
            .clickable { onClickClicked() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image, contentDescription = null,
            modifier = Modifier
                .background(Color(0xffc3ecd2), RoundedCornerShape(30.dp))
                .padding(top = 10.dp, bottom = 10.dp, start = 15.dp, end = 15.dp)
        )
        Spacer(modifier = Modifier.size(25.dp))
        Text(text = stringResource(id = textId),
            fontSize = 18.sp,
            color = Black)
    }
}

@Preview(showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        var mainClick by rememberSaveable { mutableStateOf(0) }
        var randomNumber by rememberSaveable { mutableStateOf( (2..4).random() ) }
        LemonadeApp(
            mainClick = mainClick,
            onClick = { mainClick++ },
            onReset = { mainClick = 0
                      randomNumber = (2..4).random()},
            onRandomClick = { randomNumber--
                if(randomNumber == 0)
                    mainClick++
            })
    }
}
