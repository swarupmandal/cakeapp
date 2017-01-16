package com.appsquad.cake.sql;

public class AreaMasterSql {

	public static final String saveAreaMasterSql = "INSERT INTO ca_area_master(area_name, created_by, updated_by) VALUES (?, ?, ?) ";
	
	public static final String loadAreaMasterSql = "SELECT area_master_id, area_name, is_active FROM ca_area_master order by area_master_id ";
	
	public static final String updateAreaMasterSql = "UPDATE ca_area_master SET area_name =?, updated_by=?,is_active=? WHERE area_master_id = ? ";
	
	public static final String loadAreaForAssignToCitySql = " select area_master_id, area_name,is_active from ca_area_master " +
															" where area_master_id not in(select area_id from ca_city_area_map where city_id = ? ) " +
															" and is_active = 'Y' order by area_master_id";
	
	public static final String saveAreaWithCitySql = "insert into ca_city_area_map (city_id,area_id,created_by) values(?,?,?) ";
	
	public static final String loadMappedAreaWithCitySql = "SELECT ccam.city_area_map_id, ccam.city_id, ccam.area_id, cam.area_name, ccam.is_active " +
															" FROM ca_city_area_map ccam, ca_area_master cam " +
															" where ccam.city_id = ? and ccam.area_id = cam.area_master_id order by ccam.city_area_map_id ";
	
	public static final String updateMappedAreaWithCitySql = "update ca_city_area_map set is_active = ?, updated_by = ?, update_date = current_timestamp where city_area_map_id = ? ";
	
	public static final String loadAreaWithCategorySql = " select area_master_id, area_name,is_active from ca_area_master " +
														 " where area_master_id not in(select area_id from ca_area_category_map where category_id = ? ) " +
														 " and is_active = 'Y' order by area_master_id";
	
}
