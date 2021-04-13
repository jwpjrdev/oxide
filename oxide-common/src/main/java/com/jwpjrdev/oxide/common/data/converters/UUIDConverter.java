package com.jwpjrdev.oxide.common.data.converters;

import org.bson.types.Binary;
import org.mongodb.morphia.converters.SimpleValueConverter;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;

import java.util.UUID;

// kudos to https://www.spigotmc.org/threads/466881/
public class UUIDConverter extends TypeConverter implements SimpleValueConverter {
    public UUIDConverter() {
        super(UUID.class);
    }
    
    @Override
    public Object decode(Class<?> targetClass, Object fromDBObject, MappedField optionalExtraInfo) {
        Binary binary = (Binary) fromDBObject;
        
        long msb = 0;
        long lsb = 0;
        byte[] uuidBytes = binary.getData();
        
        for (int i = 8; i < 16; i++) {
            lsb <<= 8;
            lsb |= uuidBytes[i] & 0xFFL;
        }
        
        for (int i = 0; i < 8; i++) {
            msb <<= 8;
            msb |= uuidBytes[i] & 0xFFL;
        }
        
        return new UUID(msb, lsb);
    }
    
    @Override
    public Object encode(Object value, MappedField optionalExtraInfo) {
        UUID uuid = (UUID) value;
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();
        
        byte[] uuidBytes = new byte[16];
        
        for (int i = 15; i >= 8; i--) {
            uuidBytes[i] = (byte) (lsb & 0xFFL);
            lsb >>= 8;
        }
        
        for (int i = 7; i >= 0; i--) {
            uuidBytes[i] = (byte) (msb & 0xFFL);
            msb >>= 8;
        }
        
        return new Binary((byte) 0x04, uuidBytes);
    }
}