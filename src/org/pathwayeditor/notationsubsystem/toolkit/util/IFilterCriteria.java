package org.pathwayeditor.notationsubsystem.toolkit.util;

public interface IFilterCriteria<T> {
	boolean passes(T item);
}
