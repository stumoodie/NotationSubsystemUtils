package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import org.pathwayeditor.contextadapter.publicapi.IContext;


public final class GeneralContext implements IContext {
	private final String displayName;
	private final String name;
	private final String globalId;
	private final int majorVersion;
	private final int minorVersion;
	private final int patchVersion;
	
	public GeneralContext(String globalId, String displayName, String name,
			int majorVersion, int minorVersion, int patchVersion){
		if(globalId == null || displayName == null || name == null) throw new IllegalArgumentException();
		this.displayName = displayName;
		this.name = name;
		this.globalId = globalId;
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
		this.patchVersion = patchVersion;
	}
	
	public String getDisplayName() {
		return this.displayName
		;
	}

	public String getGlobalId() {
		return this.globalId;
	}

	public int getMajorVersion() {
		return this.majorVersion;
	}

	public int getMinorVersion() {
		return this.minorVersion;
	}

	public String getName() {
		return this.name;
	}

	public int getPatchVersion() {
		return this.patchVersion;
	}

	public String getVersionString() {
		StringBuffer versionBuf = new StringBuffer();
		versionBuf.append(this.majorVersion);
		versionBuf.append(".");
		versionBuf.append(this.minorVersion);
		versionBuf.append(".");
		versionBuf.append(this.patchVersion);
		return versionBuf.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((globalId == null) ? 0 : globalId.hashCode());
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
		final GeneralContext other = (GeneralContext) obj;
		if (globalId == null) {
			if (other.globalId != null)
				return false;
		} else if (!globalId.equals(other.globalId))
			return false;
		return true;
	}

}
