package com.example.signalmatch_frontend.ui.chat.room

import androidx.compose.ui.graphics.painter.Painter
import com.example.signalmatch_frontend.data.model.response.ApiGetChatsRoomsMessagesResponse
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.Optional

class ChatItem
public constructor (
    private var chatId: String,
    private var sender: ChatItemSender,
    private var type: ChatItemType,
    private var userId: String,
    private var name: String,
    private var profileImage: Optional<Painter>,
    private var message: String,
    private var timestamp: LocalDateTime,
    private var read: Boolean
) {

    public fun getFormattedTimestamp (): String {
        val today: LocalDate = LocalDate.now();
        val fYear: String = this.timestamp.format(DateTimeFormatter.ofPattern("yyyy년 "))
        val fDate: String = this.timestamp.format(DateTimeFormatter.ofPattern("MM월 dd일 "))
        var formattedDateTime: String = this.timestamp.format(DateTimeFormatter.ofPattern("a KK:mm").withLocale(Locale.forLanguageTag("ko")))

        if (this.timestamp.toLocalDate().isBefore(today)) formattedDateTime = fDate + formattedDateTime;
        if (this.timestamp.year != today.year) formattedDateTime = fYear + formattedDateTime;

        return formattedDateTime;
    }

    public constructor (
        chatId: String,
        sender: ChatItemSender,
        type: ChatItemType,
        userId: String,
        name: String,
        message: String,
        timestamp: LocalDateTime,
        read: Boolean
    ): this (
        chatId = chatId,
        sender = sender,
        type = type,
        userId = userId,
        name = name,
        profileImage = Optional.empty(),
        message = message,
        timestamp = timestamp,
        read = read
    )

    public constructor (
        chatId: String,
        sender: ChatItemSender,
        type: ChatItemType,
        userId: String,
        name: String,
        profileImage: Painter,
        message: String,
        timestamp: LocalDateTime,
        read: Boolean
    ): this (
        chatId = chatId,
        sender = sender,
        type = type,
        userId = userId,
        name = name,
        profileImage = Optional.of(profileImage),
        message = message,
        timestamp = timestamp,
        read = read
    )

    public constructor (
        userId: Long,
        chatroomName: String,
        apiResponse: ApiGetChatsRoomsMessagesResponse
    ): this (
        chatId = apiResponse.id.toString(),
        sender = if (apiResponse.senderId == userId) ChatItemSender.SEND else ChatItemSender.RECEIVE,
        type = ChatItemType.TEXT,
        userId = apiResponse.senderId.toString(),
        name = chatroomName,
        message = apiResponse.content,
        timestamp = LocalDateTime.now(),
        read = true
    )

    public fun setChatId (ref: String) { this.chatId = ref }
    public fun setSender (ref: ChatItemSender) { this.sender = ref }
    public fun setType (ref: ChatItemType) { this.type = ref }
    public fun setUserId (ref: String) { this.userId = ref }
    public fun setName (ref: String) { this.name = ref }
    public fun setMessage (ref: String) { this.message = ref }
    public fun setTimestamp (ref: LocalDateTime) { this.timestamp = ref }
    public fun setRead (ref: Boolean) { this.read = ref }
    public fun setProfileImage (ref: Painter?) {
        if (ref == null) this.profileImage = Optional.empty();
        else this.profileImage = Optional.of(ref)
    }

    public fun getChatId (): String { return this.chatId }
    public fun getSender (): ChatItemSender { return this.sender }
    public fun getType (): ChatItemType { return this.type }
    public fun getUserId (): String { return this.userId }
    public fun getName (): String { return this.name }
    public fun getMessage (): String { return this.message }
    public fun getTimestamp (): LocalDateTime { return this.timestamp }
    public fun getRead (): Boolean { return this.read }
    public fun getProfileImage (): Painter? { return this.profileImage.orElse(null); }


}