package com.smks.personal.sudoku.init;

import java.util.Collections;
import java.util.List;

import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.eliminators.DirectPeerEliminator;
import com.smks.personal.sudoku.eliminators.HiddenSingleEliminator;
import com.smks.personal.sudoku.puzzles.Puzzles;
import com.smks.personal.sudoku.solver.Solver;

public class Driver {

	public static void main(String[] args) {
		
		long startMillis = System.currentTimeMillis();

		//for(int i = 0; i < 100; i++) {

			final Grid grid = GridInitializer.initializeGrid(Puzzles.Medium_Puzzle_1());
			
			final Solver solver = new Solver();
			final DirectPeerEliminator eliminator = new DirectPeerEliminator();
			final HiddenSingleEliminator hiddenSingleEliminator = new HiddenSingleEliminator();
		
			solver.solvePuzzle(grid, List.of(eliminator, hiddenSingleEliminator));
		
		//}
		//long millisPerSolve = (System.currentTimeMillis() - startMillis) / 100;
		System.out.println("Total millis: " + (System.currentTimeMillis() - startMillis));
	}
}
