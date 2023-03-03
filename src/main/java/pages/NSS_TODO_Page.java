package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

//Test 1: Validate when the toggle all check box is CHECKED, all check boxes for list items are also CHECKED ON.
//Test 2: Validate that a single list item could be removed using the remove button when a single checkbox is selected.
//Test 3: Validate that all list item could be removed using the remove button and when "Toggle All" functionality is on.

public class NSS_TODO_Page extends BasePage {

	// GLOBAL VARIABLE DECLARATIONS
	WebDriver driver;
	List<WebElement> Checkboxes;
	boolean isCheckedOrNot;
	int onlyListedCheckboxes;

	public NSS_TODO_Page(WebDriver driver) {
		this.driver = driver;
	}

	// WEBELEMENT LIST
	@FindBy(how = How.XPATH, using = "//input[@onclick='checkAll();']")
	WebElement TOGGLE_ALL_BUTTON_ELEMENT;
	@FindBy(how = How.XPATH, using = "//input[@value='Remove']")
	WebElement REMOVE_BUTTON_ELEMENT;
	@FindBy(how = How.XPATH, using = "//input[@value='Add']")
	WebElement ADD_BUTTON_ELEMENT;
	@FindBy(how = How.XPATH, using = "//input[@name='data']")
	WebElement TEXT_FIELD_ADD_ELEMENT;

	// CORRESPONDING METHODS
	public void clickToggleAllCheckBox() {
		TOGGLE_ALL_BUTTON_ELEMENT.click();
	}

	public void clickRemoveButton() {
		REMOVE_BUTTON_ELEMENT.click();
	}

	public void clickOnAddButtonAndInsertText(String text0, String text1, String text2, String text3, String text4) {

		String[] textInput = { text0, text1, text2, text3, text4 };
		for (int i = 0; i < textInput.length; i++) {
			TEXT_FIELD_ADD_ELEMENT.click();
			TEXT_FIELD_ADD_ELEMENT.sendKeys(textInput[i]);
			ADD_BUTTON_ELEMENT.click();
		}
	}

	// Test 1: Validate when the toggle all check box is CHECKED, all check boxes
	// for list items are also CHECKED ON.
	public void validateIfAllTheCheckBoxesAreChecked() {

		clickToggleAllCheckBox();

		Checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		for (int i = 1; i < Checkboxes.size(); i++) {
			isCheckedOrNot = driver.findElement(By.xpath("//input[@type='checkbox']")).isSelected();
		}
		String res = (isCheckedOrNot == true) ? "Yes! All checkboxes are checked"
				: "Oh No, All checkboxes are NOT checked";
		System.out.println(res);
		System.out.println("Total number of checkboxes including Toggle All are : " + Checkboxes.size());

	}

	// Test 2: Validate that a single list item could be removed using the remove
	// button when a single checkbox is selected.
	public void validateIfSingleListItemCouldBeRemoved() {

		Checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		onlyListedCheckboxes = Checkboxes.size() - 1;
		System.out.println("Total number of Checkboxes in the CheckList are : " + onlyListedCheckboxes);
		// generating a random number
		int rndNumToRemove = geneRandomNum(4);
		System.out.println("Random generated number in between 0 to 4: is ->  " + rndNumToRemove);

		String beforeXpathForText = "//body/div[2]/form/ul/li[";
		String afterXpathForText = "]";
		String beforeXpathForCheckbox = "//input[@name='todo[";
		String afterXpathForCheckbox = "]']";

		WebElement selectedTextForCheckbox;
		String getTextForCheckbox;

		for (int i = rndNumToRemove; i < onlyListedCheckboxes;) {

			WebElement selectedCheckbox = driver
					.findElement(By.xpath(beforeXpathForCheckbox + i + afterXpathForCheckbox));
			selectedCheckbox.click();// clicking on the randomly generated num of the checkbox in the list.
			selectedTextForCheckbox = driver.findElement(By.xpath(beforeXpathForText + ++i + afterXpathForText));
			getTextForCheckbox = selectedTextForCheckbox.getText();
			System.out.println("Random generated number " + "\"" + rndNumToRemove + "\"" + " is matching with: " + "\""
					+ getTextForCheckbox + "\"  and is ready to be removed.");
			if (selectedCheckbox.isSelected()) {
				clickRemoveButton();// Using Remove button to remove a single list item.
				System.out.println(getTextForCheckbox + " ->  is removed successfully !");
			}
			break;
		}
		Checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		onlyListedCheckboxes = Checkboxes.size() - 1;
		System.out.println(
				"Total number of Checkboxes after removing 1 item from the CheckList are : " + onlyListedCheckboxes);
	}

	// Test 3: Validate that all list item could be removed using the remove button
	// and when "Toggle All" functionality is on.
	public void validateIfAllListItemsCouldBeRemoved() {

		Checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		clickToggleAllCheckBox();
		isCheckedOrNot = TOGGLE_ALL_BUTTON_ELEMENT.isSelected();
		String res = (isCheckedOrNot == true) ? "Toggle All functionality is ON"
				: "Toggle All functionality is OFF";
		System.out.println(res);
		onlyListedCheckboxes = Checkboxes.size() - 1;
		System.out.println("Total number of Checkboxes in the CheckList Before clicking on Remove button are : " + onlyListedCheckboxes);
		clickRemoveButton();//clicking on Remove button to remove All the list items.
		//Now counting the checkbox list After clicking Remove button while Toggle All is ON.
		Checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		onlyListedCheckboxes = Checkboxes.size() - 1;
		System.out.println("Total number of Checkboxes in the CheckList After clicking on Remove button While Toggle All is ON  : " + onlyListedCheckboxes);
		
        
	}
}