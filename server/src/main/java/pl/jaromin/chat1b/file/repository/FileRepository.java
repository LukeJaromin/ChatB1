package pl.jaromin.chat1b.file.repository;

import pl.jaromin.chat1b.file.entity.File;

public interface FileRepository {

    long save(File file);

    File findById(long id);
}
