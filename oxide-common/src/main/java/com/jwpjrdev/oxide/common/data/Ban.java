package com.jwpjrdev.oxide.common.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.converters.UUIDConverter;

import java.util.Date;
import java.util.UUID;

// TODO: rework this system to allow custom punishments

@Data
@NoArgsConstructor
@Entity(value = "Bans", noClassnameStored = true)
public class Ban {
    @Id
    @Indexed
    private UUID id;
    @Indexed
    private UUID player;
    @Indexed
    private UUID moderator;
    private Date created;
    private Date expiration;
    private String reason;
}
