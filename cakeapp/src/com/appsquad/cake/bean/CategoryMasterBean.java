package com.appsquad.cake.bean;

public class CategoryMasterBean {

	private Integer categoryId;
	private String categoryName;
	private String categoryImage;
	private String isActive;
	private int slNo;
	private AreaMasterBean areaMasterBean = new AreaMasterBean();
	private int areacategorymapid;
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public int getSlNo() {
		return slNo;
	}
	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}
	public String getCategoryImage() {
		return categoryImage;
	}
	public void setCategoryImage(String categoryImage) {
		this.categoryImage = categoryImage;
	}
	public AreaMasterBean getAreaMasterBean() {
		return areaMasterBean;
	}
	public void setAreaMasterBean(AreaMasterBean areaMasterBean) {
		this.areaMasterBean = areaMasterBean;
	}
	public int getAreacategorymapid() {
		return areacategorymapid;
	}
	public void setAreacategorymapid(int areacategorymapid) {
		this.areacategorymapid = areacategorymapid;
	}
	
}
