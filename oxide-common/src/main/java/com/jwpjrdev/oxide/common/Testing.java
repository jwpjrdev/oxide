package com.jwpjrdev.oxide.common;

import com.jwpjrdev.oxide.common.data.OxideDAO;
import com.jwpjrdev.oxide.common.data.repos.BanRepository;
import com.jwpjrdev.oxide.common.data.types.Ban;
import com.mongodb.MongoCredential;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class Testing {
    
    public static void main(String[] args) {
        final DatabaseHandler databaseHandler = new DatabaseHandler(MongoCredential.createCredential("root", "oxide", "password".toCharArray()));
    
        databaseHandler.addDAO("ban", new OxideDAO<Ban>(Ban.class, databaseHandler.getDatastore()), new BanRepository(databaseHandler), Ban.class);
        
        // This will eventually be controlled by a class in the plugin
        final BanRepository repo = (BanRepository) databaseHandler.getRepository("ban", Ban.class);
        
        Ban ban = new Ban();
        ban.setPlayer(UUID.randomUUID());
        ban.setModerator(UUID.randomUUID());
        ban.setCreated(new Date());
        ban.setExpiration(new Date());
        ban.setReason("lol u just got banED hard xDDD");
        // OxideDAO<Ban> testDAO = databaseHandler.getBanDAO();
        repo.save(ban);
        // testDAO.save(ban);
        
        long start = System.currentTimeMillis();
        CompletableFuture<Ban> banFuture = repo.getById(ban.getId());
        banFuture.thenAccept(ban2 -> {
    
            System.out.println("Ban:");
            System.out.println("- Created: " + ban2.getCreated());
            System.out.println("- Expiration: " + ban2.getExpiration());
            System.out.println("- Reason: " + ban2.getReason());
            System.out.println("- Moderator: " + ban2.getModerator());
            System.out.println("- Player: " + ban2.getPlayer());
            
            long end = System.currentTimeMillis();
            long delay = end - start;
            System.out.println("Delay: " + delay);
        });
    }
}
