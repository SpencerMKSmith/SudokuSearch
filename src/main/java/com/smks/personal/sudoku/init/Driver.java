package com.smks.personal.sudoku.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.eliminators.Eliminator;
import com.smks.personal.sudoku.puzzles.Puzzles;
import com.smks.personal.sudoku.solver.Solver;
import com.smks.personal.sudoku.util.Output;

@Component
@ComponentScan("com.smks.personal.sudoku")
public class Driver {

	@Autowired
	private Eliminator directPeerEliminator;
	@Autowired
	private Eliminator hiddenSingleEliminator;
	
	public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(Driver.class);
        var driver = ctx.getBean(Driver.class);
        driver.run();
	}
	
	public void run() {

		final Grid grid = GridInitializer.initializeGrid(Puzzles.Medium_Puzzle_1());
		
		Output.printGrid(grid);

		final Solver solver = new Solver();
		
		final Eliminator eliminatorFunction = directPeerEliminator.andThen(hiddenSingleEliminator);
		solver.solvePuzzle(grid, eliminatorFunction);
	}
}
