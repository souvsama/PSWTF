package com.guidewire.pstesting.claimcenter.claim.payment;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.WizardStepSet;
import com.guidewire.pstesting.claimcenter.claim.FinancialPaymentsScreen;
import org.openqa.selenium.By;

public class ClaimPaymentWizardStepSet extends WizardStepSet {
    public static final String BASE_ID       = "NormalCreateCheckWizard:CheckWizard_CheckPayeesScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");
    static final By backLocator   = By.id("NormalCreateCheckWizard:Prev-btnInnerEl");
    static final By nextLocator   = By.id("NormalCreateCheckWizard:Next-btnInnerEl");
    static final By cancelLocator = By.id("NormalCreateCheckWizard:Cancel-btnInnerEl");
    static final By finishLocator = By.id("NormalCreateCheckWizard:Finish-btnInnerEl");

    public ClaimPaymentWizardStepSet(ScreenObjectController controller) {
        super(controller);
        initialize();
    }

    private void initialize() {
        addStepFactory(new PayeeInformationScreen.Factory(getController()));
        addStepFactory(new PaymentInformationScreen.Factory(getController()));
        addStepFactory(new CheckInstructionsScreen.Factory(getController()));
    }

    public ClaimPaymentWizardStepSet waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    public void clickCancel() {
        getController().click(cancelLocator);
    }

    public By getBackLocator() {
        return backLocator;
    }

    public By getNextLocator() {
        return nextLocator;
    }

    public FinancialPaymentsScreen clickFinish() {
        getController().click(finishLocator);
        return new FinancialPaymentsScreen(getController()).waitUntilVisible();
    }
}
