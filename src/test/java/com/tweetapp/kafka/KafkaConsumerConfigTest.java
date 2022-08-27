package com.tweetapp.kafka;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.time.Duration;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.MicrometerConsumerListener;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.LogIfLevelEnabled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.micrometer.core.instrument.composite.CompositeMeterRegistry;

@ContextConfiguration(classes = { KafkaConsumerConfig.class })
@ExtendWith(SpringExtension.class)
class KafkaConsumerConfigTest {
	@Autowired
	private KafkaConsumerConfig kafkaConsumerConfig;

	@Test
	void testConsumerConfig() {
		assertEquals(3, kafkaConsumerConfig.consumerConfig().size());
	}

	@Test
	void testConsumerFactory() {
		assertTrue(kafkaConsumerConfig.consumerFactory() instanceof DefaultKafkaConsumerFactory);
	}

	@Test
	void testFactory() {
		DefaultKafkaConsumerFactory<String, String> defaultKafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(
				new HashMap<>());
		assertSame(defaultKafkaConsumerFactory,
				((ConcurrentKafkaListenerContainerFactory<String, String>) kafkaConsumerConfig
						.factory(defaultKafkaConsumerFactory)).getConsumerFactory());
		ContainerProperties containerProperties = ((ConcurrentKafkaListenerContainerFactory<String, String>) kafkaConsumerConfig
				.factory(defaultKafkaConsumerFactory)).getContainerProperties();
		assertTrue(containerProperties.isOnlyLogRecordMetadata());
		assertFalse(containerProperties.isMissingTopicsFatal());
		assertTrue(containerProperties.isMicrometerEnabled());
		assertNull(containerProperties.getTopics());
		assertNull(containerProperties.getTopicPattern());
		assertNull(containerProperties.getTopicPartitions());
		assertEquals(10000L, containerProperties.getShutdownTimeout());
		assertEquals(5000L, containerProperties.getPollTimeout());
		assertEquals(3.0f, containerProperties.getNoPollThreshold());
		assertEquals(30, containerProperties.getMonitorInterval());
		assertEquals(ContainerProperties.EOSMode.BETA, containerProperties.getEosMode());
		Duration expectedConsumerStartTimeout = containerProperties.getConsumerStartTimout();
		assertSame(expectedConsumerStartTimeout, containerProperties.getConsumerStartTimeout());
		assertEquals(3, containerProperties.getCommitRetries());
		assertEquals(LogIfLevelEnabled.Level.DEBUG, containerProperties.getCommitLogLevel());
		assertEquals("", containerProperties.getClientId());
		assertEquals(ContainerProperties.AssignmentCommitOption.LATEST_ONLY_NO_TX,
				containerProperties.getAssignmentCommitOption());
		assertEquals(0, containerProperties.getAdviceChain().length);
		assertEquals(5000L, containerProperties.getAckTime());
		assertEquals(ContainerProperties.AckMode.BATCH, containerProperties.getAckMode());
		assertEquals(1, containerProperties.getAckCount());
	}

	@Test
	void testFactory2() {
		DefaultKafkaConsumerFactory<String, String> defaultKafkaConsumerFactory = (DefaultKafkaConsumerFactory<String, String>) mock(
				DefaultKafkaConsumerFactory.class);
		doNothing().when(defaultKafkaConsumerFactory).addListener((ConsumerFactory.Listener<String, String>) any());
		defaultKafkaConsumerFactory.addListener(new MicrometerConsumerListener<>(new CompositeMeterRegistry()));
		ContainerProperties containerProperties = ((ConcurrentKafkaListenerContainerFactory<String, String>) kafkaConsumerConfig
				.factory(defaultKafkaConsumerFactory)).getContainerProperties();
		assertEquals(1, containerProperties.getAckCount());
		assertTrue(containerProperties.isSyncCommits());
		assertTrue(containerProperties.isOnlyLogRecordMetadata());
		assertFalse(containerProperties.isMissingTopicsFatal());
		assertTrue(containerProperties.isMicrometerEnabled());
		assertNull(containerProperties.getTopics());
		assertNull(containerProperties.getTopicPattern());
		assertNull(containerProperties.getTopicPartitions());
		assertEquals(10000L, containerProperties.getShutdownTimeout());
		assertEquals(5000L, containerProperties.getPollTimeout());
		assertEquals(3.0f, containerProperties.getNoPollThreshold());
		assertEquals(30, containerProperties.getMonitorInterval());
		assertTrue(containerProperties.getMicrometerTags().isEmpty());
		assertTrue(containerProperties.getKafkaConsumerProperties().isEmpty());
		assertEquals(ContainerProperties.EOSMode.BETA, containerProperties.getEosMode());
		Duration expectedConsumerStartTimeout = containerProperties.getConsumerStartTimout();
		assertSame(expectedConsumerStartTimeout, containerProperties.getConsumerStartTimeout());
		assertEquals(ContainerProperties.AckMode.BATCH, containerProperties.getAckMode());
		assertEquals(3, containerProperties.getCommitRetries());
		assertEquals(LogIfLevelEnabled.Level.DEBUG, containerProperties.getCommitLogLevel());
		assertEquals(0, containerProperties.getAdviceChain().length);
		assertEquals("", containerProperties.getClientId());
		assertEquals(ContainerProperties.AssignmentCommitOption.LATEST_ONLY_NO_TX,
				containerProperties.getAssignmentCommitOption());
		assertEquals(5000L, containerProperties.getAckTime());
		verify(defaultKafkaConsumerFactory).addListener((ConsumerFactory.Listener<String, String>) any());
	}
}
