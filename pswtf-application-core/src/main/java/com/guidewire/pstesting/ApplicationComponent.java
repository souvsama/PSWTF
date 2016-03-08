package com.guidewire.pstesting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ApplicationComponent {

    private ScreenObjectController controller;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected ApplicationComponent(ScreenObjectController controller) {
        this.controller = controller;
    }

    public ScreenObjectController getController() {
        return controller;
    }

}
