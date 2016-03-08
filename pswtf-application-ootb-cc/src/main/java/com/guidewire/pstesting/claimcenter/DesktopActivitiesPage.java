package com.guidewire.pstesting.claimcenter;

import com.guidewire.pstesting.ApplicationPage;
import com.guidewire.pstesting.utilities.JavascriptHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;


public class DesktopActivitiesPage implements ApplicationPage {

    private final WebDriver driver;
    private JavascriptHelper update;
    public final Logger logger = LoggerFactory.getLogger(getClass());
    // Init visible elements
    static final String BASE_ID = "DesktopActivities:DesktopActivitiesScreen:";
    @FindBy(id = BASE_ID + "0") WebElement pageTitle;
    @FindBy(id = BASE_ID + "DesktopActivitiesLV:DesktopActivitiesFilter-inputEl") WebElement lvFilter;
    @FindBy(id = BASE_ID + "DesktopActivitiesLV-body") WebElement lvGrid;
    @FindBy(id = BASE_ID + "DesktopActivities_AssignButton") WebElement btnAssign;
    @FindBy(id = ":TabLinkMenuButton") WebElement btnLogoutMenu;
    @FindBy(id = "TabBar:UnsavedWorkTabBarLink") WebElement wkUnsaved;
    @FindBy(id = "Desktop:DesktopMenuActions") WebElement mnuActions;
    @FindBy(id = "TabBar:DesktopTab") WebElement tabDesktop;
    @FindBy(id = "TabBar:ClaimTab") WebElement tabClaim;
    @FindBy(id = "TabBar:SearchTab") WebElement tabSearch;
    // Locators for elements not initially visible
    static final By logoutLocator = By.id("TabBar:LogoutTabBarLink-itemEl");
    WebDriverWait wait ;

    public DesktopActivitiesPage(WebDriver driver){
        this.driver = driver;
        update = new JavascriptHelper(driver);
        PageFactory.initElements(driver,this);
        }

    public DesktopActivitiesPage waitUntilVisible(){
        wait = new WebDriverWait(driver,10);
        wait.until(visibilityOf(pageTitle));
        return this;
    }

    public boolean isVisible(){
        return pageTitle.isDisplayed();
    }

    public void logout(){
        wait = new WebDriverWait(driver,5);
        btnLogoutMenu.click();
        wait.until(presenceOfElementLocated(logoutLocator)).click();
    }

}
