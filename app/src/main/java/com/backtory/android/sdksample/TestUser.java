package com.backtory.android.sdksample;

/**
 * Created by mohammad on 2/15/17.
 *
 */
public class TestUser {

    private static TestUser first = new TestUserBuilder()
            .setName("TestUser")
            .setFamily("-")
            .setUsername("testUser")
            .setPassword("12341234")
            .setEmail("mm49307@gmail.com")
            .setUserId("58c188f8e4b09a3f67c63fbc")
            .build();
    private static TestUser second = new TestUserBuilder()
            .setName("TestUser2")
            .setFamily("-")
            .setUsername("testUser2")
            .setPassword("12341234")
            .setEmail("mm493072@gmail.com")
            .setUserId("58a1d423e4b09c2c6a51de27")
            .build();

    public static TestUser getFirst() {
        return first;
    }

    public static TestUser getSecond() {
        return second;
    }


    public String name;
    public String family;
    public String username;
    public String password;
    public String email;
    public String userId;

    private TestUser() {
    }

    private static class TestUserBuilder {

        private TestUser testUser;

        public TestUserBuilder() {
            testUser = new TestUser();
        }

        public TestUserBuilder setName(String name) {
            testUser.name = name;
            return this;
        }

        public TestUserBuilder setFamily(String family) {
            testUser.family = family;
            return this;
        }

        public TestUserBuilder setUsername(String username) {
            testUser.username = username;
            return this;
        }

        public TestUserBuilder setPassword(String password) {
            testUser.password = password;
            return this;
        }

        public TestUserBuilder setEmail(String email) {
            testUser.email = email;
            return this;
        }

        public TestUserBuilder setUserId(String userId) {
            testUser.userId = userId;
            return this;
        }

        public TestUser build() {
            return testUser;
        }
    }
}
