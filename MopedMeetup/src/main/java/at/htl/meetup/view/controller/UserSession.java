package at.htl.meetup.view.controller;

public final class UserSession {
    private static UserSession instance;

    private static Long userId;

    private UserSession(Long id) {
        this.userId = id;
    }

    public static UserSession getInstace(Long id) {
        if(instance == null) {
            instance = new UserSession(id);
        }
        return instance;
    }

    public static Long getId() {
        return userId;
    }

    public static void cleanUserSession() {
        instance = null;
    }
}
