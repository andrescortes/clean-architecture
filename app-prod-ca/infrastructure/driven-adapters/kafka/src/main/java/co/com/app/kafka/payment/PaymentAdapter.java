package co.com.app.kafka.payment;

import co.com.app.model.payment.Payment;
import co.com.app.model.payment.gateways.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentAdapter implements PaymentRepository {
    private final StreamBridge bridge;

    @Override
    public Mono<Boolean> publishPayment(Payment payment) {
        log.info("Payment published: {}", payment.toString());
        Message<Payment> orderMessage = MessageBuilder
                .withPayload(payment)
                .setHeader("type", "payment")
                .build();
        return Mono.defer(() -> Mono.just(bridge.send("payment-out-0", orderMessage)));
    }
}
