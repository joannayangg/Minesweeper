import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;

/** 
 *  You can use this file (and others) to test your
 *  implementation.
 */

public class GameTest {
//TEST CASES
    
    @Test
    public void clickBombEnds() {
        MineSweeper t = new MineSweeper();
        int x = 0;
        int y = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (t.getBomb(i, j) == 10) {
                    x = i;
                    y = j;
                }
            }
        }
        assertEquals(t.getBomb(x, y), 10);
        t.playTurn(y, x);
        assertTrue(t.getGameOver());
        //x and y have the coordinates of a bomb
        
    }

    @Test
    public void clickNumberDoesntEnd() {
        MineSweeper t = new MineSweeper();
        int x = 0;
        int y = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (t.getBomb(i, j) != 10) {
                    x = i;
                    y = j;
                }
            }
        }
        assertNotEquals(t.getBomb(x, y), 10);
        t.playTurn(y, x);
        assertFalse(t.getGameOver());
    }
    
    @Test
    public void clickZeroDoesntEnd() {
        MineSweeper t = new MineSweeper();
        int x = 0;
        int y = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (t.getBomb(i, j) == 0) {
                    x = i;
                    y = j;
                }
            }
        }
        assertEquals(t.getBomb(x, y), 0);
        t.playTurn(y, x);
        assertFalse(t.getGameOver());
    }
    
    @Test
    public void clickZeroOpensUpSurrounding() {
        MineSweeper t = new MineSweeper();
        int x = 0;
        int y = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (t.getBomb(i, j) == 0) {
                    x = i;
                    y = j;
                }
            }
        }
        assertEquals(t.getBomb(x, y), 0);
        t.playTurn(y, x);
        assertEquals((t.getAllMoves()).size(), 1);
        assertTrue((t.getLastMove()).size() > 1);
        
    }
    
    @Test
    public void clickUndoWorks() {
        MineSweeper t = new MineSweeper();
        int x = 0;
        int y = 0;
        int x1 = 0;
        int y2 = 0;
        boolean tr = true;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (t.getBomb(i, j) != 0 & t.getBomb(i, j) != 10) {
                    x = i;
                    y = j;
                    if (tr) {
                        x1 = i;
                        y2 = j;
                        tr = false;
                    }
                }
            }
        }
        
        t.playTurn(y, x);
        t.undo();
        assertEquals((t.getAllMoves()).size(), 0);
        t.playTurn(y, x);
        t.playTurn(y2, x1);
        t.undo();
        assertEquals((t.getAllMoves()).size(), 1);
        assertEquals((t.getLastMove()).size(), 1);
        Point pt = new Point(x, y);
        ArrayList<Point> onept = new ArrayList<Point>();
        onept.add(pt);
        assertEquals((t.getLastMove()), onept);
        
    }
    
    @Test
    public void clickResetWorks() {
        MineSweeper t = new MineSweeper();
        int x = 0;
        int y = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (t.getBomb(i, j) == 0) {
                    x = i;
                    y = j;
                }
            }
        }
        assertEquals(t.getBomb(x, y), 0);
        t.playTurn(y, x);
        t.reset();
        assertEquals((t.getAllMoves()).size(), 0);
    }
    
    @Test
    public void clickUndoAfterEndGameWorks() {
        MineSweeper t = new MineSweeper();
        int x = 0;
        int y = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (t.getBomb(i, j) == 10) {
                    x = i;
                    y = j;
                }
            }
        }
        assertEquals(t.getBomb(x, y), 10);
        t.playTurn(y, x);
        assertTrue(t.getGameOver());
        t.undo();
        assertEquals((t.getAllMoves()).size(), 0);
        assertFalse(t.getGameOver());
    }
    
    @Test
    public void clickResetAfterEndGameWorks() {
        MineSweeper t = new MineSweeper();
        int x = 0;
        int y = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (t.getBomb(i, j) == 10) {
                    x = i;
                    y = j;
                }
            }
        }
        assertEquals(t.getBomb(x, y), 10);
        t.playTurn(y, x);
        assertTrue(t.getGameOver());
        t.reset();
        assertEquals((t.getAllMoves()).size(), 0);
        assertFalse(t.getGameOver());
    }

    @Test
    public void flagWorks() {
        MineSweeper t = new MineSweeper();
        int x = 0;
        int y = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (t.getBomb(i, j) == 10) {
                    x = i;
                    y = j;
                }
            }
        }
        assertEquals(t.getBomb(x, y), 10);
        t.changeFlag(x, y);
        assertTrue(t.getFlag(x, y));
        t.playTurn(y, x);
        assertFalse(t.getGameOver());
        t.changeFlag(x, y);
        assertFalse(t.getGameOver());
    }
    
    @Test
    public void undoWithoutMoves() {
        MineSweeper t = new MineSweeper();
        assertEquals(t.getAllMoves().size(), 0);
        t.undo();
        assertFalse(t.getGameOver());
        assertEquals(t.getAllMoves().size(), 0);
    }
    
    @Test
    public void clickAfterGameEnds() {
        MineSweeper t = new MineSweeper();
        int x = 0;
        int y = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (t.getBomb(i, j) == 10) {
                    x = i;
                    y = j;
                }
            }
        }
        assertEquals(t.getBomb(x, y), 10);
        t.playTurn(y, x);
        assertTrue(t.getGameOver());
        int x1 = 0;
        int y1 = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (t.getBomb(i, j) == 0) {
                    x1 = i;
                    y1 = j;
                }
            }
        }
        assertEquals(t.getBomb(x1, y1), 0);
        t.playTurn(y1, x1);
        assertEquals(t.getAllMoves().size(), 1);
        
    }
    
    @Test
    public void border() {
        MineSweeper t = new MineSweeper();
        t.playTurn(0, 0);
        assertEquals(t.getAllMoves().size(), 1);
        
    }

}
