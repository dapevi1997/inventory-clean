package co.com.inventory.model.branch.utils;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;


public abstract class DomainEvent implements Serializable {

    public final Instant when;
    public final UUID uuid;
    public final String type;
    private String aggregateRootId;
    private String aggregateName;
    private Long versionType;


    public DomainEvent(String type, String aggregateName) {
        this.type = type;
        this.aggregateName = aggregateName;
        this.when = Instant.now();
        this.uuid =  UUID.randomUUID();
        this.versionType = 1L;
        this.aggregateRootId = UUID.randomUUID().toString();
    }


    public Instant getWhen() {
        return when;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getType() {
        return type;
    }

    public String getAggregateRootId() {
        return aggregateRootId;
    }

    public void setAggregateRootId(String aggregateRootId) {
        this.aggregateRootId = aggregateRootId;
    }

    public String getAggregateName() {
        return aggregateName;
    }

    public void setAggregateName(String aggregateName) {
        this.aggregateName = aggregateName;
    }

    public Long getVersionType() {
        return versionType;
    }

    public void setVersionType(Long versionType) {
        this.versionType = versionType;
    }
}
