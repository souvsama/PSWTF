package com.guidewire.pstesting.policycenter;

import com.guidewire.pstesting.suite.Contact;

public interface Account extends Contact {
    /* Account types */
    String PERSON = "person";
    String COMPANY = "company";

    String getId();

    void setId(String id);

    String getAccountNumber();

    void setAccountNumber(String accountNumber);

    String getName();

    String getType();

    void setType(String type);

    String getOrganization();

    void setOrganization(String organization);

    String getProducerCode();

    void setProducerCode(String producerCode);
}
