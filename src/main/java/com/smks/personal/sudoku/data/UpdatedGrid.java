package com.smks.personal.sudoku.data;

import java.util.List;

import lombok.Value;

@Value
public class UpdatedGrid {
	Grid grid;
	List<CellUpdate> cellUpdates;
}
