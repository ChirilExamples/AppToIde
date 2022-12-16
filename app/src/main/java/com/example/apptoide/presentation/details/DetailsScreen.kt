package com.example.apptoide.presentation.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apptoide.data.structure.AppInf
import com.example.apptoide.presentation.common.AppsTopAppBar
import com.example.apptoide.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.skydoves.landscapist.glide.GlideImage

@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    name: String = "asd",
    downloads: Int,
    size: Int,
    rating: Double,
    version: String,
    icon: String
) {

    Scaffold(
        topBar = {
            AppsTopAppBar()

        },
        content = {
            DetailsItem(
                padding = it,
                name = name,
                downloads = downloads,
                size = size,
                rating = rating,
                version = version,
                icon = icon
            ) //list = dummyList for test
        },
    )
}

@Destination
@Composable
fun DetailsItem(
    icon: String? = "https://pool.img.aptoide.com/appupdater/aa7a77f7744a621751f8c09c6b3437d1_fgraphic.png",
    name: String? = "Name",
    version: String? = "0.69.32036",
    downloads: Int? = 65,
    size: Int? = 144,
    rating: Double? = 0.1001,
    padding: PaddingValues,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding)
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        ItemImageCard(name = "$name", image = "$icon")

        TextDetails(
            name = "$name", downloads = "$downloads", size = "$size", rating = "$rating"
        )

        InstallButton()

        VersionDetails(version = "$version")
    }
}

@Destination
@Composable
fun ItemImageCard(name: String, image: String) {
    val readCardLabel = stringResource(id = com.example.apptoide.R.string.action_read_card)

    Row {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(200.dp)
                .semantics { onClick(label = readCardLabel + name, action = null) }
                .padding(top = 10.dp)
                .shadow(12.dp, shape = RoundedCornerShape(5.dp)),
            shape = RoundedCornerShape(5.dp),
            elevation = CardDefaults.cardElevation(16.dp),
            border = BorderStroke(width = 1.dp, color = borderGray),
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

//                Image(
//                    painter = painterResource(id = com.example.apptoide.R.drawable.test_square_img),
//                    contentDescription = "image",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .size(150.dp),
//                    contentScale = ContentScale.Fit,
//                )
                GlideImage(
                    imageModel = image,
                    contentDescription = "image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(150.dp)
                        .padding(horizontal = 8.dp),
                    contentScale = ContentScale.Crop
                )


            }
        }
    }
}

@Destination
@Composable
fun TextDetails(name: String, downloads: String, size: String, rating: String) {
    Spacer(modifier = Modifier.height(10.dp))

    Text(
        text = name, style = TextStyle(color = Color.Black, fontSize = 24.sp, FontWeight.Bold)
    )
    Spacer(modifier = Modifier.height(6.dp))
    Row(
        modifier = Modifier.fillMaxWidth(0.7f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Downloads",
            modifier = Modifier.size(15.dp),
            alignment = Alignment.BottomStart
        )
        Text(text = downloads, fontSize = 15.sp, color = paleBlack)
        Text(text = "k", color = paleBlack)
        Spacer(modifier = Modifier.width(10.dp))
        Image(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = "Size",
            modifier = Modifier.size(15.dp),
            alignment = Alignment.BottomStart
        )
        Text(text = size, fontSize = 15.sp, color = paleBlack)
        Text(text = "MB", color = paleBlack)
        Spacer(modifier = Modifier.width(10.dp))
        Image(
            imageVector = Icons.Default.Star,
            contentDescription = "Rating",
            modifier = Modifier.size(15.dp),
            alignment = Alignment.BottomCenter
        )
        Text(text = rating, fontSize = 15.sp, color = paleBlack)
    }
}

@Destination
@Composable
fun InstallButton() {
    Spacer(modifier = Modifier.height(10.dp))
    Button(
        onClick = {},
        Modifier
            .fillMaxWidth(0.7f)
            .height(60.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(orangeLeft, orangeRight)
                ) //  not working, works on text though
            ), shape = RoundedCornerShape(5.dp), colors = ButtonDefaults.buttonColors(orange)
    ) {

        Box {
            Text(
                text = "Install",
                style = TextStyle(color = Color.White, fontSize = 24.sp, FontWeight.Bold),
                modifier = Modifier.background(Color.Transparent)
            )

        }


    }
}

@Destination
@Composable
fun VersionDetails(version: String) {
    Spacer(modifier = Modifier.height(10.dp))
    Divider(color = Color.Black, thickness = 1.dp)
    Row {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,

                ) {
                Text(text = "Latest version", textAlign = TextAlign.Center)
                Text(text = version, color = paleBlack, textAlign = TextAlign.Center)
            }
        }

        Divider(
            color = Color.Black, modifier = Modifier
                .fillMaxHeight(0.13f)
                .width(1.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight(0.13f)
                .fillMaxWidth()
        ) {

            Text(text = "Other versions", textAlign = TextAlign.Center)

        }
    }
}

@Destination
@Composable
fun dummyList(): AppInf {
    return AppInf(
        1,
        "https://pool.img.aptoide.com/appupdater/aa7a77f7744a621751f8c09c6b3437d1_fgraphic.png",
        "https://pool.img.aptoide.com/appupdater/aa7a77f7744a621751f8c09c6b3437d1_fgraphic.png",
        "nama",
        0.3,
        100,
        620
    )
}