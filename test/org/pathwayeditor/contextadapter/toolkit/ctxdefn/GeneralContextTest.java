package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class GeneralContextTest {
	private GeneralContext testInstance1;
	private GeneralContext testInstance2;
	private GeneralContext testInstance3;
	private static final String TEST_GLOBALID1 = "12345"; 
	private static final String TEST_GLOBALID2 = "123457364"; 
	private static final String TEST_NAME1 = "Test Name";
	private static final String TEST_CODE1 = "testname";
	private static final String TEST_NAME2 = "Alt Test Name";
	private static final String TEST_CODE2 = "alt_testname";
	private static final int TEST_MAJOR_VER = 1;
	private static final int TEST_MINOR_VER = 5848;
	private static final int TEST_PATCH_VER = 37726;
	private static final String EXPECTED_VERS_STR = "1.5848.37726";
	
	@Before
	public void setUp() throws Exception {
		this.testInstance1 = new GeneralContext(TEST_GLOBALID1, TEST_NAME1, TEST_CODE1,
				TEST_MAJOR_VER, TEST_MINOR_VER, TEST_PATCH_VER);
		this.testInstance2 = new GeneralContext(TEST_GLOBALID2, TEST_NAME1, TEST_CODE1,
				TEST_MAJOR_VER, TEST_MINOR_VER, TEST_PATCH_VER);
		this.testInstance3 = new GeneralContext(TEST_GLOBALID1, TEST_NAME2, TEST_CODE2,
				TEST_MAJOR_VER, TEST_MINOR_VER, TEST_PATCH_VER);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testHashCode() {
		assertEquals("same code", this.testInstance1, this.testInstance3);
		assertTrue("not same code", this.testInstance1 != this.testInstance2);
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testGeneralContextNullGlobalId() {
		new GeneralContext(null, TEST_NAME1, TEST_CODE1,
				TEST_MAJOR_VER, TEST_MINOR_VER, TEST_PATCH_VER);
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testGeneralContextNullDisplayName() {
		new GeneralContext(TEST_GLOBALID1, null, TEST_CODE1,
				TEST_MAJOR_VER, TEST_MINOR_VER, TEST_PATCH_VER);
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testGeneralContextNullCode() {
		new GeneralContext(TEST_GLOBALID1, TEST_NAME1, null,
				TEST_MAJOR_VER, TEST_MINOR_VER, TEST_PATCH_VER);
	}

	@Test
	public final void testGetDisplayName() {
		assertEquals("expected value", TEST_NAME1, this.testInstance1.getDisplayName());
	}

	@Test
	public final void testGetGlobalId() {
		assertEquals("expected value", TEST_GLOBALID1, this.testInstance1.getGlobalId());
	}

	@Test
	public final void testGetMajorVersion() {
		assertEquals("expected value", TEST_MAJOR_VER, this.testInstance1.getMajorVersion());
	}

	@Test
	public final void testGetMinorVersion() {
		assertEquals("expected value", TEST_MINOR_VER, this.testInstance1.getMinorVersion());
	}

	@Test
	public final void testGetName() {
		assertEquals("expected value", TEST_CODE1, this.testInstance1.getName());
	}

	@Test
	public final void testGetPatchVersion() {
		assertEquals("expected value", TEST_PATCH_VER, this.testInstance1.getPatchVersion());
	}

	@Test
	public final void testGetVersionString() {
		assertEquals("expected value", EXPECTED_VERS_STR, this.testInstance1.getVersionString());
	}

	@Test
	public final void testEqualsObject() {
		assertTrue("same code", this.testInstance1.equals(this.testInstance1));
		assertTrue("same code", this.testInstance1.equals(this.testInstance3));
		assertTrue("same code", this.testInstance3.equals(this.testInstance1));
		assertTrue("not same code", !this.testInstance1.equals(this.testInstance2));
		assertTrue("not same as null", !this.testInstance1.equals(null));
		assertTrue("not same as another class", !this.testInstance1.equals(this));
	}

}
