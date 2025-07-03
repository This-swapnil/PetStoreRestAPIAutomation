package api.endpoints;

/*
Swagger URL -> https://petstore.swagger.io/
Create User(POST) -> https://petstore.swagger.io/v2/user
Get User (GET) -> https://petstore.swagger.io/v2/user/{username}
Update User(PUT) -> https://petstore.swagger.io/v2/user/{username}
Delete User(DELETE) -> https://petstore.swagger.io/v2/user/{username}
 */

public class Routes {
    public static String base_url = "https://petstore.swagger.io/v2/";

    //user module
    public static String post_url = base_url + "user";
    public static String post_url_array = post_url + "/createWithArray";
    public static String post_url_list = post_url + "/createWithList";
    public static String logout_url = post_url + "/logout";
    public static String login_url = post_url + "/login";
    public static String get_url = base_url + "user/{username}";
    public static String update_url = base_url + "user/{username}";
    public static String delete_url = base_url + "user/{username}";

    //Store
    public static String getStore_url = base_url + "store/inventory";
    public static String postStore_url = base_url + "store/order";
    public static String getStoreOrderId_url = base_url + "store/order/{orderId}";
    public static String deleteStoreOrderId_url = base_url + "store/order/{orderId}";

    //Pet
    public static String postUploadImage_url = base_url + "pet/{petId}/uploadImage";
    public static String postPet_url = base_url + "pet";
    public static String putPet_url = base_url + "pet";
    public static String getFindByStatus_url = base_url + "pet/findByStatus";
    public static String getPetId_url = base_url + "pet/{petId}";
    public static String deletePetId_url = base_url + "pet/{petId}";
}
