package com.example.signalmatch_frontend.ui.chat.list

import com.example.signalmatch_frontend.data.model.response.ApiGetChatsRoomsResponse
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class ChatListItem
public constructor (
    private var chatId: Long,
    private var name: String?,
    private var preview: String?,
    private var timestamp: LocalDateTime?,
    private var isUnread: Boolean,
    private var profileImage: String?
) {

    public constructor (
        chatId: Long,
        name: String,
        preview: String,
        timestamp: LocalDateTime,
        isUnread: Boolean
    ): this (
        chatId = chatId,
        name = name,
        preview = preview,
        timestamp = timestamp,
        isUnread = isUnread,
        profileImage = null
    ) { }

    public constructor (
        apiGetChatsListItem: ApiGetChatsRoomsResponse
    ): this (
        chatId = apiGetChatsListItem.roomId,
        name = apiGetChatsListItem.opponentName,
        preview = apiGetChatsListItem.lastMessage,
        timestamp = if (apiGetChatsListItem.lastMessageTime == null) null else LocalDateTime.parse(apiGetChatsListItem.lastMessageTime, DateTimeFormatter.ISO_INSTANT),
        isUnread = apiGetChatsListItem.hasUnread,
        profileImage = apiGetChatsListItem.opponentProfileImageUrl
    ) {}

    public fun getFormattedTimestamp (
    ): String? {
        val today: LocalDate = LocalDate.now();
        if (this.timestamp != null) {
            val savedTimestamp = this.timestamp

            val fYear: String = savedTimestamp?.format(DateTimeFormatter.ofPattern("yyyy년 ")) ?: ""
            val fDate: String = savedTimestamp?.format(DateTimeFormatter.ofPattern("MM월 dd일 ")) ?: ""
            var formattedDateTime: String = savedTimestamp?.format(DateTimeFormatter.ofPattern("a KK:mm").withLocale(Locale.forLanguageTag("ko"))) ?: ""

            if (savedTimestamp?.toLocalDate()?.isBefore(today) ?: false) formattedDateTime = fDate + formattedDateTime;
            if (savedTimestamp?.year != today.year) formattedDateTime = fYear + formattedDateTime;

            return formattedDateTime;
        }

        return ""
    }

    public fun setChatId (ref: Long) { this.chatId = ref }
    public fun setName (ref: String) { this.name = ref }
    public fun setPreview (ref: String) { this.preview = ref }
    public fun setTimestamp (ref: LocalDateTime) { this.timestamp = ref }
    public fun setIsUnread (ref: Boolean) { this.isUnread = ref }
    public fun setProfileImage (ref: String?) { this.profileImage = ref }

    public fun getChatId (): Long { return this.chatId }
    public fun getName (): String? { return this.name }
    public fun getPreview (): String? { return this.preview }
    public fun getTimestamp (): LocalDateTime? { return this.timestamp }
    public fun getIsUnread (): Boolean { return this.isUnread }
    public fun getProfileImage (): String? { return this.profileImage }

}