package com.appsquad.cake.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.zkoss.zul.Messagebox;

import com.appsquad.cake.bean.ItemMasterBean;
import com.appsquad.cake.database.DatabaseHandler;
import com.appsquad.cake.database.Pbpstm;
import com.appsquad.cake.sql.ItemMasterSql;

public class ItemMasterDao {



	public static int saveItem(String userName, ItemMasterBean bean){
		int id =0;
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, ItemMasterSql.insertItemSql, Arrays.asList(bean.getItemName(), bean.getItemImage(), 
																				bean.getItemDesc(), bean.getPrice(), userName));
				id = preparedStatement.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
				if(e.getMessage().startsWith("E")){
					Messagebox.show("["+bean.getItemName()+"] Already Exist" , "Error", Messagebox.OK, Messagebox.ERROR);
				}else {
					Messagebox.show(e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
				}
				
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}
				if(connection != null){
					connection.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public static ArrayList<ItemMasterBean> loadItem(){
		int count = 0;
		ArrayList<ItemMasterBean> list = new ArrayList<ItemMasterBean>();
		if(list.size()>0){
			list.clear();
		}
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, ItemMasterSql.loadItemSql, null);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					ItemMasterBean bean = new ItemMasterBean();
					count = count+1;
					bean.setSlNo(count);
					bean.setItemId(resultSet.getInt("item_id"));
					bean.setItemName(resultSet.getString("item_name"));
					bean.setItemImage(resultSet.getString("image"));
					bean.setItemDesc(resultSet.getString("item_description"));
					bean.setPrice(resultSet.getDouble("price"));
					
					if(resultSet.getString("is_active").equalsIgnoreCase("Y")){
						bean.setIsActive("YES");
					}else {
						bean.setIsActive("NO");
					}
					list.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Messagebox.show(e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
				
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}
				if(connection != null){
					connection.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static int updateItem(String userName, ItemMasterBean bean){
		int id =0;
		
		if(bean.getIsActive().equalsIgnoreCase("YES")){
			bean.setIsActive("Y");
		}else {
			bean.setIsActive("N");
		}
		
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, ItemMasterSql.updateItemSql, Arrays.asList(bean.getItemName().trim(),bean.getItemImage(),  bean.getItemDesc(), 
																				bean.getPrice(), userName, bean.getIsActive(), bean.getItemId()));
				id = preparedStatement.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
				
				Messagebox.show(e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
				
				
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}
				if(connection != null){
					connection.close();
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return id;
	}
	
	
	public static ArrayList<ItemMasterBean> loadItemNotAssignedToCat(int catId){
		int count = 0;
		ArrayList<ItemMasterBean> list = new ArrayList<ItemMasterBean>();
		if(list.size()>0){
			list.clear();
		}
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, ItemMasterSql.loadItemNotAssignedToCatSql, Arrays.asList(catId));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					ItemMasterBean bean = new ItemMasterBean();
					count = count+1;
					bean.setSlNo(count);
					bean.setItemId(resultSet.getInt("item_id"));
					bean.setItemName(resultSet.getString("item_name"));
					
					list.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Messagebox.show(e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
				
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}
				if(connection != null){
					connection.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static int saveMapperCategoryAndItem(String userName, int itemId, int categoryId){
		int id =0;
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, ItemMasterSql.saveItemCategoryMappingSql, Arrays.asList(itemId, categoryId, userName));
				id = preparedStatement.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
				
					Messagebox.show(e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
				
				
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}
				if(connection != null){
					connection.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public static ArrayList<ItemMasterBean> loadItemMappedWithCat(int catId){
		int count = 0;
		ArrayList<ItemMasterBean> list = new ArrayList<ItemMasterBean>();
		if(list.size()>0){
			list.clear();
		}
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, ItemMasterSql.loadItemCategoryMappingSql, Arrays.asList(catId));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					ItemMasterBean bean = new ItemMasterBean();
					count = count+1;
					bean.setSlNo(count);
					bean.setItemCategoryMappedId(resultSet.getInt("category_item_map_id"));
					bean.setItemId(resultSet.getInt("item_id"));
					bean.setItemName(resultSet.getString("item_name"));
					bean.getCategoryMasterBean().setCategoryId(resultSet.getInt("category_id"));
					bean.getCategoryMasterBean().setCategoryName(resultSet.getString("category_name"));
					
					if(resultSet.getString("is_active").equalsIgnoreCase("Y")){
						bean.setIsActive("YES");
					}else {
						bean.setIsActive("NO");
					}
					list.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Messagebox.show(e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
				
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}
				if(connection != null){
					connection.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
