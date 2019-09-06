package com.smks.personal.sudoku.eliminators;

import java.util.function.Function;

import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.data.UpdatedGrid;
import com.smks.personal.sudoku.error.ErrorResult;

import io.vavr.control.Either;

public interface Eliminator extends 
			Function<Either<ErrorResult, UpdatedGrid>, Either<ErrorResult, UpdatedGrid>>{
	
	default Eliminator andThen(final Eliminator other) {
		return updatedGrid -> other.apply(this.apply(updatedGrid));
	}
}
