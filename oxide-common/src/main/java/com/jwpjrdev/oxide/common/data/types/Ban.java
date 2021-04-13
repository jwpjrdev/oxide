package com.jwpjrdev.oxide.common.data.types;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

import java.util.Date;
import java.util.UUID;

// TODO: rework this system to allow custom punishments

@Data
@NoArgsConstructor
@Entity(value = "bans", noClassnameStored = true)
public class Ban {
    
    @Id
    @Indexed
    @Setter(AccessLevel.NONE)
    private UUID id = UUID.randomUUID(); // TODO: test
    @Indexed
    private UUID player; // TODO: reference to User
    @Indexed
    private UUID moderator;
    private Date created;
    private Date expiration;
    private String reason;
}
