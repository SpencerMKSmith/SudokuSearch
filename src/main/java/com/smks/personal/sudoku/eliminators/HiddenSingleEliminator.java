package com.smks.personal.sudoku.eliminators;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Iterables;
import com.smks.personal.sudoku.data.Cell;
import com.smks.personal.sudoku.data.CellUpdate;
import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.data.UpdatedGrid;
import com.smks.personal.sudoku.error.ErrorResult;
import com.smks.personal.sudoku.util.CellGatherer;
import com.smks.personal.sudoku.util.GridStateManager;

import io.vavr.control.Either;
import javafx.util.Pair;

/*
 * A Hidden Single is determined at the unit level.  It is when there is only a single
 * Cell which has a given candidate value.
 * 
 * Example: Cell A7 has 4 candidate values, but it is the only one within the A column
 * 			that could be a 4, so A7 is a 4.
 */
public class HiddenSingleEliminator implements Eliminator {

	@Autowired
	private CellGatherer cellGatherer;
	@Autowired
	private GridStateManager gridStateManager;
	
	@Override
	public Either<ErrorResult, UpdatedGrid> apply(Either<ErrorResult, UpdatedGrid> previousUpdate) {
		return previousUpdate.flatMap(this::performElimination);
	}
	
	public Either<ErrorResult, UpdatedGrid> performElimination(UpdatedGrid previousGrid) {
		final Grid grid = previousGrid.getGrid();
		
		final List<CellUpdate> cellUpdates = grid.getAllUnits()
				.stream()
				.map(unit -> cellGatherer.getCellsForUnit(grid, unit))
				.map(this::performEliminationOnUnit)
				.flatMap(List::stream)
				.distinct()
				.collect(toList());
		
		final UpdatedGrid newlyUpdatedGrid = gridStateManager.step(previousGrid, cellUpdates);
		
		return Either.right(newlyUpdatedGrid);
	}
	
	private List<CellUpdate> performEliminationOnUnit(final Set<Cell> cells) {

		return getPossibleCellsForEachCandidateValue(cells)
				.entrySet()
				.stream()
				.filter(e -> CollectionUtils.isNotEmpty(e.getValue()))
				.filter(e -> e.getValue().size() == 1)
				.map(this::getCellUpdateOfSingle)
				.collect(toList());
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
