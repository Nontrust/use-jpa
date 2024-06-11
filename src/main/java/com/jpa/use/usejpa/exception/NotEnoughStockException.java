package com.jpa.use.usejpa.exception;

public class NotEnoughStockException extends Throwable {

    public NotEnoughStockException() {
        super();
    }

    public NotEnoughStockException(String msg) {
        super(msg);
    }
}
