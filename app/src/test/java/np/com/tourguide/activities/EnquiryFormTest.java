package np.com.tourguide.activities;

import org.junit.Before;
import org.junit.Test;

import np.com.tourguide.activities.TestClass.Validation;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class EnquiryFormTest {

    private Validation validate;


    @Before
    public void run() {
        validate = new Validation();
    }

    @Test
    public void is_fullName_empty() {
        String fullName="";
        assertEquals(true, validate.isEmpty(fullName));
    }
    @Test
    public void is_email_empty() {
        String email="";
        assertEquals(true, validate.isEmpty(email));
    }
    @Test
    public void is_alternate_email_empty() {
        String email="";
        assertEquals(true, validate.isEmpty(email));
    }

    @Test
    public void is_country_list() {
        String List="";
        assertEquals(true, validate.isCountrylist_Empty(List));
    }


    @Test
    public void is_phone_number_empty() {
        String contactNumber="";
        assertEquals(true, validate.isEmpty(contactNumber));
    }
    @Test
    public void is_phone_number_valid() {
        String contactNumber="9721823677";
        assertEquals(true, validate.isValidPhoneNumber(contactNumber));
    }
    @Test
    public void is_email_valid() {
        String Email="abhishek@gmail.com";
        assertEquals(true, validate.isValidEmail(Email));
    }



    @Test
    public void numberTraveller_empty() {
        String Traveller="";
        assertEquals(true, validate.isEmpty(Traveller));
    }
    @Test
    public void tripDetails_empty() {
        String tripDetails="";
        assertEquals(true, validate.isEmpty(tripDetails));
    }


}