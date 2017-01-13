package com.appsquad.cake.model.service;

import java.util.ArrayList;

import org.zkoss.zhtml.Messagebox;

import com.appsquad.cake.bean.AreaMasterBean;
import com.appsquad.cake.bean.CityMasterBean;
import com.appsquad.cake.dao.AreaMasterDao;
import com.appsquad.cake.dao.CityMasterDao;

public class AreaMasterService {
	


	public static int insertArea(String userName, AreaMasterBean bean){
		int i =0;
		i = AreaMasterDao.saveArea(userName, bean);
		return i;
	}
	
	public static ArrayList<AreaMasterBean> loadAreas(String userName, AreaMasterBean bean){
		ArrayList<AreaMasterBean> list = new ArrayList<AreaMasterBean>();
		list = AreaMasterDao.loadArea();
		return list;
	}
	
	public static int updateAreas(String userName, AreaMasterBean bean){
		int i =0;
		i = AreaMasterDao.updateArea(userName, bean);
		return i;
	}
	
	public static ArrayList<AreaMasterBean> loadAreasforCity(int cityId){
		ArrayList<AreaMasterBean> list = new ArrayList<AreaMasterBean>();
		list = AreaMasterDao.loadAreaForCity(cityId);
		return list;
	}
	
	public static int updateAreas(String userName, CityMasterBean cityMasterBean, AreaMasterBean areaMasterBean){
		int i =0;
		i = AreaMasterDao.saveAreaWithCity(userName, cityMasterBean, areaMasterBean);
		return i;
	}
	
	public static ArrayList<AreaMasterBean> loadAreasforCityMapped(int cityId){
		ArrayList<AreaMasterBean> list = new ArrayList<AreaMasterBean>();
		list = AreaMasterDao.loadMappedAreaWithCity(cityId);
		return list;
	}
	
	public static int updateAreaCityMAp(String userName, AreaMasterBean bean){
		int i =0;
		i = AreaMasterDao.updateCityAreaMap(userName, bean);
		return i;
	}
	
	
	
	public static boolean areavalidation(AreaMasterBean bean){
		if(bean.getAreaName() != null && bean.getAreaName().trim().length() >0){
			return true;
		}else {
			Messagebox.show("Enter Area Name", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			return false;
		}
	}
	
	public static boolean areacityVal(CityMasterBean cityMasterBean, AreaMasterBean areaWithCityBean){

		if(cityMasterBean.getCityId() !=null){
			
			if(areaWithCityBean.getAreaId() != null){
				return true;
			}else {
				Messagebox.show("Select Area", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
				return false;
			}
			
		}else {
			Messagebox.show("Select City", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			return false;
		}
	}
	
	
	public static void cityAreaClear(CityMasterBean cityMasterBean, AreaMasterBean areaMasterBean){
		areaMasterBean.setAreaId(null);
		areaMasterBean.setAreaName(null);
		
		cityMasterBean.setCityId(null);
		cityMasterBean.setCityName(null);
	}
	
	public static void cityAreaMapCityClear(CityMasterBean bean){
		bean.setCityId(null);
		bean.setCityName(null);
	}
	
	public static void areaMasterClear(AreaMasterBean bean){
		bean.setAreaName(null);
	}
	


}
