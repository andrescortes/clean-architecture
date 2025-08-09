package co.com.app.kafka.order;

import co.com.app.model.order.Order;
import co.com.app.model.order.gateways.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderAdapter implements OrderRepository {
    private final StreamBridge streamBridge;

    @Override
    public Mono<Boolean> publish(Order order) {
        log.info("Order published: {}", order.toString());
        Message<Order> orderMessage = MessageBuilder.withPayload(order)
                .setHeader("type", "order")
                .build();
        return Mono.defer(() -> Mono.just(streamBridge.send("order-out-0", orderMessage)));
    }
}
