package co.com.app.api.payment;

import co.com.app.model.payment.Payment;
import co.com.app.usecase.payment.PaymentUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentHandler {
    private final PaymentUseCase paymentUseCase;

    public Mono<ServerResponse> createPayment(ServerRequest request) {
        return request.bodyToMono(Payment.class)
                .flatMap(payment -> {
                    log.info("Received payment: {}", payment.toString());
                    return paymentUseCase.publishPayment(payment);
                })
                .flatMap(response -> ServerResponse.status(201).bodyValue(response))
                .onErrorResume(e -> {
                    log.error("Error creating payment: {}", e.getMessage());
                    return ServerResponse.status(500).bodyValue("Internal Server Error");
                });
    }
}
