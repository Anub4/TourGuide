package np.com.tourguide.activities.TestClass;

import java.util.List;

public class Validation {


    private Object List;

    public boolean isEmpty(String text){
        String t="";
        return text.matches(t);
    }
    public boolean isValidEmail(String email){
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
    public boolean isValidPhoneNumber(String PhoneNumber){
        String  number= "^[0-9]{10,13}$";
        return PhoneNumber.matches(number);
    }

    public boolean isCountrylist_Empty(String Country){
        if (List =="Nepal");
        return Country.matches(String.valueOf(List));
    }


}
