package com.jwpjrdev.oxide.common.data;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

import java.util.Date;
import java.util.UUID;

// TODO: rework this system to allow custom punishments

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
    
    public Ban() {
    }
    
    public UUID getId() {
        return this.id;
    }
    
    public UUID getPlayer() {
        return this.player;
    }
    
    public UUID getModerator() {
        return this.moderator;
    }
    
    public Date getCreated() {
        return this.created;
    }
    
    public Date getExpiration() {
        return this.expiration;
    }
    
    public String getReason() {
        return this.reason;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public void setPlayer(UUID player) {
        this.player = player;
    }
    
    public void setModerator(UUID moderator) {
        this.moderator = moderator;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }
    
    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Ban)) return false;
        final Ban other = (Ban) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$player = this.getPlayer();
        final Object other$player = other.getPlayer();
        if (this$player == null ? other$player != null : !this$player.equals(other$player)) return false;
        final Object this$moderator = this.getModerator();
        final Object other$moderator = other.getModerator();
        if (this$moderator == null ? other$moderator != null : !this$moderator.equals(other$moderator)) return false;
        final Object this$created = this.getCreated();
        final Object other$created = other.getCreated();
        if (this$created == null ? other$created != null : !this$created.equals(other$created)) return false;
        final Object this$expiration = this.getExpiration();
        final Object other$expiration = other.getExpiration();
        if (this$expiration == null ? other$expiration != null : !this$expiration.equals(other$expiration))
            return false;
        final Object this$reason = this.getReason();
        final Object other$reason = other.getReason();
        if (this$reason == null ? other$reason != null : !this$reason.equals(other$reason)) return false;
        return true;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof Ban;
    }
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $player = this.getPlayer();
        result = result * PRIME + ($player == null ? 43 : $player.hashCode());
        final Object $moderator = this.getModerator();
        result = result * PRIME + ($moderator == null ? 43 : $moderator.hashCode());
        final Object $created = this.getCreated();
        result = result * PRIME + ($created == null ? 43 : $created.hashCode());
        final Object $expiration = this.getExpiration();
        result = result * PRIME + ($expiration == null ? 43 : $expiration.hashCode());
        final Object $reason = this.getReason();
        result = result * PRIME + ($reason == null ? 43 : $reason.hashCode());
        return result;
    }
    
    public String toString() {
        return "Ban(id=" + this.getId() + ", player=" + this.getPlayer() + ", moderator=" + this.getModerator() + ", created=" + this.getCreated() + ", expiration=" + this.getExpiration() + ", reason=" + this.getReason() + ")";
    }
}
