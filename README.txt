=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: 25448682
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Array: 
  The board itself is a 2D array of 16x16, with each "item" being a tile on the board that 
stores either a number tile or a bomb tile. The number tile holds the number of bombs in its 
vicinity, and bomb triggers a game over. A 0 signifies a blank space, a number between 1-8 
shows the amount of bombs in its proximity, and a 10 signifies the bomb itself. The 2D array
is needed to signify the location of the board– the 2D array structure is very similar to the 
set board structure, with 16 rows and 16 columns. The 2D array has a type of Tile because each 
tile holds information about the specific square on the board, which can be accessed through
the Tile class. 
  

  2. Collections:
  In order to implement the undo button mentioned earlier, I stored each move in
a collection it to access it when the undo button is hit. I stored them in a list, as
the moves must be ordered with the most recent ones at the tail of the list, thus I will use
an ArrayList to accomplish this feature. When the undo button is hit, the button that they had
just clicked will be unclicked, (or if they clicked a 0 all of the surrounding tiles that 
automatically are shown are unshown), and they can resume the game if a game had 
ended because of that move. The list stores (x,y) coordinates in the form of a Point, because 
this can store information about which row and column the board should impact. This 
collection is necessary because there is no pre-set number of moves with which I can create an
array– the number of moves in this Arraylist depends on the amount of clicks and is continually
updated with clicks and undos. 

  3. Testable
  I tested that each component works correctly. The main state is the class named 'Minesweeper', which outlines
  all of the rules of the Minesweeper game. The play turn method is where each mouseclick is routed to. This is 
  testable with JUnit. I will test that the game ends normally when a bomb is clicked, and continues when a bomb 
  is not clicked. As for edge cases, I can test when a user clicks a box that has already been clicked, or if they attempt 
  to click after the game has ended. The list of moves will be tested to show that the undo works, and an edge
  case would be clicking undo before they've made a move or after the game has ended.

  4. Recursion
  I will use recursion in this game when doing bomb clearing. When assigning numbers to 
the tiles that are not bombs, I will need to use recursion to look through the tiles around them. 
Furthermore, when looking for the adjacent 0 tiles, I will need to use recursion to 
successfully return all adjacent 0 tiles that are next to eachother. Iteration cannot be used because the
function must be called within itself to recurse through all of the surrounding 0s. 

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
My TicTacToe class is the one that holds the rules to Minesweeper, including the playTurn, reset, undo, and checkWinner
functions. Those functions serve to control what happens in response to user clicks and user actions. 
My Tile class is one that defines a Tile object, which has two subclasses of Bomb and NumberTile. These classes
hold functions such as getFlag, changeFlag, getNumber etc. These tiles essentially hold information about each of the
tiles in the Gameboard. 
My GameBoard class ties these classes together. It has a TicTacToe object, as well has a tileboard of Tiles that 
holds the information. It has a MouseListener that takes in right and left clicks, and changes the TicTacToe object
or Tile objects accordingly. There is also a Game class, which sets up the JPanel and the buttons of reset and undo. 
It also has an ActionListener. 

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
One stumbling block was that I originally had the intention of having inheritance classes, but I couldn't manage to 
make the uncover methods significantly different between a bomb and a number tile. I originally wanted for the bomb's
uncover method to end the game, but I soon realized it was impossible to access the game through the tile itself. So,
instead, I changed to Collections and implemented an undo button. 

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
There's a good separation of functionality, and all objects are encapsulated, such as returning a new 
ArrayList when getLastMove or getAllMoves are called. I might take out the Tile, number Tile, and Bomb classes as 
it could be implemented without those three classes. 


========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  None
