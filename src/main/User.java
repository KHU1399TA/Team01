package main;

class User {

    String firstName;
    String lastName;
    String phoneNumber;
    String username;
    String password;
    AccessLevel accessLevel;
    Date registrationDate;
    Date lastLoginDate;

    public ActionResult login(String username, String password) {
        String pass = password.hashCode();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username.equals(username)) {
                if (users.get(i).password.equals(pass)) {
                    return SUCCESS;
                } else
                    return INCORRECT_PASSWORD;
            }
        }
        return USERNAME_NOT_FOUND;
    }
}
