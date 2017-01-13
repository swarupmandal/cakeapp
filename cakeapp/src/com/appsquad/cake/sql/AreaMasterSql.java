package com.appsquad.cake.sql;

public class AreaMasterSql {

	public static final String saveAreaMasterSql = "INSERT INTO ca_area_master(area_name, created_by, updated_by) VALUES (?, ?, ?) ";
	
	public static final String loadAreaMasterSql = "SELECT area_master_id, area_name, is_active FROM ca_area_master order by area_master_id ";
	
	public static final String updateAreaMasterSql = "UPDATE ca_area_master SET area_name =?, updated_by=?,is_active=? WHERE area_master_id = ? ";
	
}
