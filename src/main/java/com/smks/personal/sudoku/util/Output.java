package com.smks.personal.sudoku.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.smks.personal.sudoku.data.Cell;
import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.data.Position;
import com.smks.personal.sudoku.data.Unit;

public class Output {

	public static void printGrid(final Grid grid) {
		final Set<Unit> rows = grid.getRows();
		final List<Unit> orderedRows = new ArrayList<>(rows);
		orderedRows.sort((a, b) -> a.getUnitIndex().compareTo(b.getUnitIndex()));
		
		IntStream.range(0, grid.getGridSize()).forEach(row -> {
			IntStream.range(0, grid.getGridSize()).forEach(column -> {
				final Position position = Position.of(column, row);
				final Cell cell = grid.getPositionToCellMap().get(position);
				if(column % 3 == 0) System.out.print(" | ");

				printCell(cell);
			});
			System.out.println("\n");
		});
		System.out.println("\n");

	}
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
	
	public static void printCell(final Cell cell) {
		if(cell.getValue().isPresent()) System.out.print(" " + cell.getValue().get() + " ");
		else System.out.print(" _ ");
	}
}
