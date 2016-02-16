package com.rest.server.storage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rest.server.models.Company;
import com.rest.server.models.CompanyOwner;
import com.rest.server.models.Owner;

public class CompanyManager extends DbManager {

	private static CompanyManager instance;
	
	private CompanyManager() {}
	
	public static CompanyManager getInstance() {
		if (instance == null) {
			instance = new CompanyManager();
		}
		return instance;
	}
	
	public Company add(Company company) throws SQLException {
		String sql = company.getInsert();
		long companyId = update(sql);
		company.setId(companyId);
		return company;		
	}
	
	//This is just a test method, to be removed later
	public void addTest() throws SQLException {
		for (int i = 0; i < 10; i++) {
			String sql = "INSERT INTO COMPANY (name, address, city, country, created_at, updated_at) " +
					"VALUES ('name" + i + "', 'address" + i + "', 'city" + i + "', 'country" + i + "', '" +
					System.currentTimeMillis() + "', '" + System.currentTimeMillis() + "'";
			update(sql);
		}
	}
	
	public List<Company> getAll(int companyId) throws SQLException {
		String sql = "SELECT id,name,address,city,country,email,phone FROM company";
		if (companyId > - 1) {
			sql += " WHERE id=" + companyId;
		}
		
		List<Company> companies = new ArrayList<Company>();
		
		List<Map<Integer, Object>> companyRows = query(sql);
		for (Map<Integer, Object> companyCols : companyRows) {
			Company company = new Company();
			company.setId((Integer)companyCols.get(0));
			company.setName((String)companyCols.get(1));
			company.setAddress((String)companyCols.get(2));
			company.setCity((String)companyCols.get(3));
			company.setCountry((String)companyCols.get(4));
			company.setEmail((String)companyCols.get(5));
			company.setPhone((String)companyCols.get(6));
			companies.add(company);
			sql = "SELECT o.id,o.first_name,o.last_name,o.email,o.phone FROM owner o, company_owner c " +
					"WHERE o.id = c.owner_id AND c.company_id=" + company.getId();
			List<Map<Integer, Object>> ownerRows = query(sql);
			for (Map<Integer, Object> ownerCols : ownerRows) {
				Owner owner = new Owner();
				owner.setId((Integer)ownerCols.get(0));
				owner.setFirstName((String)ownerCols.get(1));
				owner.setLastName((String)ownerCols.get(2));
				owner.setEmail((String)ownerCols.get(3));
				owner.setContactNumber((String)(ownerCols.get(4) + ""));
				company.addOwner(owner);
			}
			
		}
		return companies;
	}
	
	public Company getOne(int id) throws SQLException {
		List<Company> result = getAll(id);
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	
	public Company update(Company company) throws SQLException {
		String sql = company.getUpdate();
		update(sql);
		return company;
	}
	
	public Owner addOwner(long companyId, Owner owner) throws SQLException {
		String sql = owner.getInsert();
		long ownerId = update(sql);
		owner.setId(ownerId);
		CompanyOwner companyOwner = new CompanyOwner();
		companyOwner.setCompanyId(companyId);
		companyOwner.setOwnerId(ownerId);
		sql = companyOwner.getInsert();
		update(sql);
		return owner;
	}
	
	public void delete(long companyId) throws SQLException {
		String sql = "DELETE FROM company WHERE id=" + companyId;
		update(sql);
		sql = "DELETE FROM owner WHERE id IN (SELECT owner_id FROM company_owner WHERE company_id=" + companyId + ")";
		update(sql);
		sql = "DELETE FROM company_owner WHERE company_id=" + companyId;
		update(sql);
	}
}
