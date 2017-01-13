package com.appsquad.cake.sql;

public class CategoryMasterSql {
	
	public static final String saveCategorySql = "INSERT INTO ca_category_master(category_name, image, created_by, updated_by) VALUES (?, ?, ?, ?) ";
	
	public static final String loadCategorySql = "SELECT category_id, category_name, image, is_active from ca_category_master order by category_id ";
	
	public static final String updateCategorySql = "UPDATE ca_category_master SET category_name =?, image = ?, updated_by=?,is_active=? WHERE category_id = ? ";
	

}
