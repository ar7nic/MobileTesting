package service;

import models.User;

public class UserCreator {

    public static final String TESTDATA_USER_LOGIN = "testdata.user.login";
    public static final String TESTDATA_USER_PASSWORD = "testdata.user.password";

    public static User withCredentialsFromProperty(){

        return new User("my.testing.aqa@gmail.com", "Testinggmail");
//        return new User(TestDataReader.getTestData(TESTDATA_USER_LOGIN),
//                TestDataReader.getTestData(TESTDATA_USER_PASSWORD));
    }

    public static User withWrongUsername(){
        return new User("1jf7hdg64@gmail.com", "Testinggmail");
//        return new User("1jf7hdg64@gmail.com", TestDataReader.getTestData(TESTDATA_USER_PASSWORD));
    }

    public static User withWrongPassword(){
        return new User("my.testing.aqa@gmail.com", "ssdfgh");
//        return new User(TestDataReader.getTestData(TESTDATA_USER_LOGIN), "ssdfgh");
    }
}
