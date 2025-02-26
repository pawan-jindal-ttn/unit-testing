package com.demo.service;

import com.demo.domain.Order;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.internal.matchers.Or;

import static org.junit.Assert.*;

public class OrderServiceTest {

    static OrderService orderService;
    static Order order2;

    @BeforeClass
    public static void setup(){
        order2 = new Order(10, "Rasgulla", 30.0);
        orderService = new OrderService();
    }

    // Object Creation Tests
    @Test
    public void testGetInstance_FirstTimeNotNull(){
        //ensuring that instance is not null
        assertNotNull(OrderService.getInstance());
    }

    @Test
    public void testGetInstance_ComparingTwoGetInstances(){
        // ensuring any number of getInstance() invokes do not change the instance object
        OrderService getInstanceOrderService = OrderService.getInstance();
        assertEquals(getInstanceOrderService, OrderService.getInstance());
    }

    @Test
    public void testGetInstance_ComparingGetInstanceWithConstructorCalling(){
        // objects made through constructor and getInstance should be different
        assertNotEquals(orderService, OrderService.getInstance());
    }

    // Testing PlaceOrder methods
    @Test(expected = RuntimeException.class)
    public void testVoidPlaceOrder_ThrowsRuntimeException(){
        // placeOrder(Order o) can throw Runtime exception due to emailService.sendEmail(order);
        orderService.placeOrder(order2);
    }

    @Test
    public void testBooleanPlaceOrder_IsCustomerNotified(){
        // after placing the order the customer must be notified
        orderService.placeOrder(order2,"Thank you for the order" );
        assertTrue(order2.isCustomerNotified());
    }

    @Test
    public void testBooleanPlaceOrder_UpdatedPriceWithTax(){
        // priceWithTax cannot be 0 after placing an order
        orderService.placeOrder(order2,"Thank you for the order");
        assertNotEquals(0, order2.getPriceWithTax());
    }

    @Test
    public void testBooleanPlaceOrder_ReturnTrue() {
        //Checking the returned value of isNotified from sendEmail(Order o, String CC)
        assertTrue(orderService.placeOrder(order2, "Thanks for the order"));
    }
}