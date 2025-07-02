package api.test;

import api.endpoints.StoreEndPoints;
import api.payload.Store;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Instant;

public class StoreTest {
    public Logger logger;
    Faker faker;
    Store storePayload;

    @BeforeClass
    public void setupData() {
        faker = new Faker();
        storePayload = new Store();
        String currentTimestampUTC = Instant.now().toString();
        System.out.println("TimeStamp: " + currentTimestampUTC);

        storePayload.setId(faker.number().numberBetween(1, 10));
        storePayload.setPetId(faker.number().numberBetween(1000, 9999));
        storePayload.setQuantity(faker.number().numberBetween(1, 10));
        storePayload.setShipDate(currentTimestampUTC);
        storePayload.setStatus("placed");
        storePayload.setComplete(true);

        //logs
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPlaceOrder() {
        logger.info("***** Testing Placed Order *****");
        Response response = StoreEndPoints.createOrder(storePayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("***** Placed Order Test Completed *****");
    }

    @Test(priority = 2)
    public void testGetInventories() {
        logger.info("***** Testing Get Inventories *****");

        Response response = StoreEndPoints.getStore();
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("***** Get Inventories Completed *****");
    }

    @Test(priority = 3)
    public void testGetPurchaseByOrderId() {
        logger.info("***** Testing Purchase By Order Id *****");
        Response response = StoreEndPoints.getStoreOrderId(this.storePayload.getId());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("***** Purchase By Order Id Completed *****");
    }

    @Test(priority = 4)
    public void testDeleteByOrderId() {
        logger.info("***** Testing Delete By Order Id *****");
        Response response = StoreEndPoints.deleteStoreOrder(this.storePayload.getId());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("***** Delete By Order Id Completed *****");
    }
}
