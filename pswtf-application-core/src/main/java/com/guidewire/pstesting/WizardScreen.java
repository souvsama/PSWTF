package com.guidewire.pstesting;

import org.openqa.selenium.By;

public abstract class WizardScreen<T> extends ApplicationComponent {
    public WizardScreen(ScreenObjectController controller) {
        super(controller);
    }

    /**
     * Returns an object that identifies an element that is always present on the page.
     *
     * @return the element contained on the page
     */
    public abstract By getPageContains();

    public boolean isVisible() {
        return getController().pageContains(getPageContains());
    }

    @SuppressWarnings("unchecked")
    public T waitUntilVisible() {
        getController().waitUntilPageContains(getPageContains());
        return (T)this;
    }
}
