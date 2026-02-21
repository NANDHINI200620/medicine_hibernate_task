package com.kce.util;
public class OutOfStockException extends Exception {
    private static final long serialVersionUID = 1L;
    public OutOfStockException() {
        super("Medicine is out of stock");
    }
    public OutOfStockException(String message) {
        super(message);
    }
}