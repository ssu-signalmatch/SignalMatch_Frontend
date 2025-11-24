package com.example.signalmatch_frontend.ui.chat.list

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
import androidx.compose.material3.Badge
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

class ChatListItem
public constructor (
    private var chatId: String,
    private var name: String,
    private var preview: String,
    private var time: String,
    private var badge: Long,
    private var profileImage: Optional<Painter>
) {

    public fun getBadgeString (): String {
        var adjustedBadgeStr: String = this.badge.toString();
        if (adjustedBadgeStr.isNotEmpty() && adjustedBadgeStr == "0") adjustedBadgeStr = "";
        else if (adjustedBadgeStr.length > 2) adjustedBadgeStr = "99+";

        return adjustedBadgeStr
    }

    public constructor (
        chatId: String,
        name: String,
        preview: String,
        time: String,
        badge: Long
    ): this (
        chatId = chatId,
        name = name,
        preview = preview,
        time = time,
        badge = badge,
        profileImage = Optional.empty()
    ) {
    }

    public constructor (
        chatId: String,
        name: String,
        preview: String,
        time: String,
        badge: Long,
        profileImage: Painter
    ): this (
        chatId = chatId,
        name = name,
        preview = preview,
        time = time,
        badge = badge,
        profileImage = Optional.of(profileImage)
    ) {
    }

    public fun setChatId (ref: String) { this.chatId = ref }
    public fun setName (ref: String) { this.name = ref }
    public fun setPreview (ref: String) { this.preview = ref }
    public fun setTime (ref: String) { this.time = ref }
    public fun setBadge (ref: Long) { this.badge = ref }
    public fun setProfileImage (ref: Painter?) {
        if (ref == null) this.profileImage = Optional.empty();
        else this.profileImage = Optional.of(ref)
    }

    public fun getChatId (): String { return this.chatId }
    public fun getName (): String { return this.name }
    public fun getPreview (): String { return this.preview }
    public fun getTime (): String { return this.time }
    public fun getBadge (): Long { return this.badge }
    public fun getProfileImage (): Painter? { return this.profileImage.orElse(null); }

}