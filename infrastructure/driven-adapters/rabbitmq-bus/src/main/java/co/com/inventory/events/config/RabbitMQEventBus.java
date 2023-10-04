package co.com.inventory.events.config;

import co.com.inventory.events.config.data.Notification;
import co.com.inventory.mapper.JSONMapper;
import co.com.inventory.model.branch.utils.DomainEvent;
import co.com.inventory.usecase.generic.gateways.EventBus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQEventBus implements EventBus {
    public static final String EXCHANGE = "core-posts-events";
    public static final String ROUTING_KEY = "events.routing.key";
    private final RabbitTemplate rabbitTemplate;
    private final JSONMapper mapper;

    public RabbitMQEventBus(RabbitTemplate rabbitTemplate, JSONMapper mapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapper = mapper;
    }

    @Override
    public void publish(DomainEvent event) {
        var notification = new Notification(
                event.getClass()
                        .getTypeName(),
                mapper.writeToJson(event)
        );
        rabbitTemplate.convertAndSend(this.EXCHANGE,this.ROUTING_KEY,notification.serialize().getBytes());
    }

    @Override
    public void publishError(Throwable errorEvent) {

    }
}
