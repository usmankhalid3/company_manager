package com.rest.server.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.rest.server.models.BaseModel;
import com.rest.server.models.Company;
import com.rest.server.models.Owner;
import com.rest.server.storage.CompanyManager;

public class CompanyController extends BaseController {

	@Override
	public void handleGET(Map<String, Object> params) throws IOException {
		String id = (String)params.get("id");
		String resp = null;
		if (id != null) {
			Company company = CompanyManager.getInstance().getOne(Integer.parseInt(id));
			if (company != null) {
				resp = company.toJson();
			}
			else {
				error("GET VALUE Cannot find company");
			}
		}
		else {
			List<Company> companies = CompanyManager.getInstance().getAll();
			resp = BaseModel.gson.toJson(companies);
		}
		success(resp);
	}

	@Override
	public void handlePOST(Map<String, Object> params) throws IOException {
		String json = (String)params.get("company");
		Company company = Company.fromJson(json, Company.class);
		CompanyManager.getInstance().add(company);
		success(company.toJson());
	}

	@Override
	public void handlePUT(Map<String, Object> params) throws IOException {
		String json = (String)params.get("company");
		Company company = Company.fromJson(json, Company.class);
		CompanyManager.getInstance().update(company);
		success(company.toJson());
	}

	@Override
	public void handlePATCH(Map<String, Object> params) throws IOException {
		String companyId = (String)params.get("companyId");
		String json = (String)params.get("owner");
		Owner owner = Owner.fromJson(json, Owner.class);
		CompanyManager.getInstance().addOwner(Integer.parseInt(companyId), owner);
		success(owner.toJson());	
	}
}
