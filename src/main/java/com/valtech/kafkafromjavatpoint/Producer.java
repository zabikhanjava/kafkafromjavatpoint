package com.valtech.kafkafromjavatpoint;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Producer {

	public static void main(String[] args) {
		Map config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		KafkaProducer<String, String> producer = new KafkaProducer<>(config);
		ProducerRecord<String, String> record = new ProducerRecord<String, String>("partition-test-topic","3", "Hye Kafka");
		producer.send(record, new Callback() {
			public void onCompletion(RecordMetadata recordMetadata, Exception e) {
				Logger logger = LoggerFactory.getLogger(Producer.class);
				if (e == null) {
					logger.info("Successfully received the details as: \n" + "key ="+record.key()+"value"+record.value());
				}

				else {
					logger.error("Can't produce,getting error", e);

				}
			}
		});
		producer.flush();
		producer.close();

	}

}