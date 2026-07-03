package com.lins.card.data.model

enum class CardCategory(val displayName: String, val icon: String) {
    BANK("银行卡", "credit_card"),
    MEMBER("会员卡", "card_membership"),
    ID("身份证", "badge"),
    SOCIAL("社保卡", "health_and_safety"),
    DRIVER("驾驶证", "drive_eta"),
    PASSPORT("护照", "flight"),
    OTHER("其他", "more_horiz");

    companion object {
        fun fromString(value: String): CardCategory {
            return entries.find { it.name == value } ?: OTHER
        }
    }
}
