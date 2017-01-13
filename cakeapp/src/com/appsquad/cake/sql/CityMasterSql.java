package com.appsquad.cake.sql;

public class CityMasterSql {

	public static final String insertCityMasterSql = "INSERT INTO ca_city_master(city_name, created_by, updated_by) VALUES (?, ?, ?) ";
	
	public static final String loadCityListSql = "SELECT ca_city_master_id, city_name, is_active FROM ca_city_master order by ca_city_master_id ";
	
	public static final String updateCitySql = "UPDATE ca_city_master SET city_name =?, updated_by=?,is_active=? WHERE ca_city_master_id = ? ";
}
