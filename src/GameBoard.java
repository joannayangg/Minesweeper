
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


@SuppressWarnings("serial")
public class GameBoard extends JPanel {

    private MineSweeper ttt; // model for the game
    private JLabel status; // current status text

    // Game constants
    public static final int BOARD_WIDTH = 720;
    public static final int BOARD_HEIGHT = 740;

    /**
     * Initializes the game board.
     */
    public GameBoard(JLabel statusInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);
        
        ttt = new MineSweeper(); // initializes model for the game
        status = statusInit; // initializes the status JLabel

        /*
         * Listens for mouseclicks.  Updates the model, then updates the game board
         * based off of the updated model.
         */
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!ttt.getGameOver()) {
                    Point p = e.getPoint();
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        //right click
                        if (p.x < 721 & p.y < 721) {
                            ttt.changeFlag(p.y / 45, p.x / 45);
                            updateStatus(); // updates the status JLabel
                            repaint();
                        }
                        
                    } else {
                        //left click
                        // updates the model given the coordinates of the mouseclick
                        if (p.x < 721 & p.y < 721) {
                            if (!ttt.getTileBoard(p.y / 45, p.x / 45).getFlag()) {
                                ttt.playTurn(p.x / 45, p.y / 45);
                                updateStatus(); // updates the status JLabel
                                repaint(); // repaints the game board
                                
                            }
                        }
                    }
                }
            }
        });
    }


    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        ttt.reset();
       // updateStatus();
        status.setText("Keep clickin'");
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }
    
    public void undo() {
        ttt.undo();
       // updateStatus();
        status.setText("Keep clickin'");
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    private void updateStatus() {
        int winner = ttt.checkWinner();
        if (winner == 1) {
            status.setText("YOU WIN!!!");
        } else if (winner == 2) {
            status.setText("You Lost!");
        } else if (winner == 0) {
            status.setText("Keep clickin'");
        }
        
    }
    
    
    
//Draws gameboard
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Number of Tiles Uncovered: " + Integer.toString(ttt.getNumMoves()), 300, 735);
     // Draws board grid
        for (int i = 1; i < 17; i++) {
            g.drawLine(45 * i, 0, 45 * i, 720);
            g.drawLine(0, 45 * i, 720, 45 * i);
        }
        
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (ttt.getFlag(i, j)) {
                    
                    int x = (45 * i) + 27;
                    int y = (45 * j) + 18;
                    g.drawString("F", y, x);
                } 
            }
        }

        ArrayList<ArrayList<Point>> moves = ttt.getAllMoves();
        for (ArrayList<Point> move : moves) {
            for (Point pt : move) {
                if (!ttt.getFlag(pt.x, pt.y)) {
                    int x = (45 * pt.x) + 27;
                    int y = (45 * pt.y) + 18;
                    g.drawString(Integer.toString(ttt.getBomb(pt.x,pt.y)), y, x);
                }
            }
        }
        
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}