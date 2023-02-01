//package com.alphanah.alphanahbackend.old;
//
//import com.alphanah.alphanahbackend.utility.PhoneUtils;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class PhoneUtilsUnitTest {
//
//    private final String LOCAL_PHONE_NUMBER = "027777777";
//    private final String GLOBAL_PHONE_NUMBER = "+11127777777";
//
//    @Test
//    void testAddThaiAreaCode_localPhoneNumber() {
//        String globalPhoneNumber = PhoneUtils.addThaiAreaCode(LOCAL_PHONE_NUMBER);
//        Assertions.assertEquals("+66" + LOCAL_PHONE_NUMBER, globalPhoneNumber);
//    }
//
//    @Test
//    void testAddThaiAreaCode_globalPhoneNumber() {
//        String globalPhoneNumber = PhoneUtils.addThaiAreaCode(GLOBAL_PHONE_NUMBER);
//        Assertions.assertEquals(GLOBAL_PHONE_NUMBER, globalPhoneNumber);
//    }
//
//    @Test
//    void testAddThaiAreaCode_emptyPhoneNumber() {
//        String globalPhoneNumber = PhoneUtils.addThaiAreaCode("");
//        Assertions.assertNull(globalPhoneNumber);
//    }
//
//    @Test
//    void testAddThaiAreaCode_nullPhoneNumber() {
//        String globalPhoneNumber = PhoneUtils.addThaiAreaCode(null);
//        Assertions.assertNull(globalPhoneNumber);
//    }
//
////    @Test
////    void testRemoveThaiAreaCode_localPhoneNumber() {
////        String localPhoneNumber = PhoneUtils.removeThaiAreaCode(LOCAL_PHONE_NUMBER);
////        Assertions.assertEquals(LOCAL_PHONE_NUMBER, localPhoneNumber);
////    }
////
////    @Test
////    void testRemoveThaiAreaCode_globalPhoneNumber() {
////        String localPhoneNumber = PhoneUtils.removeThaiAreaCode(GLOBAL_PHONE_NUMBER);
////        Assertions.assertEquals(L);
////    }
//
//}
