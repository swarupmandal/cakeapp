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

import com.appsquad.cake.bean.CategoryMasterBean;
import com.appsquad.cake.bean.ItemMasterBean;
import com.appsquad.cake.model.service.CategoryMasterService;
import com.appsquad.cake.model.service.ItemMasterService;

public class ItemMasterController {

	private String userId;
	public Session session = null;
	
	private ItemMasterBean itemMasterBean = new ItemMasterBean();
	private CategoryMasterBean categoryMasterBean = new CategoryMasterBean();
	private ItemMasterBean itemBean = new ItemMasterBean();
	private CategoryMasterBean selectedcategoryMasterBean = new CategoryMasterBean();
	
	
	private ArrayList<ItemMasterBean> itemCategoryMappedBeanList;
	private ArrayList<ItemMasterBean> itemList;
	private ArrayList<CategoryMasterBean> categoryMasterList;
	private ArrayList<ItemMasterBean> itemMasterBeanList;
	
	
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws Exception {

		Selectors.wireComponents(view, this, false);
		session = Sessions.getCurrent();
		userId = (String) session.getAttribute("userId");
		
		itemMasterBeanList = ItemMasterService.loadItem();
		categoryMasterList = CategoryMasterService.loadCategories(userId, categoryMasterBean);
	}

	@Command
	@NotifyChange("*")
	public void onClickSave(){
		
		if(itemMasterBean.getItemName() != null && itemMasterBean.getItemName().trim().length()>0){
			int i =0;
			i = ItemMasterService.saveItem(userId, itemMasterBean);
			if(i>0){
				Messagebox.show("Saved Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
				ItemMasterService.clear(itemMasterBean);
				itemMasterBeanList = ItemMasterService.loadItem();
			}
		}else {
			Messagebox.show("Enter Item", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void oncliear(){
		ItemMasterService.clear(itemMasterBean);
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdate(@BindingParam("bean") ItemMasterBean bean){
		int i =0;
		i = ItemMasterService.updateItem(userId, bean);
		if(i>0){
			Messagebox.show("Updated Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
			itemMasterBeanList = ItemMasterService.loadItem();
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectCategory(){
		itemList = ItemMasterService.loadItemnotAss(categoryMasterBean.getCategoryId());
	}
	
	@Command
	@NotifyChange("*")
	public void onClickSaveItemWithCategory(){
		int i =0;
		
		if(categoryMasterBean.getCategoryName() != null && itemBean.getItemName() != null){
			i = ItemMasterService.saveMappedItemWithCategory(userId, itemBean.getItemId(), categoryMasterBean.getCategoryId());
		}else {
			Messagebox.show("Select Category and Item both", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		if(i>0){
			Messagebox.show("Saved Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
			categoryMasterBean.setCategoryId(null);
			categoryMasterBean.setCategoryName(null);
			
			if(categoryMasterList != null){
				categoryMasterList.clear();
			}
			
			categoryMasterList = CategoryMasterService.loadCategories(userId, categoryMasterBean);
			
			if(itemBean != null){
			itemBean.setItemId(null);
			itemBean.setItemName(null);
			}
		}
		
	}
	
	@Command
	@NotifyChange("*")
	public void onClickClearCatItem(){
		
		
		if(categoryMasterList != null){
			categoryMasterBean.setCategoryId(null);
			categoryMasterBean.setCategoryName(null);
			categoryMasterList.clear();
		}
		
		categoryMasterList = CategoryMasterService.loadCategories(userId, categoryMasterBean);
		
		if(itemBean != null){
		itemBean.setItemId(null);
		itemBean.setItemName(null);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectedCategory(){
		itemCategoryMappedBeanList = ItemMasterService.loadAssignedItemAndCategory(selectedcategoryMasterBean.getCategoryId());
		if(itemCategoryMappedBeanList.size() ==0){
			Messagebox.show("Data Not Found", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}
	
	@Command
	@NotifyChange
	public void updateCategoryItemMap(@BindingParam("bean") ItemMasterBean bean){
		
	}
	
	@Command
	@NotifyChange("*")
	public void onClickClearItemCategoryMap(){
		selectedcategoryMasterBean.setCategoryName(null);
		selectedcategoryMasterBean.setCategoryId(null);
		if(categoryMasterList != null){
			categoryMasterList.clear();
			categoryMasterList = CategoryMasterService.loadCategories(userId, categoryMasterBean);
			
		}
		if(itemCategoryMappedBeanList.size()>0){
			itemCategoryMappedBeanList.clear();
		}
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


	public ItemMasterBean getItemMasterBean() {
		return itemMasterBean;
	}


	public void setItemMasterBean(ItemMasterBean itemMasterBean) {
		this.itemMasterBean = itemMasterBean;
	}


	public ArrayList<ItemMasterBean> getItemMasterBeanList() {
		return itemMasterBeanList;
	}


	public void setItemMasterBeanList(ArrayList<ItemMasterBean> itemMasterBeanList) {
		this.itemMasterBeanList = itemMasterBeanList;
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

	public ItemMasterBean getItemBean() {
		return itemBean;
	}

	public void setItemBean(ItemMasterBean itemBean) {
		this.itemBean = itemBean;
	}

	public ArrayList<ItemMasterBean> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<ItemMasterBean> itemList) {
		this.itemList = itemList;
	}

	public CategoryMasterBean getSelectedcategoryMasterBean() {
		return selectedcategoryMasterBean;
	}

	public void setSelectedcategoryMasterBean(
			CategoryMasterBean selectedcategoryMasterBean) {
		this.selectedcategoryMasterBean = selectedcategoryMasterBean;
	}

	public ArrayList<ItemMasterBean> getItemCategoryMappedBeanList() {
		return itemCategoryMappedBeanList;
	}

	public void setItemCategoryMappedBeanList(
			ArrayList<ItemMasterBean> itemCategoryMappedBeanList) {
		this.itemCategoryMappedBeanList = itemCategoryMappedBeanList;
	}

	
	
}
