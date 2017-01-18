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

import com.appsquad.cake.bean.ItemMasterBean;
import com.appsquad.cake.model.service.ItemMasterService;

public class ItemMasterController {

	private String userId;
	public Session session = null;
	
	private ItemMasterBean itemMasterBean = new ItemMasterBean();
	
	
	
	private ArrayList<ItemMasterBean> itemMasterBeanList;
	
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws Exception {

		Selectors.wireComponents(view, this, false);
		session = Sessions.getCurrent();
		userId = (String) session.getAttribute("userId");
		
		itemMasterBeanList = ItemMasterService.loadItem();
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
	
}
