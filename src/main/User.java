package main;

abstract class User {

    String firstName;
    String lastName;
    String phoneNumber;
    String username;
    String password;
    AccessLevel accessLevel;
    Date registrationDate;
    Date lastLoginDate;

    public ActionResult login(String username, String password) {
        String pass = Integer.toString(password.hashCode());
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username.equals(username)) {
                if (users.get(i).password.equals(pass)) {
                    return ActionResult.SUCCESS;
                } else
                    return ActionResult.INCORRECT_PASSWORD;
            }
        }
        return ActionResult.USERNAME_NOT_FOUND;
    }
}
