
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Game implements Runnable {
    public void run() {
        
        
        // NOTE: the 'final' keyword denotes immutability even for local variables.
        final JFrame frame2 = new JFrame();
        //final JOptionPane instruc = new JOptionPane("Instructions");
        JOptionPane.showMessageDialog(frame2,"Instructions: In a 16x16 board, there are 40 bombs "
                + "(shown as a 10). \n Other tiles will "
                + "show a number between 0 to 8, showing the amount of bombs in its "
                + "immediate surroundings. \n Clicking on a 0 will "
                + "automatically show all consecutive 0s. Click on "
                + "all tiles except for the 40 bombs to win! \n Click on the reset or "
                + "undo button to reset or undo, and the number of moves and your current "
                + "game state is shown at the bottom. \n Good luck. "); 
        
        // Top-level frame in which game components live
        final JFrame frame = new JFrame("MineSweeper");
        frame.setLocation(300, 300);

        
        
        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Setting up...");
        status_panel.add(status);

        // Game board
        final GameBoard board = new GameBoard(status);
        frame.add(board, BorderLayout.CENTER);

        
        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);
        
        final JPanel control_panel2 = new JPanel();
        frame.add(control_panel2, BorderLayout.EAST);

      //undo button
        final JButton undo = new JButton("Undo");
        undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //make a board.undo function
                board.undo();
            }
        });
        control_panel2.add(undo);
        
        // Note here that when we add an action listener to the reset button, we define it as an
        // anonymous inner class that is an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.reset();
            }
        });
        control_panel.add(reset);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start the game
        board.reset();
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}