package org.example.exceptions;

public class ReadingFileException extends RuntimeException {
    public ReadingFileException(String message) {
        super(message);
    }
}