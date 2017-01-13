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
	
	
	
	
	
	
	
	
	
	public static boolean areavalidation(AreaMasterBean bean){
		if(bean.getAreaName() != null && bean.getAreaName().trim().length() >0){
			return true;
		}else {
			Messagebox.show("Enter Area Name", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			return false;
		}
	}
	
	public static void areaMasterClear(AreaMasterBean bean){
		bean.setAreaName(null);
	}
	


}
