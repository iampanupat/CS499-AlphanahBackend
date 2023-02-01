//package com.alphanah.alphanahbackend.old;
//
//import com.alphanah.alphanahbackend.entity.Account;
//import com.alphanah.alphanahbackend.model.enumerate.ERole;
//import com.alphanah.alphanahbackend.service.AccountService;
//import com.alphanah.alphanahbackend.service.AuthService;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.UUID;
//
//@SpringBootTest
//class AccountUnitTest {
//
//	@Autowired
//	private AccountService accountService;
//
//	@Autowired
//	private AuthService authService;
//
//	interface TestData {
//		String email = "customer@test.com";
//		String password = "!Qwertyui1";
//	}
//
//	String bearerToken;
//	Account account;
//
//	@BeforeEach
//	void init() {
//		this.bearerToken = authService.signInAccount(TestData.email, TestData.password).getAccessToken();
//		this.account = accountService.getAccount(bearerToken);
//	}
//
//	@Test
//	void testGetUuid() {
//		UUID expected = UUID.fromString("bf1fc118-4be9-4a7b-b7d3-75de228cc32b");
//		UUID actual = this.account.getUuid();
//		Assertions.assertEquals(expected, actual);
//	}
//
//	@Test
//	void testGetEmail() {
//		String expected = "customer@test.com";
//		String actual = this.account.getEmail();
//		Assertions.assertEquals(expected, actual);
//	}
//
//	@Test
//	void testGetRole() {
//		ERole expected = ERole.CUSTOMER;
//		ERole actual = this.account.getRole();
//		Assertions.assertEquals(expected, actual);
//	}
//
//	@Test
//	void testGetFirstname() {
//		String expected = "Jeff";
//		String actual = this.account.getFirstname();
//		Assertions.assertEquals(expected, actual);
//	}
//
//	@Test
//	void testGetLastname() {
//		String expected = "Bezos";
//		String actual = this.account.getLastname();
//		Assertions.assertEquals(expected, actual);
//	}
//
//	@Test
//	void testGetAddress() {
//		String expected = "USA";
//		String actual = this.account.getAddress();
//		Assertions.assertEquals(expected, actual);
//	}
//
//	@Test
//	void testGetPhone() {
//		String expected = "0123456789";
//		String actual = this.account.getPhone();
//		Assertions.assertEquals(expected, actual);
//	}
//
//	@Test
//	void testGetPicture() {
//		String expected = "https://images.alphanah.com/defaultProfilePicture.jpg";
//		String actual = this.account.getPicture();
//		Assertions.assertEquals(expected, actual);
//	}
//
//	@Test
//	void testGetCartUuid() {
//		UUID expected = null;
//		UUID actual = this.account.getCartUuid();
//		Assertions.assertEquals(expected, actual);
//	}
//
//}
