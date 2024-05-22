package dev.mutti.urlshortener.exceptions;

public class InvalidUrlException extends IllegalArgumentException {

    public InvalidUrlException(String message) {
        super(message);
    }
}
