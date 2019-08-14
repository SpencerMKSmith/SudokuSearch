package com.smks.personal.sudoku.data;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(staticName = "of")
@Builder(toBuilder = true)
public class Cell {
	
	Optional<Integer> value;
	Position position;
	Set<Integer> possibleValues;

	public Cell(final Position position, Optional<Integer> value, final Integer gridSize) {
		this.position = position;
		this.value = value;
		
		// If the cell is empty, initialize possible values with the possible range, else it is an empty set
		if(value.isEmpty()) {
			this.possibleValues = IntStream.rangeClosed(1, gridSize)
					.boxed()
					.collect(Collectors.toSet());
		} else {
			this.possibleValues = Collections.emptySet();
		}
	}
	
//	public Set<Cell> getPeers() {
//		return parentUnits.stream()
//				.map(Unit::getItemsInUnit)
//				.flatMap(List::stream)
//				.filter(cell -> !cell.equals(this))
//				.collect(Collectors.toSet());
//	}
//	
//	public void reducePossibilities(final Set<Integer> eliminatedPossibilities) {
//		possibleValues = Sets.difference(possibleValues, eliminatedPossibilities);
//	}
}
