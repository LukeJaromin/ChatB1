package pl.jaromin.chat1b.file.repository;

import pl.jaromin.chat1b.file.entity.File;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class JpaFileRepository implements FileRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public long save(File file) {
        entityManager.persist(file);
        entityManager.flush();
        return file.getId();
    }

    @Override
    public File findById(long id) {
        return entityManager.find(File.class, id);
    }

}
