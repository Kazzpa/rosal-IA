package aima.core.environment.atributos;

import aima.core.search.framework.problem.GoalTest;
import aima.core.search.local.FitnessFunction;
import aima.core.search.local.Individual;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
            if (Math.random() < 0.5) {
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
            //int numAtr = board.getNumberOfAtributesSelected();
            double corClass = 0;
            double corAtr = 1;
            int[] boardState = board.getState();
            int[] stateAux = new int[boardState.length];
            int numAtr = 0;
            //creamos un array auxiliar para operar mas facilmente que contendra los indices
            for (int i = 0; i < boardState.length; i++) {
                if (boardState[i] == 1) {
                    stateAux[numAtr] = i;
                    numAtr++;
                }
            }
            //calcular acumulacion de correlacion
            int numCor = 0;
            if (numAtr > 1) {
                for (int i = 0; i < stateAux.length; i++) {
                    corClass += board.getCorrelationClass(stateAux[i]);
                    if (i < stateAux.length - 1) {
                        for (int j = i + 1; j < stateAux.length; j++) {
                            corAtr += board.getCorrelationAtr(stateAux[i], stateAux[j]);
                            numCor++;
                        }
                    }
                }
            } else {
                corClass = board.getCorrelationClass(stateAux[0]);
            }
            //aplicar la media de las correlaciones
            corClass = corClass / numAtr;
            corAtr = corAtr / numCor;
            //Aplicamos el algoritmo CFS
            fitness = (numAtr * corClass) / Math.sqrt(numAtr + ((numAtr * (numAtr - 1)) * corAtr));
            return fitness;
        }
    }

    //devuelve el board de la clase
    public static SelAttBoard getBoardForIndividual(Individual<Integer> individual) {
        int boardSize = individual.length();
        SelAttBoard board = new SelAttBoard();
        for (int i = 0; i < boardSize; i++) {
            if (individual.getRepresentation().get(i) == 1) {
                board.SelAtribute(i);
            }
        }
        return board;
    }

}
