#Battleship
Simple project that models a simplified version of the Battleship game. In this special version of the game, there will be only one board and the game will be played automatically after a sequence of commands is interpreted. Furthermore, unlike in the original game, here the ships can move between one turn and the other.
##Rules
* The board of the game will be rectangular.
* For simplicity the ships will occupy only one square.
* Each ship position and location is represented by a combination of ‘x’ and ‘y’ coordinates and a letter representing one of the four cardinal compass points.
* An example position might be 0, 0, N, which means the ship is in the bottom left corner and facing North.
* In order to move a ship, a simple string of letters will be sent. The possible letters are 'L', 'R' and 'M'. 'L' and 'R' make the ship spin 90 degrees left or right respectively, without moving from its current spot. 'M' means move forward one grid point, and maintain the same heading.
* To try to sink a ship (that is, to shoot at a square of the board) the coordinates of the square will be sent.
* A square can’t be occupied by more than one ship but a during a movement a ship can navigate through a square occupied by a ship.

##Input
* The first line of the input is the upper-right coordinates of the board, the lower-left coordinates are assumed to be 0, 0. The format of the input will be (X, Y). This sets the size of the board.
* The second line of the input is information about the initial deployment of the ships. It will be a space separated list of positions and orientations: (X1, Y1, O1) (X2, Y2, O2) … (XN, YN, ON)
* The rest of the input will contain different lines which will contain either ship movements or shots:
  * Ship movement. The format will be the position of the ship followed by the list of movements: (X, Y) LLRRLRM
  * Shot. The format will be the position shot: (X, Y)

##Output
The output for each ship should be its final coordinates and heading and if they are sink or not.

##Example Test case

###Test input
(5, 5)

(1, 2, T) (3, 3, E)

(1, 2) LMLMLMLMM

(2, 3)

(3, 3) MMRMMRMRRM

(1, 3)

###Expected Output
(1, 3, N) SUNK

(5, 1, E)

##Assumptions
* Inputs must be exactly as in the example, with the same spaces, that is (5,5) is an invalid input for the board dimensions
* If a ship has an invalid position, it is ignored, i.e. it is not added to the board
* If an action is relative to a position without a ship, it is ignored.
* If an action has a code different from LMR, it is ignored
I try to decouple the input strings and the Ship class sending objects (Actions, Positions and Orientations) to it. In this way the code is clearer and it is easier to validate the input in a centralized way.





