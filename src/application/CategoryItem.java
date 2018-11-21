package application;

import net.sf.json.JSONObject;

/**
 * CategoryItem - entity class for a category item
 * 
 * @author Andrew P. Albanese
 *
 */
public class CategoryItem {
	public String id;
	public String name;
	public String path;
	
	/**
	 * CategoryItem - constructor for the CategoryItem class
	 * 
	 * @param categoryJson
	 */
	public CategoryItem(JSONObject categoryJson) {
		id = categoryJson.getString("id");
		name = categoryJson.getString("name");
		path = categoryJson.getString("path");
	}
	
	/**
	 * FromJson - obtains the id, name, and path from a JSON query to the site
	 * 
	 * @param categoryJson
	 */
	public void FromJson(JSONObject categoryJson) {
		id = categoryJson.getString("id");
		name = categoryJson.getString("name");
		path = categoryJson.getString("path");
	}
	
	/**
	 * toString - toString for the item
	 * 
	 * @return - returns item name
	 */
	public String toString() {
		return name;
	}
}
