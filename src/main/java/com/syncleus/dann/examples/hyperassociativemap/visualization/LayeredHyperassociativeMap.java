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
package com.syncleus.dann.examples.hyperassociativemap.visualization;

import java.util.concurrent.ExecutorService;
import com.syncleus.dann.graph.drawing.hyperassociativemap.HyperassociativeMap;

public class LayeredHyperassociativeMap extends HyperassociativeMap<SimpleGraph, SimpleNode>
{
	private static final int NODES_PER_LAYER = 16;
	private static final int DIMENSION = 3;

	LayeredHyperassociativeMap(final int layers, final ExecutorService executor)
	{
		super(new SimpleGraph(layers, NODES_PER_LAYER), DIMENSION, executor);
	}
}
