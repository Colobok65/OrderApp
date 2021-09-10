package ru.scur.orderapp.exception;

public class ThisProductIsAlreadyInTheOrderLineException extends RuntimeException{
    public ThisProductIsAlreadyInTheOrderLineException(String message){super(message);}
}
