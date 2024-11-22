package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ContactFacade {

    private final ContactService contactService;

    @Autowired
    public ContactFacade(ContactService contactService) {
        this.contactService = contactService;
    }

    public List<ContactDto> getAll(){
        List<ContactDto> res = new ArrayList<>();
        Map<Long, Contact> contactMap= contactService.getAllContacts();
        List<Contact> contactList = contactMap.values().stream().toList();
        for(Contact contact : contactList) {
            ContactDto contactDto = new ContactDto(contact);
            res.add(contactDto);
        }
        return res;
        }

    public ContactDto getContactDto(long id){
        var contact = contactService.getContact(id);
        return new ContactDto(contact);
    }

    public ContactDto createContact(String name, String phoneNumber, String email){
        Contact contact = contactService.addContact(name, phoneNumber, email);
        return new ContactDto(contact);
    }

    public ContactDto updateContact(long id, String param){
        Contact contact = contactService.updateContact(id, param);
        return new ContactDto(contact);
    }
}
