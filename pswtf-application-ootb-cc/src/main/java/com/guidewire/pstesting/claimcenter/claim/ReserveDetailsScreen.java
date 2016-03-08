package com.guidewire.pstesting.claimcenter.claim;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.claimcenter.ClaimCenterComponent;
import org.openqa.selenium.By;

/**
 * User: rgoodwin
 * Date: 04/02/2015
 */
public class ReserveDetailsScreen extends ClaimCenterComponent {
    public static final String BASE_ID       = "ClaimFinancialsTransactionsDetail:ClaimFinancialsTransactionsDetailScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");
    public static final By editButtonLocator = By.id(BASE_ID + "TransactionDetailToolbarButtonSet:TransactionDetailToolbarButtons_EditButton-btnEl");

    public ReserveDetailsScreen(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public ReserveDetailsScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    public ReserveScreen clickEditButton() {
        getController().click(editButtonLocator);
        return new ReserveScreen(getController());
    }
}
