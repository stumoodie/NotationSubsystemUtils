/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/
package org.pathwayeditor.notationsubsystem.toolkit.util;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Utility class to create a new Set by filtering out a larger set. the filtering criteria is defined
 * by implementing the <code>IFilterCriteria</code> interface.
 * @author smoodie
 *
 * @param <T> The type of the object contained in the Set to be filtered. That is the unfiltered set should be 
 * of the form type <code>Set<T></code>
 */
public final class SetFilter<T> {
	private final IFilterCriteria<T> filterCriteria;

	/**
	 * Creates the class with a specific filter criteria.
	 * @param filterCriteria an implementation of <code>IFilterCriteria<T></code> that defines the filter riteria. It cannot be null.
	 * @throws IllegalArgumentException if <code>filterCriteria</code> is null. 
	 */
	public SetFilter(IFilterCriteria<T> filterCriteria){
		if(filterCriteria == null){
			throw new IllegalArgumentException("Parameter filterCriteria cannot be null");
		}
		this.filterCriteria = filterCriteria;
	}
	
	/**
	 * Creates a set by filtering the unfilteredSet based on the match criteria specified by
	 *  <code>IFilterCriteria</code>.
	 * @param unfilteredSet A set to be filtered, which can be empty, but cannot be null.
	 * @return a set of items matching the filter criteria or an empty set if there are none.
	 * @throws IllegalArgumentException if <code>unfilteredSet</code> is null.
	 */
	public Set<T> filterSet(Set<T> unfilteredSet){
		if(unfilteredSet == null){
			throw new IllegalArgumentException("Parameter unfilteredSet cannot be null");
		}
		Iterator<T> iter = unfilteredSet.iterator();
		Set<T> retVal = new TreeSet<T>();
		while(iter.hasNext()){
			T item = iter.next();
			if(filterCriteria.passes(item) == true){
				retVal.add(item);
			}
		}
		return retVal;
	}
}
