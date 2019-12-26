/*
 * The MIT License
 *
 * Copyright 2019 kzr.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package aima.core.environment.atributos;

import aima.core.search.framework.problem.GoalTest;
import aima.core.search.local.FitnessFunction;
import aima.core.search.local.Individual;
import aima.core.util.datastructure.XYLocation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 *
 * @author kzr
 */
public class SelAttGenAlgoUtil {
    
    
	public static FitnessFunction<Integer> getFitnessFunction() {
		return new SelAttFitnessFunction();
	}
	
	public static GoalTest getGoalTest() {
		return new SelAttGoalTest();
	}
	
        //generamos un individuo aleatorio
	public static Individual<Integer> generateRandomIndividual(int boardSize) {
		List<Integer> individualRepresentation = new ArrayList<Integer>();
                int aux;
		for (int i = 0; i < boardSize; i++) {
                        aux = 0;
                        if(Math.random()< 0.5){
                            aux = 1;
                        }
			individualRepresentation.add(aux);
		}
		Individual<Integer> individual = new Individual<Integer>(individualRepresentation);
		return individual;
	}

	public static Collection<Integer> getFiniteAlphabetForBoardOfSize(int size) {
		Collection<Integer> fab = new ArrayList<Integer>();

		for (int i = 0; i < size; i++) {
			fab.add(i);
		}

		return fab;
	}
	//Algoritmo CFS
	public static class SelAttFitnessFunction implements FitnessFunction<Integer> {

		public double apply(Individual<Integer> individual) {
			double fitness = 0;
                        //Para poder calcular que tan bueno es nuestra solución
                        // debemos de usar su propia clase Board
			SelAttBoard board = getBoardForIndividual(individual);
			

			return fitness;
		}
	}

        //devuelve el board de la clase
	public static SelAttBoard getBoardForIndividual(Individual<Integer> individual) {
		int boardSize = individual.length();
		SelAttBoard board = new SelAttBoard(boardSize);
		

		return board;
	}
    
}
