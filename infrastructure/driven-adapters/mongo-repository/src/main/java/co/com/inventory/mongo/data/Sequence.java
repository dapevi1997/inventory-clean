package co.com.inventory.mongo.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequences")
@Data
public class Sequence {
    @Id
    private String id;
    private Long sequence;
}
