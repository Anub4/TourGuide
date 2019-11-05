package np.com.tourguide;


import org.junit.Before;
import org.junit.Test;

import np.com.tourguide.activities.TestClass.Validation;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LoginTest {

    private Validation validate;
    private String email;
    private String password;


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
    public void is_password_empty() {
        String password="";
        assertEquals(true, validate.isEmpty(password));
    }


    @Test
    public void is_email_valid() {
        String text = "abhishek@gmail.com";
        assertEquals(true, validate.isValidEmail(text));
    }
}
