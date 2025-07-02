package api.test;


import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class UserTest {

    public Logger logger;
    Faker faker;
    User userPayload;

    @BeforeClass
    public void setupData() {
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //logs
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPostUser() {
        logger.info("****** Creating User ******");
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("****** User is Created ******");
    }

    @Test(priority = 2)
    public void testCreateUserWithArray() {
        logger.info("****** Creating Multiple Users with Array ******");

        Faker faker = new Faker();
        User[] users = new User[5];

        for (int i = 0; i < users.length; i++) {
            User u = new User();
            u.setId(faker.number().numberBetween(1000, 9999));
            u.setUsername(faker.name().username());
            u.setFirstName(faker.name().firstName());
            u.setLastName(faker.name().lastName());
            u.setEmail(faker.internet().safeEmailAddress());
            u.setPassword(faker.internet().password(6, 10));
            u.setPhone(faker.phoneNumber().cellPhone());
            u.setUserStatus(1);
            users[i] = u;
        }

        Response response = UserEndPoints.createUserWithArray(users);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("****** Users Created with Array Successfully ******");
    }


    @Test(priority = 3)
    public void testCreateUsersWithList() {
        logger.info("****** Creating Multiple Users with List ******");
        //We create a new Faker object and User object for testCreateUsersWithList() and testCreateUserWithArray() method because each time we need unique data to send the request to API
        //If we create a separate method to generate the fake data and use it again then it will throw error, error like "User is already exits!"
        Faker faker = new Faker();
        List<User> userList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setId(faker.number().numberBetween(1000, 9999));
            user.setUsername(faker.name().username());
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setEmail(faker.internet().safeEmailAddress());
            user.setPassword(faker.internet().password(6, 10));
            user.setPhone(faker.phoneNumber().cellPhone());
            user.setUserStatus(1);

            userList.add(user);
        }

        Response response = UserEndPoints.createUserWithList(userList);

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("****** Users Created with List Successfully ******");
    }

    @Test(priority = 4)
    public void testGetUserName() {
        logger.info("****** Reading User Info ******");
        //System.out.println("UserName: " + this.userPayload.getUsername());
        Response response = UserEndPoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("****** User info is displayed ******");
    }

    @Test(priority = 5)
    public void testUpdateUserByName() {
        logger.info("****** Updating User ******");
        //Update data using payload
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        //Checking data updation
        Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
        responseAfterUpdate.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("****** User is Updated ******");
    }

    @Test(priority = 6)
    public void testDeleteUserByName() {
        logger.info("****** Deleting User ******");
        Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("****** User is Deleted ******");
    }

    @Test(priority = 7)
    public void testLoginUser() {
        logger.info("****** Logging User in... ******");

        Response response = UserEndPoints.loginUser(this.userPayload.getUsername(), this.userPayload.getPassword());
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("****** User Logged in Successfully. ******");
    }

    @Test(priority = 8)
    public void testLogoutUser() {
        logger.info("****** User is being logged out ******");

        Response response = UserEndPoints.logoutUser();
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("****** User is logged out Successfully ******");
    }
}
