package com.csumb.cst438.a1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of Game class
 * @author david wisneski
 * @veraion 1.0
 */
public class GameTest {
    
    public GameTest() {
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
     * Test of getState method, of class Game.
     * at start of game, state should be 1.
     * a correct guess will not change the state
     * an incorrect guess will increase state by 1
     */
    @org.junit.Test
    public void testGetState() {
        System.out.println("getState");
        Game instance = new Game();
        int expResult = 1;
        int result = instance.getState();
        assertEquals(expResult, result);
        instance.playGame('c');
        result = instance.getState();
        assertEquals(expResult, result);
        instance.playGame('d');
        result = instance.getState();
        assertEquals(expResult+1, result);
    }

    /**
     * Test of getWord method, of class Game.
     */
    @org.junit.Test
    public void testGetWord() {
        System.out.println("getWord");
        Game instance = new Game();
        String expResult = "computer";
        String result = instance.getWord();
        System.out.println(expResult + " " + result);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDisplayWord method, of class Game.
     */
    @org.junit.Test
    public void testGetDisplayWord() {
        System.out.println("getDisplayWord");
        Game instance = new Game();
        String expResult = "_ _ _ _ _ _ _ _";
        String result = instance.getDisplayWord();
        assertEquals(expResult, result);
        instance.playGame('r');
        result = instance.getDisplayWord();
        assertEquals("_ _ _ _ _ _ _ r", result);

    }

    /**
     * Test of startNewGame method, of class Game.
     */
    @org.junit.Test
    public void testStartNewGame() {
        System.out.println("startNewGame");
        Game instance = new Game();
        instance.startNewGame();
        instance.playGame('c');
        instance.playGame('d');
        instance.startNewGame();
        int result = instance.getState();
        assertEquals(1,result);
 
    }

    /**
     * Test of playGame method, of class Game.
     *   correct guess should return 0 , or 1 when game is won
     *   incorrect guess should return 2, or 3 when game is lost
     */
    @org.junit.Test
    public void testPlayGame() {
        System.out.println("playGame");
        char guess = 'c';
        Game instance = new Game();
        int result = instance.playGame(guess);
        int expResult = 0;
        assertEquals(expResult, result);
        result = instance.playGame('4');
        assertEquals(4, result);
        result = instance.playGame('$');
        assertEquals(4, result);
        result = instance.playGame('&');
        assertEquals(4, result);
        
        instance.startNewGame();
        System.out.println(instance.word);
        for(int i = 0; i < 2; i++){
            if (i < instance.word.length()){
                result = instance.playGame(instance.word.charAt(i));
                System.out.println(instance.word);
                assertEquals(0,result);
            }
            else if (i == instance.word.length()){
                result = instance.playGame(instance.word.charAt(i));
                assertEquals(1,result);
            }
        }
        
        instance.startNewGame();
        for(int i = 0; i < 2;){
                char r = (char)('a'+ Math.random()* ('z'-'a'+1));
                int counter = 0;
                result = instance.playGame(r);
                for(int p=0; p < instance.word.length();p++){
                    if(instance.word.charAt(p) == r)
                        counter++;
                }
                if (counter == 0){
                    assertEquals(2, result);
                    i++;
                }
            }
    }
    
}
