package br.com.vollmed.api.exception;

public class ApiException extends RuntimeException{

    public ApiException(String msg) {
        super(msg);
    }

    public ApiException(String msg, Throwable exception) {
        super(msg, exception);
    }
}
