package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import static org.junit.Assert.*;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.contextadapter.publicapi.IContext;
import org.pathwayeditor.contextadapter.publicapi.ILabelDefinition;
import org.pathwayeditor.contextadapter.publicapi.ILinkConnectionRules;
import org.pathwayeditor.contextadapter.publicapi.ILinkEndDefinition;
import org.pathwayeditor.contextadapter.publicapi.IPropertyDefinition;
import org.pathwayeditor.contextadapter.publicapi.IPropertyDefinitionFilter;
import org.pathwayeditor.contextadapter.publicapi.IPropertyDefinition.PropDefnType;


@RunWith(JMock.class)
public class LinkObjectTypeTest {
	private Mockery mockery = new JUnit4Mockery();
	
	private static final int EXPECTED_PROP_NUMS = 1; 
	private static final int EXPECTED_PROPS_EMPTY = 0; 
	
	private IPropertyDefinition testProp1;
	
	private static enum TestTypes { LINK_TYPE }; 

	@Before
	public void setUp() throws Exception {
		this.testProp1 = new TestPropDefn("prop1", PropDefnType.NUMBER, "1", 1, true, true);
	}

	@After
	public void tearDown() throws Exception {
	}

	@SuppressWarnings("deprecation")
	@Test(expected=IllegalArgumentException.class)
	public final void testLinkObjectTypeEnumTypeConstructorNullContext() {
		new LinkObjectType(null, TestTypes.LINK_TYPE);
	}

