package com.tweetapp.kafka;

import java.util.logging.Logger;
import java.util.logging.Level;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import static com.tweetapp.constants.Constants.TOPIC;

@Component
public class KafkaListeners {

	Logger logger = Logger.getLogger(KafkaListeners.class.getName());

	@KafkaListener(topics = TOPIC, groupId = "groupd_id")
	void listener(String data) {
		logger.log(Level.INFO, "Listener recieved: {0}", data);
	}
}
