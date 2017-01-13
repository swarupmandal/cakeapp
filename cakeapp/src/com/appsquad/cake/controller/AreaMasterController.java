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
import com.appsquad.cake.model.service.AreaMasterService;
import com.appsquad.cake.model.service.CityMasterService;

public class AreaMasterController {

	private AreaMasterBean areaMasterBean = new AreaMasterBean();
	
	private ArrayList<AreaMasterBean> areaMasterBeanList;
	
	
	private String username;
	private String userId;
	public Session session = null;
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws Exception {

		Selectors.wireComponents(view, this, false);
		session = Sessions.getCurrent();
		userId = (String) session.getAttribute("userId");
		
		areaMasterBeanList = AreaMasterService.loadAreas(userId, areaMasterBean);
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
				areaMasterBeanList= AreaMasterService.loadAreas(userId, areaMasterBean);
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
	
}
