/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aima.core.environment.selatt;

import aima.core.search.framework.problem.GoalTest;
import aima.core.search.local.FitnessFunction;
import aima.core.search.local.Individual;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author kzr
 */
public class SelAttDemo {

    private static final int boardSize = 9;

    public static void main(String[] args) {

        newSelAttDemo();
    }

    private static void newSelAttDemo() {

        selAttGeneticAlgorithmSearch();
    }

    public static void selAttGeneticAlgorithmSearch() {
        System.out.println("\nSelAttDemo GeneticAlgorithm  -->");
        try {
            FitnessFunction<Integer> fitnessFunction = SelAttGenAlgoUtil.getFitnessFunction();
            GoalTest goalTest = SelAttGenAlgoUtil.getGoalTest();

            //poblacion,tasa de mutacion y tiempo de ejecucion
            double parameters[][] = {{5, 10, 15, 20, 25, 30},
            {0, 0.01, 0.10, 0.20, 0.30},
            {10L, 20L, 30L, 50L}};
            //iteraremos sobre estos parametros para encontrar los mejores
            for (int i = 0; i < parameters[2].length; i++) {
                for (int j = 0; j < parameters[0].length; j++) {
                    for (int k = 0; k < parameters[1].length; k++) {
                        //realizamos una serie de iteraciones para medir mejor los resultados obtenidos
                        double auxFitness = 0;
                        double auxExtraRate = 0;
                        double auxIterations = 0;
                        for (int l = 0; l < 5; l++) {

                            // Generate an initial population
                            Set<Individual<Integer>> population = new HashSet<Individual<Integer>>();
                            int populationSize = (int) parameters[0][j];
                            for (int w = 0; w < populationSize; w++) {
                                population.add(SelAttGenAlgoUtil.generateRandomIndividual(boardSize));
                            }

                            GeneticAlgorithmSelAtt<Integer> ga = new GeneticAlgorithmSelAtt<Integer>(boardSize,
                                    SelAttGenAlgoUtil.getFiniteAlphabetForBoardOfSize(boardSize), parameters[1][k]);

                            // Run for a set amount of time 
                            Individual<Integer> bestIndividual
                                    = ga.geneticAlgorithm(population, fitnessFunction, goalTest, (long) parameters[2][i]);
                            auxFitness += fitnessFunction.apply(bestIndividual);
                            auxExtraRate += ga.getExtraIndRate();
                            auxIterations += ga.getIterations();
                            if (l == 4) {
                                auxFitness = auxFitness / 5;
                                auxExtraRate = auxExtraRate / 5;
                                auxIterations = auxIterations / 5;
                                System.out.println("\nMax Time (1 second) Best Individual=\n"
                                        + SelAttGenAlgoUtil.getBoardForIndividual(bestIndividual));
                                System.out.println("Board Size      = " + boardSize);
                                System.out.println("# Board Layouts = " + (new BigDecimal(boardSize)).pow(boardSize));
                                System.out.println("Fitness         = " + auxFitness);
                                System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
                                System.out.println("Population Size = " + ga.getPopulationSize());
                                System.out.println("Iterations      = " + auxIterations);
                                //Indicamos tambien el porcentaje de individuos creados
                                //nuevos al haber sido rechazado uno anteriormente
                                System.out.print("Extra rate      = ");
                                DecimalFormat numberFormat = new DecimalFormat("#0.0");
                                System.out.println(numberFormat.format(auxExtraRate) + "%");
                                System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");
                                System.out.println("Mutation rate   = " + parameters[1][k]);
                            }
                        }
                    }
                }
            }
            // Generate an initial population
            Set<Individual<Integer>> population = new HashSet<Individual<Integer>>();
            int populationSize = 5;
            for (int w = 0; w < populationSize; w++) {
                population.add(SelAttGenAlgoUtil.generateRandomIndividual(boardSize));
            }

            GeneticAlgorithmSelAtt<Integer> ga = new GeneticAlgorithmSelAtt<Integer>(boardSize,
                    SelAttGenAlgoUtil.getFiniteAlphabetForBoardOfSize(boardSize), 0.2);

            // Run for a set amount of time 
            Individual<Integer> bestIndividual
                    = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 10L);
            System.out.println("----------------------------------\n"
                    + "Best solution with those parameters:");
            System.out.println("\nMax Time (1 second) Best Individual=\n"
                    + SelAttGenAlgoUtil.getBoardForIndividual(bestIndividual));
            System.out.println("Board Size      = " + boardSize);
            System.out.println("# Board Layouts = " + (new BigDecimal(boardSize)).pow(boardSize));
            System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
            System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
            System.out.println("Population Size = " + ga.getPopulationSize());
            System.out.println("Iterations      = " + ga.getIterations());
            //Indicamos tambien el porcentaje de individuos creados
            //nuevos al haber sido rechazado uno anteriormente
            System.out.print("Extra rate      = ");
            DecimalFormat numberFormat = new DecimalFormat("#0.0");
            System.out.println(numberFormat.format(ga.getExtraIndRate()) + "%");
            System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");
            System.out.println("Mutation rate   = " + 0.2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
