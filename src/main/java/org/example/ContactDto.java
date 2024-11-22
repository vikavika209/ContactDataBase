package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactDto {
    @JsonProperty("contactId")
    private long contactId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("email")
    private String email;

    public ContactDto(Contact contact) {
        this.contactId = contact.getId();
        this.name = contact.getName();
        this.phoneNumber = contact.getPhoneNumber();
        this.email = contact.getEmail();
    }

    public ContactDto() {
    }

    public long getContactId() {
        return contactId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
