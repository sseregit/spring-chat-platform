package io.github.sseregit.springchatplatform.common.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorCode implements CodeInterface {
    SUCCESS(0, "SUCCESS"),
    USER_ALREADY_EXISTS(-1, "User already exists"),
    USER_SAVED_FAILED(-2, "User saved failed"),
    NOT_EXIST_USER(-3, "User not exists"),
    MISS_MATCH_PASSWORD(-4, "Miss match password"),
    ;

    private final Integer code;
    private final String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
