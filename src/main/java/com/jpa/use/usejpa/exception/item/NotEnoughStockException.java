package com.jpa.use.usejpa.exception.item;

import com.jpa.use.usejpa.exception.ItemException;

public class NotEnoughStockException extends ItemException {
    private final static String DEFAULT_MSG = "Item 의 stock이 적절하지 않습니다.";

    public NotEnoughStockException() {
        super(DEFAULT_MSG);
    }

    public NotEnoughStockException(String msg) {
        super(msg);
    }
}
