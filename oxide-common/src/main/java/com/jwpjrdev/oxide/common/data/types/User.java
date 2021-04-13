package com.jwpjrdev.oxide.common.data.types;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity(value = "users", noClassnameStored = true)
public class User {
    
    @Id
    @Indexed
    private UUID id;
    @Indexed
    private UUID player;
}
