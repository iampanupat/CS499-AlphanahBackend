//package com.alphanah.alphanahbackend.old;
//
//import com.alphanah.alphanahbackend.entity.Account;
//import com.alphanah.alphanahbackend.service.AccountService;
//import com.alphanah.alphanahbackend.utility.JWTUtils;
//import com.alphanah.alphanahbackend.utility.PhoneUtils;
//import com.amazonaws.AmazonServiceException;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Objects;
//import java.util.UUID;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.Random.class)
//class AccountServiceUnitTest {
//
//	final String FIRST_ACTIVE_TOKEN = "eyJraWQiOiJiZzFcL2p5SFFTbTdwa0M4N2pVeFZnZjBOUGtTN09xVUJZekkxcFhWY1NvWT0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJjODU4ODYxMC1mZTA0LTQyZmUtYWI4MS1lNmQxZWIwNTIzYTMiLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAuYXAtc291dGhlYXN0LTEuYW1hem9uYXdzLmNvbVwvYXAtc291dGhlYXN0LTFfc0VyQ2N3RlZWIiwiY2xpZW50X2lkIjoiN2hvcGpkYWQ5b2hqNm4xdjloOTNjNGVvM2MiLCJvcmlnaW5fanRpIjoiMDdhOTM2ZDgtZGVmOS00OTMwLWJiNzYtNmZlOTI2N2QyZGU2IiwiZXZlbnRfaWQiOiI3YTQyOGU4Mi01Y2U0LTRkZmQtODYxZC0xYTAyNGFkYzNlMDMiLCJ0b2tlbl91c2UiOiJhY2Nlc3MiLCJzY29wZSI6ImF3cy5jb2duaXRvLnNpZ25pbi51c2VyLmFkbWluIiwiYXV0aF90aW1lIjoxNjc0OTQ2NDEwLCJleHAiOjE2NzQ5NTAwMTAsImlhdCI6MTY3NDk0NjQxMCwianRpIjoiNTRjNjUyNDMtODMwMi00N2M5LTk2MDUtZWY0OTRmMGI1Yzg0IiwidXNlcm5hbWUiOiJjODU4ODYxMC1mZTA0LTQyZmUtYWI4MS1lNmQxZWIwNTIzYTMifQ.Aen7MOi8rcY2IH0oZwM189xhOMPyd1odLppKt8SxRQWpjZk34Oa4QbO7CQbbc0Aui6CAyXmcIr-Wxtzel9KdvZLyvcOxhc-84wwK4ZAlhkfUiyapfXEcLTH6dN3XLXSjEPGx6NWVyb3Hef8nsVlNNwxmlhseJJIzHShoiINJ1JkxUMBo9wgFSvYZc7LeF6oGMY3tVbFzwjz-qNzCmSxcxiw-OHvYVXsrxlKgtiVjMm3uML4sVgXrWUA-TtyCDwyofPRiMFNfvQWxHjYO6oT-ZWD-32PzbvwE_0dZXhCssSpCn54P_uYFuD77_07kn4jaf6ZG4DX3AavworTdvTBIDw";
//	final String SECOND_ACTIVE_TOKEN = "eyJraWQiOiJiZzFcL2p5SFFTbTdwa0M4N2pVeFZnZjBOUGtTN09xVUJZekkxcFhWY1NvWT0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxNDJhZGMyNi0zNGRiLTRhMmEtOTIyOC01N2E2MzBiZmMxNmQiLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAuYXAtc291dGhlYXN0LTEuYW1hem9uYXdzLmNvbVwvYXAtc291dGhlYXN0LTFfc0VyQ2N3RlZWIiwiY2xpZW50X2lkIjoiN2hvcGpkYWQ5b2hqNm4xdjloOTNjNGVvM2MiLCJvcmlnaW5fanRpIjoiYjA0NzQ0MDItNDIxNS00MzliLThlNDctMTE3ZTY0ODhkODdmIiwiZXZlbnRfaWQiOiI0OWU3YTQyNy03YTFjLTQ3MDgtOGFhYy02ZGM5MmJlNWY5ZjUiLCJ0b2tlbl91c2UiOiJhY2Nlc3MiLCJzY29wZSI6ImF3cy5jb2duaXRvLnNpZ25pbi51c2VyLmFkbWluIiwiYXV0aF90aW1lIjoxNjc0OTQ2NDQ4LCJleHAiOjE2NzQ5NTAwNDgsImlhdCI6MTY3NDk0NjQ0OCwianRpIjoiMzI1YTNmNjItYzM3ZC00ODAzLWFiMjEtYmZiYjg5NTg4YWExIiwidXNlcm5hbWUiOiIxNDJhZGMyNi0zNGRiLTRhMmEtOTIyOC01N2E2MzBiZmMxNmQifQ.B5PxRMgnSLQ5PTB-2cBdgHMS675I_CNkmGR9ZbXq24XgJbeK9CG36PShKeroDohtABdIFGjc-3EUJdP7OvQ0kDDSuvH9KEhg1H4aUDdGXWkEQL9Q0NEVvlD1UuxBA6-PozpB2dPwbfvZoB5OyP_VXg6p8fHWPUdRMc8Z2e1wnl9xrmJWlLOGvNsHSkxLtKGkQ2a5vVL5IHMAW6FFTuC2Ou_tlTArhTWiYhP7pBFyGkqJm-w7hNYthHvcr2fQYCSxuddUw2GTNmo7ssq073rSnBZqYFlG8-nLow98S3hDWv66MFNW3NYlbJhg3HJk7UyAPZOg44kg_OuU1lwsH5vMRA";
//	final String EXPIRED_TOKEN = "eyJraWQiOiJiZzFcL2p5SFFTbTdwa0M4N2pVeFZnZjBOUGtTN09xVUJZekkxcFhWY1NvWT0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI0OWI3OTQ5ZC1iZDVmLTQxMTUtYmE5Yi0yNzY1NjI2MGM3MDAiLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAuYXAtc291dGhlYXN0LTEuYW1hem9uYXdzLmNvbVwvYXAtc291dGhlYXN0LTFfc0VyQ2N3RlZWIiwiY2xpZW50X2lkIjoiN2hvcGpkYWQ5b2hqNm4xdjloOTNjNGVvM2MiLCJvcmlnaW5fanRpIjoiNjc2NmMyYzEtYTUzMi00NWRmLTliYjQtMWJmZGU5ZWVjZTFiIiwiZXZlbnRfaWQiOiI3OWIxZDdiYS0wYTgzLTQ0NDItYjJlOC01NWZlODdiNDhjODQiLCJ0b2tlbl91c2UiOiJhY2Nlc3MiLCJzY29wZSI6ImF3cy5jb2duaXRvLnNpZ25pbi51c2VyLmFkbWluIiwiYXV0aF90aW1lIjoxNjc0OTA0Mjc5LCJleHAiOjE2NzQ5MDc4NzksImlhdCI6MTY3NDkwNDI3OSwianRpIjoiOWFiMTdlOTEtYzUyYi00ZTBiLWFjNmQtMDVmN2U0ZmE0YjFiIiwidXNlcm5hbWUiOiI0OWI3OTQ5ZC1iZDVmLTQxMTUtYmE5Yi0yNzY1NjI2MGM3MDAifQ.ZMP4HlI3es5CXUQOIAiImAYZ_ezz0gedu6HkiQ_sS2SGjGHKc2gomGFKQaOPc-1l97AtstcWWeFlWx-iJJFseIqxyHGD1G4SMaL7b-AMGTy6GZxCXyQoNNjEcUPf1AgoLrIDV-ERNgujWKxB45JAEvINX3fnfK58fqMC9lvkMZWAHZBGFtzgYKBYibTRo3T-HoS0NOFuzLucjj8n0xraCTDkDyBgqNZww_60_xuNPXk9QTeri87u8ljtAtQtBU55fV2PUjlUJXpOlmO_F7jgot4OIf7JQBKNLiCHDxTvCuU1lBFjeT9C8v6fNFgc_SUKEHMM_bw4FWwUE1JS_EI03A";
//	final String INVALID_TOKEN = "THIS_IS_NOT_VALID_TOKEN";
//
//	@Autowired
//	AccountService accountService;
//
//	@BeforeEach
//	void beforeEach() {
//		if (!JWTUtils.isValid(FIRST_ACTIVE_TOKEN))
//			throw new RuntimeException("FIRST_ACTIVE_TOKEN is not a valid token.");
//		if (JWTUtils.isExpired(FIRST_ACTIVE_TOKEN))
//			throw new RuntimeException("FIRST_ACTIVE_TOKEN is expired.");
//		if (!JWTUtils.isValid(SECOND_ACTIVE_TOKEN))
//			throw new RuntimeException("SECOND_ACTIVE_TOKEN is not a valid token.");
//		if (JWTUtils.isExpired(SECOND_ACTIVE_TOKEN))
//			throw new RuntimeException("SECOND_ACTIVE_TOKEN is expired.");
//		Account account_1 = accountService.getAccount(FIRST_ACTIVE_TOKEN);
//		Account account_2 = accountService.getAccount(SECOND_ACTIVE_TOKEN);
//		if (account_1.getUuid().equals(account_2.getUuid()))
//			throw new RuntimeException("FIRST_ACTIVE_TOKEN and SECOND_ACTIVE_TOKEN must taken from different accounts.");
//		if (!JWTUtils.isValid(EXPIRED_TOKEN))
//			throw new RuntimeException("EXPIRED_TOKEN is not a valid token.");
//		if (!JWTUtils.isExpired(EXPIRED_TOKEN))
//			throw new RuntimeException("EXPIRED_TOKEN must be expired.");
//		if (JWTUtils.isValid(INVALID_TOKEN))
//			throw new RuntimeException("INVALID_TOKEN must be invalid token.");
//	}
//
//	@Test
//	void testGetAccountValidToken() {
//		Account account = accountService.getAccount(FIRST_ACTIVE_TOKEN);
//		Assertions.assertNotNull(account);
//	}
//
//	@Test
//	void testGetAccountExpiredValidToken() {
//		Account account = accountService.getAccount(EXPIRED_TOKEN);
//		Assertions.assertNull(account);
//	}
//
//	@Test
//	void testGetAccountInvalidToken() {
//		Account account = accountService.getAccount(INVALID_TOKEN);
//		Assertions.assertNull(account);
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateUuid() {
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount(FIRST_ACTIVE_TOKEN, "sub", UUID.randomUUID().toString());
//		});
//		Assertions.assertEquals("Cannot modify the non-mutable attribute sub", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("InvalidParameterException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateEmptyUuid() {
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount(FIRST_ACTIVE_TOKEN, "sub", "");
//		});
//		Assertions.assertEquals("Cannot modify the non-mutable attribute sub", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("InvalidParameterException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateNullUuid() {
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount(FIRST_ACTIVE_TOKEN, "sub", null);
//		});
//		Assertions.assertEquals("Cannot modify the non-mutable attribute sub", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("InvalidParameterException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//	@Disabled
//	@Test
//	void testUpdateAccountValidToken_updateValidEmail() {
//		String randomValue = RandomStringUtils.randomAlphabetic(100) + "@email.com";
//		String originalEmail = accountService.getAccount(FIRST_ACTIVE_TOKEN).getEmail();
//		String updatedEmail = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "email", randomValue).getEmail();
//		Assertions.assertNotEquals(originalEmail, updatedEmail);
//		Assertions.assertEquals(randomValue, updatedEmail);
//		String resetEmail = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "email", Objects.isNull(originalEmail) ? "" : originalEmail).getEmail();
//		Assertions.assertEquals(originalEmail, resetEmail);
//	}
//
//	@Disabled
//	@Test
//	void testUpdateAccountValidToken_updateValidEmailDuplicate() {
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount(FIRST_ACTIVE_TOKEN, "email", accountService.getAccount(SECOND_ACTIVE_TOKEN).getEmail());
//		});
//		Assertions.assertEquals("An account with the given email already exists.", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("AliasExistsException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateInvalidEmail() {
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount(FIRST_ACTIVE_TOKEN, "email", RandomStringUtils.randomAlphabetic(100));
//		});
//		Assertions.assertEquals("Invalid email address format.", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("InvalidParameterException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//	@Disabled
//	@Test
//	void testUpdateAccountValidToken_updateEmptyEmail() {
//		String originalEmail = accountService.getAccount(FIRST_ACTIVE_TOKEN).getEmail();
//		String updatedEmail = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "email", "").getEmail();
//		Assertions.assertNull(updatedEmail);
//		String resetEmail = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "email", Objects.isNull(originalEmail) ? "" : originalEmail).getEmail();
//		Assertions.assertEquals(originalEmail, resetEmail);
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateNullEmail() {
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount(FIRST_ACTIVE_TOKEN, "email", null);
//		});
//		Assertions.assertEquals("Attribute value for email must not be null", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("InvalidParameterException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateFirstname() {
//		String randomValue = RandomStringUtils.randomAlphabetic(21);
//		String originalFirstname = accountService.getAccount(FIRST_ACTIVE_TOKEN).getFirstname();
//		String updatedFirstname = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "name", randomValue).getFirstname();
//		Assertions.assertNotEquals(originalFirstname, updatedFirstname);
//		Assertions.assertEquals(randomValue, updatedFirstname);
//		String resetFirstname = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "name", Objects.isNull(originalFirstname) ? "" : originalFirstname).getFirstname();
//		Assertions.assertEquals(originalFirstname, resetFirstname);
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateEmptyFirstname() {
//		String originalFirstname = accountService.getAccount(FIRST_ACTIVE_TOKEN).getFirstname();
//		String updatedFirstname = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "name", "").getFirstname();
//		Assertions.assertNull(updatedFirstname);
//		String resetFirstname = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "name", Objects.isNull(originalFirstname) ? "" : originalFirstname).getFirstname();
//		Assertions.assertEquals(originalFirstname, resetFirstname);
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateNullFirstname() {
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount(FIRST_ACTIVE_TOKEN, "name", null);
//		});
//		Assertions.assertEquals("Attribute value for name must not be null", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("InvalidParameterException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateLastname() {
//		String randomValue = RandomStringUtils.randomAlphabetic(21);
//		String originalLastname = accountService.getAccount(FIRST_ACTIVE_TOKEN).getLastname();
//		String updatedLastname = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "family_name", randomValue).getLastname();
//		Assertions.assertNotEquals(originalLastname, updatedLastname);
//		Assertions.assertEquals(randomValue, updatedLastname);
//		String resetLastname = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "family_name", Objects.isNull(originalLastname) ? "" : originalLastname).getLastname();
//		Assertions.assertEquals(originalLastname, resetLastname);
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateEmptyLastname() {
//		String originalLastname = accountService.getAccount(FIRST_ACTIVE_TOKEN).getLastname();
//		String updatedLastname = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "family_name", "").getLastname();
//		Assertions.assertNull(updatedLastname);
//		String resetLastname = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "family_name", Objects.isNull(originalLastname) ? "" : originalLastname).getLastname();
//		Assertions.assertEquals(originalLastname, resetLastname);
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateNullLastname() {
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount(FIRST_ACTIVE_TOKEN, "family_name", null);
//		});
//		Assertions.assertEquals("Attribute value for family_name must not be null", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("InvalidParameterException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateAddress() {
//		String randomValue = RandomStringUtils.randomAlphabetic(100);
//		String originalAddress = accountService.getAccount(FIRST_ACTIVE_TOKEN).getAddress();
//		String updatedAddress = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "address", randomValue).getAddress();
//		Assertions.assertNotEquals(originalAddress, updatedAddress);
//		Assertions.assertEquals(randomValue, updatedAddress);
//		String resetAddress = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "address", Objects.isNull(originalAddress) ? "" : originalAddress).getAddress();
//		Assertions.assertEquals(originalAddress, resetAddress);
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateEmptyAddress() {
//		String originalAddress = accountService.getAccount(FIRST_ACTIVE_TOKEN).getAddress();
//		String updatedAddress = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "address", "").getAddress();
//		Assertions.assertNull(updatedAddress);
//		String resetAddress = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "address", Objects.isNull(originalAddress) ? "" : originalAddress).getAddress();
//		Assertions.assertEquals(originalAddress, resetAddress);
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateNullAddress() {
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount(FIRST_ACTIVE_TOKEN, "address", null);
//		});
//		Assertions.assertEquals("Attribute value for address must not be null", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("InvalidParameterException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateValidThaiPhone() {
//		String randomValue = PhoneUtils.addThaiAreaCode(RandomStringUtils.randomNumeric(10));
//		String originalPhone = accountService.getAccount(FIRST_ACTIVE_TOKEN).getPhone();
//		String updatedPhone = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "phone_number", randomValue).getPhone();
//		Assertions.assertNotEquals(originalPhone, updatedPhone);
//		Assertions.assertEquals(PhoneUtils.removeThaiAreaCode(randomValue), updatedPhone);
//		String resetPhone = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "phone_number", Objects.isNull(originalPhone) ? "" : PhoneUtils.addThaiAreaCode(originalPhone)).getPhone();
//		Assertions.assertEquals(originalPhone, resetPhone);
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateValidOtherPhone() {
//		String randomValue = "+111" + RandomStringUtils.randomNumeric(10);
//		String originalPhone = accountService.getAccount(FIRST_ACTIVE_TOKEN).getPhone();
//		String updatedPhone = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "phone_number", randomValue).getPhone();
//		Assertions.assertNotEquals(originalPhone, updatedPhone);
//		Assertions.assertEquals(randomValue, updatedPhone);
//		String resetPhone = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "phone_number", Objects.isNull(originalPhone) ? "" : originalPhone).getPhone();
//		Assertions.assertEquals(originalPhone, resetPhone);
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateInvalidPhone() {
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount(FIRST_ACTIVE_TOKEN, "phone_number", RandomStringUtils.randomNumeric(10));
//		});
//		Assertions.assertEquals("Invalid phone number format.", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("InvalidParameterException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateEmptyPhone() {
//		String originalPhone = accountService.getAccount(FIRST_ACTIVE_TOKEN).getPhone();
//		String updatedPhone = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "phone_number", "").getPhone();
//		Assertions.assertNull(updatedPhone);
//		String resetPhone = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "phone_number", Objects.isNull(originalPhone) ? "" : originalPhone).getPhone();
//		Assertions.assertEquals(originalPhone, resetPhone);
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateNullPhone() {
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount(FIRST_ACTIVE_TOKEN, "phone_number", null);
//		});
//		Assertions.assertEquals("Attribute value for phone_number must not be null", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("InvalidParameterException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updatePicture() {
//		String randomValue = "https://" + RandomStringUtils.randomAlphabetic(20) + ".com/" + RandomStringUtils.randomAlphabetic(100);
//		String originalImage = accountService.getAccount(FIRST_ACTIVE_TOKEN).getPicture();
//		String updatedImage = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "picture", randomValue).getPicture();
//		Assertions.assertNotEquals(originalImage, updatedImage);
//		Assertions.assertEquals(randomValue, updatedImage);
//		String resetImage = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "picture", Objects.equals(originalImage, "https://images.alphanah.com/defaultProfilePicture.jpg") ? "" : originalImage).getPicture();
//		Assertions.assertEquals(originalImage, resetImage);
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateEmptyPicture() {
//		String originalImage = accountService.getAccount(FIRST_ACTIVE_TOKEN).getPicture();
//		String updatedImage = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "picture", "").getPicture();
//		Assertions.assertEquals("https://images.alphanah.com/defaultProfilePicture.jpg", updatedImage);
//		String resetImage = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "picture", Objects.equals(originalImage, "https://images.alphanah.com/defaultProfilePicture.jpg") ? "" : originalImage).getPicture();
//		Assertions.assertEquals(originalImage, resetImage);
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateNullPicture() {
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount(FIRST_ACTIVE_TOKEN, "picture", null);
//		});
//		Assertions.assertEquals("Attribute value for picture must not be null", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("InvalidParameterException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateValidCartUuid() {
//		UUID randomValue = UUID.randomUUID();
//		UUID originalCartUuid = accountService.getAccount(FIRST_ACTIVE_TOKEN).getCartUuid();
//		UUID updatedCartUuid = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "custom:cart_uuid", randomValue.toString()).getCartUuid();
//		Assertions.assertNotEquals(originalCartUuid, updatedCartUuid);
//		Assertions.assertEquals(randomValue, updatedCartUuid);
//		UUID resetCartUuid = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "custom:cart_uuid", Objects.isNull(originalCartUuid) ? "" : originalCartUuid.toString()).getCartUuid();
//		Assertions.assertEquals(originalCartUuid, resetCartUuid);
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateInvalidCartUuid() {
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount(FIRST_ACTIVE_TOKEN, "custom:cart_uuid", "INVALID_CART_UUID");
//		});
//		Assertions.assertEquals("user.custom:cart_uuid: String must be no shorter than 36 characters\n", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("InvalidParameterException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateEmptyCartUuid() {
//		UUID originalCartUuid = accountService.getAccount(FIRST_ACTIVE_TOKEN).getCartUuid();
//		UUID updatedCartUuid = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "custom:cart_uuid", "").getCartUuid();
//		Assertions.assertNull(updatedCartUuid);
//		UUID resetCartUuid = accountService.updateAccount(FIRST_ACTIVE_TOKEN, "custom:cart_uuid", Objects.isNull(originalCartUuid) ? "" : originalCartUuid.toString()).getCartUuid();
//		Assertions.assertEquals(originalCartUuid, resetCartUuid);
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateNullCartUuid() {
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount(FIRST_ACTIVE_TOKEN, "custom:cart_uuid", null);
//		});
//		Assertions.assertEquals("Attribute value for custom:cart_uuid must not be null", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("InvalidParameterException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateRole() {
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount(FIRST_ACTIVE_TOKEN, "custom:role", "customer");
//		});
//		Assertions.assertEquals("user.custom:role: Attribute cannot be updated.\n", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("InvalidParameterException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateEmptyRole() {
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount(FIRST_ACTIVE_TOKEN, "custom:role", "");
//		});
//		Assertions.assertEquals("user.custom:role: Attribute cannot be updated.\n", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("InvalidParameterException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateNullRole() {
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount(FIRST_ACTIVE_TOKEN, "custom:role", null);
//		});
//		Assertions.assertEquals("Attribute value for custom:role must not be null", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("InvalidParameterException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateInvalidCognitoFieldName() {
//		String randomValue = RandomStringUtils.randomAlphabetic(20);
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount(FIRST_ACTIVE_TOKEN, randomValue, "INVALID_COGNITO_FIELD_NAME");
//		});
//		Assertions.assertEquals("user." + randomValue + ": Attribute does not exist in the schema.\n", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("InvalidParameterException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateEmptyCognitoFieldName() {
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount(FIRST_ACTIVE_TOKEN, "", "EMPTY_COGNITO_FIELD_NAME");
//		});
//		Assertions.assertEquals("2 validation errors detected: Value '' at 'userAttributes.1.member.name' failed to satisfy constraint: Member must have length greater than or equal to 1; Value '' at 'userAttributes.1.member.name' failed to satisfy constraint: Member must satisfy regular expression pattern: [\\p{L}\\p{M}\\p{S}\\p{N}\\p{P}]+", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("InvalidParameterException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//	@Test
//	void testUpdateAccountValidToken_updateNullCognitoFieldName() {
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount(FIRST_ACTIVE_TOKEN, null, "EMPTY_COGNITO_FIELD_NAME");
//		});
//		Assertions.assertEquals("1 validation error detected: Value null at 'userAttributes.1.member.name' failed to satisfy constraint: Member must not be null", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("InvalidParameterException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//	@Test
//	void testUpdateAccountExpiredValidToken() {
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount(EXPIRED_TOKEN, "EXPIRED_TOKEN_1", "EXPIRED_TOKEN_2");
//		});
//		Assertions.assertEquals("Access Token has expired", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("NotAuthorizedException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//	@Test
//	void testUpdateAccountInvalidToken() {
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount(INVALID_TOKEN, "INVALID_TOKEN_1", "INVALID_TOKEN_2");
//		});
//		Assertions.assertEquals("Invalid Access Token", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("NotAuthorizedException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//	@Test
//	void testUpdateAccountEmptyToken() {
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount("", "NULL_TOKEN_1", "NULL_TOKEN_2");
//		});
//		Assertions.assertEquals("1 validation error detected: Value at 'accessToken' failed to satisfy constraint: Member must satisfy regular expression pattern: [A-Za-z0-9-_=.]+", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("InvalidParameterException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//	@Test
//	void testUpdateAccountNullToken() {
//		AmazonServiceException exception = Assertions.assertThrows(AmazonServiceException.class, () -> {
//			accountService.updateAccount(null, "NULL_TOKEN_1", "NULL_TOKEN_2");
//		});
//		Assertions.assertEquals("1 validation error detected: Value at 'accessToken' failed to satisfy constraint: Member must not be null", exception.getErrorMessage());
//		Assertions.assertEquals("AWSCognitoIdentityProvider", exception.getServiceName());
//		Assertions.assertEquals(400, exception.getStatusCode());
//		Assertions.assertEquals("InvalidParameterException", exception.getErrorCode());
//		Assertions.assertEquals("Client", exception.getErrorType().toString());
//	}
//
//}