package com.guidewire.pstesting;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public abstract class WizardStepSet extends ApplicationComponent {
    private int currentStep = 0;

    private final List<WizardScreen> steps = new ArrayList<>();

    /**
     * Constructs a <code>WizardStepSet</code>.
     *
     * @param controller the <code>ScreenObjectController</code> to use
     */
    public WizardStepSet(ScreenObjectController controller) {
        super(controller);
    }

    public int getCurrentStepIndex() {
        return currentStep;
    }

    public void setCurrentStepIndex(int currentStep) {
        this.currentStep = currentStep;
    }

    /**
     * Returns the locator for the back button.
     *
     * @return the locator for the back button
     */
    public abstract By getBackLocator();

    /**
     * Returns the locator for the next button.
     *
     * @return the locator for the next button
     */
    public abstract By getNextLocator();

    /**
     * Indicates if the back button exists on the step
     *
     * @return <code>true</code> if the back button is available
     * on the step; <code>false</code> otherwise.
     */
    public boolean isBackAvailable() {
        return getController().elementExists(getBackLocator());
    }

    /**
     * Indicates if the next button exists on the step
     *
     * @return <code>true</code> if the next button is available
     * on the step; <code>false</code> otherwise.
     */
    public boolean isNextAvailable() {
        return getController().elementExists(getNextLocator());
    }

    public void addStepFactory(WizardScreenFactory factory) {
        steps.add(factory.create());
    }

    public WizardScreen getCurrentStepScreen() {
        return getStepScreen(currentStep);
    }

    public WizardScreen clickBack() {
        return clickBack(1);
    }

    public WizardScreen clickNext() {
        return clickNext(1);
    }

    public WizardScreen clickBack(int stepCount) {
        WizardScreen screen = null;
        for (int i = 0; i < stepCount; i++) {
            if (isBackAvailable()) {
                getController().click(getBackLocator()).waitUntilUpdateDone();
                screen = getStepScreen(--currentStep);
            } else {
                throw new UnsupportedOperationException("Back operation not available");
            }
        }
        return screen;
    }

    public WizardScreen clickNext(int stepCount) {
        WizardScreen screen = null;
        for (int i = 0; i < stepCount; i++) {
            if (isNextAvailable()) {
                getController().click(getNextLocator()).waitUntilUpdateDone();
                // Check for errors before moving on
                if (noErrorsOnPage()){
                    screen = getStepScreen(++currentStep);
                } else {
                    screen = getStepScreen(currentStep);
                    break;
                }
                //screen = (noErrorsOnPage()) ? getStepScreen(++currentStep) : getStepScreen(currentStep);
            } else {
                throw new UnsupportedOperationException("Next operation not available");
            }
        }
        return screen;
    }

    protected boolean noErrorsOnPage(){
        String userMsgs = checkForErrors();
        return userMsgs.isEmpty();
    }

    protected String checkForErrors() {
        UserMessage userMsg = new UserMessage(getController().getWebDriver());
        if (userMsg.isVisible()){
            logger.warn(userMsg.messageType + ": " + userMsg.messageText);
            // Don't fail for a warning
            if (userMsg.messageType.contains("ERROR")) {
                return (userMsg.messageType + ": " + userMsg.messageText);
            } else {
                return "";
            }
        } else {
            return ""; //getController().findScreenObject(By.className("g-title")).getText();
        }
    }

    private WizardScreen getStepScreen(int stepIndex) {
        return (WizardScreen) steps.get(stepIndex).waitUntilVisible();
    }
}
