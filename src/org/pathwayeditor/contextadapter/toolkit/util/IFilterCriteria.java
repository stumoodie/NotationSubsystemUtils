package org.pathwayeditor.contextadapter.toolkit.util;

public interface IFilterCriteria<T> {
	boolean passes(T item);
}
