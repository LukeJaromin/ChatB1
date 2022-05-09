package pl.jaromin.chat1b.file.service;

import pl.jaromin.chat1b.file.dto.DownloadFileDto;
import pl.jaromin.chat1b.file.dto.UploadFileDto;
import pl.jaromin.chat1b.file.entity.File;
import pl.jaromin.chat1b.file.exceptions.FileNotFindException;
import pl.jaromin.chat1b.file.repository.FileRepository;
import pl.jaromin.chat1b.jms.JmsMessageDto;
import pl.jaromin.chat1b.jms.JmsMessageService;
import pl.jaromin.chat1b.jms.MessageType;
import pl.jaromin.chat1b.user.entity.ChatUser;
import pl.jaromin.chat1b.user.exception.UserDoesNotExistException;
import pl.jaromin.chat1b.user.repository.UserRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

public class FileServiceImpl implements FileService {

    @Inject
    FileRepository fileRepository;

    @Inject
    JmsMessageService jmsMessageService;

    @Inject
    UserRepository userRepository;

    @Override
    @Transactional
    public String uploadFile(UploadFileDto fileDto) {
        checkUser(fileDto.getSender());
        checkUser(fileDto.getReceiver());
        long fileId = fileRepository.save(mapToFile(fileDto));
        jmsMessageService.sendJmsMessage(mapToJmsMessage(fileDto, fileId));
        return "File uploaded successfully.";
    }

    private JmsMessageDto mapToJmsMessage(UploadFileDto fileDto, long fileId) {
        String link = String.format("http://localhost:8080/jee/api/file/%d?receiver=%s", fileId, fileDto.getReceiver());
        return JmsMessageDto.builder()
                .messageType(MessageType.PRIVATE)
                .receivers(Set.of(fileDto.getReceiver()))
                .text(String.format("%s send you a file: %s. You can download it using this link: %s", fileDto.getSender(), fileDto.getName(), link))
                .build();
    }

    private File mapToFile(UploadFileDto fileDto) {
        File file = new File();
        file.setName(fileDto.getName());
        file.setBytes(toByreArray(fileDto.getPath()));
        file.setReceiver(fileDto.getReceiver());
        return file;
    }

    @Override
    public DownloadFileDto downloadFile(long fileId, String receiver) {
        File file = fileRepository.findById(fileId);
        if (file == null || !file.getReceiver().equals(receiver)) {
            return null;
        }
        return DownloadFileDto.builder()
                .name(file.getName())
                .bytes(file.getBytes())
                .build();
    }

    private static byte[] toByreArray(String path) {
        byte[] bytesArray;
        try {
            bytesArray = Files.readAllBytes(Path.of(path));
        } catch (IOException e) {
            throw new FileNotFindException(path);
        }
        return bytesArray;
    }

    private void checkUser(String username) {
        ChatUser user = userRepository.findById(username);
        if (user == null) {
            throw new UserDoesNotExistException(username);
        }
    }
}
