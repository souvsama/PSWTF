package com.guidewire.pstesting.policycenter;

public class Product {
    private final String code;
    private final String name;
    private final String nameDisplayKey;
    private final SubmissionWizardStepSetFactory wizardFactory;

    private Product(String code, String name, String nameDisplayKey, SubmissionWizardStepSetFactory wizardFactory) {
        this.code = code;
        this.name = name;
        this.nameDisplayKey = nameDisplayKey;
        this.wizardFactory = wizardFactory;
    }

    public static Product create(String code, String name, String nameDisplayKey, SubmissionWizardStepSetFactory wizardFactory) {
        return new Product(code, name, nameDisplayKey, wizardFactory);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getNameDisplayKey() {
        return nameDisplayKey;
    }

    public SubmissionWizardStepSetFactory getWizardFactory() {
        return wizardFactory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
