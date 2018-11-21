package application;

import java.util.List;

/**
 * ProductItem - class which contains all necessary fields for a productItem on a general level
 * 
 * @author Andrew P. Albanese
 */
public class ProductItem {
	private String itemID;
	private String name;
	private String description;
	private String categoryPath;
	private double itemSalePrice;
	private double msrp;
	private String upc;
	private String availability;
	private List<ProductItemImage> images;
	
	/**
	 * getItemID - getter for itemID
	 * 
	 * @return - returns itemID
	 */
	public String getItemID() {
		return this.itemID;
	}
	
	/**
	 * setItemID - setter for itemID
	 * 
	 * @param - itemId
	 * @return - returns categories
	 */
	public void setItemID(String itemId) {
		itemID = itemId;
	}
	
	/**
	 * getName - getter for name
	 * 
	 * @return - returns name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * setName - setter for name
	 * 
	 * @param - _name
	 * @return - returns name
	 */
	public void setName(String _name) {
		name = _name;
	}
	
	/**
	 * getDescription - getter for description
	 * 
	 * @return - returns description
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * setDescription - setter for description
	 * 
	 * @param - _description
	 * @return - returns description
	 */
	public void setDescription(String _description) {
		description = _description;
	}
	
	/**
	 * getCategoryPath - getter for categoryPath
	 * 
	 * @return - returns categoryPath
	 */
	public String getCategoryPath() {
		return this.categoryPath;
	}
	
	/**
	 * setCategoryPath - setter for categoryPath
	 * 
	 * @param - _categoryPath
	 * @return - returns categoryPath
	 */
	public void setCategoryPath(String _categoryPath) {
		categoryPath = _categoryPath;
	}
	
	/**
	 * getItemSalePrice - getter for itemSalePrice
	 * 
	 * @return - returns itemSalePrice
	 */
	public double getItemSalePrice() {
		return this.itemSalePrice;
	}
	
	/**
	 * setItemSalePrice - setter for itemSalePrice
	 * 
	 * @param - _itemSalePrice
	 * @return - returns itemSalePrice
	 */
	public void setItemSalePrice(double _itemSalePrice) {
		itemSalePrice = _itemSalePrice;
	}
	
	/**
	 * getMsrp - getter for msrp
	 * 
	 * @return - returns msrp
	 */
	public double getMsrp() {
		return this.msrp;
	}
	
	/**
	 * setMsrp - setter for msrp
	 * 
	 * @param - _msrp
	 * @return - returns msrp
	 */
	public void setMsrp(double _msrp) {
		msrp = _msrp;
	}
	
	/**
	 * getUPC - getter for upc
	 * 
	 * @return - returns upc
	 */
	public String getUPC() {
		return this.upc;
	}
	
	/**
	 * setUPC - setter for upc
	 * 
	 * @param - _upc
	 * @return - returns upc
	 */
	public void setUPC(String _upc) {
		upc = _upc;
	}
	
	/**
	 * getAvailability - getter for availability
	 * 
	 * @return - returns availability
	 */
	public String getAvailability() {
		return this.availability;
	}
	
	/**
	 * setAvailability - setter for availability
	 * 
	 * @param - _availability
	 * @return - returns availability
	 */
	public void setAvailability(String _availability) {
		availability = _availability;
	}
	
	/**
	 * getImages - getter for images
	 * 
	 * @return - returns images
	 */
	public List<ProductItemImage> getImages(){
		return images;
	}
	
	/**
	 * setImages - setter for images
	 * 
	 * @param - _images
	 * @return - returns images
	 */
	public void setImages(List<ProductItemImage> _images) {
		images = _images;
	}
	
	/**
	 * addImage - add an image for images
	 * 
	 * @param - image
	 */
	public void addImage(ProductItemImage image) {
		images.add(image);
	}
	
	/**
	 * addImage - add an image for images and also clears list of other images before doing so
	 * 
	 * @param - image, clearListBeforeAdd
	 */
	public void addImage(ProductItemImage image, boolean clearListBeforeAdd) {
		if(clearListBeforeAdd) {
			images.clear();
		}
		images.add(image);
	}
	
	/**
	 * toString - toString for item name
	 * 
	 * @return - name
	 */
	public String toString() {
		return name;
	}
}