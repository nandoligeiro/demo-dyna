package com.ligeiro.dyna.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public InvoiceController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public ResponseEntity<String> generateInvoice(@RequestParam(defaultValue = "false") boolean fail) {

        String invoiceEvent = fail
                ? "{\"status\":\"error\"}"
                : "{\"status\":\"success\"}";

        kafkaTemplate.send("invoice-events", invoiceEvent);
        if (fail) throw new RuntimeException("Simulated failure");
        return ResponseEntity.ok("Invoice sent to Kafka");
    }
}
