package co.com.inventory.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    public static final String EXCHANGE = "core-posts-events";
    public static final String EVENTS_QUEUE = "events.queue";
    public static final String PROXY_QUEUE = "proxy.queue";
    public static final String ROUTING_KEY = "events.routing.key";
    public static final String PROXY_ROUTING_KEY = "proxy.routing.key";

    @Bean
    public RabbitAdmin rabbitAdmin(RabbitTemplate rabbitTemplate){
        var admin = new RabbitAdmin(rabbitTemplate);
        admin.declareExchange(new TopicExchange(EXCHANGE));
        return admin;
    }
    @Bean
    public Queue eventsQueue(){
        return new Queue(EVENTS_QUEUE);
    }
    @Bean
    public Queue proxyQueue(){
        return new Queue(PROXY_QUEUE);
    }

    @Bean
    public TopicExchange eventsExchange(){
        return new TopicExchange(EXCHANGE);
    }
    @Bean
    public Binding eventsBinding(){
        return BindingBuilder.bind(this.eventsQueue()).to(this.eventsExchange()).with(ROUTING_KEY);
    }
    @Bean
    public Binding proxyBinding(){
        return BindingBuilder.bind(this.proxyQueue()).to(this.eventsExchange()).with(PROXY_ROUTING_KEY);
    }
}
