package org.uncommons.watchmaker.framework;

import java.util.List;

public interface EvaluationStrategy<T>
{
	/**
     * Takes a population, assigns a fitness score to each member and returns
     * the members with their scores attached, sorted in descending order of
     * fitness (descending order of fitness score for natural scores, ascending
     * order of scores for non-natural scores).
     * @param population The population to evaluate (each candidate is assigned
     * a fitness score).
     * @return The evaluated population (a list of candidates with attached fitness
     * scores).
     */
	List<EvaluatedCandidate<T>> evaluatePopulation(List<T> population);
	
	/**
     * <p>Specifies whether this evaluation strategy generates <i>natural</i> fitness
     * scores or not.</p>
     * <p>Natural fitness scores are those in which the fittest
     * individual in a population has the highest fitness value.  In this
     * case the algorithm is attempting to maximise fitness scores.
     * There need not be a specified maximum possible value.</p>
     * <p>In contrast, <i>non-natural</i> fitness evaluation results in fitter
     * individuals being assigned lower scores than weaker individuals.
     * In the case of non-natural fitness, the algorithm is attempting to
     * minimise fitness scores.</p>
     * <p>An example of a situation in which non-natural fitness scores are
     * preferable is when the fitness corresponds to a cost and the algorithm
     * is attempting to minimise that cost.</p>
     * <p>The terminology of <i>natural</i> and <i>non-natural</i> fitness scores
     * is introduced by the Watchmaker Framework to describe the two types of fitness
     * scoring that exist within the framework.  It does not correspond to either
     * <i>standardised fitness</i> or <i>normalised fitness</i> in the EA
     * literature.  Standardised fitness evaluation generates non-natural
     * scores with a score of zero corresponding to the best possible fitness.
     * Normalised fitness evaluation is similar to standardised fitness but
     * with the scores adjusted to fall within the range 0 - 1.</p>
     * @return True if a high fitness score means a fitter candidate
     * or false if a low fitness score means a fitter candidate.
     */
	boolean isNatural();
	
	/**
     * By default, fitness evaluations are performed on separate threads (as many as there are
     * available cores/processors).  Use this method to force evaluation to occur synchronously
     * on the request thread.  This is useful in restricted environments where programs are not
     * permitted to start or control threads.  It might also lead to better performance for
     * programs that have extremely lightweight/trivial fitness evaluations.
     * @param singleThreaded If true, fitness evaluations will be performed synchronously on the
     * request thread.  If false, fitness evaluations will be performed by worker threads.
     */
	public void setSingleThreaded(boolean singleThreaded);
}
