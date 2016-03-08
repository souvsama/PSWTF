package com.guidewire.pstesting.policycenter;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.submission.SubmissionWizardStepSet;

public interface SubmissionWizardStepSetFactory {
    SubmissionWizardStepSet create(ScreenObjectController controller);
}
