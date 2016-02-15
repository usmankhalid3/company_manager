package com.rest.server.storage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rest.server.models.Company;
import com.rest.server.models.Owner;

public class CompanyManager extends DbManager {

	private static CompanyManager instance;
	private Map<Integer, Company> companies = new HashMap<Integer, Company>();
	
	private CompanyManager() {}
	
	public static CompanyManager getInstance() {
		if (instance == null) {
			instance = new CompanyManager();
		}
		return instance;
	}
	
	public Company add(Company company) {
		Integer id = getNextId();
		company.setId(id);
		companies.put(id, company);
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
	
	public List<Company> getAll() {
		return new ArrayList<Company>(companies.values());
	}
	
	public Company getOne(int id) {
		if (id > 0) {
			return companies.get(id);
		}
		//TODO: report an error		
		return null;
	}
	
	public Company update(Company company) {
		if (companies.containsKey(company.getId())) {
			companies.put(company.getId(), company);
			return company;
		}
		//TODO: report an error
		return null;
	}
	
	public Owner addOwner(int id, Owner owner) {
		if (id > 0 && companies.containsKey(id)) {
			companies.get(id).addOwner(owner);
			return owner;
		}
		//TODO: report an error		
		return null;
	}
}
