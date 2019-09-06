package com.smks.personal.sudoku.error;

import java.util.List;

import com.smks.personal.sudoku.data.CellUpdate;

public class ErrorResult {

	String message;
	Exception exception;
	List<CellUpdate> updatesPriorToError;
}
