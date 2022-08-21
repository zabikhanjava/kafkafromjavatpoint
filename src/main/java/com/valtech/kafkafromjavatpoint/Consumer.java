package com.valtech.kafkafromjavatpoint;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class Consumer {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(Consumer.class.getName());

		Map config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "third_app");
		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

		// creating consumer
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(config);
		// Subscribing
		consumer.subscribe(Arrays.asList("partition-test-topic"));
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<String, String> record : records) {
				System.out.println("Key: " + record.key() + ", Value:" + record.value());
				System.out.println("Partition:" + record.partition() + ",Offset:" + record.offset());
			}
		}
	}

}
