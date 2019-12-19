package com.example.community.Exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"该问题不存在，换个试试吧~"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003,"未登录不能继续操作，请先拖鞋进家门。"),
    SYS_ERROR(2004,"服务器冒烟了 ，阿连和老赵不在家哦！"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "回复的评论不存在了~~~"),
    COMMENT_CONTENT_IS_EMPTY(2007, "评论内容为空，请重新输入")
    ;

    private Integer code;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code ;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
