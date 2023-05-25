package demo.model;

import java.util.Objects;

public class UserPrincipal {
    private String login;
    private String password;

    public UserPrincipal() {
    }

    public UserPrincipal(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrincipal that = (UserPrincipal) o;

        if (!Objects.equals(login, that.login)) return false;
        return Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserPrincipal{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
