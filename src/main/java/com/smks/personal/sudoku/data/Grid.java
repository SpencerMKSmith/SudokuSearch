package com.smks.personal.sudoku.data;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Value;

@Value
public class Grid {

	Integer gridSize;
	List<Cell> cells;
	Set<Unit> blocks;
	Set<Unit> columns;
	Set<Unit> rows;
	Map<Position, Cell> positionToCellMap;
	
	public void putUpdatedCell(final Cell updatedCell) {
		positionToCellMap.put(updatedCell.getPosition(), updatedCell);
	}
	
	public Set<Unit> getAllUnits() {
		return Stream.of(blocks, columns, rows)
				.flatMap(Set::stream)
				.collect(Collectors.toSet());
	}
}
