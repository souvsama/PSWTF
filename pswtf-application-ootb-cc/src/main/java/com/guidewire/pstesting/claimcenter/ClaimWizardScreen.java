package com.guidewire.pstesting.claimcenter;

import com.guidewire.pstesting.utilities.JavascriptHelper;
import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.WizardScreen;
import org.openqa.selenium.By;

public abstract class ClaimWizardScreen<T> extends WizardScreen<T> {
    public static final String RESOURCE_BASE_NAME = "locale";
    private JavascriptHelper jscript = new JavascriptHelper(getController().getWebDriver());
    static final By backLocator   = By.id("FNOLWizard:Prev-btnInnerEl");
    static final By nextLocator   = By.id("FNOLWizard:Next-btnInnerEl");
    static final By cancelLocator = By.id("FNOLWizard:Cancel-btnInnerEl");
    static final By finishLocator = By.id("FNOLWizard:Finish-btnInnerEl");
    static final By msgBoxLocator = By.id("messagebox-1001");
    static final By btnOKLocator = By.linkText("OK");

    protected ClaimWizardScreen(ScreenObjectController controller) {
        super(controller);
    }

    /**
     * Returns the base name of the resource bundle
     *
     * @return the base name of the resource bundle, a fully qualified class name
     */
    public String getResourceBaseName() {
        return RESOURCE_BASE_NAME;
    }

    public void cancelClaim(){
        getController().clickAndWait(cancelLocator,msgBoxLocator);
        getController().click(btnOKLocator);
    }

    public void clickNext(){
        getController().click(nextLocator);
    }

    public void clickBack(){
        getController().click(backLocator);
    }

    public void clickFinish(){
        getController().click(finishLocator);
    }

    /**
     * Submits wizard page to next step and checks for user messages displayed
     * @return   Text of any user messages
     */
    public String submitToNextStep(By nextPageLocator){
        if (getController().findScreenObject(nextLocator).isDisplayed()) {
            try {
                clickNext();
                getController().waitForElement(nextPageLocator);
                logger.info("New page: " + jscript.getPageName());
            } catch (Exception e) {
                // Ignore exception
            }
        }
        return getController().checkForErrors();
    }
}
