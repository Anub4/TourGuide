package np.tourguide;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import np.com.tourguide.R;
import np.com.tourguide.activities.LoginActivity;
import np.com.tourguide.activities.RegisterActivity;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RegisterActivityTest {
    @Rule
    public ActivityTestRule<RegisterActivity> testRule = new ActivityTestRule<>(RegisterActivity.class);
    public String email = "abhishekadhikari16a@gmail.com";
    public String fullname="abhishek";
    public String country="nepal";
    public String phonenumber="9803134828";
    public String password = "123456";
    public String confirmpassword="123456";

    @Test
    public void register() {
        onView(withId(R.id.etEmail)).perform(typeText(email));
        closeSoftKeyboard();
        onView(withId(R.id.etFullName)).perform(typeText(fullname));
        closeSoftKeyboard();
        onView(withId(R.id.etCountry)).perform(typeText(country));
        closeSoftKeyboard();
        onView(withId(R.id.etPhoneNumber)).perform(typeText(phonenumber));
        closeSoftKeyboard();
        onView(withId(R.id.etPassword)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.etConfirmPassword)).perform(typeText(confirmpassword));
        closeSoftKeyboard();
        onView(withId(R.id.btnRegister)).perform(click());
    }
}
