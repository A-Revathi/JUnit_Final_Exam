package test;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pages.NSS_TODO_Page;
import util.BrowserFactory;

public class NSS_TODO_Test {

	WebDriver driver;
	NSS_TODO_Page nssTodopage;
	String text0 = "Apple";
	String text1 = "Banana";
	String text2 = "orange";
	String text3 = "Mango";
	String text4 = "Kiwi";
	
	//Test 1: Validate when the toggle all check box is CHECKED, all check boxes for list items are also CHECKED ON.
	@Test
	public void test1(){
		
		driver = BrowserFactory.init();
		
		nssTodopage = PageFactory.initElements(driver, NSS_TODO_Page.class);
		nssTodopage.clickToggleAllCheckBox();
		nssTodopage.clickRemoveButton();
		nssTodopage.clickOnAddButtonAndInsertText(text0,text1,text2,text3,text4);
		nssTodopage.validateIfAllTheCheckBoxesAreChecked();
		//BrowserFactory.tearDown();
    }
	
	//Test 2: Validate that a single list item could be removed using the remove button when a single checkbox is selected.
	//@Test
	public void test2() {
	
		driver = BrowserFactory.init();
		
		nssTodopage = PageFactory.initElements(driver, NSS_TODO_Page.class);
		nssTodopage.clickToggleAllCheckBox();
		nssTodopage.clickRemoveButton();
		nssTodopage.clickOnAddButtonAndInsertText(text0,text1,text2,text3,text4);
		nssTodopage.validateIfSingleListItemCouldBeRemoved();
		//BrowserFactory.tearDown();
	}
	
	//Test 3: Validate that all list item could be removed using the remove button and when "Toggle All" functionality is on.
    //@Test
    public void test3() {
    	
    	driver = BrowserFactory.init();
    	
    	nssTodopage = PageFactory.initElements(driver, NSS_TODO_Page.class);
		nssTodopage.clickToggleAllCheckBox();
		nssTodopage.clickRemoveButton();
		nssTodopage.clickOnAddButtonAndInsertText(text0,text1,text2,text3,text4);
		nssTodopage.validateIfAllListItemsCouldBeRemoved();
    }


}
