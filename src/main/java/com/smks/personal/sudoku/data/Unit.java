package com.smks.personal.sudoku.data;

import java.util.List;

import lombok.Value;

@Value
public class Unit {
	UnitType unitType;
	List<Cell> itemsInUnit;
}
