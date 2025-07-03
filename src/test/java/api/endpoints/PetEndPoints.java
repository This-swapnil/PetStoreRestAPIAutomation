package api.endpoints;

import api.payload.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PetEndPoints {
    public static Response postUploadImage(int petId, File imageFile) {
        return given()
                .multiPart("file", imageFile)
                .pathParam("petId", petId)
                .accept(ContentType.JSON)
                .when()
                .post(Routes.postUploadImage_url);
    }

    public static Response postAddPet(Pet payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.postPet_url);
    }

    public static Response putUpdatePet(Pet payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .put(Routes.postPet_url);
    }

    public static Response getPetByStatus(String status) {
        return given()
                .queryParam("status", status)
                .when()
                .get(Routes.getFindByStatus_url);
    }

    public static Response getPetByPetId(int petId) {
        return given()
                .pathParam("petId", petId)
                .when()
                .get(Routes.getPetId_url);
    }


    public static Response deletePet(int petId) {
        return given()
                .when()
                .pathParam("petId", petId)
                .delete(Routes.deletePetId_url);
    }
}
