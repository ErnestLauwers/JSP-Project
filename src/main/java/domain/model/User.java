package domain.model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private int userid = 0;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Team team;
    private Role role;

    public User(String email, String password, String firstName, String lastName, Team team) {
        setEmail(email);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setTeam(team);
        setRole(Role.EMPLOYEE);
        setUserid(userid++);
    }

    public User(int userid, String email, String password, String firstName, String lastName, Team team) {
        this(email, password, firstName, lastName, team);
        this.setRole(Role.EMPLOYEE);
        this.setUserid(userid);
    }

    public User() {
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setEmail(String email) {
        if (email == null);
        else if (email.isEmpty()) {
            throw new IllegalArgumentException("No email given");
        }
        String USERID_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(USERID_PATTERN);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            throw new IllegalArgumentException("No valid email");
        }
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    private String getPassword() {
        return password;
    }

    public boolean isCorrectPassword(String password) {
        if (password.isEmpty()) {
            throw new IllegalArgumentException("No password given");
        }
        return getPassword().equals(password);
    }

    public void setPassword(String password) {
        if (password == null);
        else if (password.isEmpty()) {
            throw new IllegalArgumentException("No password given");
        }
        this.role = Role.EMPLOYEE;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null);
        else if (firstName.isEmpty()) {
            throw new IllegalArgumentException("No firstname given");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null);
        else if (lastName.isEmpty()) {
            throw new IllegalArgumentException("No last name given");
        }
        this.lastName = lastName;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setTeam(String team) {
        if (team == null);
        else {
            try {
                this.team = Team.valueOf(team.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new DomainException("No team selected");
            }
        }
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRole(String role) {
        if (role == null);
        else {
            try {
                this.role = Role.valueOf(role.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new DomainException("No role selected");
            }
        }
    }

    public boolean isPasswordCorrect(String password) {
        if (password != null && password.equals(this.password)) return true;
        return false;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + ": " + getUserid() + ", " + getEmail() + ", " + getRole() + ", " + getTeam();
    }
}