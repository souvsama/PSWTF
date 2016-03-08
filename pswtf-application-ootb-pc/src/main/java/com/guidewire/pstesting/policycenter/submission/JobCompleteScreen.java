package com.guidewire.pstesting.policycenter.submission;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import org.openqa.selenium.By;

public class JobCompleteScreen extends PolicyCenterComponent {
    public static final String BASE_ID       = "JobComplete:JobCompleteScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By viewPolicyLinkLocator = By.id(BASE_ID + "JobCompleteDV:ViewPolicy-inputEl");

    public JobCompleteScreen(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    @SuppressWarnings("unchecked")
    public JobCompleteScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    public PolicySummaryScreen clickViewPolicy() {
        getController().click(viewPolicyLinkLocator);
        return new PolicySummaryScreen(getController()).waitUntilVisible();
    }
}
