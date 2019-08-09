package com.smks.personal.sudoku.eliminators;

import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.data.UpdatedGrid;

public interface Eliminator {

	UpdatedGrid eliminatePossibleValues(final Grid grid);
}
