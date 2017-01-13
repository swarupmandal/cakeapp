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

import com.appsquad.cake.bean.CityMasterBean;
import com.appsquad.cake.bean.LoginBean;
import com.appsquad.cake.model.service.CityMasterService;

public class CityMasterController {

	private CityMasterBean cityMasterBean = new CityMasterBean();
	
	
	private ArrayList<CityMasterBean> cityMasterBeanList;
	
	
	private String username;
	private String userId;
	
	
	
	public Session session = null;
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws Exception {

		Selectors.wireComponents(view, this, false);
		session = Sessions.getCurrent();
		userId = (String) session.getAttribute("userId");
		
		cityMasterBeanList = CityMasterService.loadCitys(userId, cityMasterBean);
	}

	@Command
	@NotifyChange("*")
	public void onClickSave(){
		if(CityMasterService.cityValidation(cityMasterBean)){
			int i = 0;
			i = CityMasterService.insertCity(userId, cityMasterBean);
			if(i>0){
				Messagebox.show("Saved Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
				CityMasterService.cityMasterClear(cityMasterBean);
				cityMasterBeanList = CityMasterService.loadCitys(userId, cityMasterBean);
			}
		}	
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdate(@BindingParam("bean") CityMasterBean bean){
		
		int i = 0;
		i = CityMasterService.updateCitys(userId, bean);
		if(i>0){
			Messagebox.show("Updated Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
			cityMasterBeanList = CityMasterService.loadCitys(userId, bean);
		}
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
	
	
}
