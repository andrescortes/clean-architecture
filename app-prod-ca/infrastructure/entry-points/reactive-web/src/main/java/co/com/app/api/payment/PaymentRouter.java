package co.com.app.api.payment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PaymentRouter {

    @Bean
    public RouterFunction<ServerResponse> paymentRt(PaymentHandler paymentHandler) {
        return route(POST("/payments/create"), paymentHandler::createPayment);
    }
}
