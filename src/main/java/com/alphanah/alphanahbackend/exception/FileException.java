package com.alphanah.alphanahbackend.exception;

public class FileException extends AlphanahBaseException {

    public FileException(String message) {
        super("file." + message);
    }

    public static FileException fileNull() {
        return new FileException("null");
    }

    public static FileException fileMaxSize() {
        return new FileException("max.size");
    }

    public static FileException unsupported() {
        return new FileException("unsupported.file.type");
    }

}
