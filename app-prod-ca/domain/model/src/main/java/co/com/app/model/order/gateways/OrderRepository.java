package co.com.app.model.order.gateways;

import co.com.app.model.order.Order;
import reactor.core.publisher.Mono;

public interface OrderRepository {
    Mono<Boolean> publish(Order order);
}
