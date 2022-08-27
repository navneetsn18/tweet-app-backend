package com.tweetapp.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class ReplyTest {

	@Test
	public void testConstructor() {
		Reply actualReply = new Reply();
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
		actualReply.setDate(fromResult);
		actualReply.setReply("Reply");
		actualReply.setUsername("janedoe");
		assertSame(fromResult, actualReply.getDate());
		assertEquals("Reply", actualReply.getReplyMsg());
		assertEquals("janedoe", actualReply.getUsername());
	}

	@Test
	public void testConstructor2() {
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		Reply actualReply = new Reply("janedoe", "Reply",
				Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		Date fromResult = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
		actualReply.setDate(fromResult);
		actualReply.setReply("Reply");
		actualReply.setUsername("janedoe");
		assertSame(fromResult, actualReply.getDate());
		assertEquals("Reply", actualReply.getReplyMsg());
		assertEquals("janedoe", actualReply.getUsername());
	}
}
