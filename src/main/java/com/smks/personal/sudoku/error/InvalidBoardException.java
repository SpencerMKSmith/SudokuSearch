package com.smks.personal.sudoku.error;

public class InvalidBoardException extends RuntimeException {

	private static final long serialVersionUID = -645046106930763125L;

    public InvalidBoardException(final String message) {
        super(message);
    }
}
