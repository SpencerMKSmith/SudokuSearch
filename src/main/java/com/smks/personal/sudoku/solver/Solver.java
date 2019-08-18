package com.smks.personal.sudoku.solver;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.smks.personal.sudoku.data.CellUpdate;
import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.data.UpdatedGrid;
import com.smks.personal.sudoku.eliminators.Eliminator;
import com.smks.personal.sudoku.util.Output;
import com.smks.personal.sudoku.validate.GridValidator;
public class Solver {

	public Grid solvePuzzle(final Grid grid, final List<Eliminator> eliminators) {
		final SingleSetter setter = new SingleSetter();
		final GridValidator validator = new GridValidator();
		
		//System.out.println("Solving the puzzle with this eliminator: " + eliminators.get(0).getClass().getName());
		
		final List<CellUpdate> allCellUpdates = new ArrayList<>();
		Set<CellUpdate> newUpdates;
		
		do {
			
			newUpdates = eliminators.stream()
					.map(eliminator -> eliminator.eliminatePossibleValues(grid))
					.map(UpdatedGrid::getCellUpdates)
					.flatMap(Set::stream)
					.collect(Collectors.toSet());
			
			allCellUpdates.addAll(newUpdates);
			
			System.out.println("Number of updates: " + newUpdates.size() + ", New updates: " + newUpdates);
			final UpdatedGrid updatedGrid = setter.solveSingles(grid);
			
			System.out.println("Number of values set: " + updatedGrid.getCellUpdates().size() + ", New values: " + updatedGrid.getCellUpdates());

			
		} while(isNotEmpty(newUpdates));
		
		Output.printGrid(grid);
		
		System.out.println("Is Grid complete and valid? " + validator.isGridValid(grid));
		
		return grid;
	}
}
