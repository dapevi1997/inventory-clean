package co.com.inventory.events.config.data;

import co.com.inventory.mapper.JSONMapperImpl;

import java.time.Instant;

public class Notification {
    private final String type;
    private final String body;
    private final Instant instant;

    public Notification(String type, String body) {
        this.type = type;
        this.body = body;
        this.instant = Instant.now();
    }

    private Notification(){
        this(null,null);
    }

    public String serialize(){
        return new JSONMapperImpl().writeToJson(this);
    }

    public Notification deserialize(String aSerialization) {
        return (Notification) new JSONMapperImpl().readFromJson(aSerialization, Notification.class);
    }

    public static Notification from(String aNotification){
        return new Notification().deserialize(aNotification);
    }

    public String getType() {
        return type;
    }

    public String getBody() {
        return body;
    }

    public Instant getInstant() {
        return instant;
    }
}
