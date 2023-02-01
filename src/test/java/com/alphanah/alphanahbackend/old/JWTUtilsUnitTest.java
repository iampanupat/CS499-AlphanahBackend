//package com.alphanah.alphanahbackend.old;
//
//import com.alphanah.alphanahbackend.utility.JWTUtils;
//import com.auth0.jwt.exceptions.JWTDecodeException;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class JWTUtilsUnitTest {
//
//    final String ACTIVE_TOKEN = "eyJraWQiOiJiZzFcL2p5SFFTbTdwa0M4N2pVeFZnZjBOUGtTN09xVUJZekkxcFhWY1NvWT0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxNDJhZGMyNi0zNGRiLTRhMmEtOTIyOC01N2E2MzBiZmMxNmQiLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAuYXAtc291dGhlYXN0LTEuYW1hem9uYXdzLmNvbVwvYXAtc291dGhlYXN0LTFfc0VyQ2N3RlZWIiwiY2xpZW50X2lkIjoiN2hvcGpkYWQ5b2hqNm4xdjloOTNjNGVvM2MiLCJvcmlnaW5fanRpIjoiOGE2N2VjZWYtZmQyNS00ZWFlLWJkMTAtODZmMDQwNTViNGU2IiwiZXZlbnRfaWQiOiIwNzBkZmIwNC1hNWZlLTRmMWQtODZjOS0xNmI3OGViMTY1ZmQiLCJ0b2tlbl91c2UiOiJhY2Nlc3MiLCJzY29wZSI6ImF3cy5jb2duaXRvLnNpZ25pbi51c2VyLmFkbWluIiwiYXV0aF90aW1lIjoxNjc0OTQ1NTg1LCJleHAiOjE2NzQ5NDkxODUsImlhdCI6MTY3NDk0NTU4NSwianRpIjoiZDBjOWM0ZjYtNjA2YS00YjdiLWEzNmMtODE5NjFiZjNmYTUzIiwidXNlcm5hbWUiOiIxNDJhZGMyNi0zNGRiLTRhMmEtOTIyOC01N2E2MzBiZmMxNmQifQ.j16ZqLkHE7IsVxezp8HqPJwFvOQEHrWJXW5HiXulCgpyFdxtkhWJD5hqhvJunETnf2g1yjH59k6xYIrQI8KBxsiGeVFH1CNMxGUvBemuGfSBsZ6rzTLzEJGivfkjR9daRyAh1Vdepi6fS_KOG74x03cdhoOTCLBqS7qTh1c6v3XOJk8NrGV0wN-3Wr4DfsX6peG3aA3SR49JAJHY13LfE1zvUPJoCEc5dPSsB0ja9OS4JN1DLLW9pwMHMegHRrBD5FxWhDijo2sUThvQZk9zSOJJGdYdhgOHzjlogKphPdL_aXSFX6Q3F5zYwvQVA12a1YKQKiswunZFr04OZkqBmA";
//    final String EXPIRED_TOKEN = "eyJraWQiOiJiZzFcL2p5SFFTbTdwa0M4N2pVeFZnZjBOUGtTN09xVUJZekkxcFhWY1NvWT0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI0OWI3OTQ5ZC1iZDVmLTQxMTUtYmE5Yi0yNzY1NjI2MGM3MDAiLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAuYXAtc291dGhlYXN0LTEuYW1hem9uYXdzLmNvbVwvYXAtc291dGhlYXN0LTFfc0VyQ2N3RlZWIiwiY2xpZW50X2lkIjoiN2hvcGpkYWQ5b2hqNm4xdjloOTNjNGVvM2MiLCJvcmlnaW5fanRpIjoiNjc2NmMyYzEtYTUzMi00NWRmLTliYjQtMWJmZGU5ZWVjZTFiIiwiZXZlbnRfaWQiOiI3OWIxZDdiYS0wYTgzLTQ0NDItYjJlOC01NWZlODdiNDhjODQiLCJ0b2tlbl91c2UiOiJhY2Nlc3MiLCJzY29wZSI6ImF3cy5jb2duaXRvLnNpZ25pbi51c2VyLmFkbWluIiwiYXV0aF90aW1lIjoxNjc0OTA0Mjc5LCJleHAiOjE2NzQ5MDc4NzksImlhdCI6MTY3NDkwNDI3OSwianRpIjoiOWFiMTdlOTEtYzUyYi00ZTBiLWFjNmQtMDVmN2U0ZmE0YjFiIiwidXNlcm5hbWUiOiI0OWI3OTQ5ZC1iZDVmLTQxMTUtYmE5Yi0yNzY1NjI2MGM3MDAifQ.ZMP4HlI3es5CXUQOIAiImAYZ_ezz0gedu6HkiQ_sS2SGjGHKc2gomGFKQaOPc-1l97AtstcWWeFlWx-iJJFseIqxyHGD1G4SMaL7b-AMGTy6GZxCXyQoNNjEcUPf1AgoLrIDV-ERNgujWKxB45JAEvINX3fnfK58fqMC9lvkMZWAHZBGFtzgYKBYibTRo3T-HoS0NOFuzLucjj8n0xraCTDkDyBgqNZww_60_xuNPXk9QTeri87u8ljtAtQtBU55fV2PUjlUJXpOlmO_F7jgot4OIf7JQBKNLiCHDxTvCuU1lBFjeT9C8v6fNFgc_SUKEHMM_bw4FWwUE1JS_EI03A";
//    final String INVALID_TOKEN = "THIS_IS_NOT_VALID_TOKEN";
//
//    @Test
//    void testRemoveBearer_validBearerToken() {
//        String token = JWTUtils.removeBearer("Bearer QWERTYUIOP");
//        Assertions.assertEquals("QWERTYUIOP", token);
//    }
//
//    @Test
//    void testRemoveBearer_invalidBearerToken() {
//        String token = JWTUtils.removeBearer("bearer QWERTYUIOP");
//        Assertions.assertEquals("bearer QWERTYUIOP", token);
//    }
//
//    @Test
//    void testRemoveBearer_emptyBearerToken() {
//        String token = JWTUtils.removeBearer("");
//        Assertions.assertEquals("", token);
//    }
//
//    @Test
//    void testRemoveBearer_nullBearerToken() {
//        String token = JWTUtils.removeBearer(null);
//        Assertions.assertNull(token);
//    }
//
//    @Test
//    void testIsValid_validToken() {
//        Assertions.assertTrue(JWTUtils.isValid(ACTIVE_TOKEN));
//        Assertions.assertTrue(JWTUtils.isValid(EXPIRED_TOKEN));
//    }
//
//    @Test
//    void testIsValid_invalidToken() {
//        Assertions.assertFalse(JWTUtils.isValid(INVALID_TOKEN));
//    }
//
//    @Test
//    void testIsExpired_expiredToken() {
//        Assertions.assertTrue(JWTUtils.isExpired(EXPIRED_TOKEN));
//    }
//
//    @Test
//    void testIsExpired_notExpiredToken() {
//        Assertions.assertFalse(JWTUtils.isExpired(ACTIVE_TOKEN));
//    }
//
//    @Test
//    void testIsExpired_invalidToken() {
//        JWTDecodeException exception = Assertions.assertThrows(JWTDecodeException.class, () -> {
//            JWTUtils.isExpired(INVALID_TOKEN);
//        });
//        Assertions.assertEquals("The token was expected to have 3 parts, but got 0.", exception.getMessage());
//    }
//
//}
