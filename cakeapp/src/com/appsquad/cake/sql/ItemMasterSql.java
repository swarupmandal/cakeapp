package com.appsquad.cake.sql;

public class ItemMasterSql {

	public static final String insertItemSql = "INSERT INTO ca_item_master(item_name, image, item_description, price, created_by) VALUES (?, ?, ?, ?, ?) ";
	
	public static final String updateItemSql = "UPDATE ca_item_master SET item_name=?, image=?, item_description=?, price=?, updated_by=?, is_active=?, update_date= current_timestamp WHERE item_id = ? ";
	
	public static final String loadItemSql = "select item_id, item_name, image, item_description, price, is_active from ca_item_master order by item_id ";
	
	
}
