package com.guidewire.pstesting.claimcenter.claim;


import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.WizardStepSet;
import org.openqa.selenium.By;

public abstract class ClaimWizardStepSet extends WizardStepSet {
    public static final String RESOURCE_BASE_NAME = "locale.cc";

    static final By backLocator   = By.id("FNOLWizard:Prev-btnInnerEl");
    static final By nextLocator   = By.id("FNOLWizard:Next-btnInnerEl");
    static final By cancelLocator = By.id("FNOLWizard:Cancel-btnInnerEl");
    static final By finishLocator = By.id("FNOLWizard:Finish-btnInnerEl");

    public ClaimWizardStepSet(ScreenObjectController controller) {
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

    public void clickCancel() {
        getController().click(cancelLocator);
    }

    public By getBackLocator() {
        return backLocator;
    }

    public By getNextLocator() {
        return nextLocator;
    }


    public NewClaimSavedScreen clickFinish() {
        getController().click(finishLocator);
        return new NewClaimSavedScreen(getController()).waitUntilVisible();
    }


}
