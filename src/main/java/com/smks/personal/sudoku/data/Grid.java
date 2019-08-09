package com.smks.personal.sudoku.data;

import java.util.List;

import lombok.Value;

@Value
public class Grid {

	Integer gridSize;
	List<Cell> cells;
	List<Unit> blocks;
	List<Unit> columns;
	List<Unit> rows;
}
