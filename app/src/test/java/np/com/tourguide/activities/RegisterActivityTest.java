package np.com.tourguide.activities;

import org.junit.Before;
import org.junit.Test;

import np.com.tourguide.activities.TestClass.Validation;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class RegisterActivityTest {
    private Validation validate;


    @Before
    public void run() {
        validate = new Validation();
    }

    @Test
    public void is_email_empty() {
        String email="";
        assertEquals(true, validate.isEmpty(email));
    }
    @Test
    public void is_full_name() {
        String fullName="";
        assertEquals(true, validate.isEmpty(fullName));
    }
    @Test
    public void is_country_empty() {
        String country="";
        assertEquals(true, validate.isEmpty(country));
    }
    @Test
    public void is_phone_empty() {
        String number="";
        assertEquals(true, validate.isEmpty(number));
    }
    @Test
    public void is_phone_number_valid() {
        String phoneNumber="9841354221";
        assertEquals(true, validate.isValidPhoneNumber(phoneNumber));
    }


    @Test
    public void is_password_empty() {
        String password="";
        assertEquals(true, validate.isEmpty(password));
    }
    @Test
    public void is_confirm_password_empty() {
        String confirm_Password="";
        assertEquals(true, validate.isEmpty(confirm_Password));
    }


}