package application;

import java.util.ArrayList;
import java.util.List;
import java.net.*;
import java.io.*;
import org.apache.commons.io.IOUtils;
import net.sf.json.*;

import net.sf.json.JSONObject;

/**
 * StoreApiAdapterWalmart - class which is a store adapter for wal art specifically and contains all the fields which can be obtained through their JSON queries
 * 
 * @author Andrew P. Albanese
 *
 */
public class StoreApiAdapterWalmart extends BaseStoreApiAdapter {
	
	protected List<CategoryItem> categories; 
	protected JSONArray categoryJsonArray;
	
	/**
	 * StoreApiAdapterWalmart - constructor for the wal mart adapter
	 * 
	 * @param _apiKey
	 */
	public StoreApiAdapterWalmart(String _apiKey) {
		super(_apiKey);
		initCategories();
	}
	
	/**
	 * initCategories - initializes Arrays for category array so they can be used to be populated when queried
	 */
	protected void initCategories() {		
		String JSonString = "{'Data':'No Data'}";
		try {
			JSonString = readURL("http://api.walmartlabs.com/v1/taxonomy?apiKey=" + apiKey + "&format=json");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
        JSONObject catsJSON = JSONObject.fromObject(JSonString);
        categoryJsonArray = catsJSON.getJSONArray("categories");
        
        categories = new ArrayList<CategoryItem>();
        
        for(int i = 0; i < categoryJsonArray.size(); i++) {        	
        	categories.add(new CategoryItem(categoryJsonArray.getJSONObject(i)));
        }               
	}

	/**
	 * getCategories - getter for categories
	 * 
	 * @return - returns categories
	 */
	@Override 
	public List<CategoryItem> getCategories()
	{
		return categories;
	}
	
	/**
	 * getSubCategories - getter for subcategories
	 * 
	 * @param - String type id
	 * @return - returns subcategories
	 */
	@Override
	public List<CategoryItem> getSubCategoriesForCategory(String id){
		List<CategoryItem> subCatList = new ArrayList<CategoryItem>();
		for(int i = 0; i < categoryJsonArray.size();i++) {
			JSONObject cat = categoryJsonArray.getJSONObject(i);
			if(cat.getString("id") == id) {
				JSONArray subcatArray = cat.getJSONArray("children");
				if(subcatArray.size()>0) {
					for(int j = 0; j < subcatArray.size(); j++) {
						JSONObject subcat = subcatArray.getJSONObject(j);
						subCatList.add(new CategoryItem(subcat));
					}					
				}
			}			
		}
		return subCatList;
	}
	
	/**
	 * searchProduct - executes the search and queries for info
	 * 
	 * @param - String searchStr, String catId, String subCatId
	 * @return - returns list of queried search items
	 */
	@Override
	public ProductItems searchProduct(String searchStr, String catId, String subCatId) {
		String JSonString = "{'Data':'No Data'}";
		String searchCat = subCatId != null && subCatId != "" 
				? subCatId : catId != null && catId != ""
					? catId : "";
		try {
			String urlRoot = "http://api.walmartlabs.com/v1/search?apiKey=9gg853sunqrh4zvb7g4kdkhk";
			String qryStr = searchStr != null && searchStr != "" 
					? "&query=" + searchStr : "&query=";
			
			JSonString = searchCat != "" 
					? readURL(urlRoot+qryStr+"&categoryId="+searchCat+"&sort=bestseller")
					: readURL(urlRoot+qryStr+"&sort=bestseller");			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		ProductItems searchItems = new ProductItems();
		searchItems.LoadProductItems(JSonString);
		
		return searchItems;
	}

	/**
	 * getProduct - getter for product
	 * 
	 * @param - String itemID
	 * @return - returns product itemId
	 */
	@Override
	public ProductItem getProduct(String itemID) {
		return getProductFromField("itemId",itemID);
	}

	/**
	 * getProductFromUPC - getter for product from its UPC
	 * 
	 * @param - String upc
	 * @return - returns product from upc
	 */
	@Override
	public ProductItem getProductFromUPC(String upc) {
		return getProductFromField("upc",upc);
	}

	/**
	 * getPrice - getter for price
	 * 
	 * @param - String itemID
	 * @return - returns price
	 */
	@Override
	public double getPrice(String itemID) {		
		return getProduct(itemID).getItemSalePrice();
	}
	
	/** 
	 * readURL - reads the URL in 
	 * 
	 * @param webservice provides the URL address of the web service to be accessed
	 * @return String representation of the given web page or service
	 * @throws java.net.MalformedURLException if the given url is poorly formed
	 * @throws java.io.IOException if IOUtils encounters an IOException
	 *
	 **/ 
	private static String readURL(String webservice) throws java.net.MalformedURLException, java.io.IOException
	{
		// create a URL object from the given address		
		URL service = new URL(webservice);
		
		// use IOUtils to access the URL and return a string		
		String result = IOUtils.toString(service, "UTF-8");
		return result;
    }
	
	/**
	 * getProductFromField - gets the product given a field
	 * 
	 * @param fieldName
	 * @param fieldText
	 * @return item
	 */
	private ProductItem getProductFromField(String fieldName, String fieldText) {
		String JSonString = "{'Data':'No Data'}";
		try {
			JSonString = readURL("http://api.walmartlabs.com/v1/items?apiKey=9gg853sunqrh4zvb7g4kdkhk&"+fieldName+"="+fieldText);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
        JSONObject itemsJSON = JSONObject.fromObject(JSonString);
        JSONArray itemsArray = itemsJSON.getJSONArray("items");
        JSONObject itemJson = itemsArray.getJSONObject(0);
        
        
        
        ProductItem item = new ProductItem();
        item.setItemID(itemJson.getString("itemId"));
        item.setName(itemJson.getString("name"));
        item.setDescription(itemJson.getString("shortDescription"));
        item.setCategoryPath(itemJson.getString("categoryPath"));
        item.setItemSalePrice(itemJson.getDouble("salePrice"));
        item.setMsrp(itemJson.getDouble("msrp"));
        item.setUPC(itemJson.getString("UPC"));
        item.setAvailability(itemJson.getString("availability"));
        List<ProductItemImage> imgList = new ArrayList<ProductItemImage>();
        
        JSONArray imgArray = itemJson.getJSONArray("imageEntities");
        
        for(int i = 0; i < imgArray.size(); i++) {
        	JSONObject imgJson = itemsArray.getJSONObject(i);
        	ProductItemImage img = new ProductItemImage();        	
        	img.upc = itemJson.getString("upc");
        	img.thumbnailURL = imgJson.getString("thumbnailImage");
        	img.mediumImageURL = imgJson.getString("mediumImage");
        	img.largeImageURL = imgJson.getString("largeImage");
        	imgList.add(img);
        }
		return item;		
	}
}