	@SuppressWarnings("deprecation")
	@Test(expected=IllegalArgumentException.class)
	public final void testLinkObjectTypeEnumTypeConstructorNullType() {
		final IContext mockContext = this.mockery.mock(IContext.class, "mockContext");
		new LinkObjectType(mockContext, null);
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testLinkObjectTypeNullContext() {
		new LinkObjectType(null, TestTypes.LINK_TYPE.name(), TestTypes.LINK_TYPE.ordinal());
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testLinkObjectTypeNullType() {
		final IContext mockContext = this.mockery.mock(IContext.class, "mockContext");
		new LinkObjectType(mockContext, null, TestTypes.LINK_TYPE.ordinal());
	}

	@Test
	public final void testAddProperty() {
		final IContext mockContext = this.mockery.mock(IContext.class, "mockContext");
		LinkObjectType testInstance = new LinkObjectType(mockContext, TestTypes.LINK_TYPE.name(), TestTypes.LINK_TYPE.ordinal());
		this.mockery.assertIsSatisfied();
		IPropertyDefinitionFilter propDef = testInstance.getPropertyDefinitionFilter();
		assertEquals("empty props list", EXPECTED_PROPS_EMPTY, propDef.getAllProperties().size());
		testInstance.addProperty(this.testProp1);
		assertEquals("props added", EXPECTED_PROP_NUMS, propDef.getAllProperties().size());
	}

	@Test
	public final void testGetLinkConnectionRules() {
		final IContext mockContext = this.mockery.mock(IContext.class, "mockContext");
		LinkObjectType testInstance = new LinkObjectType(mockContext, TestTypes.LINK_TYPE.name(), TestTypes.LINK_TYPE.ordinal());
		ILinkConnectionRules connectionRule1 = testInstance.getLinkConnectionRules();
		ILinkConnectionRules connectionRule2 = testInstance.getLinkConnectionRules();
		this.mockery.assertIsSatisfied();
		assertTrue("connectionRules are set", connectionRule1 != null);
		assertTrue("connection rules instance is the same", connectionRule1 == connectionRule2);
	}

	@Test
	public final void testGetLinkSource() {
		final IContext mockContext = this.mockery.mock(IContext.class, "mockContext");
		LinkObjectType testInstance = new LinkObjectType(mockContext, TestTypes.LINK_TYPE.name(), TestTypes.LINK_TYPE.ordinal());
		ILinkEndDefinition endDefn1 = testInstance.getLinkSource();
		ILinkEndDefinition endDefn2 = testInstance.getLinkSource();
		this.mockery.assertIsSatisfied();
		assertTrue("link end defn are set", endDefn1 != null);
		assertTrue("link end defn instances are the same", endDefn1 == endDefn2);
	}

	@Test
	public final void testGetLinkTarget() {
		final IContext mockContext = this.mockery.mock(IContext.class, "mockContext");
		LinkObjectType testInstance = new LinkObjectType(mockContext, TestTypes.LINK_TYPE.name(), TestTypes.LINK_TYPE.ordinal());
		ILinkEndDefinition endDefn1 = testInstance.getLinkTarget();
		ILinkEndDefinition endDefn2 = testInstance.getLinkTarget();
		this.mockery.assertIsSatisfied();
		assertTrue("link end defn are set", endDefn1 != null);
		assertTrue("link end defn instances are the same", endDefn1 == endDefn2);
	}

	@Test
	public final void testGetContext() {
		final IContext mockContext = new TestContext();
		LinkObjectType testInstance = new LinkObjectType(mockContext, TestTypes.LINK_TYPE.name(), TestTypes.LINK_TYPE.ordinal());
		IContext actualCtx = testInstance.getContext();
		assertTrue("same ctx", mockContext.equals(actualCtx));
	}

	@Test
	public final void testGetTypeCode() {
		final IContext mockContext = new TestContext();
		LinkObjectType testInstance = new LinkObjectType(mockContext, TestTypes.LINK_TYPE.name(), TestTypes.LINK_TYPE.ordinal());
		int actualType = testInstance.getTypeCode();
		assertEquals("same type code", TestTypes.LINK_TYPE.ordinal(), actualType);
	}

	@Test
	public final void testGetTypeName() {
		final IContext mockContext = new TestContext();
		LinkObjectType testInstance = new LinkObjectType(mockContext, TestTypes.LINK_TYPE.name(), TestTypes.LINK_TYPE.ordinal());
		String actualType = testInstance.getTypeName();
		assertEquals("same type name", TestTypes.LINK_TYPE.name(), actualType);
	}

	@Test
	public final void testGetPropertyDefinitionFilter() {
		final IContext mockContext = this.mockery.mock(IContext.class, "mockContext");
		LinkObjectType testInstance = new LinkObjectType(mockContext, TestTypes.LINK_TYPE.name(), TestTypes.LINK_TYPE.ordinal());
		IPropertyDefinitionFilter defn1 = testInstance.getPropertyDefinitionFilter();
		IPropertyDefinitionFilter defn2 = testInstance.getPropertyDefinitionFilter();
		this.mockery.assertIsSatisfied();
		assertTrue("link end defn are set", defn1 != null);
		assertTrue("link end defn instances are the same", defn1 == defn2);
	}

	
	private static class TestPropDefn implements IPropertyDefinition {
		private final String name;
		private final PropDefnType type;
		private final String value;
		private final Object valueObject;
		private final boolean editable;
		private final boolean visualisable;
		
		public TestPropDefn(String name, PropDefnType type, String value,
				Object valueObject, boolean editable, boolean visualisable){
			this.name = name;
			this.type = type;
			this.value = value;
			this.valueObject = valueObject;
			this.editable = editable;
			this.visualisable = visualisable;
		}
		
		public String getName() {
			return this.name;
		}

		public PropDefnType getType() {
			return this.type;
		}

		public String getValue() {
			return this.value;
		}

		public Object getValueObject() {
			return this.valueObject;
		}

		public boolean isEditable() {
			return this.editable;
		}

		public boolean isVisualisable() {
			return this.visualisable;
		}

		public int compareTo(IPropertyDefinition o) {
			return this.name.compareTo(o.getName());
		}

		public ILabelDefinition getAppearance() {
			return new DefaultLabelDefinition();
		}
	}
	
	private static class TestContext implements IContext {
		private final String name = "182736746736";
		
		public String getDisplayName() {
			return "Test Context";
		}

		public String getGlobalId() {
			return name;
		}

		public int getMajorVersion() {
			return 1;
		}

		public int getMinorVersion() {
			return 0;
		}

		public String getName() {
			return "testctx";
		}

		public int getPatchVersion() {
			return 0;
		}

		public String getVersionString() {
			return "1.0.0";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
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
			final TestContext other = (TestContext) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
		
	}
}
