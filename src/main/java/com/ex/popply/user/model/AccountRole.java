package com.ex.popply.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AccountRole {

    USER("USER"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN");

    private String value;
}
