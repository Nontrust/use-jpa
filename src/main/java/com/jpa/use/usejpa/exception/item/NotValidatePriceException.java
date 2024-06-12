package com.jpa.use.usejpa.exception.item;

import com.jpa.use.usejpa.exception.ItemException;

public class NotValidatePriceException extends ItemException {
    private final static String DEFAULT_MSG =  "Item 의 price가 적절하지 않습니다.";
    
    public NotValidatePriceException() {
        super(DEFAULT_MSG);
    }

    public NotValidatePriceException(String msg) {
         super(msg);
    }
}
