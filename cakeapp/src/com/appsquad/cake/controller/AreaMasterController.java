package com.appsquad.cake.controller;

import java.util.ArrayList;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Messagebox;

import com.appsquad.cake.bean.AreaMasterBean;
import com.appsquad.cake.bean.CityMasterBean;
import com.appsquad.cake.dao.AreaMasterDao;
import com.appsquad.cake.dao.CityMasterDao;
import com.appsquad.cake.model.service.AreaMasterService;
import com.appsquad.cake.model.service.CityMasterService;

public class AreaMasterController {

	private AreaMasterBean areaMasterBean = new AreaMasterBean();
	private CityMasterBean cityMasterBean = new CityMasterBean();
	private AreaMasterBean areaWithCityBean = new AreaMasterBean() ;
	private CityMasterBean existingcityAreaBean = new CityMasterBean();
	private AreaMasterBean areacityMappedBean = new AreaMasterBean();
	
	
	private ArrayList<AreaMasterBean> areawithcityMappedList;
	private ArrayList<AreaMasterBean> areawithcityList;
	private ArrayList<CityMasterBean> cityMasterBeanList;
	private ArrayList<AreaMasterBean> areaMasterBeanList;
	
	
	private String username;
	private String userId;
	public Session session = null;
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws Exception {

		Selectors.wireComponents(view, this, false);
		session = Sessions.getCurrent();
		userId = (String) session.getAttribute("userId");
		
		areaMasterBeanList = AreaMasterService.loadAreas();
		cityMasterBeanList = CityMasterDao.loadCity();
	}

	@Command
	@NotifyChange("*")
	public void onClickSave(){
		if(AreaMasterService.areavalidation(areaMasterBean)){
			int i = 0;
			i = AreaMasterService.insertArea(userId, areaMasterBean);
			if(i>0){
				Messagebox.show("Saved Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
				AreaMasterService.areaMasterClear(areaMasterBean);
				areaMasterBeanList= AreaMasterService.loadAreas();
			}
		}	
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdate(@BindingParam("bean") AreaMasterBean bean){
		
		int i = 0;
		i = AreaMasterService.updateAreas(userId, bean);
		if(i>0){
			Messagebox.show("Updated Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
			areaMasterBeanList = AreaMasterService.loadAreas(userId, areaMasterBean);
		}
	}

	
	@Command
	@NotifyChange("*")
	public void onSelectCity(){
		areawithcityList = AreaMasterService.loadAreasforCity(cityMasterBean.getCityId());
		if(areawithcityList.size() ==0){
			Messagebox.show("No Area Found", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		
	}
	
	
	@Command
	@NotifyChange("*")
	public void onClickSaveAreaWithCity(){
		if(AreaMasterService.areacityVal(cityMasterBean, areaWithCityBean)){
			int i = 0;
			i= AreaMasterDao.saveAreaWithCity(userId, cityMasterBean, areaWithCityBean);
			if(i>0){
				Messagebox.show("Saved Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
				AreaMasterService.areacityVal(cityMasterBean, areaWithCityBean);
				AreaMasterService.cityAreaClear(cityMasterBean, areaWithCityBean);
				cityMasterBeanList = CityMasterDao.loadCity();
			}
					
		}
		
		
	}
	
	@Command
	@NotifyChange("*")
	public void onExistingSelectCity(){
		areawithcityMappedList = AreaMasterService.loadAreasforCityMapped(existingcityAreaBean.getCityId());
	}
	
	@Command
	@NotifyChange("*")
	public void updateCityAreaMap(@BindingParam("bean") AreaMasterBean bean){
		int i =0;
		i = AreaMasterService.updateAreaCityMAp(userId, bean);
		if(i>0){
			Messagebox.show("Updated Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
			areawithcityMappedList = AreaMasterService.loadAreasforCityMapped(existingcityAreaBean.getCityId());
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickClearCityMap(){
		AreaMasterService.cityAreaMapCityClear(existingcityAreaBean);
		if(areawithcityMappedList != null){
			areawithcityMappedList.clear();
		}
		cityMasterBeanList = CityMasterDao.loadCity();
		
	}
	
	@Command
	@NotifyChange("*")
	public void onClickClear(){
		cityMasterBean.setCityId(null);
		cityMasterBean.setCityName(null);
		
		areaMasterBean.setAreaId(null);
		areaMasterBean.setAreaName(null);
		
		areaWithCityBean.setAreaId(null);
		areaWithCityBean.setAreaName(null);
		
		if(areawithcityList != null){
			areawithcityList.clear();
		}
		cityMasterBeanList = CityMasterDao.loadCity();
	}
	
	public AreaMasterBean getAreaMasterBean() {
		return areaMasterBean;
	}

	public void setAreaMasterBean(AreaMasterBean areaMasterBean) {
		this.areaMasterBean = areaMasterBean;
	}

	public ArrayList<AreaMasterBean> getAreaMasterBeanList() {
		return areaMasterBeanList;
	}

	public void setAreaMasterBeanList(ArrayList<AreaMasterBean> areaMasterBeanList) {
		this.areaMasterBeanList = areaMasterBeanList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public CityMasterBean getCityMasterBean() {
		return cityMasterBean;
	}

	public void setCityMasterBean(CityMasterBean cityMasterBean) {
		this.cityMasterBean = cityMasterBean;
	}

	public ArrayList<CityMasterBean> getCityMasterBeanList() {
		return cityMasterBeanList;
	}

	public void setCityMasterBeanList(ArrayList<CityMasterBean> cityMasterBeanList) {
		this.cityMasterBeanList = cityMasterBeanList;
	}

	public AreaMasterBean getAreaWithCityBean() {
		return areaWithCityBean;
	}

	public void setAreaWithCityBean(AreaMasterBean areaWithCityBean) {
		this.areaWithCityBean = areaWithCityBean;
	}

	public ArrayList<AreaMasterBean> getAreawithcityList() {
		return areawithcityList;
	}

	public void setAreawithcityList(ArrayList<AreaMasterBean> areawithcityList) {
		this.areawithcityList = areawithcityList;
	}

	public CityMasterBean getExistingcityAreaBean() {
		return existingcityAreaBean;
	}

	public void setExistingcityAreaBean(CityMasterBean existingcityAreaBean) {
		this.existingcityAreaBean = existingcityAreaBean;
	}

	public AreaMasterBean getAreacityMappedBean() {
		return areacityMappedBean;
	}

	public void setAreacityMappedBean(AreaMasterBean areacityMappedBean) {
		this.areacityMappedBean = areacityMappedBean;
	}

	public ArrayList<AreaMasterBean> getAreawithcityMappedList() {
		return areawithcityMappedList;
	}

	public void setAreawithcityMappedList(
			ArrayList<AreaMasterBean> areawithcityMappedList) {
		this.areawithcityMappedList = areawithcityMappedList;
	}
	
}
