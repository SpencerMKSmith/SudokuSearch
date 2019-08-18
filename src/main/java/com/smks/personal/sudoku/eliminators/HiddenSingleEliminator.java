package com.smks.personal.sudoku.eliminators;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Iterables;
import com.smks.personal.sudoku.data.Cell;
import com.smks.personal.sudoku.data.CellUpdate;
import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.data.UpdatedGrid;
import com.smks.personal.sudoku.util.CellGatherer;

import javafx.util.Pair;

public class HiddenSingleEliminator implements Eliminator {

	@Override
	public UpdatedGrid eliminatePossibleValues(Grid grid) {
		
		final Set<CellUpdate> cellUpdates = grid.getAllUnits()
				.stream()
				.map(unit -> CellGatherer.getCellsForUnit(grid, unit))
				.map(this::performEliminationOnUnit)
				.flatMap(Set::stream)
				.collect(toSet());
		
		cellUpdates.stream().forEach(cellUpdate -> grid.putUpdatedCell(cellUpdate.getUpdatedCell()));
		
		return UpdatedGrid.of(grid, cellUpdates);
	}
	
	private Set<CellUpdate> performEliminationOnUnit(final Set<Cell> cells) {

		return getPossibleCellsForEachCandidateValue(cells)
				.entrySet()
				.stream()
				.filter(e -> CollectionUtils.isNotEmpty(e.getValue()))
				.filter(e -> e.getValue().size() == 1)
				.map(this::getCellUpdateOfSingle)
				.collect(toSet());
	}
	
	private Map<Integer, Set<Cell>> getPossibleCellsForEachCandidateValue(final Set<Cell> cells) {
		// Key represents the candidate values found in the unit
		// Value is the set of cells in the unit which has the key as a candidate
		return cells.stream()
				.filter(cell -> cell.getValue().isEmpty())
				.map(this::inflateCellWithCandidateValues)
				.flatMap(Set::stream)
				.collect(groupingBy(Pair::getValue, mapping(Pair::getKey, toSet())));
		
	}
	
	// Only called when the Set has a size of 1
	private CellUpdate getCellUpdateOfSingle(final Map.Entry<Integer, Set<Cell>> valueToCellEntry) {
		final Integer newValue = valueToCellEntry.getKey();
		final Cell cell = Iterables.getOnlyElement(valueToCellEntry.getValue());
		final Set<Integer> eliminatedValues = cell.getPossibleValues()
				.stream()
				.filter(value -> value != newValue)
				.collect(toSet());
		
		final Cell newCell = cell.toBuilder().possibleValues(Set.of(newValue)).build();
		return CellUpdate.of(newCell, eliminatedValues, this.getClass().getName());
	}
	
	private Set<Pair<Cell, Integer>> inflateCellWithCandidateValues(final Cell cell) {
		return cell.getPossibleValues()
				.stream()
				.map(value -> new Pair<>(cell, value))
				.collect(Collectors.toSet());
	}

}
