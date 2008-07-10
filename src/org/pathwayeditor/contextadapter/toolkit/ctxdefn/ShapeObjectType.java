package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import org.pathwayeditor.businessobjects.constants.LineStyle;
import org.pathwayeditor.businessobjects.constants.ShapeType;
import org.pathwayeditor.contextadapter.publicapi.IContext;
import org.pathwayeditor.contextadapter.publicapi.IPropertyDefinition;
import org.pathwayeditor.contextadapter.publicapi.IPropertyDefinitionFilter;
import org.pathwayeditor.contextadapter.publicapi.IShapeObjectType;


public final class ShapeObjectType implements IShapeObjectType {
	private final PropertyDefinitionFilter propertyDefinitionFilter;
	private final ShapeParentingRules shapeParentingRules;
	private final IContext context;
	private final int type;
	private final String typeName;
	private String description;
	private String detailedDescription;
	private int fillColourRed;
	private int fillColourGreen;
	private int fillColourBlue;
	//private Transparency fillTransparency;
	private int lineWidth;
	private LineStyle lineStyle;
	private int lineColourRed;
	private int lineColourGreen;
	private int lineColourBlue;
	private String name;
	private ShapeType shapeType;
	private int sizeWidth;
	private int sizeHeight;
	private String url;
	private boolean fillEditable;
	private boolean lineColourEditable;
	private boolean lineStyleEditable;
	private boolean lineWidthEditable;
	
	/**
	 * @deprecated use another constructor that avoids the enumerated type. This is here for backwards
	 * compatibility with old OT mechanism. 
	 * @param context
	 * @param type
	 */
	public ShapeObjectType(IContext context, Enum<?> type){
		this(context, (type == null) ? null : type.name(), (type == null) ? -1 : type.ordinal());
	}
	
	public ShapeObjectType(IContext context, String typeName, int type){
		if(context == null || typeName ==  null) throw new IllegalArgumentException("parameters cannot be null");
		this.context = context;
		this.type = type;
		this.typeName = typeName;
		this.shapeParentingRules = new ShapeParentingRules(this);
		this.propertyDefinitionFilter = new PropertyDefinitionFilter(this);
	}
	
	public void addProperty(IPropertyDefinition propertyDefinition) {
		this.propertyDefinitionFilter.add(propertyDefinition);
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public void setDetailedDescription(String detailedText) {
		this.detailedDescription = detailedText;
	}

	public void setFillProperty(int red, int green, int blue) {
		this.fillColourRed = red;
		this.fillColourGreen = green;
		this.fillColourBlue = blue;
	}

	public void setLineProperty(int lineWidth, LineStyle lineStyle,	int red, int green, int blue) {
		this.lineWidth = lineWidth;
		this.lineStyle = lineStyle;
		this.lineColourRed = red;
		this.lineColourGreen = green;
		this.lineColourBlue = blue;
	}

	public void setName(String defaultNameText) {
		this.name = defaultNameText;
	}

	public void setShapeType(ShapeType shapeType) {
		this.shapeType = shapeType;
	}

	public void setURL(String defaultURL) {
		this.url = defaultURL;
	}

	public int getTypeCode() {
		return this.type;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public IContext getContext() {
		return context;
	}

	public IPropertyDefinitionFilter getPropertiesFilter() {
		return this.propertyDefinitionFilter;
	}

	public int getLineWidth() {
		return lineWidth;
	}

	public LineStyle getLineStyle() {
		return lineStyle;
	}

	public void setSize(int width, int height) {
		this.sizeWidth = width;
		this.sizeHeight = height;
	}

	public String getURL() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	public String getDetailedDescription() {
		return detailedDescription;
	}

	public String getName() {
		return name;
	}

	public ShapeType getShapeType() {
		return shapeType;
	}


	public int getFillColourRed() {
		return fillColourRed;
	}

	public int getFillColourGreen() {
		return fillColourGreen;
	}

	public int getFillColourBlue() {
		return fillColourBlue;
	}

	public int getLineColourRed() {
		return lineColourRed;
	}

	public int getLineColourGreen() {
		return lineColourGreen;
	}

	public int getLineColourBlue() {
		return lineColourBlue;
	}

	public int getSizeWidth() {
		return sizeWidth;
	}

	public int getSizeHeight() {
		return sizeHeight;
	}

	public String getUrl() {
		return url;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((context == null) ? 0 : context.hashCode());
		result = prime * result + type;
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
		final ShapeObjectType other = (ShapeObjectType) obj;
		if (context == null) {
			if (other.context != null)
				return false;
		} else if (!context.equals(other.context))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	public ShapeParentingRules getParentingRules() {
		return this.shapeParentingRules;
	}

	public boolean isFillEditable() {
		return fillEditable;
	}

	public void setFillEditable(boolean fillEditable) {
		this.fillEditable = fillEditable;
	}

	public boolean isLineColourEditable() {
		return lineColourEditable;
	}

	public void setLineColourEditable(boolean lineColourEditable) {
		this.lineColourEditable = lineColourEditable;
	}

	public boolean isLineStyleEditable() {
		return lineStyleEditable;
	}

	public void setLineStyleEditable(boolean lineStyleEditable) {
		this.lineStyleEditable = lineStyleEditable;
	}

	public boolean isLineWidthEditable() {
		return lineWidthEditable;
	}

	public void setLineWidthEditable(boolean lineWidthEditable) {
		this.lineWidthEditable = lineWidthEditable;
	}

}
