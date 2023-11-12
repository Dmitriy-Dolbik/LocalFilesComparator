package org.example.exceptions;

public class WritingFileException extends RuntimeException {
    public WritingFileException(String message) {
        super(message);
    }
}