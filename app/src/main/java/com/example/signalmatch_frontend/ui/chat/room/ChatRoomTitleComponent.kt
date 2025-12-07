package com.example.signalmatch_frontend.ui.chat.room

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.signalmatch_frontend.R

@Composable
fun ChatRoomTitle (
    navController: NavController,
    modifier: Modifier = Modifier,
    title: String,
    count: Long
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RectangleShape,
                shadow = Shadow(
                    radius = 16.dp,
                    spread = 4.dp,
                    color = Color(0x1c000000),
                    offset = DpOffset(x = 0.dp, y = 4.dp)
                )
            )
            .background(color = Color(0xFFAEF1EB))
    ) {
        FlowRow (
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 18.dp,
                    bottom = 18.dp,
                    start = 18.dp,
                    end = 18.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Row (
                modifier = Modifier.fillMaxWidth(0.24f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton (
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .height(28.dp)
                        .width(28.dp)
                        .padding(0.dp)
                ) {
                    Image (
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = "back",
                        modifier = Modifier
                            .height(28.dp)
                            .width(28.dp)
                    )
                }

                Spacer(Modifier.width(4.dp))

                Text (
                    text = getChatCountString(ref = count),
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Text (
                modifier = Modifier.weight(1f),
                text = title,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )

            Spacer (modifier = Modifier.fillMaxWidth(0.24f))
        }
    }
}

fun getChatCountString (ref: Long): String {
    var adjustedBadgeStr: String = ref.toString();
    if (adjustedBadgeStr.isNotEmpty() && adjustedBadgeStr == "0") adjustedBadgeStr = "";
    else if (adjustedBadgeStr.length > 2) adjustedBadgeStr = "99+";

    return adjustedBadgeStr
}