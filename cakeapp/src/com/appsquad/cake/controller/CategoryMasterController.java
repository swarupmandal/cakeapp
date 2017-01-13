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
import com.appsquad.cake.bean.CategoryMasterBean;
import com.appsquad.cake.model.service.AreaMasterService;
import com.appsquad.cake.model.service.CategoryMasterService;

public class CategoryMasterController {

	private CategoryMasterBean categoryMasterBean = new CategoryMasterBean();
	
	private ArrayList<CategoryMasterBean> categoryMasterList;
	
	private String userId;
	public Session session = null;
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws Exception {

		Selectors.wireComponents(view, this, false);
		session = Sessions.getCurrent();
		userId = (String) session.getAttribute("userId");
		
		categoryMasterList = CategoryMasterService.loadCategories(userId, categoryMasterBean);
	}

	@Command
	@NotifyChange("*")
	public void onClickSave(){
		if(CategoryMasterService.categoryvalidation(categoryMasterBean)){
			int i = 0;
			i = CategoryMasterService.insertCategories(userId, categoryMasterBean);
			if(i>0){
				Messagebox.show("Saved Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
				CategoryMasterService.categotyMasterClear(categoryMasterBean);
				categoryMasterList = CategoryMasterService.loadCategories(userId, categoryMasterBean);
			}
		}	
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdate(@BindingParam("bean") CategoryMasterBean bean){
		
		int i = 0;
		i = CategoryMasterService.updatecategoryies(userId, bean);
		if(i>0){
			Messagebox.show("Updated Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
			categoryMasterList = CategoryMasterService.loadCategories(userId, categoryMasterBean);
		}
	}

	public CategoryMasterBean getCategoryMasterBean() {
		return categoryMasterBean;
	}

	public void setCategoryMasterBean(CategoryMasterBean categoryMasterBean) {
		this.categoryMasterBean = categoryMasterBean;
	}

	public ArrayList<CategoryMasterBean> getCategoryMasterList() {
		return categoryMasterList;
	}

	public void setCategoryMasterList(
			ArrayList<CategoryMasterBean> categoryMasterList) {
		this.categoryMasterList = categoryMasterList;
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
