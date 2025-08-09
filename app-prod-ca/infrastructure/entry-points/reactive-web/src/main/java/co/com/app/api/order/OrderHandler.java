package co.com.app.api.order;

import co.com.app.model.order.Order;
import co.com.app.usecase.order.OrderUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderHandler {
    private final OrderUseCase orderUseCase;

    public Mono<ServerResponse> createOrder(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Order.class)
                .flatMap(order -> {
                    log.info("Received order: {}", order.toString());
                    return orderUseCase.publishOrder(order);
                })
                .flatMap(response -> ServerResponse.status(201).bodyValue(response))
                .onErrorResume(e -> {
                    log.error("Error creating order: {}", e.getMessage());
                    return ServerResponse.status(500).bodyValue("Internal Server Error");
                });
    }
}
