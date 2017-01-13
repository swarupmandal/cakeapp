package com.appsquad.cake.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.zkoss.zul.Messagebox;

import com.appsquad.cake.bean.AreaMasterBean;
import com.appsquad.cake.bean.CategoryMasterBean;
import com.appsquad.cake.database.DatabaseHandler;
import com.appsquad.cake.database.Pbpstm;
import com.appsquad.cake.sql.AreaMasterSql;
import com.appsquad.cake.sql.CategoryMasterSql;

public class CategoryMasterDao {







	public static int saveCategory(String userName, CategoryMasterBean bean){
		int id =0;
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, CategoryMasterSql.saveCategorySql, Arrays.asList(bean.getCategoryName().trim(),bean.getCategoryImage(), userName, userName));
				id = preparedStatement.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
				if(e.getMessage().startsWith("E")){
					Messagebox.show("["+bean.getCategoryName()+"] Already Exist" , "Error", Messagebox.OK, Messagebox.ERROR);
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
	
	public static ArrayList<CategoryMasterBean> loadCategory(){
		int count = 0;
		ArrayList<CategoryMasterBean> list = new ArrayList<CategoryMasterBean>();
		if(list.size()>0){
			list.clear();
		}
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, CategoryMasterSql.loadCategorySql, null);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					CategoryMasterBean bean = new CategoryMasterBean();
					count = count+1;
					bean.setSlNo(count);
					bean.setCategoryId(resultSet.getInt("category_id"));
					bean.setCategoryName(resultSet.getString("category_name"));
					bean.setCategoryImage(resultSet.getString("image"));
					
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
	
	public static int updateCategory(String userName, CategoryMasterBean bean){
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
				preparedStatement = Pbpstm.createQuery(connection, CategoryMasterSql.updateCategorySql, Arrays.asList(bean.getCategoryName().trim(),bean.getCategoryImage(),  userName, bean.getIsActive(), bean.getCategoryId()));
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
	
	
	

	

	

	
}
