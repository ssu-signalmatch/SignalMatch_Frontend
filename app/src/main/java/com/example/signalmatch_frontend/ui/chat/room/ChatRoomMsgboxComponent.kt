package com.example.signalmatch_frontend.ui.chat.room

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.signalmatch_frontend.R
import com.example.signalmatch_frontend.viewmodel.ChatRoomViewModel


@Composable
fun ChatRoomMsgBox (
    modifier: Modifier = Modifier,
    roomId: Long,
    viewModel: ChatRoomViewModel = hiltViewModel()
) {
    var type by remember { mutableIntStateOf(ChatItemType.TEXT.code) }
    val message = TextFieldState("")

    Row (
        modifier = modifier
            .height(64.dp)
            .fillMaxWidth()
            .dropShadow(
                shape = RectangleShape,
                shadow = Shadow(
                    radius = 16.dp,
                    spread = 4.dp,
                    color = Color(0x1c000000),
                    offset = DpOffset(x = 0.dp, y = (-4).dp)
                )
            )
            .background(
                color = Color.White,
                shape = RectangleShape
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer (modifier = Modifier.width(12.dp))

        IconButton (
            onClick = {
                // TODO
            },
            modifier = Modifier
                .height(28.dp)
                .width(28.dp)
                .padding(0.dp)
        ) {
            Image (
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = "plus",
                modifier = Modifier
                    .height(28.dp)
                    .width(28.dp)
            )
        }

        Spacer (modifier = Modifier.width(8.dp))

        Box (
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(0xFFD9D9D9),
                    shape = RoundedCornerShape(22.dp)
                )
                .height(44.dp)
                .weight(1f)
        ) {
            BasicTextField (
                state = message,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 12.dp,
                        bottom = 12.dp,
                        start = 12.dp,
                        end = 12.dp
                    ),
                textStyle = TextStyle(
                    color = Color(0xFF313131),
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                ),
            )
        }

        Spacer (modifier = Modifier.width(8.dp))

        IconButton (
            onClick = {
                viewModel.sendMessage(
                    roomId = roomId,
                    message = message.text as String
                )
            },
            modifier = Modifier
                .height(28.dp)
                .width(28.dp)
                .padding(0.dp)
        ) {
            Image (
                painter = painterResource(id = R.drawable.ic_send),
                contentDescription = "plus",
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp)
            )
        }

        Spacer (modifier = Modifier.width(12.dp))

    }
}