package com.demo.service;

import com.demo.domain.Order;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmailServiceTest {

    static Order order1;
    static EmailService emailService;
    static EmailService getInstance;

    @BeforeClass
    public static void setup() {
        order1 = new Order(10, "Samosa", 15.0);
        emailService = new EmailService();
        getInstance = EmailService.getInstance();
    }

    @Test
    public void testGetInstance_FirstTimeNotNull() {
        //getInstance() must not return Null
        assertNotNull(getInstance);
    }

    @Test
    public void testGetInstance_ComparingTwoGetInstances() {
        //ensuring single object creation
        assertEquals(getInstance, EmailService.getInstance());
    }

    @Test
    public void testGetInstance_ComparingGetInstanceWithNewEmailServiceObject() {
        //ensuring that new object made using constructor is different
        //from object made using getInstance
        assertNotEquals(getInstance, emailService);
    }

    @Test(expected = RuntimeException.class)
    public void testSendEmail_ThrowsRuntimeException() {
        //It throws runtime exception
        emailService.sendEmail(order1);
    }

    @Test
    public void testSendEmailBoolean_ReturnTrue(){
        assertTrue(emailService.sendEmail(order1, "Thank you for your order"));
    }
}