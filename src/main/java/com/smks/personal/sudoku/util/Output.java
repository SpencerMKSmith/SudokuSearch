package com.smks.personal.sudoku.util;

import java.util.List;

import com.smks.personal.sudoku.data.Cell;
import com.smks.personal.sudoku.data.Unit;

public class Output {

	public static void printCellList(final List<Cell> cells) {
		
		for(int row = 0; row < 9; row++) {
			for(int item = 1; item <= 9; item++) {
				int cellNumber = row * 9 + item;
				int cellIndex = cellNumber - 1;
				
				printCell(cells.get(cellIndex));
				
				if(item % 3 == 0) System.out.print(" | ");
			}
			System.out.println("\n");

		}
	}
	
	public static void printRows(final List<Unit> rows) {
		for(final Unit unit : rows) {
			for(final Cell cell : unit.getItemsInUnit()) {
				printCell(cell);
			}
			System.out.println("\n");
		}
	}
	
	public static void printCell(final Cell cell) {
		if(cell.getValue().isPresent()) System.out.print(" " + cell.getValue().get() + " ");
		else System.out.print(" _ ");
	}
}
