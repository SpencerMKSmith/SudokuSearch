package com.smks.personal.sudoku.solver;

import java.util.Optional;

import com.smks.personal.sudoku.data.Cell;
import com.smks.personal.sudoku.data.Grid;

/*
 * A Single is the only candidate value for a given cell.  This will find cells
 * which contain singles and set their values (future: update peers).
 */
public class SingleSetter {

	public Grid solveSingles(final Grid grid) {
	
		grid.getCells().stream()
				.filter(cell -> cell.getValue().isEmpty())
				.filter(cell -> cell.getPossibleValues().size() == 1)
				.forEach(cell -> cell.setValue(getSingle(cell)));
		
		return grid;
	}
	
	private Optional<Integer> getSingle(final Cell cell) {
		//System.out.println("Setting single: " + cell);
		return cell.getPossibleValues().stream().findFirst();
	}
}
