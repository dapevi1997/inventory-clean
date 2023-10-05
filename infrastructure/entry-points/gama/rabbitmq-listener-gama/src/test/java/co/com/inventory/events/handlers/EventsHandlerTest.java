package co.com.inventory.events.handlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reactivecommons.api.domain.DomainEvent;
import reactor.test.StepVerifier;

import java.util.UUID;

public class EventsHandlerTest {

    EventsHandler eventsHandler;

    @BeforeEach
    void setUp() {
        eventsHandler = new EventsHandler();
    }

 @Test
    void handleEventATest(){
        StepVerifier.create(eventsHandler.handleEventA(
            new DomainEvent<>("EVENT",
                UUID.randomUUID().toString(),
                    "Data"))).expectComplete();

    }

}
