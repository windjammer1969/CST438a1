/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csumb.cst438.a1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import com.sun.net.httpserver.Headers;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Assert;


/**
 *
 * @author david wisneski
 * @version 1.0
 * last update 3-21-2017
 */

public class MyHttpServerTest {
    
    public MyHttpServerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class MyHttpServer.
     */
    @Test
    public void testHandle() {
        Game game = new Game(); // Because code changed in MyHttpServer, need access to Game.java to accertain the random word
        String expectedBody = "<!DOCTYPE html><html><head><title>MyHttpServer</title></head><body><h2>Hangman</h2>"
            + "<img src=\"" + "h" + game.getState() + ".gif" + "\">"
            + "<h2 style=\"font-family:'Lucida Console', monospace\"> ";
          String expectedBody2 = "<form action=\"/\" method=\"get\"> "
           + "Guess a character <input type=\"text\" name=\"guess\" maxlength=1><br>"
           + "<input type=\"submit\" value=\"Submit\">" + "</form></body></html>";

    Headers header = new Headers();
    try {
        TestHttpExchange t = new TestHttpExchange("/", header);
        MyHttpServer.MyHandler handler = new MyHttpServer.MyHandler();
        handler.handle(t);
        // check response for cookie returned, response code=200, and expected response body 
        Headers response = t.getResponseHeaders();
        String cookie1 = response.getFirst("Set-cookie");
        assertEquals("Bad content type", "text/html", response.getFirst("Content-type"));
        assertNotNull("No cookie returned", cookie1);
        assertEquals("Bad response code.",200, t.getResponseCode());
        //Was initially set to equals, however this should only be triggered if expectedBody and t.getOsteam are NOT equal to one another.
        Assert.assertThat(t.getOstream().toString(),CoreMatchers.containsString(expectedBody));
        Assert.assertThat(t.getOstream().toString(),CoreMatchers.containsString(expectedBody2));
    } catch (Exception e) {
        fail("unexpected exception in testHandle "+e.getMessage());
    }
    
    Headers giffy1 = new Headers();
    try{
    TestHttpExchange t = new TestHttpExchange("/h1.gif", giffy1);
    MyHttpServer.MyHandler handler = new MyHttpServer.MyHandler();
    handler.handle(t);
    // check response for expect output
    Headers response = t.getResponseHeaders();
    assertEquals("Bad content type", "image/gif", response.getFirst("Content-type"));
    assertEquals("Bad response code.",200, t.getResponseCode());
    // check that length of response body is 8581 bytes. 
    assertEquals("Bad response length.","8581", response.getFirst("Content-length"));
    } catch (Exception e) {
        fail("unexpected exception in testHandle "+e.getMessage());
    }
    
        Headers giffy2 = new Headers();
    try{
    TestHttpExchange t = new TestHttpExchange("/h9.gif", giffy2);
    MyHttpServer.MyHandler handler = new MyHttpServer.MyHandler();
    handler.handle(t);
    // check response for expect output
    Headers response = t.getResponseHeaders();
    assertEquals("Bad content type", null, response.getFirst("Content-type"));
    } catch (Exception e) {
        fail("unexpected exception in testHandle "+e.getMessage());
    }
    
}
}