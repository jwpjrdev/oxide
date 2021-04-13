package com.jwpjrdev.oxide.common.data.interfaces;

import java.util.List;
import java.util.UUID;

public interface Moderatable {
    
    List getAllByModerator(UUID moderator);
}
