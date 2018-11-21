package application;

import java.util.List;

/**
 * IStoreApiAdapter - interface class which provide conditional fields for all the adapters to conform correctly 
 * 
 * @author Andrew P. Albanese
 *
 */
public interface IStoreApiAdapter {
	
	void setApiKey(String apiKey);
	String getApiKey();
	List<CategoryItem> getCategories();
	List<CategoryItem> getSubCategoriesForCategory(String id);
	ProductItems searchProduct(String searchStr, String catId, String subCatId);
	ProductItem getProduct(String itemId);
	ProductItem getProductFromUPC(String upc);
	double getPrice(String itemId);
	
}
