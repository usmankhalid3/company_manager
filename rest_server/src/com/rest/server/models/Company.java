package com.rest.server.models;

import java.util.ArrayList;
import java.util.List;

public class Company extends BaseModel {

	private String name;
	private String address;
	private String city;
	private String country;
	private String email;
	private String phone;
	private List<Owner> owners = new ArrayList<Owner>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<Owner> getOwners() {
		return owners;
	}
	public void setOwners(List<Owner> owners) {
		this.owners = owners;
	}
	
	public void addOwner(Owner owner) {
		if (owner != null) {
			this.owners.add(owner);
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owners == null) ? 0 : owners.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
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
		Company other = (Company) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (owners == null) {
			if (other.owners != null)
				return false;
		} else if (!owners.equals(other.owners))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		return true;
	}
	
	public String getInsert() {
		String sql = "INSERT INTO company(name,address,city,country,email,phone,created_ts,updated_ts) VALUES (";
		sql += "\"" + name + "\",";
		sql += "\"" + address + "\",";
		sql += "\"" + city + "\",";
		sql += "\"" + country + "\",";
		sql += "\"" + email + "\",";
		sql += "\"" + phone + "\",";
		sql += System.currentTimeMillis() + ",";
		sql += System.currentTimeMillis();
		sql += ")";
		return sql;
	}
	@Override
	public String getUpdate() {
		String sql = "UPDATE company SET ";
		sql += "name=\"" + name + "\",";
		sql += "address=\"" + address + "\",";
		sql += "city=\"" + city + "\",";
		sql += "country=\"" + country + "\",";
		sql += "email=\"" + email + "\",";
		sql += "phone=\"" + phone + "\",";
		sql += "updated_ts=" + System.currentTimeMillis();
		sql += " WHERE id=" + id;
		return sql;
	}
}
