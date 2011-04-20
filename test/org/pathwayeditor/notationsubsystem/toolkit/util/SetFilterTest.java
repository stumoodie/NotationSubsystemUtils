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

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SetFilterTest {
	private SetFilter<String> testInstance;
	private static final String TEST_SET_ARR[] = { "P1", "F2", "P3", "P4", "F5" };
	private static final String EXPECTED_SET_ARR[] = { "P1", "P3", "P4" };
	private static final int EXPECTED_NUM_ELEMENTS = 3;
	private Set<String> testSet;
	private Set<String> expectedSet;
	
	@Before
	public void setUp() throws Exception {
		this.testInstance = new SetFilter<String>(new IFilterCriteria<String>(){

			@Override
			public boolean passes(String item) {
				return item.startsWith("P");
			}
			
		});
		this.testSet = new TreeSet<String>(Arrays.asList(TEST_SET_ARR));
		this.expectedSet = new TreeSet<String>(Arrays.asList(EXPECTED_SET_ARR));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=java.lang.IllegalArgumentException.class)
	public final void testSetFilterWithNullParam() {
		new SetFilter<String>(null);
	}

	@Test
	public final void testFilterSet() {
		Set<String> filteredSet = this.testInstance.filterSet(this.testSet);
		assertEquals("setsMatch", expectedSet, filteredSet);
		assertEquals("setsSameSize", EXPECTED_NUM_ELEMENTS, filteredSet.size());
	}

	@Test(expected=java.lang.IllegalArgumentException.class)
	public final void testFilterSetWithNullParam() {
		this.testInstance.filterSet(null);
	}
}
