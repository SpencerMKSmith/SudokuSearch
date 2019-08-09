package com.smks.personal.sudoku.solver;

import java.util.List;

import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.eliminators.Eliminator;

public class Solver {

	public Grid solvePuzzle(final Grid grid, final List<Eliminator> eliminators) {
		
		System.out.println("Solving the puzzle with this eliminator: " + eliminators.get(0).getClass().getName());
		eliminators.get(0).eliminatePossibleValues(grid);
		return grid;
	}
}
