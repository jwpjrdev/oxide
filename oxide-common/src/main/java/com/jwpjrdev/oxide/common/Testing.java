package com.jwpjrdev.oxide.common;

import com.jwpjrdev.oxide.common.data.OxideDAO;
import com.jwpjrdev.oxide.common.data.Repository;
import com.jwpjrdev.oxide.common.data.repos.BanRepository;
import com.jwpjrdev.oxide.common.data.types.Ban;
import com.mongodb.MongoCredential;

import java.util.Date;
import java.util.UUID;

public class Testing {
    
    private static DatabaseHandler databaseHandler;
    
    public static void main(String[] args) {
        databaseHandler = new DatabaseHandler(MongoCredential.createCredential("root", "oxide", "password".toCharArray()));
    
        databaseHandler.addDAO("ban", new OxideDAO<Ban>(Ban.class, databaseHandler.getDatastore()), new BanRepository(databaseHandler), Ban.class);
        
        BanRepository repo = (BanRepository) databaseHandler.getRepository("ban", Ban.class);
        
        Ban ban = new Ban();
        ban.setPlayer(UUID.randomUUID());
        ban.setModerator(UUID.randomUUID());
        ban.setCreated(new Date());
        ban.setExpiration(new Date());
        ban.setReason("lol u just got banED hard");
        OxideDAO<Ban> testDAO = databaseHandler.getDAO("ban", Ban.class);
        // OxideDAO<Ban> testDAO = databaseHandler.getBanDAO();
        repo.save(ban);
        // testDAO.save(ban);
        
//        Ban ban2 = databaseHandler
//                .getDatastore()
//                .createQuery(Ban.class)
//                .filter("id", ban.getId())
//                .get();
        Ban ban2 = repo.getById(ban.getId());
        
        System.out.println("Ban:");
        System.out.println("- Created: " + ban2.getCreated());
        System.out.println("- Expiration: " + ban2.getExpiration());
        System.out.println("- Reason: " + ban2.getReason());
        System.out.println("- Moderator: " + ban2.getModerator());
        System.out.println("- Player: " + ban2.getPlayer());
    }
}
