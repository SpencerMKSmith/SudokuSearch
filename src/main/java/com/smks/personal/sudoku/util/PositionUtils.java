package com.smks.personal.sudoku.util;

import com.smks.personal.sudoku.data.Position;

public class PositionUtils {

	/*
	 * Gives the Position object given a boardsize and the index of the value on initialization.
	 * This assumes that the incoming board is a single list of integers.
	 * 
	 * Example 1: gridSize = 9, index = 12 (denoted by X below
	 * Output: Row = 2, Column = 3
	 * 
	 * Example 2: gridSize = 9, index = 81
	 * Output: Row = 9, Column = 9
	 *   1 2 3   4 5 6   7 8 9
	 * 1 _ _ _ | _ _ _ | _ _ _
	 * 2 _ _ X | _ _ _ | _ _ _
	 * 3 _ _ _ | _ _ _ | _ _ _
	 * 4 _ _ _ | _ _ _ | _ _ _
	 * 5 _ _ _ | _ _ _ | _ _ _
	 * 6 _ _ _ | _ _ _ | _ _ _
	 * 7 _ _ _ | _ _ _ | _ _ _
	 * 8 _ _ _ | _ _ _ | _ _ _
	 * 9 _ _ _ | _ _ _ | _ _ _

	 */
	public static Position getPositionForIndex(final Integer gridSize, final Integer index) {
		
		final Integer row = index / gridSize;
		final Integer column = index % gridSize;
		return Position.of(column, row);
	}
}
