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
package org.pathwayeditor.notationsubsystem.toolkit.ndom;

/**
 * @author nhanlon
 *  Throw if NDOM building fails. If building the NDOM succeeds but errors exist, these 
 *  should be set into the ReportBuilder as failed rules and no NdomException should be thrown.
 */
public class NdomException extends Exception {

	private static final long serialVersionUID = 7599384053290533617L;

	public NdomException() {
		super();
	}

	public NdomException(String message, Throwable cause) {
		super(message, cause);
	}

	public NdomException(String message) {
		super(message);
	}

	public NdomException(Throwable cause) {
		super(cause);
	}

}