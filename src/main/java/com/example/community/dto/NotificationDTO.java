package com.example.community.dto;

import lombok.Data;

@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private Long outerid;
    private Integer type;

    private String outerTitle;
    private String typeName;
    private String notifierName;
}
