/*
 *  Copyright (c) 2009,  Mark Logic Corporation.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *  The use of the Apache License does not indicate that this project is
 *  affiliated with the Apache Software Foundation.
 */

package com.marklogic.jcr.compat;

import org.apache.jackrabbit.core.state.NodeState;
import org.apache.jackrabbit.core.state.PropertyState;
import org.apache.jackrabbit.core.NodeId;
import org.apache.jackrabbit.core.PropertyId;
import org.apache.jackrabbit.core.value.BLOBFileValue;
import org.apache.jackrabbit.core.value.InternalValue;
import org.apache.jackrabbit.spi.NameFactory;
import org.apache.jackrabbit.spi.commons.name.NameFactoryImpl;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: Jul 16, 2008
 * Time: 6:53:40 PM
 */
public class PMAdapter14 implements PMAdapter
{
	private final NameFactory factory = NameFactoryImpl.getInstance();

	public NodeState newNodeState (NodeId id)
	{
		return new NodeState (id, null, null, NodeState.STATUS_NEW, false);
	}

	public void setNodeTypeName (NodeState state, String name)
	{
		state.setNodeTypeName (factory.create (name));
	}

	public String getTypeNameAsString (NodeState state)
	{
		return state.getNodeTypeName().toString();
	}

	public String getPropertyStateNameAsString (PropertyState state)
	{
		return state.getName().toString();
	}

	public String getChildNodeEntryAsString (NodeState.ChildNodeEntry entry)
	{
		return entry.getName().toString();
	}

	public String getPropertyIdNameAsString (PropertyId propId)
	{
		return propId.getName().toString();
	}

	public void addPropertyName (NodeState state, String name)
	{
		state.addPropertyName (factory.create (name));
	}

	public void addChildNodeEntry (NodeState state, String childName, NodeId nodeId)
	{
		state.addChildNodeEntry (factory.create (childName), nodeId);
	}

	public void addName (Set set, String name)
	{
		set.add (factory.create (name));
	}

	public boolean sameNodeTypeName (NodeState state, String name)
	{
		return factory.create (name).equals (state.getNodeTypeName());
	}

	public boolean samePropertyName (PropertyState state, String name)
	{
		return state.getName().equals (factory.create (name));
	}

	public BLOBFileValue getBlobFileValue (InternalValue val)
	{
		return val.getBLOBFileValue();
	}

	public String propertyHashKey (PropertyState state)
	{
		return state.getParentId().getUUID().toString() + "|" + state.getName().toString();
	}
}
