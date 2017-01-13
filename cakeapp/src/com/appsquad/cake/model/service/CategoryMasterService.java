package com.appsquad.cake.model.service;

import java.util.ArrayList;

import org.zkoss.zhtml.Messagebox;

import com.appsquad.cake.bean.AreaMasterBean;
import com.appsquad.cake.bean.CategoryMasterBean;
import com.appsquad.cake.dao.CategoryMasterDao;

public class CategoryMasterService {
	
	public static int insertCategories(String userName, CategoryMasterBean bean){
		int i =0;
		i = CategoryMasterDao.saveCategory(userName, bean);
		return i;
	}
	
	public static ArrayList<CategoryMasterBean> loadCategories(String userName, CategoryMasterBean bean){
		ArrayList<CategoryMasterBean> list = new ArrayList<CategoryMasterBean>();
		list = CategoryMasterDao.loadCategory();
		return list;
	}
	
	public static int updatecategoryies(String userName, CategoryMasterBean bean){
		int i =0;
		i = CategoryMasterDao.updateCategory(userName, bean);
		return i;
	}
	
	
	
	
	
	
	
	
	
	public static boolean categoryvalidation(CategoryMasterBean bean){
		if(bean.getCategoryName() != null && bean.getCategoryName().trim().length() >0){
			return true;
		}else {
			Messagebox.show("Enter Category Name", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			return false;
		}
	}
	
	public static void categotyMasterClear(CategoryMasterBean bean){
		bean.setCategoryName(null);
		bean.setCategoryImage(null);
	}
	




}
