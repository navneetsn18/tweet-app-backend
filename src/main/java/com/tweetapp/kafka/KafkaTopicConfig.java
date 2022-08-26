package com.tweetapp.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import static com.tweetapp.constants.Constants.*;

@Configuration
public class KafkaTopicConfig {
	
	@Bean
	public NewTopic tweetAppTopics() {
		return TopicBuilder.name(TOPIC).build();
	}
}
