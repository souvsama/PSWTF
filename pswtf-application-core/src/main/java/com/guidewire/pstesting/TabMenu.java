package com.guidewire.pstesting;

import org.openqa.selenium.By;

public class TabMenu extends ApplicationComponent {
    private final By tabLocator;
    private final By tabContains;

    public TabMenu(ScreenObjectController controller, By tabLocator) {
        this(controller, tabLocator, null);
    }

    public TabMenu(ScreenObjectController controller, By tabLocator, By tabContains) {
        super(controller);
        this.tabLocator = tabLocator;
        this.tabContains = tabContains;
    }

    public By getLocator() {
        return tabLocator;
    }

    public boolean isVisible() {
        return getController().pageContains(tabLocator);
    }

    public TabMenu waitUntilVisible() {
        getController().waitUntilPageContains(tabLocator);
        return this;
    }

    public void click() {
        getController().click(tabLocator);
    }

    public void clickArrow() {
        getController().clickRightEdgeAndWait(tabLocator, tabContains);
    }

    public boolean isSelected() {
        return getController().elementExists(tabContains);
    }

    public TabMenu waitUntilSelected() {
        getController().waitUntilPageContains(tabContains);
        return this;
    }
}
