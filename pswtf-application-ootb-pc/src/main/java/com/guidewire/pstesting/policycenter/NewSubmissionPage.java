package com.guidewire.pstesting.policycenter;

import com.guidewire.pstesting.ApplicationComponent;
import com.guidewire.pstesting.ApplicationPage;
import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.WizardStepSet;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class NewSubmissionPage extends ApplicationComponent implements ApplicationPage {
    public static final String BASE_ID       = "NewSubmission:NewSubmissionScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By organizationLocator = By.id(BASE_ID + "SelectAccountAndProducerDV:ProducerSelectionInputSet:Producer-inputEl");
    static final By producerCodeLocator = By.id(BASE_ID + "SelectAccountAndProducerDV:ProducerSelectionInputSet:ProducerCode-inputEl");

    static final By defaultBaseStateLocator = By.id(BASE_ID + "ProductSettingsDV:DefaultBaseState-inputEl");

    private final ProductOfferingsTable productOfferingsTable;

    /* Map of wizard step sets keyed by product identifier */
    public final Map<String, WizardStepSet> wizardStepSetMap = new HashMap<>();

    public NewSubmissionPage(ScreenObjectController controller) {
        super(controller);
        this.productOfferingsTable = new ProductOfferingsTable(controller);
    }

    public boolean isVisible() {
        return productOfferingsTable.isVisible();
    }

    public NewSubmissionPage waitUntilVisible() {
        productOfferingsTable.waitUntilVisible();
        return this;
    }

    public NewSubmissionPage setOrganization(String organization) {
        // Is the value already set? If so, do nothing
        if (organization != null && !organization.equals(getOrganization())) {
            getController().typeAndTab(organizationLocator, organization);
        }
        return this;
    }

    public String getOrganization() {
        return getController().getValue(organizationLocator);
    }

    public NewSubmissionPage setProducerCode(String producerCode) {
        // Is the value already set? If so, do nothing
        if (producerCode != null && !producerCode.equals(getProducerCode())) {
            getController().clickTypeAndEnter(producerCodeLocator, producerCode);
        }
        return this;
    }

    public String getProducerCode() {
        return getController().getValue(producerCodeLocator);
    }

    public NewSubmissionPage setDefaultBaseState(String defaultState) {
        // Is the value already set? If so, do nothing
        if (defaultState != null && !defaultState.equals(getDefaultBaseState())) {
            getController().clickTypeAndEnter(defaultBaseStateLocator, defaultState);
        }
        return this;
    }

    public String getDefaultBaseState() {
        return getController().getValue(defaultBaseStateLocator);
    }

    public ProductOfferingsTable getProductOfferingsTable() {
        return productOfferingsTable;
    }

    /**
     * Selects an offering and initiates the submission wizard.
     *
     * @param product the product to select
     *
     * @return the initial submission wizard step for the offering
     */
    public WizardStepSet selectProduct(Product product) {
        WizardStepSet stepSet = product.getWizardFactory().create(getController());  // Initialize wizard step set
        ScreenObjectController controller = stepSet.getController();
        // Get product offering name
        String offering = controller.getString(product.getNameDisplayKey());  // Get the localize product name if available
        if (offering == null) {
            offering = product.getName();
        }
        // Locate and select the product in the offerings table
        ProductOfferingsTable offeringsTable = getProductOfferingsTable();
        Integer currentPage = offeringsTable.getCurrentPage();
        if (currentPage == null) {
            offeringsTable.selectRow(offering);
        } else {
            if (currentPage != 1) {
                offeringsTable.selectPage("1");
            }
            while (true) {
                // Is the offering on the current page? If so, we're done otherwise check next page
                if (offeringsTable.rowExists(offering)) {
                    offeringsTable.selectRow(offering);
                    break;
                }
                // Advance to next page and confirm that one exists
                currentPage++;
                offeringsTable.selectPage(currentPage.toString());
                if (!offeringsTable.getCurrentPage().equals(currentPage)) {
                    throw new RuntimeException("Failed to locate offering: " + offering);
                }
            }
        }
        return stepSet;
    }

    public NewSubmissionPage selectOfferingsPage(String offeringsPage) {
        getProductOfferingsTable().selectPage(offeringsPage);
        return this;
    }

    public void addWizardStepSet(Product product, WizardStepSet stepSet) {
        wizardStepSetMap.put(product.getCode(), stepSet);
    }

    public WizardStepSet getWizardStepSet(Product product) {
        WizardStepSet stepSet = wizardStepSetMap.get(product.getCode());
        stepSet.setCurrentStepIndex(0);
        return stepSet;
    }

}
