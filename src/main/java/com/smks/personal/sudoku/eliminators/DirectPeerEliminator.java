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
import com.smks.personal.sudoku.util.CellGatherer;

/*
 * The most commonly used elimination method where the value of each peer
 * is looked at to eliminate possible values in the given cell.
 * 
 */
public class DirectPeerEliminator implements Eliminator {

	
	@Override
	public UpdatedGrid eliminatePossibleValues(Grid grid) {
		
		final List<CellUpdate> cellUpdates = grid.getPositionToCellMap()
				.values()
				.stream()
				.filter(cell -> cell.getValue().isEmpty())
				.map(cell -> reducePossibilities(grid, cell))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
		
		cellUpdates.stream().forEach(cellUpdate -> grid.putUpdatedCell(cellUpdate.getUpdatedCell()));
		return new UpdatedGrid(grid, cellUpdates);
	}
	
	public Optional<CellUpdate> reducePossibilities(final Grid grid, final Cell cell) {
		final Set<Integer> eliminatedPossibilities = getEliminatedPossibilities(grid, cell);
		
		if(CollectionUtils.isEmpty(eliminatedPossibilities))
			return Optional.empty();

		final Set<Integer> newPossibilities = Sets.difference(cell.getPossibleValues(), eliminatedPossibilities);
		final Cell updatedCell = Cell.of(cell.getValue(), cell.getPosition(), newPossibilities);
		
		return Optional.of(CellUpdate.of(updatedCell, eliminatedPossibilities, this.getClass().getName()));
	}

	/*
	 * Returns a set of integers which represents values no longer
	 * possible for the given cell.
	 */
	public Set<Integer> getEliminatedPossibilities(final Grid grid, final Cell cell) {
		if(cell.getValue().isPresent()) {
			return Collections.emptySet();
		}
				
		final Set<Integer> valuesOfPeers = CellGatherer.getPeerCellsOfPosition(grid, cell.getPosition())
				.stream()
				.map(Cell::getValue)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toSet());
			
		return Sets.intersection(cell.getPossibleValues(), valuesOfPeers);
	}


}
