package co.com.app.api.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class OrderRouter {

    @Bean
    public RouterFunction<ServerResponse> orderRt(OrderHandler orderHandler) {
        return route(POST("/orders/create"), orderHandler::createOrder);
    }
}
