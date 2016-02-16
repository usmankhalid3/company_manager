package com.rest.server.controllers;

import java.io.IOException;
import java.sql.SQLException;
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
			Company company = null;
			try {
				company = CompanyManager.getInstance().getOne(Integer.parseInt(id));
			} catch (NumberFormatException e) {
				error("Invalid company Id");
				return;
			} catch (SQLException e) {
				error("Could not fetch company with id = " + id);
				return;
			}
			if (company != null) {
				resp = company.toJson();
			}
			else {
				error("Could not fetch company with id = " + id);
				return;
			}
		}
		else {
			List<Company> companies = null;
			try {
				companies = CompanyManager.getInstance().getAll(-1);
			} catch (SQLException e) {
				error("Could not fetch the company list");
				return;
			}
			if (companies != null) {
				resp = BaseModel.gson.toJson(companies);
			}
			else {
				error("Could not fetch the list of companies");
				return;
			}
		}
		success(resp);
	}

	@Override
	public void handlePOST(Map<String, Object> params) throws IOException {
		String json = (String)params.get("company");
		Company company = Company.fromJson(json, Company.class);
		try {
			CompanyManager.getInstance().add(company);
			success(company.toJson());
		} catch (SQLException e) {
			error("Could not create a new company");
			return;
		}
	}

	@Override
	public void handlePUT(Map<String, Object> params) throws IOException {
		String json = (String)params.get("company");
		Company company = Company.fromJson(json, Company.class);
		try {
			CompanyManager.getInstance().update(company);
		} catch (SQLException e) {
			error("Could not update company with id = " + company.getId());
			return;
		}
		success(company.toJson());
	}

	@Override
	public void handlePATCH(Map<String, Object> params) throws IOException {
		String companyId = (String)params.get("companyId");
		String json = (String)params.get("owner");
		Owner owner = Owner.fromJson(json, Owner.class);
		try {
			CompanyManager.getInstance().addOwner(Integer.parseInt(companyId), owner);
		} catch (NumberFormatException e) {
			error("Invalid company Id");
			return;
		} catch (SQLException e) {
			error("Could not create a new owner");
			return;
		}
		success(owner.toJson());	
	}

	@Override
	public void handleDELETE(Map<String, Object> params) throws IOException {
		String companyId = (String)params.get("id");
		try {
			CompanyManager.getInstance().delete(Long.parseLong(companyId));
		} catch (NumberFormatException e) {
			error("Invalid company Id");
		} catch (SQLException e) {
			error("Could not delete the company with id = " + companyId);
		}
	}
}
