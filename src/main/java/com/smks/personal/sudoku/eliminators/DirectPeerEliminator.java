package com.smks.personal.sudoku.eliminators;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Sets;
import com.smks.personal.sudoku.data.Cell;
import com.smks.personal.sudoku.data.CellUpdate;
import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.data.UpdatedGrid;

/*
 * The most commonly used elimination method where the value of each peer
 * is looked at to eliminate possible values in the given cell.
 * 
 */
public class DirectPeerEliminator implements Eliminator {

	
	@Override
	public UpdatedGrid eliminatePossibleValues(Grid grid) {
		
		final List<CellUpdate> cellUpdates = grid.getCells()
				.stream()
				.filter(cell -> cell.getValue().isEmpty())
				.map(this::reducePossibilities)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
		
		return new UpdatedGrid(grid, cellUpdates);
	}
	
	public Optional<CellUpdate> reducePossibilities(final Cell cell) {
		final Set<Integer> eliminatedPossibilities = getEliminatedPossibilities(cell);
		
		cell.reducePossibilities(eliminatedPossibilities);
		
		return CollectionUtils.isEmpty(eliminatedPossibilities)
				? Optional.empty()
				: Optional.of(CellUpdate.of(cell, eliminatedPossibilities, this.getClass().getName()));
	}
	/*
	 * Returns a set of integers which represents values no longer
	 * possible for the given cell.
	 */
	public Set<Integer> getEliminatedPossibilities(final Cell cell) {
		if(cell.getValue().isPresent()) {
			return Collections.emptySet();
		}
				
		final Set<Integer> valuesOfPeers = cell.getPeers().stream()
				.map(Cell::getValue)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toSet());
		
		return Sets.intersection(cell.getPossibleValues(), valuesOfPeers);
	}


}
