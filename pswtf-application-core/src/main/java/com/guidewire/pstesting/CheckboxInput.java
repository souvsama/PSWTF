package com.guidewire.pstesting;

import org.openqa.selenium.By;

public class CheckboxInput extends ApplicationComponent {

    private String locatorPrefix;
    private String locatorSuffix;

    protected CheckboxInput(ScreenObjectController controller) {
        super(controller);
    }

    public CheckboxInput(ScreenObjectController controller, String locatorPrefix, String locatorSuffix) {
        super(controller);
        this.locatorPrefix = locatorPrefix;
        this.locatorSuffix = locatorSuffix;
    }

    public void setLocatorPrefix(String locatorPrefix) {
        this.locatorPrefix = locatorPrefix;
    }

    public String getLocatorPrefix() {
        return locatorPrefix;
    }

    public void setLocatorSuffix(String locatorSuffix) {
        this.locatorSuffix = locatorSuffix;
    }

    public String getLocatorSuffix() {
        return locatorSuffix;
    }

    /**
     * Use this method to select all available checkbox options.
     */
    public void clickAll() {
        // Loop through all existing checkbox field using a counter. Exit loop when an element is not found.
        for(int counter = 0; getController().elementExists(By.id(locatorPrefix + counter + locatorSuffix)); counter++ ) {
            getController().click(By.id(locatorPrefix + counter + locatorSuffix));
        }
    }

    /**
     * Use this method to select specific checkbox.
     * @param checkboxIndices Indices of the checkbox that needs to be selected. First checkbox has an index of zero.
     */
    public void clickSpecific(Integer[] checkboxIndices) {
        for (Integer index : checkboxIndices) {
            By element = By.id(locatorPrefix + index + locatorSuffix);
            if (getController().elementExists(element)) {
                getController().click(element);
            }
        }
    }

}

