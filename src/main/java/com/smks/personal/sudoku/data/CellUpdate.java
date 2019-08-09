package com.smks.personal.sudoku.data;

import java.util.Set;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(staticName = "of")
public class CellUpdate {

	Cell updatedCell;
	Set<Integer> removedPossibilities;
	String eliminatorName;
}
