/******************************************************************************
 *                                                                             *
 *  Copyright: (c) Syncleus, Inc.                                              *
 *                                                                             *
 *  You may redistribute and modify this source code under the terms and       *
 *  conditions of the Open Source Community License - Type C version 1.0       *
 *  or any later version as published by Syncleus, Inc. at www.syncleus.com.   *
 *  There should be a copy of the license included with this file. If a copy   *
 *  of the license is not included you are granted no right to distribute or   *
 *  otherwise use this file except through a legal and valid license. You      *
 *  should also contact Syncleus, Inc. at the information below if you cannot  *
 *  find a license:                                                            *
 *                                                                             *
 *  Syncleus, Inc.                                                             *
 *  2604 South 12th Street                                                     *
 *  Philadelphia, PA 19148                                                     *
 *                                                                             *
 ******************************************************************************/
package com.syncleus.dann.examples.tsp;

import java.util.List;
import java.util.SortedSet;
import com.syncleus.dann.genetics.AbstractGeneticAlgorithmFitnessFunction;
import com.syncleus.dann.genetics.AbstractValueGene;
import com.syncleus.dann.math.Vector;

public class TravellingSalesmanFitnessFunction extends AbstractGeneticAlgorithmFitnessFunction<TravellingSalesmanFitnessFunction>
{
	public static final int MINIMUM_CITIES = 4;
	private final Vector[] cities;
	private double totalDistance = 0.0;

	public TravellingSalesmanFitnessFunction(final TravellingSalesmanChromosome chromosome, final Vector[] cities)
	{
		super(chromosome);

		if(chromosome == null)
			throw new IllegalArgumentException("chromosome can not be null");
		if(cities == null)
			throw new IllegalArgumentException("cities can not be null");
		if(cities.length < MINIMUM_CITIES)
			throw new IllegalArgumentException("cities must have atleast " + MINIMUM_CITIES + " elements");
		if(chromosome.getGeneCount() != cities.length)
			throw new IllegalArgumentException("Cities must have the same number of elements as genes in the chromosome");

		this.cities = cities.clone();
	}

	@Override
	public TravellingSalesmanChromosome getChromosome()
	{
		return ((TravellingSalesmanChromosome)(super.getChromosome()));
	}

	/**
	 * Evaluates the fitness of the chromosome being wrapped relative to the
	 * specified chromosome.
	 *
	 * @param compareWith The fitness function containing a chromosome to
	 * compare to.
	 * @return If this chromosome is more fit it will return a
	 * positive value, if it is less fit it will be negative. If they are
	 * both equally as fit it will return 0.
	 * @since 2.0
	 */
	@Override
	public int compareTo(final TravellingSalesmanFitnessFunction compareWith)
	{
		if(this.totalDistance < compareWith.totalDistance)
			return 1;
		else if(this.totalDistance > compareWith.totalDistance)
			return -1;
		else
			return 0;
	}

	/**
	 * Called once after the class is initialized in case child implementations
	 * want to cash a value for compareTo. This must be thread safe.
	 *
	 * @since 2.0
	 */
	@Override
	public synchronized void process()
	{
		final SortedSet<AbstractValueGene> sortedGenes = this.getChromosome().getSortedGenes();
		final List<AbstractValueGene> indexedGenes = this.getChromosome().getGenes();

		//calculate the distance going through the genes sorted by city priority
		double currentDistance = 0.0;
		Vector firstPosition = null;
		Vector lastPosition = null;
		for(AbstractValueGene sortedGene : sortedGenes)
		{
			final Vector currentPosition = this.cities[indexedGenes.indexOf(sortedGene)];
			if(lastPosition == null)
			{
				lastPosition = currentPosition;
				firstPosition = currentPosition;
			}
			else
			{
				currentDistance += lastPosition.calculateRelativeTo(currentPosition).getDistance();
				lastPosition = currentPosition;
			}
		}
		currentDistance += firstPosition.calculateRelativeTo(lastPosition).getDistance();

		this.totalDistance = currentDistance;
	}
}
