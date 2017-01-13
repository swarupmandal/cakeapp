package com.appsquad.cake.model.service;

import java.util.ArrayList;

import org.zkoss.zhtml.Messagebox;

import com.appsquad.cake.bean.CityMasterBean;
import com.appsquad.cake.dao.CityMasterDao;

public class CityMasterService {

	public static int insertCity(String userName, CityMasterBean bean){
		int i =0;
		i = CityMasterDao.saveCity(userName, bean);
		return i;
	}
	
	public static ArrayList<CityMasterBean> loadCitys(String userName, CityMasterBean bean){
		ArrayList<CityMasterBean> list = new ArrayList<CityMasterBean>();
		list = CityMasterDao.loadCity();
		return list;
	}
	
	public static int updateCitys(String userName, CityMasterBean bean){
		int i =0;
		i = CityMasterDao.updateCity(userName, bean);
		return i;
	}
	
	
	
	
	
	
	
	
	
	public static boolean cityValidation(CityMasterBean bean){
		if(bean.getCityName() != null && bean.getCityName().trim().length() >0){
			return true;
		}else {
			Messagebox.show("Enter City Name", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			return false;
		}
	}
	
	public static void cityMasterClear(CityMasterBean bean){
		bean.setCityName(null);
	}
	
}
