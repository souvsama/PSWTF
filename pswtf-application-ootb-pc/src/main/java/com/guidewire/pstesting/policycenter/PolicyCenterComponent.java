package com.guidewire.pstesting.policycenter;

import com.guidewire.pstesting.ApplicationComponent;
import com.guidewire.pstesting.ScreenObjectController;
import org.openqa.selenium.By;

public abstract class PolicyCenterComponent extends ApplicationComponent {
    public static final String RESOURCE_BASE_NAME = "locale.pc";

    private String baseIdentifier;

    protected PolicyCenterComponent(ScreenObjectController controller) {
        super(controller);
    }

    protected PolicyCenterComponent(ScreenObjectController controller, String baseIdentifier) {
        super(controller);
        this.baseIdentifier = baseIdentifier;
    }

    /**
     * Returns the base name of the resource bundle
     *
     * @return the base name of the resource bundle, a fully qualified class name
     */
    public String getResourceBaseName() {
        return RESOURCE_BASE_NAME;
    }

    protected String getBaseIdentifier() {
        return baseIdentifier;
    }

    protected By createLocator(String id) {
        StringBuilder builder = new StringBuilder();
        builder.append(baseIdentifier);
        if (!baseIdentifier.endsWith(":")) {
            builder.append(":");
        }
        builder.append(id);
        return By.id(builder.toString());
    }
}
