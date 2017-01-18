package com.appsquad.cake.bean;

public class ItemMasterBean {

	
	private int itemCategoryMappedId;
	private Integer ItemId;
	private String ItemName;
	private String ItemImage;
	private String isActive;
	private String itemDesc;
	private Double price;
	private int slNo;
	
	private CategoryMasterBean categoryMasterBean = new CategoryMasterBean();
	
	public Integer getItemId() {
		return ItemId;
	}
	public void setItemId(Integer itemId) {
		ItemId = itemId;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public String getItemImage() {
		return ItemImage;
	}
	public void setItemImage(String itemImage) {
		ItemImage = itemImage;
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
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getItemCategoryMappedId() {
		return itemCategoryMappedId;
	}
	public void setItemCategoryMappedId(int itemCategoryMappedId) {
		this.itemCategoryMappedId = itemCategoryMappedId;
	}
	public CategoryMasterBean getCategoryMasterBean() {
		return categoryMasterBean;
	}
	public void setCategoryMasterBean(CategoryMasterBean categoryMasterBean) {
		this.categoryMasterBean = categoryMasterBean;
	}
	
	
}
