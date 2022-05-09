package pl.jaromin.chat1b.file.service;

import pl.jaromin.chat1b.file.dto.DownloadFileDto;
import pl.jaromin.chat1b.file.dto.UploadFileDto;

public interface FileService {

    String uploadFile(UploadFileDto file);

    DownloadFileDto downloadFile(long fileId, String receiver);
}
