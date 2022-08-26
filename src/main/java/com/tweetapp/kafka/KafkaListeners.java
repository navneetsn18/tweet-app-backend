package com.tweetapp.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import static com.tweetapp.constants.Constants.TOPIC;

@Component
public class KafkaListeners {

	@KafkaListener(topics = TOPIC, groupId = "groupd_id")
	void listener(String data) {
		System.out.println("Listener recieved: " + data);
	}
}
