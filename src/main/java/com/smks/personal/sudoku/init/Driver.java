package com.smks.personal.sudoku.init;

import java.util.Collections;
import java.util.List;

import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.eliminators.DirectPeerEliminator;
import com.smks.personal.sudoku.solver.Solver;

public class Driver {

	public static void main(String[] args) {
		final List<Integer> clues = List.of(
				0, 6, 0, 3, 0, 5, 8, 0, 4,
				5, 3, 7, 0, 9, 0, 0, 0, 0,
				0, 4, 0, 0, 0, 6, 3, 0, 7,
				0, 9, 0, 0, 5, 1, 2, 3, 8,
				0, 0, 0, 0, 0, 0, 0, 0, 0,
				7, 1, 3, 6, 2, 0, 0, 4, 0,
				3, 0, 6, 4, 0, 0, 0, 1, 0,
				0, 0, 0, 1, 6, 0, 5, 2, 3,
				1, 0, 2, 0, 0, 9, 0, 8, 0);
		
		final Grid grid = GridInitializer.initializeGrid(clues);
		
		final Solver solver = new Solver();
		final DirectPeerEliminator eliminator = new DirectPeerEliminator();
		
		solver.solvePuzzle(grid, Collections.singletonList(eliminator));
	}
}
