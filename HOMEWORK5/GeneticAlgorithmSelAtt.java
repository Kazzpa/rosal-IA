/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aima.core.environment.selatt;

import aima.core.search.framework.Metrics;
import aima.core.search.framework.problem.GoalTest;
import aima.core.search.local.FitnessFunction;
import aima.core.search.local.Individual;
import aima.core.util.CancelableThread;
import aima.core.util.Util;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author kzr
 */
public class GeneticAlgorithmSelAtt<A> {

    protected static final String POPULATION_SIZE = "populationSize";
    protected static final String ITERATIONS = "iterations";
    protected static final String TIME_IN_MILLISECONDS = "timeInMSec";
    protected static final String INDIVIDUALS_FAIL = "individualFail";
    protected static final String INDIVIDUALS_SUCCESS = "individualSuccess";
    //
    protected Metrics metrics = new Metrics();
    //
    protected int individualLength;
    protected List<A> finiteAlphabet;
    protected double mutationProbability;

    protected Random random;
    private List<ProgressTracer<A>> progressTracers = new ArrayList<ProgressTracer<A>>();

    public GeneticAlgorithmSelAtt(int individualLength, Collection<A> finiteAlphabet, double mutationProbability) {
        this(individualLength, finiteAlphabet, mutationProbability, new Random());
    }

    public GeneticAlgorithmSelAtt(int individualLength, Collection<A> finiteAlphabet, double mutationProbability,
            Random random) {
        this.individualLength = individualLength;
        this.finiteAlphabet = new ArrayList<A>(finiteAlphabet);
        this.mutationProbability = mutationProbability;
        this.random = random;

        assert (this.mutationProbability >= 0.0 && this.mutationProbability <= 1.0);
    }

    /**
     * Progress tracers can be used to display progress information.
     */
    public void addProgressTracer(ProgressTracer<A> pTracer) {
        progressTracers.add(pTracer);
    }

    /**
     * Starts the genetic algorithm and stops after a specified number of
     * iterations.
     */
    public Individual<A> geneticAlgorithm(Collection<Individual<A>> initPopulation,
            FitnessFunction<A> fitnessFn, final int maxIterations) {
        GoalTest goalTest = new GoalTest() {
            @Override
            public boolean isGoalState(Object state) {
                return getIterations() >= maxIterations;
            }
        };
        return geneticAlgorithm(initPopulation, fitnessFn, goalTest, 0L);
    }

    /**
     * Template method controlling search. It returns the best individual in the
     * specified population, according to the specified FITNESS-FN and goal
     * test.
     *
     * @param population a set of individuals
     * @param fitnessFn a function that measures the fitness of an individual
     * @param goalTest test determines whether a given individual is fit enough
     * to return. Can be used in subclasses to implement additional termination
     * criteria, e.g. maximum number of iterations.
     * @param maxTimeMilliseconds the maximum time in milliseconds that the
     * algorithm is to run for (approximate). Only used if > 0L.
     * @return the best individual in the specified population, according to the
     * specified FITNESS-FN and goal test.
     */
    // function GENETIC-ALGORITHM(population, FITNESS-FN) returns an individual
    // inputs: population, a set of individuals
    // FITNESS-FN, a function that measures the fitness of an individual
    public Individual<A> geneticAlgorithm(Collection<Individual<A>> initPopulation, FitnessFunction<A> fitnessFn,
            GoalTest goalTest, long maxTimeMilliseconds) {
        Individual<A> bestIndividual = null;

        // Create a local copy of the population to work with
        List<Individual<A>> population = new ArrayList<Individual<A>>(initPopulation);
        // Validate the population and setup the instrumentation
        validatePopulation(population);
        updateMetrics(population, 0, 0L);

        long startTime = System.currentTimeMillis();

        // repeat
        int itCount = 0;
        do {
            population = nextGeneration(population, fitnessFn);
            bestIndividual = retrieveBestIndividual(population, fitnessFn);

            updateMetrics(population, ++itCount, System.currentTimeMillis() - startTime);

            // until some individual is fit enough, or enough time has elapsed
            if (maxTimeMilliseconds > 0L && (System.currentTimeMillis() - startTime) > maxTimeMilliseconds) {
                break;
            }
            if (CancelableThread.currIsCanceled()) {
                break;
            }
        } while (!goalTest.isGoalState(bestIndividual));

        notifyProgressTracers(itCount, population);
        // return the best individual in population, according to FITNESS-FN
        return bestIndividual;
    }

