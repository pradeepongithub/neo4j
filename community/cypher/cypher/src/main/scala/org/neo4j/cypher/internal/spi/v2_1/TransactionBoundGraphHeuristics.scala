/**
 * Copyright (c) 2002-2014 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.cypher.internal.spi.v2_1

import org.neo4j.kernel.api.heuristics.Heuristics
import org.neo4j.cypher.internal.compiler.v2_1.spi.GraphHeuristics
import org.neo4j.cypher.internal.compiler.v2_1.{RelTypeId, LabelId}
import org.neo4j.graphdb.Direction

class TransactionBoundGraphHeuristics(heuristics: Heuristics) extends GraphHeuristics {

  def numNodes() =
    ( heuristics.liveNodesRatio() * heuristics.maxAddressableNodes() ).toInt

  def numNodesWithLabel(labelId: LabelId) =
    ( heuristics.labelDistribution().get( labelId.id ) * numNodes ).toInt

  def numNodesWithRelationshipType(relTypeId: RelTypeId): Int =
    ??? // numNodesForRatio( heuristics.relationshipTypeDistribution().get( relTypeId.id ) )

  def avgDegreeByLabelTypeAndDirection(labelId: LabelId, relTypeId: RelTypeId, direction: Direction): Double =
    heuristics.degree( labelId.id, relTypeId.id, direction )
}
