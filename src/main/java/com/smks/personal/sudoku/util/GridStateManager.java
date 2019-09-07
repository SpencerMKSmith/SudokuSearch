package com.smks.personal.sudoku.util;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.base.Functions;
import com.google.common.collect.ImmutableMap;
import com.smks.personal.sudoku.data.Cell;
import com.smks.personal.sudoku.data.CellUpdate;
import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.data.Position;
import com.smks.personal.sudoku.data.UpdatedGrid;

public class GridStateManager {

	public UpdatedGrid step(final UpdatedGrid previous, final List<CellUpdate> newUpdates) {
		// TODO: Change Grid to be immutable and modify this to return a new Grid


		// Get the new cell map and create the new grid from it
		final Map<Position, Cell> newCellMap = updateCellMap(previous.getGrid(), newUpdates);
		final Grid newGrid = previous.getGrid().toBuilder().positionToCellMap(newCellMap).build();

		// Get all updates to this point on the new grid
		final List<CellUpdate> allUpdates = concatUpdateLists(previous.getCellUpdates(), newUpdates);
		
		return UpdatedGrid.of(newGrid, allUpdates);	
	}
	
	private Map<Position, Cell> updateCellMap(final Grid previousGrid, final List<CellUpdate> newUpdates) {

		
		final ImmutableMap.Builder<Position, Cell> cellMapBuilder = ImmutableMap.<Position, Cell>builder();
		
		// Put in all new values first
		final Map<Position, Cell> cellMapWithUpdatesOnly = newUpdates.stream()
				.map(CellUpdate::getUpdatedCell)
				.collect(Collectors.toMap(Cell::getPosition, Function.identity()));
		
		cellMapBuilder.putAll(cellMapWithUpdatesOnly);
		
		// Fill in the rest of the values, ensuring to not put duplicate keys
		final Map<Position, Cell> unchangedValues = previousGrid.getPositionToCellMap()
				.entrySet()
				.stream()
				.filter(e -> !cellMapWithUpdatesOnly.containsKey(e.getKey()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
		cellMapBuilder.putAll(unchangedValues);
		
		return cellMapBuilder.build();
	}
	
	private List<CellUpdate> concatUpdateLists(final List<CellUpdate> previousUpdates, final List<CellUpdate> newUpdates) {
		return Stream.of(previousUpdates, newUpdates)
			.flatMap(List::stream)
			.collect(toList());
	}
}
