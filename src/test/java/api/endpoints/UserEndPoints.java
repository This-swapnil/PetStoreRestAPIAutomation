package api.endpoints;


import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

//Created to perform the CRUD operations of the user API
public class UserEndPoints {
    public static Response createUser(User payload) {

        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_url);
    }


    public static Response createUserWithArray(User[] users) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(users)
                .when()
                .post(Routes.post_url_array);
    }


    public static Response createUserWithList(List<User> users) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(users)
                .when()
                .post(Routes.post_url_list);
    }


    public static Response readUser(String username) {

        return given()
                .pathParam("username", username)
                .when()
                .get(Routes.get_url);
    }

    public static Response updateUser(String username, User payload) {

        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .body(payload)
                .when()
                .put(Routes.update_url);
    }

    public static Response deleteUser(String username) {

        return given()
                .pathParam("username", username)
                .when()
                .delete(Routes.delete_url);
    }


    public static Response loginUser(String username, String password) {
        return given()
                .queryParam("username", username)
                .queryParam("password", password)
                .when()
                .get(Routes.login_url);
    }

    public static Response logoutUser() {
        return given()
                .when()
                .get(Routes.logout_url);
    }
}