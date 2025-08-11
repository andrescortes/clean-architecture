package co.com.app.kafka.consumer.order;

import co.com.app.model.order.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderConsumer {
//    private final OrderUseCase useCase;

    @Bean
    public Consumer<Message<Order>> processOrder() {
        return order -> {
            log.info("Order received");
            printOrder(order.getPayload());
        };
    }

    private void printOrder(Order order) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String orderStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(order);
            log.info("Order: {}", orderStr);
        } catch (Exception e) {
            log.error("Error printing order", e);
        }
    }
}