    public Individual<A> retrieveWorstIndividual(Collection<Individual<A>> population, FitnessFunction<A> fitnessFn) {
        Individual<A> worstIndividual = null;
        double worstSoFarFValue = Double.POSITIVE_INFINITY;

        for (Individual<A> individual : population) {
            double fValue = fitnessFn.apply(individual);
            if (fValue < worstSoFarFValue) {
                worstIndividual = individual;
                worstSoFarFValue = fValue;
            }
        }

        return worstIndividual;
    }

    public Individual<A> retrieveBestIndividual(Collection<Individual<A>> population, FitnessFunction<A> fitnessFn) {
        Individual<A> bestIndividual = null;
        double bestSoFarFValue = Double.NEGATIVE_INFINITY;

        for (Individual<A> individual : population) {
            double fValue = fitnessFn.apply(individual);
            if (fValue > bestSoFarFValue) {
                bestIndividual = individual;
                bestSoFarFValue = fValue;
            }
        }

        return bestIndividual;
    }

    /**
     * Sets the population size and number of iterations to zero.
     */
    public void clearInstrumentation() {
        updateMetrics(new ArrayList<Individual<A>>(), 0, 0L);
    }

    /**
     * Returns all the metrics of the genetic algorithm.
     *
     * @return all the metrics of the genetic algorithm.
     */
    public Metrics getMetrics() {
        return metrics;
    }

    /**
     * Returns the population size.
     *
     * @return the population size.
     */
    public int getPopulationSize() {
        return metrics.getInt(POPULATION_SIZE);
    }

    /**
     * Returns the number of iterations of the genetic algorithm.
     *
     * @return the number of iterations of the genetic algorithm.
     */
    public int getIterations() {
        return metrics.getInt(ITERATIONS);
    }
    public int getExtraIndividuals(){
        return metrics.getInt(INDIVIDUALS_FAIL);
    }
    public int getIndividuals(){
        return metrics.getInt(INDIVIDUALS_SUCCESS);
    }
    public double getExtraIndRate(){
        int success =  metrics.getInt(INDIVIDUALS_SUCCESS);
        int fail = metrics.getInt(INDIVIDUALS_FAIL);
        int suma = success + fail;
        double res = (double)fail / (double)suma;
        res = res * 100;
        return res;
    }
    /**
     *
     * @return the time in milliseconds that the genetic algorithm took.
     */
    public long getTimeInMilliseconds() {
        return metrics.getLong(TIME_IN_MILLISECONDS);
    }

    /**
     * Updates statistic data collected during search.
     *
     * @param itCount the number of iterations.
     * @param time the time in milliseconds that the genetic algorithm took.
     */
    protected void updateMetrics(Collection<Individual<A>> population, int itCount, long time) {
        metrics.set(POPULATION_SIZE, population.size());
        metrics.set(ITERATIONS, itCount);
        metrics.set(TIME_IN_MILLISECONDS, time);
    }
    protected void addExtraMetric(){
        metrics.set(INDIVIDUALS_FAIL,getExtraIndividuals()+1);
    }
    
            
    protected void addIndividualMetric(){
        metrics.set(INDIVIDUALS_SUCCESS,getIndividuals()+1);
    }
    //
    // PROTECTED METHODS
    //
    // Note: Override these protected methods to create your own desired
    // behavior.
    //
    /**
     * Primitive operation which is responsible for creating the next
     * generation. Override to get progress information!
     */
    protected List<Individual<A>> nextGeneration(List<Individual<A>> population, FitnessFunction<A> fitnessFn) {
        // new_population <- empty set
        List<Individual<A>> newPopulation = new ArrayList<Individual<A>>(population.size());

        Individual<A> bestIndividual = retrieveBestIndividual(population, fitnessFn);
        newPopulation.add(bestIndividual);
        Individual<A> worstIndividual = retrieveWorstIndividual(population, fitnessFn);
        double fValue = fitnessFn.apply(worstIndividual);
        // for i = 1 to SIZE(population) do
        for (int i = 1; i < population.size(); i++) {
            // x <- RANDOM-SELECTION(population, FITNESS-FN)
            boolean encontrado = false;
            while (!encontrado) {

                Individual<A> x = randomSelection(population, fitnessFn);
                // y <- RANDOM-SELECTION(population, FITNESS-FN)
                Individual<A> y = randomSelection(population, fitnessFn);
                // child <- REPRODUCE(x, y)
                Individual<A> child = reproduce(x, y);
                // if (small random probability) then child <- MUTATE(child)
                if (random.nextDouble() <= mutationProbability) {
                    child = mutate(child);
                }
                if (fitnessFn.apply(child) >= fValue) {
                    // add child to new_population if its better than worst individual
                    encontrado = true;
                    newPopulation.add(child);
                    addIndividualMetric();
                }else{
                    addExtraMetric();
                }
            }
        }
        notifyProgressTracers(getIterations(), population);
        return newPopulation;
    }

