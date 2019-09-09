# SudokuSearch
Sudoku solver in Java.


### Grid
This object holds the current state of the Sudoku board.  This object (as with all other data objects) is immutable.  Any changes that need to be made will result in a copy of the Grid being made and used.

### Eliminators
Eliminators are functions that will take a Grid and analyze it to attempt to *eliminate* possible candidates from cells.  The eliminators will then perform the changes on a cloned copy of the Grid.

### Setters
Setters will set the values of a Cell.  As mentioned above, this results in a copy of the Grid being made with the new value set.

# TODO
A list of items that I have planned to work on

### Quality changes
1. Functional test of entire program
1. Unit test DirectPeerEliminator
1. Unit test HiddenSingleEliminator
1. Determine possible way that an ErrorResult could occur in an Eliminator (are there any possible scenarios where this would happen or is it over-engineering to have that as a possible response?)
1. Improve this documentation

### New Features
1. Begin to check for invalid boards somewhere
1. If no changes are made to a board, and the board is unfinished, implement guessing.  
1. Implement a CrossHatchEliminator.  This looks at a row or column which *cross hatches* two blocks.  If there are values which must be set in the intersecting row/column in one block, then those values may be eliminated from the other cross hatched block.
1. Implement Metrics



