package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddNewContactTests extends AppiumConfig {

    @BeforeClass
    public void preCondition() {
        new AuthenticationScreen(driver)
                .filLoginRegistrationForm(Auth.builder().email("margo@gmail.com").password("Mmar123456$").build())
                .submitLogin();
    }


    @Test
    public void createNewContactSuccess() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Simon")
                .lastName("Wow" + i)
                .email("wow" + i + "@gmail.com")
                .phone("6789456" + i)
                .address("Haifa")
                .description("The best")
                .build();


        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(),contact.getLastName());


    }

    @Test
    public void createNewContactSuccessReq() {

    }


    @Test
    public void createContactWithEmptyName(){
        Contact contact = Contact.builder()
                .name("")
                .lastName("Wow")
                .email("wow@gmail.com")
                .phone("678945665945" )
                .address("Haifa")
                .description("The best")
                .build();


        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorMessageHasText("{name=must not be blank}");

    }

    @AfterClass
    public void postCondition() {
        new ContactListScreen(driver).logout();
    }
}
