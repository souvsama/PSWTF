package com.guidewire.pstesting.claimcenter.desktop;

import com.guidewire.pstesting.ApplicationComponent;
import com.guidewire.pstesting.ApplicationPage;
import com.guidewire.pstesting.ScreenObjectController;
import org.openqa.selenium.By;

public class DesktopPage extends ApplicationComponent implements ApplicationPage {
    public static final String BASE_ID = "DesktopActivities:";

    static final By assignButtonLocation = By.id(BASE_ID + "DesktopActivitiesScreen:DesktopActivities_AssignButton-btnInnerEl");

    public DesktopPage(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(assignButtonLocation);
    }

    public DesktopPage waitUntilVisible() {
        getController().waitUntilPageContains(assignButtonLocation);
        return this;
    }
}
