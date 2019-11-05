package np.tourguide;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import np.com.tourguide.R;
import np.com.tourguide.activities.EnquireActivity;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class EnquiryformTest {
    @Rule
    public ActivityTestRule<EnquireActivity> testRule = new ActivityTestRule<>(EnquireActivity.class);
    public String fullname="abhishek";
    public String email = "abhishekadhikari16a@gmail.com";
//    public String country="Afghanistan";
    public String phonenumber="9803134828";
    public String message = "i want to explore the world";


    @Test
    public void enquiry() {
        onView(withId(R.id.etEmail)).perform(typeText(email));
        closeSoftKeyboard();
        onView(withId(R.id.etFullName)).perform(typeText(fullname));
        closeSoftKeyboard();
//        onView(withId(R.id.etCountry)).perform(typeText(country));
//        closeSoftKeyboard();
        onView(withId(R.id.etPhoneNumber)).perform(typeText(phonenumber));
        closeSoftKeyboard();
        onView(withId(R.id.etMessage)).perform(typeText(message));
        closeSoftKeyboard();
        onView(withId(R.id.btnEnquiry)).perform(click());
    }
}
