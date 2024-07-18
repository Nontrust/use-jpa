package com.jpa.use.usejpa.exception.item;

import com.jpa.use.usejpa.exception.ItemException;

public class NotValidatedItemDTypeException extends ItemException {
    private final static String DEFAULT_MSG = "Item 의 Type이 적절하지 않습니다.";

    public NotValidatedItemDTypeException() {
        super(DEFAULT_MSG);
    }

    public NotValidatedItemDTypeException(String msg) {
        super(msg);
    }
}
