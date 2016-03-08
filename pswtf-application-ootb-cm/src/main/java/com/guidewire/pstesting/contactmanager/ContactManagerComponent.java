package com.guidewire.pstesting.contactmanager;

import com.guidewire.pstesting.ApplicationComponent;
import com.guidewire.pstesting.ScreenObjectController;

public abstract class ContactManagerComponent extends ApplicationComponent {
    public static final String RESOURCE_BASE_NAME = "locale.cm";

    protected ContactManagerComponent(ScreenObjectController controller) {
        super(controller);
    }

    /**
     * Returns the base name of the resource bundle
     *
     * @return the base name of the resource bundle, a fully qualified class name
     */
    public String getResourceBaseName() {
        return RESOURCE_BASE_NAME;
    }
}
