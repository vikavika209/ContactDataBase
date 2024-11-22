package org.example;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static javax.lang.model.SourceVersion.isName;

@Service
public class InMemoryContactService implements ContactService{
    Map<Long, Contact> contactMap;

    public InMemoryContactService(Map<Long, Contact> contactMap) {
        this.contactMap = new HashMap<>();
    }

    @Override
    public Contact getContact(long id) {
        return findContact(id)
                .orElseThrow(() -> new IllegalArgumentException("Contact not found " + id));
    }

    @Override
    public Map<Long, Contact> getAllContacts() {
        System.out.println(contactMap);
        return contactMap;
    }

    @Override
    public Contact addContact(String name, String phoneNumber, String email) {
        long maxKey = contactMap
                .keySet()
                .stream()
                .max(Long::compareTo)
                .orElse(0L);

        long id = maxKey + 1;
        Contact contact = new Contact(id, name, phoneNumber, email);
        contactMap.put(id, contact);
        return contact;
    }

    @Override
    public Contact updateContact(long id, String param) {
        if (isName(param)) {
            Contact contact = new Contact(id, param, contactMap.get(id).getPhoneNumber(), contactMap.get(id).getEmail());
            contactMap.put(id, contact);
            return contact;
        }
        else if (isEmail(param)) {
            Contact contact = new Contact(id, contactMap.get(id).getName(), contactMap.get(id).getPhoneNumber(), param);
            contactMap.put(id, contact);
            return contact;
        }

        else if (isPhoneNumber(param)) {
            Contact contact = new Contact(id, contactMap.get(id).getName(), param, contactMap.get(id).getEmail());
            contactMap.put(id, contact);
            return contact;
        }
        else throw new IllegalArgumentException("The input string can't be read " + param);
    }

        @Override
        public Optional<Contact> findContact(long id){
            return Optional.ofNullable(contactMap.get(id));
        }

        private boolean isName(String line){
            String nameRegex = "^[A-Za-zА-Яа-яЁё\\s'-]+$";
            return line.matches(nameRegex);
        }
        private boolean isPhoneNumber(String line){
            String phoneNumberRegex = "^\\+?[0-9\\-]{7,15}$";
            return line.matches(phoneNumberRegex);
        }

        private boolean isEmail(String line){
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9+.-]+$";
            return line.matches(emailRegex);
        }
}
