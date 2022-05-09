package pl.jaromin.chat1b.file.exceptions;

public class FileNotFindException extends RuntimeException {

    public FileNotFindException(String path) {
        super("File does not exist: " + path);
    }
}
