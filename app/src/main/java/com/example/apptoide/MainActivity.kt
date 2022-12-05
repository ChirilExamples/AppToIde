package com.example.apptoide

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apptoide.domain.ViewModelATI
import com.example.apptoide.ui.theme.AppToIdeTheme
import com.example.apptoide.ui.theme.white
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppToIdeTheme {
                // A surface container using the 'background' color from the theme
                ToDisplayBottom()

            }
        }
    }
}


@Composable
fun ToDisplayBottom(viewModel: ViewModelATI = viewModel(LocalContext.current as ComponentActivity)) {
    val itemList by viewModel.itemsList.collectAsState()

    itemList.data?.let {
//        BottomScroll(itemsList = it)
        Log.i("DebugNetwork", itemList.toString())
    }
}

@Composable
fun BottomScroll(itemsList: List<com.example.apptoide.data.structure.List>) {

    LazyRow {
        items(
            items = itemsList
        ) {
            BottomCardItem(image = it.icon, name = it.name, rating = it.rating)
        }
    }


}

@Composable
@Preview(showBackground = true)
fun TopCardItem() {
    Card(
        modifier = Modifier.size(width = 300.dp, height = 200.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {  // TODO:
            Image(
                painter = painterResource(id = R.drawable.test_square_img),
                contentDescription = "Image Rect",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent, Color.Black
                            ), startY = 400f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column {
                    Text(
                        text = "Name Name",
                        style = TextStyle(color = Color.White, fontSize = 16.sp, FontWeight.Bold),
                        maxLines = 1,
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Star",
                            Modifier.size(10.dp),
                            tint = white
                        )
                        Text(
                            text = "Rating",
                            style = TextStyle(color = Color.White, fontSize = 10.sp)
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun BottomCardItem(image: String, name: String, rating: Double) {
    Card(
        modifier = Modifier.size(width = 60.dp, height = 100.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(16.dp)
    ) {
        Column {
            Card(shape = RoundedCornerShape(6.dp), modifier = Modifier.padding(3.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.test_square_img),
                    contentDescription = image,
                    modifier = Modifier.padding(),
                )
            }
            Text(
                text = name,
                maxLines = 2,
                fontSize = 6.sp,
                modifier = Modifier.padding(bottom = 3.dp, start = 4.dp, end = 4.dp)
            )
            Row(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Star",
                    Modifier.size(6.dp)
                )
                Text(
                    text = "$rating",
                    maxLines = 2,
                    fontSize = 6.sp,
                    modifier = Modifier
                        .padding(start = 2.dp, end = 4.dp)
                        .wrapContentHeight(),
                    textAlign = TextAlign.Center,
                )

            }
        }
    }
}


@Composable
//@Preview
fun TestingStuff() {
    Column {
        Box(contentAlignment = Alignment.TopEnd) {
            GlideImage(
                imageModel = R.drawable.test_square_img, modifier = Modifier.height(height = 210.dp)
            )
        }
    }
}