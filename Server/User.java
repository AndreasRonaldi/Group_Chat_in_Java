package Server;

public class User {
    public String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User))
            return false;

        User u = (User) o;

        return username.equals(u.username) && password.equals(u.password);
    }

    public boolean isItThisUser(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
