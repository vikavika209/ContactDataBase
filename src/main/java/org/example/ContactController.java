package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    private final ContactFacade contactFacade;

    @Autowired
    public ContactController(ContactFacade contactFacade) {
        this.contactFacade = contactFacade;
    }

    @GetMapping
    public List<ContactDto> getAll(){
        return getContactFacade().getAll();
    }

    @GetMapping("/{contactId}")
    public ContactDto getContact (@PathVariable("contactId") long contactId){
        return getContactFacade().getContactDto(contactId);
    }

    @PostMapping
    public ContactDto createContact(@RequestParam String name,
                                    @RequestParam String phone,
                                    @RequestParam String email){
        return getContactFacade().createContact(name, phone, email);
    }

    @PutMapping("/{contactId}")
    public ContactDto updateContact(@PathVariable("contactId") long contactId,
                                    @RequestParam String param){
        return getContactFacade().updateContact(contactId, param);
    }

    public ContactFacade getContactFacade() {
        return contactFacade;
    }
}