    // RANDOM-SELECTION(population, FITNESS-FN)
    protected Individual<A> randomSelection(List<Individual<A>> population, FitnessFunction<A> fitnessFn) {
        // Default result is last individual
        // (just to avoid problems with rounding errors)
        Individual<A> selected = population.get(population.size() - 1);

        // Determine all of the fitness values
        double[] fValues = new double[population.size()];
        for (int i = 0; i < population.size(); i++) {
            fValues[i] = fitnessFn.apply(population.get(i));
        }
        // Normalize the fitness values
        fValues = Util.normalize(fValues);
        double prob = random.nextDouble();
        double totalSoFar = 0.0;
        for (int i = 0; i < fValues.length; i++) {
            // Are at last element so assign by default
            // in case there are rounding issues with the normalized values
            totalSoFar += fValues[i];
            if (prob <= totalSoFar) {
                selected = population.get(i);
                break;
            }
        }

        selected.incDescendants();
        return selected;
    }

    // function REPRODUCE(x, y) returns an individual
    // inputs: x, y, parent individuals
    protected Individual<A> reproduce(Individual<A> x, Individual<A> y) {
        // n <- LENGTH(x);
        // Note: this is = this.individualLength
        List<A> childRepresentation = new ArrayList<A>();
        Iterator<A> itpadre = x.getRepresentation().iterator();
        Iterator<A> itmadre = y.getRepresentation().iterator();
        while (itpadre.hasNext() && itmadre.hasNext()) {
            //Aleatoriamente seleccionaremos si añadimos un bit del padre o de la madre
            if (Math.random() > 0.5) {
                childRepresentation.add(itpadre.next());
                itmadre.next();
            } else {
                childRepresentation.add(itmadre.next());
                itpadre.next();
            }
        }
        Individual<A> child = new Individual<A>(childRepresentation);
        return child;
    }

    protected Individual<A> mutate(Individual<A> child) {
        int mutateOffset = randomOffset(individualLength);
        int alphaOffset = randomOffset(finiteAlphabet.size());

        List<A> mutatedRepresentation = new ArrayList<A>(child.getRepresentation());

        mutatedRepresentation.set(mutateOffset, finiteAlphabet.get(alphaOffset));

        Individual<A> mutatedChild = new Individual<A>(mutatedRepresentation);

        return mutatedChild;
    }

    protected int randomOffset(int length) {
        return random.nextInt(length);
    }

    protected void validatePopulation(Collection<Individual<A>> population) {
        // Require at least 1 individual in population in order
        // for algorithm to work
        if (population.size() < 1) {
            throw new IllegalArgumentException("Must start with at least a population of size 1");
        }
        // String lengths are assumed to be of fixed size,
        // therefore ensure initial populations lengths correspond to this
        for (Individual<A> individual : population) {
            if (individual.length() != this.individualLength) {
                throw new IllegalArgumentException("Individual [" + individual
                        + "] in population is not the required length of " + this.individualLength);
            }
        }
    }

    private void notifyProgressTracers(int itCount, Collection<Individual<A>> generation) {
        for (ProgressTracer<A> tracer : progressTracers) {
            tracer.traceProgress(getIterations(), generation);
        }
    }

    /**
     * Interface for progress tracers.
     *
     * @author Ruediger Lunde
     */
    public interface ProgressTracer<A> {

        void traceProgress(int itCount, Collection<Individual<A>> population);
    }
}
