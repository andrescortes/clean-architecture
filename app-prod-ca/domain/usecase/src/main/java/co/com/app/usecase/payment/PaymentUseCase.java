package co.com.app.usecase.payment;

import co.com.app.model.payment.Payment;
import co.com.app.model.payment.gateways.PaymentRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PaymentUseCase {
    private final PaymentRepository paymentRepository;

    public Mono<String> publishPayment(Payment payment) {
        return paymentRepository.publishPayment(payment)
                .flatMap(isSuccess -> isSuccess
                        ? Mono.just("The payment was created with id: ".concat(payment.getId()))
                        : Mono.just("The payment failed with id: ".concat(payment.getId()))
                );
    }
}
