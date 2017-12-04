package myprojects.automation.assignment5.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Pavel Holinko on 03.12.2017.
 */
public class UserData {

    private String firstname;
    private String lastname;
    private String email;

    public UserData(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    // class variable


    private static String randomIdentifier() {
        String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new java.util.Random();
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = rand.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
        }
        return builder.toString();
    }

    public static UserData generate() {
        return new UserData(
                randomIdentifier(),
                randomIdentifier(),
                randomIdentifier() + "@p33.org" );
    }
}
