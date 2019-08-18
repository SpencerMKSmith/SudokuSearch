package com.smks.personal.sudoku.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.smks.personal.sudoku.data.Cell;
import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.data.Position;
import com.smks.personal.sudoku.data.Unit;
import com.smks.personal.sudoku.data.UnitType;
import com.smks.personal.sudoku.init.unit.UnitInitializer;
import com.smks.personal.sudoku.util.Output;
import com.smks.personal.sudoku.util.PositionUtils;

public class GridInitializer {

	public static Grid initializeGrid(final List<Integer> clues) {
		
		final Integer gridSize = (int) Math.sqrt(clues.size());
		final List<Cell> cellList = new ArrayList<>();
		
		// Take the input list of values and create a Cell object list
		for(int i = 0; i < clues.size(); i++) {
			final Integer currentValue = clues.get(i);
			final Optional<Integer> cellValue = currentValue <= 0
					? Optional.empty() 
					: Optional.of(currentValue);
					
			final Position cellPosition = PositionUtils.getPositionForIndex(gridSize, i);
			cellList.add(new Cell(cellPosition, cellValue, gridSize));
			
		}
		
		//Output.printCellList(cellList);
		
		// Create Units from the Cell list
		Set<Unit> blocks = UnitInitializer.initializeUnit(gridSize, cellList, UnitType.BLOCK);
		Set<Unit> columns = UnitInitializer.initializeUnit(gridSize, cellList, UnitType.COLUMN);
		Set<Unit> rows = UnitInitializer.initializeUnit(gridSize, cellList, UnitType.ROW);

		final Map<Position, Cell> posToCellMap = cellList.stream()
				.collect(Collectors.toMap(Cell::getPosition, Function.identity()));

//		// Create a map of cells to a list of units to which that cell belongs.  The list should be
//		// 3 items long.
//		final Map<Cell, Set<Unit>> mapOfCellToUnitsThatItBelongsTo = Stream.of(rows, columns, blocks)
//				.flatMap(Set::stream)
//				.flatMap(unit -> CellGatherer.getCellsForUnit(grid, unit)
//						.stream()
//						.map(cell -> Tuple.of(unit, cell)))
//				.collect(Collectors.groupingBy(
//						Tuple2::_2, 
//						Collectors.mapping(Tuple2::_1, Collectors.toSet())));
//		
//		// For each cell, pass the references to the Units that they belong to
//		mapOfCellToUnitsThatItBelongsTo.entrySet().stream()
//			.forEach(entry -> entry.getKey().setParentUnits(entry.getValue()));
//		

		return new Grid(gridSize, cellList, blocks, columns, rows, posToCellMap);
	}
}
