package pages;

import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage {
	
	public void selectFromDropdown(WebElement element, String visibleText) {
		Select sel = new Select(element);
		sel.selectByVisibleText(visibleText);
	}

	public int geneRandomNum(int boundary) {
		Random rnd = new Random();
		int generatedNum = rnd.nextInt(boundary);
		return generatedNum;
	}
}
