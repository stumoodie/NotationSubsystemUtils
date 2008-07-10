package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import org.pathwayeditor.businessobjects.constants.LineStyle;
import org.pathwayeditor.contextadapter.publicapi.IContext;
import org.pathwayeditor.contextadapter.publicapi.ILinkEndDefinition;
import org.pathwayeditor.contextadapter.publicapi.ILinkObjectType;
import org.pathwayeditor.contextadapter.publicapi.IPropertyDefinition;
import org.pathwayeditor.contextadapter.publicapi.IPropertyDefinitionFilter;


public final class LinkObjectType implements ILinkObjectType {
	private final PropertyDefinitionFilter propertyDefinitionFilter;
	private String description;
	private String detailedDescription;
	private int lineColourBlue;
	private int lineColourGreen;
	private int lineColourRed;
	private LineStyle lineStyle;
	private int lineWidth;
	private final LinkConnectionRules linkConnectionRules;
	private final LinkEndDefinition linkSourceEndDefinition;
	private final LinkEndDefinition linkTargetEndDefinition;
	private String name;
	private boolean lineStyleEditable;
	private boolean lineColourEditable;
	private boolean lineWidthEditable;
	private final IContext context;
	private final int typeCode;
	private final String typeName;
	private String url; 
	
	/**
	 * @deprecated use another constructor that avoids the enumerated type. This is here for backwards
	 * compatibility with old OT mechanism. 
	 * @param context
	 * @param type
	 */
	public LinkObjectType(IContext context, Enum<?> type){
		this(context, (type == null) ? null : type.name(), (type == null) ? 0 : type.ordinal());
	}

	public LinkObjectType(IContext context, String typeName, int typeCode){
		if(context == null || typeName == null) throw new IllegalArgumentException("arguments null");
		this.context = context;
		this.typeCode = typeCode;
		this.typeName = typeName;
		this.linkSourceEndDefinition = new LinkEndDefinition(this, ILinkEndDefinition.LinkEndType.SOURCE);
		this.linkTargetEndDefinition = new LinkEndDefinition(this, ILinkEndDefinition.LinkEndType.TARGET);
		this.linkConnectionRules = new LinkConnectionRules(this);
		this.propertyDefinitionFilter = new PropertyDefinitionFilter(this);
	}
	
	public void addProperty(IPropertyDefinition propertyDefinition) {
		this.propertyDefinitionFilter.add(propertyDefinition);
	}

	public String getDescription() {
		return this.description;
	}

	public String getDetailedDescription() {
		return this.detailedDescription;
	}

	public int getLineColourBlue() {
		return this.lineColourBlue;
	}

	public int getLineColourGreen() {
		return this.lineColourGreen;
	}

	public int getLineColourRed() {
		return this.lineColourRed;
	}

	public LineStyle getLineStyle() {
		return this.lineStyle;
	}

	public int getLineWidth() {
		return this.lineWidth;
	}

	public LinkConnectionRules getLinkConnectionRules() {
		return this.linkConnectionRules;
	}

	public LinkEndDefinition getLinkSource() {
		return this.linkSourceEndDefinition;
	}

	public LinkEndDefinition getLinkTarget() {
		return this.linkTargetEndDefinition;
	}

	public String getName() {
		return this.name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}

	public void setLineProperty(int lineWidth, LineStyle lineStyle, int red,
			int green, int blue) {
		this.lineWidth = lineWidth;
		this.lineStyle = lineStyle;
		this.lineColourRed = red;
		this.lineColourGreen = green;
		this.lineColourBlue = blue;
	}

	public void setName(String defaultNameText) {
		this.name = defaultNameText;
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

	public IPropertyDefinitionFilter getPropertyDefinitionFilter() {
		return this.propertyDefinitionFilter;
	}

	public boolean isLineStyleEditable() {
		return lineStyleEditable;
	}

	public void setLineStyleEditable(boolean lineStyleEditable) {
		this.lineStyleEditable = lineStyleEditable;
	}

	public boolean isLineColourEditable() {
		return lineColourEditable;
	}

	public void setLineColourEditable(boolean lineColourEditable) {
		this.lineColourEditable = lineColourEditable;
	}

	public boolean isLineWidthEditable() {
		return lineWidthEditable;
	}

	public void setLineWidthEditable(boolean lineWidthEditable) {
		this.lineWidthEditable = lineWidthEditable;
	}

	public String getURL() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
		final LinkObjectType other = (LinkObjectType) obj;
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
