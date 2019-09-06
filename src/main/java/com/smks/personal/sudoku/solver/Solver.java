package com.smks.personal.sudoku.solver;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;

import com.smks.personal.sudoku.data.CellUpdate;
import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.data.UpdatedGrid;
import com.smks.personal.sudoku.eliminators.Eliminator;
import com.smks.personal.sudoku.error.ErrorResult;
import com.smks.personal.sudoku.util.Output;
import com.smks.personal.sudoku.validate.GridValidator;

import io.vavr.control.Either;
public class Solver {

	@Autowired
	private GridValidator validator;
	@Autowired
	private SingleSetter setter;
	
	public Grid solvePuzzle(final Grid grid, final Eliminator eliminator) {
				
		final List<CellUpdate> allCellUpdates = new ArrayList<>();
		List<CellUpdate> newUpdates;
		final Either<ErrorResult, UpdatedGrid> starter = Either.right(UpdatedGrid.of(grid, Collections.emptyList()));
		
		do {
			
			Either<ErrorResult, UpdatedGrid> eitherResult = eliminator.apply(starter);
			newUpdates = eitherResult.get().getCellUpdates();
			
			allCellUpdates.addAll(newUpdates);
			
			System.out.println("Number of updates: " + newUpdates.size() + ", New updates: " + newUpdates);
			final UpdatedGrid updatedGrid = setter.solveSingles(eitherResult.get());

			System.out.println("Number of values set: " + updatedGrid.getCellUpdates().size() + ", New values: " + updatedGrid.getCellUpdates());

			
		} while(isNotEmpty(newUpdates));
		
		Output.printGrid(grid);
		
		System.out.println("Is Grid complete and valid? " + validator.isGridValid(grid));
		
		return grid;
	}
}
