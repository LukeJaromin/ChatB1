package pl.jaromin.chat1b.file.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class File {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private byte[] bytes;

    private String receiver;
}
