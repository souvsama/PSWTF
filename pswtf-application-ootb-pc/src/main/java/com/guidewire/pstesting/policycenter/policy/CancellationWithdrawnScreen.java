package com.guidewire.pstesting.policycenter.policy;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import com.guidewire.pstesting.policycenter.submission.PolicySummaryScreen;
import org.openqa.selenium.By;

public class CancellationWithdrawnScreen extends PolicyCenterComponent {
    public static final String BASE_ID = "JobComplete:JobCompleteScreen:";
    public static final By PAGE_CONTAINS    = By.id(BASE_ID + "ttlBar");

    public static final By viewPolicyLocator = By.id(BASE_ID + "JobCompleteDV:ViewPolicy-inputEl");

    protected CancellationWithdrawnScreen(ScreenObjectController controller) {
        super(controller);
    }

    public CancellationWithdrawnScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public PolicySummaryScreen viewPolicy(){
        getController().click(viewPolicyLocator);
        return new PolicySummaryScreen(getController()).waitUntilVisible();
    }
}
