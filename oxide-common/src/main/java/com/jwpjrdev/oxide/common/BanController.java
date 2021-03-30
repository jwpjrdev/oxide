package com.jwpjrdev.oxide.common;

import java.util.Date;
import java.util.UUID;

public abstract class BanController {
    
    public abstract void addBan(UUID id, UUID player, UUID moderator, Date expiration, String reason);
    public abstract String getAllBans();
}
