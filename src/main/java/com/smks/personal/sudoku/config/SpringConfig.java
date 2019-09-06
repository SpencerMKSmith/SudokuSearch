package com.smks.personal.sudoku.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.smks.personal.sudoku.eliminators.DirectPeerEliminator;
import com.smks.personal.sudoku.eliminators.Eliminator;
import com.smks.personal.sudoku.eliminators.HiddenSingleEliminator;
import com.smks.personal.sudoku.solver.SingleSetter;
import com.smks.personal.sudoku.util.CellGatherer;
import com.smks.personal.sudoku.util.GridStateManager;
import com.smks.personal.sudoku.validate.GridValidator;

@Configuration
public class SpringConfig {

	@Bean
	@Qualifier("directPeerEliminator")
	public Eliminator directPeerEliminator() {
		return new DirectPeerEliminator();
	}
	
	@Bean
	@Qualifier("hiddenSingleEliminator")
	public Eliminator hiddenSingleEliminator() {
		return new HiddenSingleEliminator();
	}

	@Bean
	public CellGatherer cellGatherer() {
		return new CellGatherer();
	}
	
	@Bean
	public GridValidator gridValidator() {
		return new GridValidator();
	}
	
	@Bean
	public SingleSetter singleSetter() {
		return new SingleSetter();
	}
	
	@Bean
	public GridStateManager gridStateManager() {
		return new GridStateManager();
	}

}
