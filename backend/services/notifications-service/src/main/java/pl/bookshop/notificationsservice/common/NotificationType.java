package pl.bookshop.notificationsservice.common;

import lombok.Getter;

@Getter
public enum NotificationType {

    EMAIL("EMAIL"),
    TELEGRAM("TELEGRAM"),
    PUSH("PUSH"),
    SMS("SMS"),
    WHATSAPP("WHATSAPP"),
    UNKNOWN("UNKNOWN");

    private final String value;

    NotificationType(String value) {
        this.value = value;
    }

    public static NotificationType fromString(String channel) {
        if (channel == null) {
            return UNKNOWN;
        }
        String normalized = channel.trim().toUpperCase();
        for (NotificationType type : NotificationType.values()) {
            if (type.value.equals(normalized)) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
