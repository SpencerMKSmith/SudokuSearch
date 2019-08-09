package com.smks.personal.sudoku.solver;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.List;

import com.smks.personal.sudoku.data.CellUpdate;
import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.eliminators.Eliminator;
import com.smks.personal.sudoku.util.Output;
public class Solver {

	public Grid solvePuzzle(final Grid grid, final List<Eliminator> eliminators) {
		final SingleSetter setter = new SingleSetter();
		
		System.out.println("Solving the puzzle with this eliminator: " + eliminators.get(0).getClass().getName());
		
		final List<CellUpdate> allCellUpdates = new ArrayList<>();
		List<CellUpdate> newUpdates;
		
		do {
			newUpdates = eliminators.get(0).eliminatePossibleValues(grid).getCellUpdates();
			allCellUpdates.addAll(newUpdates);
			
			System.out.println("New updates: " + newUpdates);
			setter.solveSingles(grid);
			
		} while(isNotEmpty(newUpdates));
		
		Output.printCellList(grid.getCells());
		return grid;
	}
}
