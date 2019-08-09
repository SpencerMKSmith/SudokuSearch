package com.smks.personal.sudoku.init.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BlockCellNumberFunctionTest {

	@Test
	public void test_BlockCellNumberFunction() {
		final CellNumberFunction function = new BlockCellNumberFunction();
		
		assertEquals(1, function.cellNumberToExtract(9, 1, 1));
		assertEquals(4, function.cellNumberToExtract(9, 2, 1));
		assertEquals(28, function.cellNumberToExtract(9, 4, 1));
		assertEquals(34, function.cellNumberToExtract(9, 6, 1));
		assertEquals(55, function.cellNumberToExtract(9, 7, 1));
		assertEquals(68, function.cellNumberToExtract(9, 8, 5));
		assertEquals(81, function.cellNumberToExtract(9, 9, 9));
	}
}
