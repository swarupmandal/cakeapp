
package com.appsquad.cake.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.zkoss.zul.Messagebox;

import com.appsquad.cake.bean.AreaMasterBean;
import com.appsquad.cake.bean.CityMasterBean;
import com.appsquad.cake.database.DatabaseHandler;
import com.appsquad.cake.database.Pbpstm;
import com.appsquad.cake.sql.AreaMasterSql;
import com.appsquad.cake.sql.CityMasterSql;

public class AreaMasterDao {


	public static int saveArea(String userName, AreaMasterBean bean){
		int id =0;
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, AreaMasterSql.saveAreaMasterSql, Arrays.asList(bean.getAreaName().trim(),userName, userName));
				id = preparedStatement.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
				if(e.getMessage().startsWith("E")){
					Messagebox.show("["+bean.getAreaName()+"] Already Exist" , "Error", Messagebox.OK, Messagebox.ERROR);
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
	
	public static ArrayList<AreaMasterBean> loadArea(){
		int count = 0;
		ArrayList<AreaMasterBean> list = new ArrayList<AreaMasterBean>();
		if(list.size()>0){
			list.clear();
		}
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, AreaMasterSql.loadAreaMasterSql, null);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					AreaMasterBean bean = new AreaMasterBean();
					count = count+1;
					bean.setSlNo(count);
					bean.setAreaId(resultSet.getInt("area_master_id"));
					bean.setAreaName(resultSet.getString("area_name"));
					
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
	
	public static int updateArea(String userName, AreaMasterBean bean){
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
				preparedStatement = Pbpstm.createQuery(connection, AreaMasterSql.updateAreaMasterSql, Arrays.asList(bean.getAreaName().trim(), userName, bean.getIsActive(), bean.getAreaId()));
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
	
	
	public static ArrayList<AreaMasterBean> loadAreaForCity(int cityId){
		int count = 0;
		ArrayList<AreaMasterBean> list = new ArrayList<AreaMasterBean>();
		if(list.size()>0){
			list.clear();
		}
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, AreaMasterSql.loadAreaForAssignToCitySql, Arrays.asList(cityId));
				
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					AreaMasterBean bean = new AreaMasterBean();
					count = count+1;
					bean.setSlNo(count);
					bean.setAreaId(resultSet.getInt("area_master_id"));
					bean.setAreaName(resultSet.getString("area_name"));
					
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
	
	
	public static int saveAreaWithCity(String userName,CityMasterBean cityMasterBean, AreaMasterBean areaMasterBean){
		int id =0;
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, AreaMasterSql.saveAreaWithCitySql, Arrays.asList(cityMasterBean.getCityId() ,areaMasterBean.getAreaId(), userName));
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
	

	public static ArrayList<AreaMasterBean> loadMappedAreaWithCity(int cityId){
		int count = 0;
		ArrayList<AreaMasterBean> list = new ArrayList<AreaMasterBean>();
		if(list.size()>0){
			list.clear();
		}
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, AreaMasterSql.loadMappedAreaWithCitySql, Arrays.asList(cityId));
				
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					AreaMasterBean bean = new AreaMasterBean();
					count = count+1;
					bean.setSlNo(count);
					bean.setAreaId(resultSet.getInt("area_id"));
					bean.setAreaName(resultSet.getString("area_name"));
					bean.setCityAreaMapId(resultSet.getInt("city_area_map_id"));
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
	
	
	public static int updateCityAreaMap(String userName, AreaMasterBean bean){
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
				preparedStatement = Pbpstm.createQuery(connection, AreaMasterSql.updateMappedAreaWithCitySql, Arrays.asList(bean.getIsActive(), userName, bean.getCityAreaMapId()));
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
