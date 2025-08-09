package co.com.app.usecase.order;

import co.com.app.model.order.Order;
import co.com.app.model.order.gateways.OrderRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class OrderUseCase {
    private final OrderRepository orderRepository;

    public Mono<String> publishOrder(Order order) {
        return orderRepository.publish(order)
                .flatMap(isSuccess -> isSuccess
                        ? Mono.just("Order published successfully: " + order.getOrderId())
                        : Mono.just("Failed to publish order: " + order.getOrderId())
                );
    }
}
