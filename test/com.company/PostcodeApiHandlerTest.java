package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostcodeApiHandlerTest {

    @Test
    void validate() {
        PostcodeApiHandler paHandler = new PostcodeApiHandler();
        // Check a valid postcode is indeed valid
        assertTrue(paHandler.validate("KT246HX"), "KT246HX is valid");
        // Check the empty string is not valid (actually 404)
        assertFalse(paHandler.validate(""), "Empty is not valid");
        // Check an invalid postcode is indeed invalid
        assertFalse(paHandler.validate("E101"), "E101 is not valid");
    }

    @Test
    void getDetails() {
        PostcodeApiHandler paHandler = new PostcodeApiHandler();
        Postcode kt246hx = paHandler.getDetails("KT246HX");
        // Check correct country and region are returned
        assertEquals("England", kt246hx.getCountry());
        assertEquals("South East", kt246hx.getRegion());
    }

    @Test
    void nearest() {
        PostcodeApiHandler paHandler = new PostcodeApiHandler();
        List<String> nearest = paHandler.nearest("KT246HX");
        // Check the 3 nearest postcodes
        assertEquals("KT24 6HX", nearest.get(0));
        assertEquals("KT24 6JD", nearest.get(1));
        assertEquals("KT24 6JB", nearest.get(2));
    }
}