package fr.ippon.sample.kafkasample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private MessageProducer messageProducer;

    public void sendMessage(String msg) {
        messageProducer.sendMessage(msg);
    }

    @Bean
    public MessageProducer messageProducer() {
        return new MessageProducer();
    }

    @Bean
    public MessageListener messageListener() {
        return new MessageListener();
    }

    public static class MessageProducer {

        @Autowired
        private KafkaTemplate<String, String> kafkaTemplate;

        @Value(value = "${message.topic.name}")
        private String topicName;

        public void sendMessage(String message) {
            kafkaTemplate.send(topicName, message);
        }
    }

    public static class MessageListener {

        @KafkaListener(topics = "${message.topic.name}", containerGroup = "foo", containerFactory = "kafkaListenerContainerFactory")
        public void listenGroup(String message) {
            System.out.println("Received Message in group 'foo': " + message);
        }
    }
}
