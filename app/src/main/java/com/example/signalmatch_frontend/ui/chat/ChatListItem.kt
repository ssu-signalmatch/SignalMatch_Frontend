package com.example.signalmatch_frontend.ui.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Optional

class ChatListItem constructor (
    private var name: String,
    private var preview: String,
    private var time: String,
    private var badge: String,
    private var painter: Optional<Painter>
) {

    @Composable
    public fun get () {
        var adjustedBadgeStr: String = badge;
        if (adjustedBadgeStr.isNotEmpty() && adjustedBadgeStr == "0") adjustedBadgeStr = "";
        else if (adjustedBadgeStr.length > 2) adjustedBadgeStr = "99+";

        Button (
            onClick = {
                // TODO
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = 10.dp,
                bottom = 10.dp
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                disabledContainerColor = Color.LightGray
            )
        ) {
            Row (
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box (
                    modifier = Modifier
                        .size(60.dp, 60.dp)
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(12.dp)
                        )
                ) { // Profile Image Box
                    if (painter.isPresent) {
                        Image (
                            painter = painter.get(),
                            contentDescription = "profile image / $name",
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 6.dp,
                            bottom = 6.dp,
                            start = 0.dp,
                            end = 0.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) { // Content Row Layout

                    Column (
                        modifier = Modifier
                            .widthIn(max = 232.dp)
                            .fillMaxHeight()
                        ,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) { // Title, Preview Column Layout
                        Text (
                            text = name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text (
                            text = preview,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF848484),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Column ( // Date, Badge Column Layout
                        modifier = Modifier.fillMaxHeight(),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.spacedBy(
                            space = 8.dp,
                            alignment = Alignment.Bottom
                        )
                    ) {
                        Text (
                            modifier = Modifier
                                .height(16.dp)
                                .width(
                                    when (adjustedBadgeStr.length) {
                                        0 -> 0.dp
                                        1 -> 16.dp
                                        2 -> 20.dp
                                        else -> 24.dp
                                    }
                                )
                                .background(
                                    color = Color(0xFFAEF1EB),
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            text = adjustedBadgeStr,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            maxLines = 1,
                            lineHeight = 16.sp,
                            textAlign = TextAlign.Center
                        )

                        Text (
                            text = time,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF848484),
                            maxLines = 1,
                            overflow = TextOverflow.Visible
                        )
                    }
                }
            }
        }
    }

    public constructor (
        name: String,
        preview: String,
        time: String,
        badge: String
    ): this (
        name = name,
        preview = preview,
        time = time,
        badge = badge,
        painter = Optional.empty()
    ) {
    }

    public constructor (
        name: String,
        preview: String,
        time: String,
        badge: String,
        painter: Painter
    ): this (
        name = name,
        preview = preview,
        time = time,
        badge = badge,
        painter = Optional.of(painter)
    ) {
    }

    public fun setName (ref: String) { this.name = ref }
    public fun setPreview (ref: String) { this.preview = ref }
    public fun setTime (ref: String) { this.time = ref }
    public fun setBadge (ref: String) { this.badge = ref }
    public fun setPainter (ref: Painter?) {
        if (ref == null) this.painter = Optional.empty();
        else this.painter = Optional.of(ref)
    }

    public fun getName (): String { return this.name }
    public fun getPreview (): String { return this.preview }
    public fun getTime (): String { return this.time }
    public fun getBadge (): String { return this.badge }
    public fun getPainter (): Painter? { return this.painter.orElse(null); }


}