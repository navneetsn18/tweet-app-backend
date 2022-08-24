package com.tweetapp.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweetapp.dto.request.ForgotPassword;
import com.tweetapp.dto.request.PostTweet;
import com.tweetapp.dto.request.Register;
import com.tweetapp.dto.request.TweetReply;
import com.tweetapp.dto.request.UpdateTweet;
import com.tweetapp.dto.response.TweetResponse;
import com.tweetapp.dto.response.UserResponse;
import com.tweetapp.service.TweetsService;
import com.tweetapp.service.UserService;

@ContextConfiguration(classes = { TweetAppController.class })
@ExtendWith(SpringExtension.class)
class TweetAppControllerTest {
	@Autowired
	private TweetAppController tweetAppController;

	@MockBean
	private TweetsService tweetsService;

	@MockBean
	private UserService userService;

	/**
	 * Method under test: {@link TweetAppController#resetPassword(ForgotPassword)}
	 */
	@Test
	void testResetPassword() throws Exception {
		when(userService.resetPassword((ForgotPassword) any())).thenReturn(new UserResponse(new ArrayList<>(), "?"));

		ForgotPassword forgotPassword = new ForgotPassword();
		forgotPassword.setPassword("pass");
		forgotPassword.setUsername("janedoe");
		String content = (new ObjectMapper()).writeValueAsString(forgotPassword);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1.0/user/resetpassword")
				.contentType(MediaType.APPLICATION_JSON).content(content);
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(tweetAppController).build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("{\"userDtos\":[],\"status\":\"?\"}"));
	}

	/**
	 * Method under test: {@link TweetAppController#forgotPassword(String)}
	 */
	@Test
	void testForgotPassword() throws Exception {
		when(userService.forgotPassword((String) any())).thenReturn(new UserResponse(new ArrayList<>(), "?"));
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/api/v1.0/user/forgotpassword/{username}", "janedoe");
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(tweetAppController).build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("{\"userDtos\":[],\"status\":\"?\"}"));
	}

	/**
	 * Method under test: {@link TweetAppController#getAllUsers()}
	 */
	@Test
	void testGetAllUsers() throws Exception {
		when(userService.getAllUsers()).thenReturn(new UserResponse(new ArrayList<>(), "?"));
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1.0/user/all");
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(tweetAppController).build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("{\"userDtos\":[],\"status\":\"?\"}"));
	}

	/**
	 * Method under test: {@link TweetAppController#getAllLoggedInUsers()}
	 */
	@Test
	void testGetAllLoggedInUsers() throws Exception {
		when(userService.getAllLoggedInUsers()).thenReturn(new UserResponse(new ArrayList<>(), "?"));
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1.0/user/getallloggedinusers");
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(tweetAppController).build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("{\"userDtos\":[],\"status\":\"?\"}"));
	}

	/**
	 * Method under test: {@link TweetAppController#logout(String)}
	 */
	@Test
	void testLogout() throws Exception {
		when(userService.logout((String) any())).thenReturn(new UserResponse(new ArrayList<>(), "?"));
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1.0/user/logout/{username}",
				"janedoe");
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(tweetAppController).build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("{\"userDtos\":[],\"status\":\"?\"}"));
	}

	/**
	 * Method under test: {@link TweetAppController#getAllTweets()}
	 */
	@Test
	void testGetAllTweets() throws Exception {
		when(tweetsService.getAllTweets()).thenReturn(new TweetResponse(new ArrayList<>(), "?"));
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1.0/tweets/all");
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(tweetAppController).build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("{\"tweetDtos\":[],\"status\":\"?\"}"));
	}

	/**
	 * Method under test: {@link TweetAppController#deleteTweet(String, Long)}
	 */
	@Test
	void testDeleteTweet() throws Exception {
		when(tweetsService.deleteTweet((String) any(), (Long) any()))
				.thenReturn(new TweetResponse(new ArrayList<>(), "?"));
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/api/v1.0/tweets/delete/{username}/{id}", "janedoe", 123L);
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(tweetAppController).build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("{\"tweetDtos\":[],\"status\":\"?\"}"));
	}

	/**
	 * Method under test: {@link TweetAppController#likeTweet(Long, String)}
	 */
	@Test
	void testLikeTweet() throws Exception {
		when(tweetsService.likeTweet((Long) any(), (String) any()))
				.thenReturn(new TweetResponse(new ArrayList<>(), "?"));
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/api/v1.0/tweets/like/{username}/{id}", "janedoe", 123L);
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(tweetAppController).build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("{\"tweetDtos\":[],\"status\":\"?\"}"));
	}

	/**
	 * Method under test: {@link TweetAppController#getAllTweetsOfUser(String)}
	 */
	@Test
	void testGetAllTweetsOfUser() throws Exception {
		when(tweetsService.getTweetsByUsername((String) any())).thenReturn(new TweetResponse(new ArrayList<>(), "?"));
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1.0/tweets/{username}",
				"janedoe");
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(tweetAppController).build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("{\"tweetDtos\":[],\"status\":\"?\"}"));
	}

	/**
	 * Method under test: {@link TweetAppController#postNewTweet(PostTweet)}
	 */
	@Test
	void testPostNewTweet() throws Exception {
		when(tweetsService.postTweet((PostTweet) any())).thenReturn(new TweetResponse(new ArrayList<>(), "?"));

		PostTweet postTweet = new PostTweet();
		postTweet.setTweet("Tweet");
		postTweet.setUsername("janedoe");
		String content = (new ObjectMapper()).writeValueAsString(postTweet);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1.0/tweets/add")
				.contentType(MediaType.APPLICATION_JSON).content(content);
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(tweetAppController).build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("{\"tweetDtos\":[],\"status\":\"?\"}"));
	}

	/**
	 * Method under test: {@link TweetAppController#register(Register)}
	 */
	@Test
	void testRegister() throws Exception {
		when(userService.registerUser((Register) any())).thenReturn(new UserResponse(new ArrayList<>(), "?"));

		Register register = new Register();
		register.setEmail("jane.doe@example.org");
		register.setFirstName("Jane");
		register.setLastName("Doe");
		register.setPassword("pass");
		register.setUsername("janedoe");
		String content = (new ObjectMapper()).writeValueAsString(register);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1.0/user/register")
				.contentType(MediaType.APPLICATION_JSON).content(content);
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(tweetAppController).build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("{\"userDtos\":[],\"status\":\"?\"}"));
	}

	/**
	 * Method under test: {@link TweetAppController#replyToTweet(TweetReply)}
	 */
	@Test
	void testReplyToTweet() throws Exception {
		when(tweetsService.replyTweet((TweetReply) any())).thenReturn(new TweetResponse(new ArrayList<>(), "?"));

		TweetReply tweetReply = new TweetReply();
		tweetReply.setId(123L);
		tweetReply.setReply(new ArrayList<>());
		String content = (new ObjectMapper()).writeValueAsString(tweetReply);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1.0/tweets/reply")
				.contentType(MediaType.APPLICATION_JSON).content(content);
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(tweetAppController).build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("{\"tweetDtos\":[],\"status\":\"?\"}"));
	}

	/**
	 * Method under test: {@link TweetAppController#searchByUsername(String)}
	 */
	@Test
	void testSearchByUsername() throws Exception {
		when(userService.findUser((String) any())).thenReturn(new UserResponse(new ArrayList<>(), "?"));
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1.0/user/search/{username}",
				"janedoe");
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(tweetAppController).build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("{\"userDtos\":[],\"status\":\"?\"}"));
	}

	/**
	 * Method under test: {@link TweetAppController#updateATweet(UpdateTweet)}
	 */
	@Test
	void testUpdateATweet() throws Exception {
		when(tweetsService.updateTweet((UpdateTweet) any())).thenReturn(new TweetResponse(new ArrayList<>(), "?"));

		UpdateTweet updateTweet = new UpdateTweet();
		updateTweet.setId(123L);
		updateTweet.setTweet("Tweet");
		String content = (new ObjectMapper()).writeValueAsString(updateTweet);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1.0/tweets/update")
				.contentType(MediaType.APPLICATION_JSON).content(content);
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(tweetAppController).build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("{\"tweetDtos\":[],\"status\":\"?\"}"));
	}
}
