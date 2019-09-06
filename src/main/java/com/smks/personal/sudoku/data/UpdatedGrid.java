package com.smks.personal.sudoku.data;

import java.util.List;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(staticName = "of")
public class UpdatedGrid {
	Grid grid;
	List<CellUpdate> cellUpdates;
}
