package np.com.tourguide.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils {
    public static FirebaseAuth auth = FirebaseAuth.getInstance();
    public static DatabaseReference database = FirebaseDatabase.getInstance().getReference();
}
