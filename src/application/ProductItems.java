package application;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.*;

/**
 * ProductItems - class which populate a list of items using the productItem class
 * 
 * @author Andrew P. Albanese
 *
 */
public class ProductItems {
	private int vendorID;	
	private String vendor;		
	private List<ProductItem> items;
	private String queryStr;
	private String categoryId;
	private String sort;
	private String responseGroup;
	private int totalResults;
	private int start;
	private int numItems;
	
	/**
	 * ProductItems - constructor for class ProductItems
	 */
	public ProductItems() {
		items = new ArrayList<ProductItem>();
	}
	
	public String getQueryStr() {return this.queryStr;}
	public String getCategoryId() {return this.categoryId;}
	public String getSort() {return this.sort;}
	public String getResponseGroup() {return this.responseGroup;}	
	public int getTotalResults() {return this.totalResults;}
	
	public int getNumItems() {return this.numItems;}
	public void setNumItems(int _numItems) {numItems = _numItems;}
	
	public int getStart() {return this.start;}
	public void setStart(int _start) {start = _start;} 

	public int getVendorID() {return this.vendorID;	}	
	public void setVendorID(int _vendorID) {vendorID = _vendorID;}
	
	/**
	 * getVendor - getter for vendor
	 * 
	 * @return vendor
	 */
	public String getVendor() {
		return this.vendor;
	}
	
	/**
	 * setVendor - setter for vendor
	 * 
	 * @param _vendor
	 */
	public void setVendor(String _vendor) {
		vendor = _vendor;
	}
	
	/**
	 * getItemsList - getter for items
	 * 
	 * @return items
	 */
	public List<ProductItem> getItemList() {
		return this.items;
	}
	
	/**
	 * setItemsList - setter for items
	 * 
	 * @param _items
	 */
	public void setItemList(List<ProductItem> _items) {
		items = _items;
	}
	
	/**
	 * addItem - add the param item to the list items
	 * @param item
	 * @return item
	 */
	public boolean addItem(ProductItem item) {
		return items.add(item);
	}
	
	/**
	 * LoadProductItems - loads the product Items from the sites JSON
	 * 
	 * @param jsonString
	 */
	public void LoadProductItems(String jsonString) {
        JSONObject itemsObj = JSONObject.fromObject(jsonString);
        LoadProductItems(itemsObj);	
	}
	
	/**
	 * LoadProductItems - populates each item in items with their respective fields
	 * 
	 * @param itemsObj
	 */
	public void LoadProductItems(JSONObject itemsObj) {
        queryStr = itemsObj.getString("query");
        categoryId = itemsObj.getString("categoryId");
        sort = itemsObj.getString("sort");
        responseGroup = itemsObj.getString("responseGroup");
        totalResults = itemsObj.getInt("totalResults");
        numItems = itemsObj.getInt("numItems");
        start = itemsObj.getInt("start");
		JSONArray itemsArray = itemsObj.getJSONArray("items");
		int size = itemsArray.size();
		items.clear();
		
		
		for(int i=0; i < size; i++) {
			System.out.println(itemsArray.getString(i));
	        JSONObject itemJson = itemsArray.getJSONObject(i);
	        ProductItem item = getProductItemFromJsonObj(itemJson);
	        items.add(item);
		}
				
	}
	
	/**
	 * getProductItemFromJsonObj - getter for a productItem from the JsonObj
	 * 
	 * @param itemJson
	 * @return item 
	 */
	public ProductItem getProductItemFromJsonObj(JSONObject itemJson) {                       
        ProductItem item = new ProductItem();
        item.setItemID(itemJson.getString("itemId"));
        item.setName(itemJson.getString("name"));
        if(itemJson.containsKey("shortDescription")) {
            item.setDescription(itemJson.getString("shortDescription"));        	
        }
        item.setCategoryPath(itemJson.getString("categoryPath"));
        item.setItemSalePrice(itemJson.getDouble("salePrice"));
        
        if(itemJson.containsKey("msrp"))
        	item.setMsrp(itemJson.getDouble("msrp"));
        
        if(itemJson.containsKey("UPC"))
        	item.setUPC(itemJson.getString("UPC"));
        
        if(itemJson.containsKey("availability"))
        	item.setAvailability(itemJson.getString("availability"));
        
        List<ProductItemImage> imgList = new ArrayList<ProductItemImage>();
        
        JSONArray imgArray = itemJson.getJSONArray("imageEntities");
        
        for(int i = 0; i < imgArray.size(); i++) {
        	JSONObject imgJson = imgArray.getJSONObject(i);
        	ProductItemImage img = new ProductItemImage();        	
        	img.upc = itemJson.getString("upc");
        	img.thumbnailURL = imgJson.getString("thumbnailImage");
        	img.mediumImageURL = imgJson.getString("mediumImage");
        	img.largeImageURL = imgJson.getString("largeImage");
        	imgList.add(img);
        }
        
        item.setImages(imgList);
        
        return item;		
	}
} 
