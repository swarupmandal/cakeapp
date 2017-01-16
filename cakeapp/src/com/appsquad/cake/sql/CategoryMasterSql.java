package com.appsquad.cake.sql;

public class CategoryMasterSql {
	
	public static final String saveCategorySql = "INSERT INTO ca_category_master(category_name, image, created_by, updated_by) VALUES (?, ?, ?, ?) ";
	
	public static final String loadCategorySql = "SELECT category_id, category_name, image, is_active from ca_category_master order by category_id ";
	
	public static final String updateCategorySql = "UPDATE ca_category_master SET category_name =?, image = ?, updated_by=?,is_active=? WHERE category_id = ? ";
	
	public static final String saveAreaWithCategorySql = "INSERT INTO ca_area_category_map(area_id, category_id, created_by) VALUES (?, ?, ?) ";
	
	public static final String loadCategoryWithAreaSql = " SELECT cacm.area_category_map_id, cacm.category_id, ccm.category_name, cacm.is_active, " +
														 " cacm.area_id, cam.area_name " +	
														 " from ca_area_category_map cacm, ca_category_master ccm, ca_area_master cam " +
														 " where cacm.area_id = cam.area_master_id and cacm.category_id = ccm.category_id and cacm.category_id = ? order by category_id ";
	
	public static final String updateCategoryAreaSql = "update ca_area_category_map set is_active = ?, updated_by=?, update_date = current_timestamp where area_category_map_id = ? ";
	

}
