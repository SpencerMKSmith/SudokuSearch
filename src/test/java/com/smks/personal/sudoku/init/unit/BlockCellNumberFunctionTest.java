package com.smks.personal.sudoku.init.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.smks.personal.sudoku.data.Position;

public class BlockCellNumberFunctionTest {

	@Test
	public void test_BlockCellNumberFunction() {
		final CellPositionFunction function = new BlockCellPositionFunction();
		
		assertEquals(Position.of(0, 0), function.getCellPositionForUnitAndItem(9, 0, 0));
		assertEquals(Position.of(3, 0), function.getCellPositionForUnitAndItem(9, 1, 0));
		assertEquals(Position.of(0, 3), function.getCellPositionForUnitAndItem(9, 3, 0));
		assertEquals(Position.of(6, 3), function.getCellPositionForUnitAndItem(9, 5, 0));
		assertEquals(Position.of(8, 4), function.getCellPositionForUnitAndItem(9, 5, 5));
		assertEquals(Position.of(2, 6), function.getCellPositionForUnitAndItem(9, 6, 2));
		assertEquals(Position.of(4, 7), function.getCellPositionForUnitAndItem(9, 7, 4));
		assertEquals(Position.of(8, 8), function.getCellPositionForUnitAndItem(9, 8, 8));
	}
}
