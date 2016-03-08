package com.guidewire.pstesting.claimcenter.claim.payment;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.WizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import com.guidewire.pstesting.claimcenter.ClaimWizardScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class PaymentInformationScreen extends ClaimWizardScreen<PaymentInformationScreen> {
    public static final String BASE_ID      = "NormalCreateCheckWizard:CheckWizard_CheckPaymentsScreen:";
    public static final By PAGE_CONTAINS    = By.id(BASE_ID + "ttlBar");
    public static final By paymentLineItemsLocator    = By.id(BASE_ID + "NewCheckPaymentPanelSet:NewPaymentDetailDV:EditablePaymentLineItemsLV-body");
    public static final By paymentAmountLocator     = By.name("Amount");
    public static final By lineCategoryLocator      = By.name("LineCategory");

    public PaymentInformationScreen(ScreenObjectController controller) {
        super(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public PaymentInformationScreen setCategory(String category) {
        WebElement rowElement = getFirstRowInPaymentLineItemsLV();
        WebElement categoryCell = getRowCell(rowElement, 1); // 2nd column in the LV
        By listLocator = By.className("x-list-plain");
        try {
            getController().clickRightEdgeAndWait(categoryCell, listLocator);
        } catch (Exception e) {
            getController().clickAndWaitFluently(categoryCell, listLocator);
        }
        getController().setTextAndTab(lineCategoryLocator,category);
/*
        getController().clickAndWait(categoryCell, 1)
                .pressDown()
                .pressEnter()
                .sleep(1000);
*/
        return this;
    }

    public PaymentInformationScreen setAmount(String paymentAmount) {
        WebElement rowElement = getFirstRowInPaymentLineItemsLV();
        WebElement paymentAmountCell = getRowCell(rowElement, 2); // 3rd column in the LV
        By listLocator = By.className("x-list-plain");
        try {
            getController().clickRightEdgeAndWait(paymentAmountCell, listLocator);
        } catch (Exception e) {
            getController().clickAndWaitFluently(paymentAmountCell, listLocator);
        }
        getController().setTextAndTab(paymentAmountLocator,paymentAmount);
/*
        getController().click(paymentAmountCell)
                .type(paymentAmount)
                .pressEnter()
                .sleep(1000);
*/
        return this;
    }

    private WebElement getFirstRowInPaymentLineItemsLV() {
        WebElement tbodyElement = getController().findScreenObject(paymentLineItemsLocator).findElement(By.tagName("tbody"));
        List<WebElement> rows = tbodyElement.findElements(By.tagName("tr"));
        WebElement firstRow = rows.get(0);
        return firstRow;
    }

    private WebElement getRowCell(WebElement rowElement, int index) {
        return rowElement.findElements(By.tagName("td")).get(index);
    }

    //================================================================================
    // Factory
    //================================================================================

    public static class Factory implements WizardScreenFactory {
        private final ScreenObjectController controller;

        public Factory(ScreenObjectController controller) {
            this.controller = controller;
        }

        /**
         * Constructs a <code>PaymentInformationScreen</code>.
         */
        public WizardScreen create() {
            return new PaymentInformationScreen(controller);
        }

        /**
         * Constructs a <code>PaymentInformationScreen</code> and waits until it is visible.
         *
         * @return the new <code>PaymentInformationScreen</code> instance
         */
        public WizardScreen createAndWait() {
            return new PaymentInformationScreen(controller).waitUntilVisible();
        }
    }
}
