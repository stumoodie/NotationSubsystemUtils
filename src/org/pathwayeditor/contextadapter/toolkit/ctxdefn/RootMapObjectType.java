package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import org.pathwayeditor.contextadapter.publicapi.IContext;
import org.pathwayeditor.contextadapter.publicapi.IRootMapObjectType;


public final class RootMapObjectType implements IRootMapObjectType {
	private final IContext context;
	private final int typeCode;
	private final String typeName;
	private final RootMapParentingRules parentingRules;
	
	/**
	 * @deprecated use another constructor that avoids the enumerated type. This is here for backwards
	 * compatibility with old OT mechanism. 
	 * @param context
	 * @param type
	 */
	public RootMapObjectType(IContext context, Enum<?> type){
		this(context, (type == null) ? null : type.name(), (type == null) ? -1 : type.ordinal());
	}
	
	public RootMapObjectType(IContext context, String typeName, int typeCode){
		if(context == null || typeName == null) throw new IllegalArgumentException("parameters are null");
		this.context = context;
		this.typeCode = typeCode;
		this.typeName = typeName;
		this.parentingRules = new RootMapParentingRules(this);
	}
	
	public IContext getContext() {
		return this.context;
	}

	public int getTypeCode() {
		return this.typeCode;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public RootMapParentingRules getShapeParentingRules() {
		return this.parentingRules;
	}
	public RootMapParentingRules getParentingRules(){
		return getShapeParentingRules();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((context == null) ? 0 : context.hashCode());
		result = prime * result + typeCode;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final RootMapObjectType other = (RootMapObjectType) obj;
		if (context == null) {
			if (other.context != null)
				return false;
		} else if (!context.equals(other.context))
			return false;
		if (typeCode != other.typeCode)
			return false;
		return true;
	}

}
