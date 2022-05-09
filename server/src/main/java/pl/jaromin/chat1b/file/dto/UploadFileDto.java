package pl.jaromin.chat1b.file.dto;

import lombok.Data;

@Data
public class UploadFileDto {

    private String sender;

    private String receiver;

    private String name;

    private String path;
}
