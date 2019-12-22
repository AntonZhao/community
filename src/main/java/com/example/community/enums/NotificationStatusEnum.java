package com.example.community.enums;

public enum NotificationStatusEnum {
    UNREAD(0),READ(1);
    private int Status;

    public int getStatus() {
        return Status;
    }

    NotificationStatusEnum(int status) {
        Status = status;
    }
}
