package com.example.signalmatch_frontend.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun HomeNewsTabContainer (
    data: ArrayList<HomeNewItem>?
) {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        data?.take(5)?.map { item ->
            HomeNewsTabItem (
                id = item.id,
                name = item.name,
                bio1 = item.bio1,
                bio2 = item.bio2,
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun HomeNewsTabItem (
    id: Int,
    name: String,
    bio1: String?,
    bio2: String?,
    profileImage: String? = null
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
    ) {
        Box (
            modifier = Modifier
                .size(72.dp, 72.dp)
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            profileImage?.let {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = profileImage
                    ),
                    contentDescription = "profile image",
                    contentScale = ContentScale.FillBounds
                )
            }
        }

        Spacer(modifier = Modifier
            .fillMaxHeight()
            .width(8.dp))

        Column (
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {

            Row {
                Text (
                    text = name,
                    fontSize = 16.sp,
                    lineHeight = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF333333),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text (
                text = bio1?: " ",
                fontSize = 14.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF555555),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text (
                text = bio2 ?: "",
                fontSize = 10.sp,
                lineHeight = 10.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF555555),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }
    }

}

data class HomeNewItem  (
    var id: Int,
    var name: String,
    var bio1: String?,
    var bio2: String?,
    var profileImage: String? = null
) {

}