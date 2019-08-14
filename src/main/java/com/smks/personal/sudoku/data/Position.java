package com.smks.personal.sudoku.data;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(staticName = "of")
public class Position {
	Integer column;
	Integer row;
}
