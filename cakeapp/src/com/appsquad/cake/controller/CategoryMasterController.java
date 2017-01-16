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
	private CategoryMasterBean exCategoryMasterBean = new CategoryMasterBean();
	private CategoryMasterBean selectedexCategoryMasterBean = new CategoryMasterBean();
	private AreaMasterBean exAreaMasterBean = new AreaMasterBean();
	
	
	private ArrayList<CategoryMasterBean> categoryMasterList;
	private ArrayList<AreaMasterBean> exAreaMasterList;
	private ArrayList<CategoryMasterBean> categoryAreaMapList;
	
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
	public void onSelectedExistingCategory(){
		categoryAreaMapList = CategoryMasterService.loadCategoriYWithAreas(selectedexCategoryMasterBean);
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
	
	@Command
	@NotifyChange("*")
	public void onSelectExCat(){
		exAreaMasterList = AreaMasterService.loadAreasforCategoryMapped(exCategoryMasterBean);
	}
	
	
	@Command
	@NotifyChange("*")
	public void onClickSaveAreaWithCategory(){

		if(CategoryMasterService.categoryAreavalidation(exCategoryMasterBean, exAreaMasterBean)){
			int i = 0;
			i = CategoryMasterService.insertCategoryWithArea(userId, exCategoryMasterBean, exAreaMasterBean);
			if(i>0){
				Messagebox.show("Saved Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
				CategoryMasterService.categotyMasterClear(categoryMasterBean);
				CategoryMasterService.onClickExClear(exCategoryMasterBean, exAreaMasterBean);
				categoryMasterList = CategoryMasterService.loadCategories(userId, categoryMasterBean);
				if(exAreaMasterList != null){
					 exAreaMasterList.clear();
				}
				
			}
		}	
	
	}
	
	
	
	@Command
	@NotifyChange("*")
	public void onClickExClear(){
		CategoryMasterService.onClickExClear(exCategoryMasterBean, exAreaMasterBean);
		categoryMasterList = CategoryMasterService.loadCategories(userId, categoryMasterBean);
		if(exAreaMasterList != null){
		 exAreaMasterList.clear();
		}
	}
	
	@Command
	@NotifyChange("*")
	public void updateCategoryAreaMap(@BindingParam("bean") CategoryMasterBean bean){
		int i =0;
		i = CategoryMasterService.updatecategoryWithArea(userId, bean);

		
		if(i>0){
			Messagebox.show("Updated Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
			categoryAreaMapList = CategoryMasterService.loadCategoriYWithAreas(selectedexCategoryMasterBean);
		}
		
	}
	
	@Command
	@NotifyChange("*")
	public void onClickClearCityMap(){
		selectedexCategoryMasterBean.setCategoryId(null);
		selectedexCategoryMasterBean.setCategoryName(null);
		categoryMasterList = CategoryMasterService.loadCategories(userId, categoryMasterBean);
		if(categoryAreaMapList != null){
			categoryAreaMapList.clear();
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

	public CategoryMasterBean getExCategoryMasterBean() {
		return exCategoryMasterBean;
	}

	public void setExCategoryMasterBean(CategoryMasterBean exCategoryMasterBean) {
		this.exCategoryMasterBean = exCategoryMasterBean;
	}

	public ArrayList<AreaMasterBean> getExAreaMasterList() {
		return exAreaMasterList;
	}

	public void setExAreaMasterList(ArrayList<AreaMasterBean> exAreaMasterList) {
		this.exAreaMasterList = exAreaMasterList;
	}

	public AreaMasterBean getExAreaMasterBean() {
		return exAreaMasterBean;
	}

	public void setExAreaMasterBean(AreaMasterBean exAreaMasterBean) {
		this.exAreaMasterBean = exAreaMasterBean;
	}

	public ArrayList<CategoryMasterBean> getCategoryAreaMapList() {
		return categoryAreaMapList;
	}

	public void setCategoryAreaMapList(
			ArrayList<CategoryMasterBean> categoryAreaMapList) {
		this.categoryAreaMapList = categoryAreaMapList;
	}

	public CategoryMasterBean getSelectedexCategoryMasterBean() {
		return selectedexCategoryMasterBean;
	}

	public void setSelectedexCategoryMasterBean(
			CategoryMasterBean selectedexCategoryMasterBean) {
		this.selectedexCategoryMasterBean = selectedexCategoryMasterBean;
	}
	
	
	
}
