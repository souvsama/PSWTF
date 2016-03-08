package com.guidewire.pstesting;

public interface WizardScreenFactory {
    /**
     * Constructs a new instance of a wizard screen.
     *
     * @return the new wizard screen instance
     */
    WizardScreen create();

    /**
     * Constructs a new instance of a wizard screen and waits until it is visible.
     *
     * @return the new wizard screen instance
     */
    WizardScreen createAndWait();
}
