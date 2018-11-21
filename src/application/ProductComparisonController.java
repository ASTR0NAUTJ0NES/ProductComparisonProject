package application;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * ProductComparisonController.java - controller class for ProductInfoGUI.fxml
 * 
 * @author Andrew P. Albanese
 * 
 */
public class ProductComparisonController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cboxSubCats"
    private ComboBox<CategoryItem> cboxSubCats; // Value injected by FXMLLoader

    @FXML // fx:id="btnSearch"
    private Button btnSearch; // Value injected by FXMLLoader

    @FXML // fx:id="editSearch"
    private TextField editSearch; // Value injected by FXMLLoader

    @FXML // fx:id="cboxCats"
    private ComboBox<CategoryItem> cboxCats; // Value injected by FXMLLoader

    @FXML // fx:id="imgView"
    private ImageView imgView; // Value injected by FXMLLoader

    @FXML // fx:id="resultsGrid"
    private GridPane resultsGrid; // Value injected by FXMLLoader
    
    @FXML
    private ListView<ProductItem> resultsListView;
    
    private StoreApiAdapterWalmart walMart;
    
    /**
     * onResultsListViewClicked - onAction method which executes code based on a mouse click of the ResultsListView
     * 
     * @param - event
     * @see - item detail
     */
    @FXML
    void onResultsListViewClicked(MouseEvent event) {
    	ProductItem item = resultsListView.getSelectionModel().getSelectedItem();
    	System.out.println(item.getImages().size());
    	if(item.getImages().size()>0) {
    		if(item.getImages().get(0).thumbnailURL!=null && item.getImages().get(0).thumbnailURL!="") {
            	String imageSrc = item.getImages().get(0).mediumImageURL;            	
    	    	Image img = new Image(imageSrc);
    	    	imgView.setImage(img);    		    			
    		}
    	}
    }
    
    /**
     * onCatAction - onAction method which executes code when a selection is made on the category comboBox
     * 
     * @param - event
     * @see - allows user to access subcategories
     */
    @FXML
    void OnCatAction(ActionEvent event) {
    	CategoryItem cat = cboxCats.getSelectionModel().getSelectedItem();    	
    	List<CategoryItem> subCats = walMart.getSubCategoriesForCategory(cat.id);
    	ObservableList<CategoryItem> obList = FXCollections.observableArrayList(subCats);
    	cboxSubCats.getItems().clear();
    	cboxSubCats.getItems().addAll(obList);    	
    	cboxSubCats.setDisable(false);
    	cboxSubCats.setPromptText("SubCategories");
    	cboxSubCats.setAccessibleHelp("SubCategories");
    	cboxSubCats.setAccessibleText("SubCategories");
    }

    @FXML
    void onCatMouseReleased(MouseEvent event) {
    }    
    
    /**
     * onSearchAction - onAction method which executes code when the user clicks the search button
     * 
     * @param - event
     * @see - reveals the resultsListView to be handled
     */
    @FXML
    protected void onSearchAction(ActionEvent event) {
        	String searchStr = editSearch.getText();
       	if(searchStr != null && searchStr != "") {
        	String catId = cboxCats.getValue().id;
        	String subCatId = cboxSubCats.getValue().id;
        	ProductItems foundItems = walMart.searchProduct(searchStr, catId, subCatId);
        	List<ProductItem> list = foundItems.getItemList();
        	ObservableList<ProductItem> obList = FXCollections.observableArrayList(list);
        	resultsListView.getItems().clear();
        	resultsListView.getItems().addAll(obList);    		
    	}
    }

    @FXML
    protected void onCatTextChanged(InputMethodEvent event) {
    }

    @FXML
    protected void onSubCatTextChanged(InputMethodEvent event) {
    }    

    /**
     * initialize - onAction method which contains FXML initialization code along with an initialization of the walmart store adapter and category list
     * 
     */
	@FXML // This method is called by the FXMLLoader when initialization is complete
    public void initialize() {
        assert cboxSubCats != null : "fx:id=\"cboxSubCats\" was not injected: check your FXML file 'ProductInfoGUI.fxml'.";
        assert btnSearch != null : "fx:id=\"btnSearch\" was not injected: check your FXML file 'ProductInfoGUI.fxml'.";
        assert editSearch != null : "fx:id=\"editSearch\" was not injected: check your FXML file 'ProductInfoGUI.fxml'.";
        assert cboxCats != null : "fx:id=\"cboxCats\" was not injected: check your FXML file 'ProductInfoGUI.fxml'.";
        assert imgView != null : "fx:id=\"imgView\" was not injected: check your FXML file 'ProductInfoGUI.fxml'.";
        assert resultsGrid != null : "fx:id=\"resultsGrid\" was not injected: check your FXML file 'ProductInfoGUI.fxml'.";
        
        walMart = new StoreApiAdapterWalmart("9gg853sunqrh4zvb7g4kdkhk");
        List<CategoryItem> cats = walMart.getCategories();
                
    	ObservableList<CategoryItem> obList = FXCollections.observableArrayList(cats);
    	cboxCats.getItems().clear();
    	cboxCats.getItems().addAll(obList);
        
    }
}
