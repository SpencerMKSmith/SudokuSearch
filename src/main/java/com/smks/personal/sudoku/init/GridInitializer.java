package com.smks.personal.sudoku.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.smks.personal.sudoku.data.Cell;
import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.data.Position;
import com.smks.personal.sudoku.data.Unit;
import com.smks.personal.sudoku.data.UnitType;
import com.smks.personal.sudoku.init.unit.UnitInitializer;
import com.smks.personal.sudoku.util.Output;
import com.smks.personal.sudoku.util.PositionUtils;

import io.vavr.Tuple;
import io.vavr.Tuple2;

public class GridInitializer {

	public static Grid initializeGrid(final List<Integer> clues) {
		
		final Integer gridSize = (int) Math.sqrt(clues.size());
		final List<Cell> cellList = new ArrayList<>();
		List<Unit> rows = new ArrayList<>();
		List<Unit> columns = new ArrayList<>();
		List<Unit> blocks = new ArrayList<>();
		
		// Take the input list of values and create a Cell object list
		for(int i = 0; i < clues.size(); i++) {
			final Integer currentValue = clues.get(i);
			final Optional<Integer> cellValue = currentValue <= 0
					? Optional.empty() 
					: Optional.of(currentValue);
					
			final Position cellPosition = PositionUtils.getPositionForIndex(gridSize, i);
			cellList.add(new Cell(cellPosition, cellValue, gridSize));
			
		}
		
		Output.printCellList(cellList);
		
		// Create Units from the Cell list
		blocks = UnitInitializer.initializeUnit(gridSize, cellList, UnitType.BLOCK);
		columns = UnitInitializer.initializeUnit(gridSize, cellList, UnitType.COLUMN);
		rows = UnitInitializer.initializeUnit(gridSize, cellList, UnitType.ROW);

		// Create a map of cells to a list of units to which that cell belongs.  The list should be
		// 3 items long.
		final Map<Cell, Set<Unit>> tupleList = Stream.of(rows, columns, blocks)
				.flatMap(List::stream)
				.flatMap(unit -> unit.getItemsInUnit()
						.stream()
						.map(cell -> Tuple.of(unit, cell)))
				.collect(Collectors.groupingBy(
						Tuple2::_2, 
						Collectors.mapping(Tuple2::_1, Collectors.toSet())));
		
		// For each cell, pass the references to the Units that they belong to
		tupleList.entrySet().stream()
			.forEach(entry -> entry.getKey().setParentUnits(entry.getValue()));
		
		return new Grid(gridSize, cellList, blocks, columns, rows);
	}
}
