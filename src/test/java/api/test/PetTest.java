package api.test;

import api.endpoints.PetEndPoints;
import api.payload.Category;
import api.payload.Pet;
import api.payload.Tag;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PetTest {
    public Logger logger;
    Faker faker;
    Pet petPayload;
    String[] statuses;
    Random random;

    @BeforeClass
    public void setupData() {
        faker = new Faker();
        petPayload = new Pet();


        //Logs
        logger = LogManager.getLogger(this.getClass());

        //Create Category
        Category category = new Category();
        category.setId(faker.number().numberBetween(1001, 9999));
        category.setName(faker.animal().name());

        //Create Tag
        Tag tag = new Tag();
        tag.setId(faker.number().numberBetween(100, 999));
        tag.setName(faker.funnyName().name());
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);

        //PhotoURLs
        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("testData/cat_1.jpg");
        photoUrls.add("testData/cat_2.png");
        photoUrls.add("testData/cat_3.png");

        //Status must have only 3 values i.e "available", "Sold", "Pending"
        statuses = new String[]{"available", "pending", "sold"};
        random = new Random(); //to select any value from the array to test the api

        //Create Pet
        petPayload.setId(faker.idNumber().hashCode());
        petPayload.setCategory(category);
        petPayload.setName(faker.animal().name());
        petPayload.setPhotoUrls(photoUrls);
        petPayload.setTags(tags);
        petPayload.setStatus(statuses[random.nextInt(statuses.length)]);
    }

    @Test(priority = 1)
    public void testAddNewPet() {
        logger.info("****** Testing Add New Pet ******");

        Response response = PetEndPoints.postAddPet(petPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("****** Add New Pet tested successfully ******");
    }

    @Test(priority = 2)
    public void testUploadImage() {
        File imageFile = new File("testData/cat_1.jpg");

        logger.info("****** Testing Upload Image ******");
        Response response = PetEndPoints.postUploadImage(this.petPayload.getId(), imageFile);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("****** Upload Image Completed ******");
    }

    @Test(priority = 3)
    public void testGetPetByStatus() {
        logger.info("****** Testing Get Pet details By Status ******");
        Response response = PetEndPoints.getPetByStatus(this.petPayload.getStatus());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("****** Get Pet details By Status Completed ******");
    }

    @Test(priority = 4)
    public void testGetPetById() {
        logger.info("****** Testing Get Pet details By Id ******");
        Response response = PetEndPoints.getPetByPetId(98801744);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("****** Get Pet details By Status Id ******");
    }

    @Test(priority = 5)
    public void testUpdatePetData() {
        logger.info("****** Testing Update Pet Data ******");

        petPayload.setName("dog");
        Response response = PetEndPoints.putUpdatePet(petPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("****** Update Pet Data tested successfully ******");
    }

    @Test(priority = 6)
    public void testDeletePet() {
        logger.info("****** Testing Delete Pet ******");

        petPayload.setName("dog");
        Response response = PetEndPoints.deletePet(this.petPayload.getId());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("****** Delete Pet tested successfully ******");
    }
}
