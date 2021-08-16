
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class MineSweeper {

    
  //  private int[][] boards;
    private int[][] bombs;
    private Tile[][] tileboard;
    
    //this is because some moves (ex, if you click 0) have multiple blocks to undo
    private ArrayList<ArrayList<Point>> moves;
    
    private boolean gameOver;
    
    private int numMoves;

    /**
     * Constructor sets up game state.
     */
    public MineSweeper() {
        reset();
    }
    
    private ArrayList<Point> getSurroundingZeros(ArrayList<Point> zeros, int i, int j) {
        //get all surrounding zeros of i and j in ttt.getBoard using getBomb(i, j)
        if (getBomb(i, j) == 0) {
         // to account for the border pieces
            for (int row = Math.max(i - 1, 0); row <= Math.min(i + 1, 15); row++) {
                for (int col = Math.max(j - 1, 0); col <= Math.min(j + 1, 15); col ++) {
                    //no repetition
                    if (!zeros.contains(new Point(row,col))) {
                        zeros.add(new Point(row,col));
                        if (getBomb(row, col) == 0) {
                            //RECURSION
                            ArrayList<Point> sur = getSurroundingZeros(zeros, row, col);
                            for (Point pt: sur) {
                              //no repetition
                                if (!zeros.contains(new Point(pt.x, pt.y))) {
                                    zeros.add(new Point(pt.x, pt.y));
                                }
                            }
                        }
                    }
                }
            }
            return zeros;
        }
        return zeros;

    }

    /**
     * playTurn allows players to play a turn. This ensures that the game 
     * runs as long as gameOver is false, and implements the rules of 
     * uncovering all 0s if the tile clicked is a 0, uncovering all number tiles,
     * and uncovering all bombs.
     */
    public boolean playTurn(int c, int r) {
        
        if (gameOver) {
            return false;
        }
        for (ArrayList<Point> move : moves) {
            if (move.contains(new Point(r,c))) {
                return false;
            }
            
        }
        if (!getFlag(r,c)) {
            
            //uncover all of the ones next to the 0 if it is a 0
            if (getBomb(r,c) == 0) {
                ArrayList<Point> surrounding = new ArrayList<>();
                surrounding.add(new Point(r,c));
                (tileboard[r][c]).uncover(); 
                //getting all zeroes next to it
                ArrayList<Point> zeroes = getSurroundingZeros(surrounding, r, c);
                surrounding.addAll(zeroes);
                
                //adding to moves
                if (!moves.contains(surrounding)) {
                    moves.add(surrounding);
                }
                
                for (Point pt : surrounding) {
                    //       System.out.println("x=" + pt.x + " y=" + pt.y);
                    (tileboard[pt.x][pt.y]).uncover(); 
                    numMoves++;
                }
            } else {
                //adding to moves
                
                ArrayList<Point> surrounding = new ArrayList<>();
                surrounding.add(new Point(r,c));
                
                if (!moves.contains(surrounding)) {
                    moves.add(surrounding);
                }
                (tileboard[r][c]).uncover(); 
                numMoves++;
                
                if (getBomb(r,c) == 10) {
                    gameOver = true;
                    return false;
                }
            }
        }
        
        

        return true;
        
    }

    /**
     * checkWinner checks whether the game has reached a win 
     * condition. 
     */
    public int checkWinner() {
        // check if every non-bomb tile is unchecked
        boolean coveredexists = false;
        
        for (ArrayList<Point> move : moves) {
            for (Point pt : move) {
                if (tileboard[pt.x][pt.y].getNumber() == 10) {
                    gameOver = true;
                    return 2;
                }
            }
        }
        
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                //if a bomb tile is uncovered
                // look into moves and see if any of the "moves" are bombs
                /*
                if (tileboard[i][j].getNumber() == 10 & tileboard[i][j].getCover()) {
                    gameOver = true;
                    return 2;
                }*/
                
                if ((!tileboard[i][j].getCover()) & tileboard[i][j].getNumber() != 10) {
                    coveredexists = true;
                }
            }
        }
        
     // if the tile is covered
        if (coveredexists) {
            return 0;
        } else {
            gameOver = true;
            return 1;
        }

    }

    /**
     * printGameState prints the current game state
     * for debugging.
     */
    
    public ArrayList<Point> getLastMove() {
        ArrayList<Point> lastmove = ((ArrayList<ArrayList<Point>>) moves).get(moves.size() - 1);
        return new ArrayList<Point>(lastmove);
    }
    
    public ArrayList<ArrayList<Point>> getAllMoves() {
        return new ArrayList<ArrayList<Point>>(moves);
    }
    
    public void undo() {
        int sizeofmove = 0;
        int index = moves.size() - 1;
        if (index >= 0) {
            for (Point pt : moves.get(index)) {
                (tileboard[pt.x][pt.y]).changeCoverTo(false);
            }
            sizeofmove = moves.get(index).size();
            moves.remove(index);
            if (gameOver) {
                changeGameOver(false);
            }
        }
        numMoves = numMoves - sizeofmove;
    }
    
    /**
     * reset (re-)sets the game state to start a new game.
     */
    public void reset() {
        numMoves = 0;
        int[][] boards = new int[16][16];
        int k = 1;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                boards[i][j] = k;
                k++;
            }
        }
        gameOver = false;
        //random number generator to choose 40 numbers between 1 to 256
        bombs = new int[16][16];
        moves = new ArrayList<ArrayList<Point>>();
        
        Random rand = new Random();
        //chooses 0-255
        ArrayList<Integer> bomb = new ArrayList<>();
        int index = 1; 
        while (index < 40) {
            int random = (rand.nextInt(256));
            if (!bomb.contains(random)) {
                bomb.add(random);
                index++;
            }
        }
        //creates the bombs 2D list where if it = 10, is a bomb, otherwise should be #
        int index2 = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (bomb.contains(index2)) {
                    bombs[i][j] = 10;
                }
                index2++;
            }
        }
        //fills in the rest of the squares
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (bombs[i][j] != 10) {
                    bombs[i][j] = getSurrounding(i,j);
                }
            }
        }
        //2D board of tiles that will be primarily used
        tileboard = new Tile[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (bombs[i][j] == 10) {
                    tileboard[i][j] = new Bomb();
                } else {
                    tileboard[i][j] = new NumberTile(bombs[i][j]);
                    
                }
            }
        }
        
        
    }
    //checks all surrounding cells 
    public int getSurrounding(int i, int j) {
        if (bombs[i][j] != 10) {
            int sur = 0;
            for (int row = Math.max(0, i - 1); row <= Math.min(i + 1, 15); row++) {
                for (int col = Math.max(0, j - 1); col <= Math.min(j + 1, 15); col ++) {
                    if (bombs[row][col] == 10) {
                        sur++;
                    }
                }
            }
            return sur;
        }
        return 10;
    }
    
    public void changeGameOver(boolean x) {
        gameOver = x;
    }
    public boolean getGameOver() {
        return gameOver;
    }
    
    public int getNumMoves() {
        int in = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (tileboard[i][j].getCover()) {
                    in++;
                }
            }
        }
        return in;
       // return numMoves;
    }
    

  //checks all surrounding cells 
    
    public Tile getTileBoard(int i, int j) {
        if (i > 15 | j > 15) {
            return new NumberTile(11);
        }
        if (tileboard[i][j] instanceof Bomb) {
            Tile x = new Bomb();
            return x;
        } else {
            Tile x = new NumberTile(tileboard[i][j].getNumber());
            return x;
        }
        
        
    }
    
    
    public int getBomb(int i, int j) {
        return (tileboard[i][j]).getNumber();
    }
    
    public boolean getUncover(int i, int j) {
        return (tileboard[i][j]).getCover();
    }
    
    
    public boolean getFlag(int c, int r) {
        return tileboard[c][r].getFlag();
    }
    
    public void changeFlag(int c, int r) {
        tileboard[c][r].changeFlag();
    }
    
    
}
