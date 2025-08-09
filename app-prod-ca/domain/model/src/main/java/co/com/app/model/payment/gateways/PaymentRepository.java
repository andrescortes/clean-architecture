package co.com.app.model.payment.gateways;

import co.com.app.model.payment.Payment;
import reactor.core.publisher.Mono;

public interface PaymentRepository {
    Mono<Boolean> publishPayment(Payment payment);
}
