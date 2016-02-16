package com.rest.server.models;

public class CompanyOwner extends BaseModel {

	private long companyId;
	private long ownerId;
	
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (companyId ^ (companyId >>> 32));
		result = prime * result + (int) (ownerId ^ (ownerId >>> 32));
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
		CompanyOwner other = (CompanyOwner) obj;
		if (companyId != other.companyId)
			return false;
		if (ownerId != other.ownerId)
			return false;
		return true;
	}
	@Override
	public String getInsert() {
		String sql = "INSERT INTO company_owner(company_id,owner_id,created_ts,updated_ts) VALUES (";
		sql += companyId + ",";
		sql += ownerId + ",";
		sql += System.currentTimeMillis() + ",";
		sql += System.currentTimeMillis();
		sql += ")";
		return sql;
	}
	@Override
	public String getUpdate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
