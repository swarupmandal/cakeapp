package com.appsquad.cake.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.zkoss.zul.Messagebox;

import com.appsquad.cake.bean.CityMasterBean;
import com.appsquad.cake.database.DatabaseHandler;
import com.appsquad.cake.database.Pbpstm;
import com.appsquad.cake.sql.CityMasterSql;

public class CityMasterDao {



	public static int saveCity(String userName, CityMasterBean bean){
		int id =0;
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, CityMasterSql.insertCityMasterSql, Arrays.asList(bean.getCityName().trim(),userName, userName));
				id = preparedStatement.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
				if(e.getMessage().startsWith("E")){
					Messagebox.show("["+bean.getCityName()+"] Already Exist" , "Error", Messagebox.OK, Messagebox.ERROR);
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
	
	public static ArrayList<CityMasterBean> loadCity(){
		int count = 0;
		ArrayList<CityMasterBean> list = new ArrayList<CityMasterBean>();
		if(list.size()>0){
			list.clear();
		}
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, CityMasterSql.loadCityListSql, null);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					CityMasterBean bean = new CityMasterBean();
					count = count+1;
					bean.setSlNo(count);
					bean.setCityId(resultSet.getInt("ca_city_master_id"));
					bean.setCityName(resultSet.getString("city_name"));
					
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
	
	public static int updateCity(String userName, CityMasterBean bean){
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
				preparedStatement = Pbpstm.createQuery(connection, CityMasterSql.updateCitySql, Arrays.asList(bean.getCityName().trim(), userName, bean.getIsActive(), bean.getCityId()));
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
