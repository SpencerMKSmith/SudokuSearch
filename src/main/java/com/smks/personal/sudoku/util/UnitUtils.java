package com.smks.personal.sudoku.util;

import java.util.Set;
import static java.util.stream.Collectors.toSet;

import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.data.Position;
import com.smks.personal.sudoku.data.Unit;

public class UnitUtils {

	public static Set<Unit> getUnitsForPosition(final Position position, final Grid grid) {
		return grid.getAllUnits()
				.stream()
				.filter(unit -> doesUnitContainPosition(unit, position))
				.collect(toSet());
	}
	
	public static boolean doesUnitContainPosition(final Unit unit, final Position position) {
		return unit.getCellPositions().contains(position);
	}
}
