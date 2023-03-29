package business.logic.layer;

public class UserProfileInstance {
    private static UserProfile instance;

    public static UserProfile getUserProfile()
    {
        if (instance == null)
        {
            instance = new UserProfile();
        }
        return instance;
    }


}
