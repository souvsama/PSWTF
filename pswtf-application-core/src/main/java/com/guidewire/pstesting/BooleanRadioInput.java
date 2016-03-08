package com.guidewire.pstesting;

import org.openqa.selenium.By;

public class BooleanRadioInput extends ApplicationComponent {
    private static final String DEFAULT_SUFFIX = "-inputEl";

    private String locatorPrefix;
    private String locatorSuffix = DEFAULT_SUFFIX;

    public BooleanRadioInput(ScreenObjectController controller) {
        super(controller);
    }

    public BooleanRadioInput(ScreenObjectController controller, String locatorPrefix) {
        super(controller);
        this.locatorPrefix = locatorPrefix;
    }

    public BooleanRadioInput(ScreenObjectController controller, String locatorPrefix, String locatorSuffix) {
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

    public void setValue(Boolean value) {
        if (value != null) {
            getController().click(By.id(locatorPrefix + (value ? "true" : "false") + locatorSuffix))
                    .waitUntilUpdateDone();
        }
    }
}
