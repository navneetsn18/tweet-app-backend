package com.tweetapp.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import static com.tweetapp.constants.Constants.TOPIC;
import static com.tweetapp.constants.Constants.LISTENER;;

@Component
public class KafkaListeners {

	Logger logger = LoggerFactory.getLogger(KafkaListeners.class);

	@KafkaListener(topics = TOPIC, groupId = "groupd_id")
	void listener(String data) {
		String output = LISTENER + data;
		logger.info(output);
	}
}
