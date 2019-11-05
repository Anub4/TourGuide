package np.tourguide;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import np.com.tourguide.R;
import np.com.tourguide.activities.LoginActivity;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {
    @Rule
    public ActivityTestRule<LoginActivity> testRule = new ActivityTestRule<>(LoginActivity.class);
    public String email = "abhishekadhikari16a@gmail.com";
    public String password = "qwertyuiop";

    @Test
    public void login() {
        onView(withId(R.id.etEmail)).perform(typeText(email));
        closeSoftKeyboard();
        onView(withId(R.id.etPassword)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.btnLogin)).perform(click());
    }
}
