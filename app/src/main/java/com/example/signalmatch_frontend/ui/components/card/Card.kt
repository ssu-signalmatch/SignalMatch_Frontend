package com.example.signalmatch_frontend.ui.components.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.signalmatch_frontend.R
import com.example.signalmatch_frontend.ui.components.image.ProfileImageSelector


@Composable
fun MypageCard(
    navController: NavController,
    text: String,
    route: String
){
    Card(
        modifier = Modifier
            .width(349.dp)
            .height(58.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFAFAFA)
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text,
                    color = Color(0xFF848484),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(start = 40.dp)
                        .clickable {
                            navController.navigate(route)
                        }
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(R.drawable.ic_right),
                    contentDescription = "오른쪽 화살표",
                    modifier = Modifier
                        .size(20.dp)
                        .offset(x = (-20).dp)
                )
            }
        }
    }
}


@Composable
fun MypageCard2(
    navController : NavController,
    text: String,
    text2: String,
    route: String,
    route2: String
) {
    Card(
        modifier = Modifier
            .width(349.dp)
            .height(93.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFAFAFA)
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text,
                    color = Color(0xFF848484),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(start = 40.dp)
                        .clickable {
                            navController.navigate(route)
                        }
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(R.drawable.ic_right),
                    contentDescription = "오른쪽 화살표",
                    modifier = Modifier
                        .size(20.dp)
                        .offset(x = (-20).dp)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text2,
                    color = Color(0xFF848484),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(start = 40.dp)
                        .clickable {
                            navController.navigate(route2)
                        }
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(R.drawable.ic_right),
                    contentDescription = "오른쪽 화살표",
                    modifier = Modifier
                        .size(20.dp)
                        .offset(x = (-20).dp)
                )
            }
        }
    }
}

@Composable
fun MypageCard3(
    text: String,
    onClick: () -> Unit
){
    Card(
        modifier = Modifier
            .width(349.dp)
            .height(58.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFAFAFA)
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text,
                    color = Color(0xFF848484),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(start = 40.dp)
                        .clickable { onClick() }
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(R.drawable.ic_right),
                    contentDescription = "오른쪽 화살표",
                    modifier = Modifier
                        .size(20.dp)
                        .offset(x = (-20).dp)
                )
            }
        }
    }
}


@Composable
fun StartupProfileCard(
    navController: NavController,
    model: String?,
    name: String,
    status: String,
    userId: Int
) {
    Card(
        modifier = Modifier
            .width(349.dp)
            .height(146.dp)
            .clickable {
                navController.navigate("startup-profile detail/$userId")
            },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFAFAFA)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    if (!model.isNullOrBlank()) {
                        AsyncImage(
                            model = model,
                            contentDescription = "프로필 이미지",
                            modifier = Modifier.matchParentSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Image(
                            painter = painterResource(R.drawable.ic_profile),
                            contentDescription = "기본 프로필 이미지",
                            modifier = Modifier.matchParentSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Spacer(modifier = Modifier.width(30.dp))
                Column() {
                    Text(
                        text = name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = status,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF848484)
                    )
                }
            }
        }
    }
}



@Composable
fun InvestorProfileCard(
    navController: NavController,
    model: String?,
    name: String,
    role: String,
    userId: Int

) {
    Card(
        modifier = Modifier
            .width(349.dp)
            .height(146.dp)
            .clickable {
                navController.navigate("investor-profile detail/$userId")
            },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFAFAFA)
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    if (!model.isNullOrBlank()) {
                        AsyncImage(
                            model = model,
                            contentDescription = "프로필 이미지",
                            modifier = Modifier.matchParentSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Image(
                            painter = painterResource(R.drawable.ic_profile),
                            contentDescription = "기본 프로필 이미지",
                            modifier = Modifier.matchParentSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Spacer(modifier = Modifier.width(30.dp))

                Column {
                    Text(
                        text = name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = role,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF848484)
                    )
                }
            }
        }
    }
}


@Composable
fun SearchCard1(
    navController: NavController,
    @DrawableRes iconRes: Int,
    name: String,
    category: String,
    saveCount : Int
){
    Card(
        modifier = Modifier
            .width(349.dp)
            .height(99.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFAFAFA)
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ){
            Row(
                modifier = Modifier.fillMaxHeight(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(23.dp))
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(35.dp)
                )
                Spacer(modifier = Modifier.width(25.dp))
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = category,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF848484)
                    )
                }
            }
            Spacer(Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    "♥ + ${saveCount} ↑      ",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF848484)
                )
            }
        }
    }
}


@Composable
fun SearchCard2(
    navController: NavController,
    rank: String,
    name: String,
    category: String,
    saveCount : Int
){
    Card(
        modifier = Modifier
            .width(349.dp)
            .height(99.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFAFAFA)
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ){
            Row(
                modifier = Modifier.fillMaxHeight(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(33.dp))
                Text(
                    text = rank,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF848484)
                )
                Spacer(modifier = Modifier.width(36.dp))
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = category,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF848484)
                    )
                }
            }
            Spacer(Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    "♥ + ${saveCount} ↑      ",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF848484)
                )
            }
        }
    }
}

@Composable
fun SearchCard3(
    navController: NavController,
    name: String,
    category: String,
    saveCount : Int
){
    Card(
        modifier = Modifier
            .width(349.dp)
            .height(99.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFAFAFA)
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ){
            Row(
                modifier = Modifier.fillMaxHeight(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(72.dp))
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = category,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF848484)
                    )
                }
            }
            Spacer(Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    "♥ + ${saveCount} ↑      ",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF848484)
                )
            }
        }
    }
}

