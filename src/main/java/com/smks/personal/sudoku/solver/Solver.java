package com.smks.personal.sudoku.solver;

import java.util.Collections;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.data.UpdatedGrid;
import com.smks.personal.sudoku.eliminators.Eliminator;
import com.smks.personal.sudoku.error.ErrorResult;
import com.smks.personal.sudoku.validate.GridValidator;

import io.vavr.control.Either;

public class Solver {

	@Autowired
	private GridValidator validator;
	@Autowired
	private SingleSetter setter;
	
	public Either<ErrorResult, UpdatedGrid> solvePuzzle(final UpdatedGrid previous, final Eliminator eliminator) {
		
		final Either<ErrorResult, UpdatedGrid> updateResult = eliminator.apply(Either.right(previous)) // Eliminate candidates
			.flatMap(setter::solveSingles);				// Set values
			
		// In the case of an error, return.  If both objects are equal than also return
		if(updateResult.isLeft() || Objects.equals(updateResult.get(), previous)) 
			return updateResult;
			
		// Else recurse the solver
		return solvePuzzle(updateResult.get(), eliminator);		
	}
	
	// Used the first time the solver is called
	public Either<ErrorResult, UpdatedGrid> solvePuzzle(final Grid newGrid, final Eliminator eliminator) {
		return solvePuzzle(UpdatedGrid.of(newGrid, Collections.emptyList()), eliminator);
	}
}
