package np.com.tourguide.activities.TestClass;

public class Validation {
    public boolean isEmpty(String text){
        return text.equals("");
    }
    public boolean isValidEmail(String email){
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}
