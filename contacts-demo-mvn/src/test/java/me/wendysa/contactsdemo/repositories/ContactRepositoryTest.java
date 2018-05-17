package me.wendysa.contactsdemo.repositories;

import me.wendysa.contactsdemo.models.Contact;
import org.junit.*;
import static org.junit.Assert.*;

public class ContactRepositoryTest {
    private ContactRepository contactRepository;

    @Before
    public void initialise() {
        contactRepository = new ContactRepository();
    }

    @After
    public void tearDown() {
        contactRepository.deleteAll();
    }

    @Test
    public void createNullContact() {
        Contact nullContact = contactRepository.push(null);
        assertNull(nullContact);
    }
}
