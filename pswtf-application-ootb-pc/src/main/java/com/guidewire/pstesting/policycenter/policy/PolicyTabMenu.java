package com.guidewire.pstesting.policycenter.policy;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.TabMenu;
import com.guidewire.pstesting.policycenter.NewSubmissionPage;
import com.guidewire.pstesting.policycenter.submission.PolicySummaryScreen;
import org.openqa.selenium.By;

public class PolicyTabMenu extends TabMenu{
    static final String BASE_ID = "TabBar:PolicyTab:";

    static final By policyTabLocator          = By.id("TabBar:PolicyTab");
    static final By newSubmissionLocator       = By.id(BASE_ID + "PolicyTab_NewSubmission");
    static final By policySearchLocator       = By.id(BASE_ID + "PolicyTab_PolicyRetrievalItem-inputEl");
    static final By policySearchButtonLocator = By.id(BASE_ID + "PolicyTab_PolicyRetrievalItem_Button");

    public PolicyTabMenu(ScreenObjectController controller) {
        super(controller, policyTabLocator, newSubmissionLocator);
    }

    public void clickArrow() {
        getController().clickRightEdgeAndWait(getLocator(), newSubmissionLocator);
    }

    public NewSubmissionPage clickNewSubmission(){
        getController().click(newSubmissionLocator);
        return new NewSubmissionPage(getController()).waitUntilVisible();
    }

    public PolicySummaryScreen search(String policyNumber){
        getController().type(policySearchLocator, policyNumber).waitUntilUpdateDone();
        getController().click(policySearchButtonLocator);
        return new PolicySummaryScreen(getController()).waitUntilVisible();
    }
}
