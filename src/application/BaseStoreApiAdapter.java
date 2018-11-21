package application;

import java.util.List;

/**
 * BaseStoreApiAdapter - abstract class which is an adapter for any store which has these fields in common to be used
 * 
 * @author Andrew P. Albanese
 */
public abstract class BaseStoreApiAdapter implements IStoreApiAdapter {
	
	protected String apiKey;

	public BaseStoreApiAdapter(String _apiKey) {
		apiKey = _apiKey;
	}
	
	/**
	 * setApiKey - setter for apiKey
	 * 
	 * @param - apiKey
	 * @return - new apiKey
	 */
	@Override
	public void setApiKey(String _apiKey) {
        apiKey = _apiKey;
	}
	
	/**
	 * getApiKey - getter for apiKey
	 * 
	 * @return - current apiKey
	 */
	@Override
	public String getApiKey() {
        return this.apiKey;
	}	

	/**
	 * getSubCategoriesForCategory - abstract signature where descendant classes will return product sub-categories
	 * 
	 * @param - String type id
	 */
	@Override
	public abstract List<CategoryItem> getSubCategoriesForCategory(String id);
	
	/**
	 * getCategories - abstract signature where descendant classes will return product categories
	 * 
	 */
	@Override
	public abstract List<CategoryItem> getCategories();
	
	/**
	 * searchProduct - abstract signature where descendant classes will return product search results
	 * 
	 * @param - String searchStr, String catId, String subCatId
	 */
	@Override
	public abstract ProductItems searchProduct(String searchStr, String catId, String subCatId);

	/**
	 * getProduct - abstract signature where descendant classes will return product sub-categories
	 * 
	 * @param - String type id
	 */
	@Override
	public abstract ProductItem getProduct(String sku);

	/**
	 * getProductFromUPC - abstract signature where descendant classes will return product from the UPC
	 * 
	 * @param - String type upc
	 */
	@Override
	public abstract ProductItem getProductFromUPC(String upc);

	/**
	 * getPrice - abstract signature where descendant classes will return product price from sku
	 * 
	 * @param - String type sku
	 */
	@Override
	public abstract double getPrice(String sku);

}
