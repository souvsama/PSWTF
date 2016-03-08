package com.guidewire.pstesting.policycenter.submission;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.WizardStepSet;
import org.openqa.selenium.By;

public abstract class SubmissionWizardStepSet extends WizardStepSet {
    public static final String RESOURCE_BASE_NAME = "locale.pc";

    static final By backLocator = By.id("SubmissionWizard:Prev-btnInnerEl");
    static final By nextLocator = By.id("SubmissionWizard:Next-btnInnerEl");

    /**
     * Returns the base name of the resource bundle
     *
     * @return the base name of the resource bundle, a fully qualified class name
     */
    public String getResourceBaseName() {
        return RESOURCE_BASE_NAME;
    }

    public By getBackLocator() {
        return backLocator;
    }

    public By getNextLocator() {
        return nextLocator;
    }

    public SubmissionWizardStepSet(ScreenObjectController controller) {
        super(controller);
    }
}
