package com.appsquad.cake.sql;

public class ItemMasterSql {

	public static final String insertItemSql = "INSERT INTO ca_item_master(item_name, image, item_description, price, created_by) VALUES (?, ?, ?, ?, ?) ";
	
	public static final String updateItemSql = "UPDATE ca_item_master SET item_name=?, image=?, item_description=?, price=?, updated_by=?, is_active=?, update_date= current_timestamp WHERE item_id = ? ";
	
	public static final String loadItemSql = "select item_id, item_name, image, item_description, price, is_active from ca_item_master order by item_id ";
	
	
	
	public static final String loadItemNotAssignedToCatSql = "select item_id,item_name from ca_item_master where is_active = 'Y' " +
															 "and item_id not in(select item_id from ca_category_item_map where category_id = ? ) ";
	
	
	public static final String saveItemCategoryMappingSql = "insert into ca_category_item_map (item_id,category_id,created_by) values (?,?,?)";
	
	public static final String loadItemCategoryMappingSql =   " SELECT ccim.category_item_map_id, ccim.category_id, ccim.item_id, ccim.is_active, ccm.category_name, cim.item_name " +
															  " FROM ca_category_item_map ccim, ca_item_master cim, ca_category_master ccm " +
															  " where ccim.category_id= ccm.category_id " +
															  " and ccim.item_id = cim.item_id and ccim.category_id = ? ";
	
	
															 
	
	
}
