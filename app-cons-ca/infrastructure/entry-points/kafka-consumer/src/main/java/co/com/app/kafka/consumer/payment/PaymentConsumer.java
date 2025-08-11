package co.com.app.kafka.consumer.payment;

import co.com.app.model.payment.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentConsumer {

    @Bean
    public Consumer<Payment> processPayment() {
        return payment -> {
            log.info("Payment received");
            printPayment(payment);
        };
    }

    private void printPayment(Payment payment) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String paymentStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(payment);
            log.info("Payment: {}", paymentStr);
        } catch (Exception e) {
            log.error("Error printing payment", e);
        }
    }
}
