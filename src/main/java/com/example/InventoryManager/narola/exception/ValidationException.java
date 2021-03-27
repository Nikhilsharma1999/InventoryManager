package com.example.InventoryManager.narola.exception;

public class ValidationException extends RuntimeException {
    private static final long serialVersionUID = -1256819596622997898L;

    public ValidationException(String string){
        super(string);
    }
}
