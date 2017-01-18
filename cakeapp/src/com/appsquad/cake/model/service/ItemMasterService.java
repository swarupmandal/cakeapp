package com.appsquad.cake.model.service;

import java.util.ArrayList;

import com.appsquad.cake.bean.ItemMasterBean;
import com.appsquad.cake.dao.ItemMasterDao;

public class ItemMasterService {

	public static int saveItem(String userName, ItemMasterBean bean){
		int i =0;
		i= ItemMasterDao.saveItem(userName, bean);
		return i;
	}
	
	public static ArrayList<ItemMasterBean> loadItem(){
		ArrayList<ItemMasterBean> list = new ArrayList<ItemMasterBean>();
		list = ItemMasterDao.loadItem();
		return list;			
	}
	
	public static int updateItem(String userName, ItemMasterBean bean){
		int i =0;
		i= ItemMasterDao.updateItem(userName, bean);
		return i;
	}
	
	public static ArrayList<ItemMasterBean> loadItemnotAss(int catId){
		ArrayList<ItemMasterBean> list = new ArrayList<ItemMasterBean>();
		list = ItemMasterDao.loadItemNotAssignedToCat(catId);
		return list;			
	}
	
	public static int saveMappedItemWithCategory(String userName, int itemId, int categoryId){
		int i =0;
		i= ItemMasterDao.saveMapperCategoryAndItem(userName, itemId, categoryId);
		return i;
	}
	
	public static ArrayList<ItemMasterBean> loadAssignedItemAndCategory(int catId){
		ArrayList<ItemMasterBean> list = new ArrayList<ItemMasterBean>();
		list = ItemMasterDao.loadItemMappedWithCat(catId);
		return list;			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void clear(ItemMasterBean bean){
		bean.setItemName(null);
		bean.setItemImage(null);
		bean.setItemDesc(null);
		bean.setPrice(null);
			
	}
}
