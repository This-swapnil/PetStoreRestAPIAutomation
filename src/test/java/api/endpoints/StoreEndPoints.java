package api.endpoints;

import api.payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class StoreEndPoints {
    public static Response getStore() {
        return given()
                .when()
                .get(Routes.getStore_url);
    }

    public static Response getStoreOrderId(int orderId) {
        return given()
                .pathParam("orderId", orderId)
                .when()
                .get(Routes.getStoreOrderId_url);
    }

    public static Response createOrder(Store payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.postStore_url);
    }

    public static Response deleteStoreOrder(int orderId) {
        return given()
                .pathParam("orderId", orderId)
                .when()
                .delete(Routes.deleteStoreOrderId_url);
    }
}
