package com.foodona.foodona.Restarant.rest;

import com.foodona.foodona.Restarant.Response.AddressResponse;
import com.foodona.foodona.Restarant.Response.LoginResponse;
import com.foodona.foodona.Restarant.Response.OrderDetailsResponse;
import com.foodona.foodona.Restarant.Response.OrderHistoryResponse;
import com.foodona.foodona.Restarant.Response.UserAddAddress;
import com.foodona.foodona.Restarant.Response.UserDeleteResponse;
import com.foodona.foodona.Restarant.Response.UserEditAddressResponse;
import com.foodona.foodona.Restarant.Response.UserProfileResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    // @POST("userlogin.php?")
    @POST("userlogin.php?")
    Call<ResponseBody> Login(@Body RequestBody requestBody);

    @POST("userlogin.php?")
    Call<LoginResponse> Loginn(@Body RequestBody requestBody);

    @POST("order_details.php?")
    Call<OrderDetailsResponse> OrderDetails(@Body RequestBody requestBody);

    @POST("userregister.php?")
    Call<ResponseBody> Sign_up(@Body RequestBody requestBody);


    @POST("restaurantlist.php?")
    Call<ResponseBody> Restaurent_List(@Body RequestBody requestBody);

    @POST("restaurant_menu.php?")
    Call<ResponseBody> Restaurent_Menu(@Body RequestBody requestBody);

    @POST("verify_otp")
    Call<ResponseBody> Verify_otp(@Body RequestBody requestBody);

    @POST("forget")
    Call<ResponseBody> Forget_Pass(@Body RequestBody requestBody);

    @POST("restaurant_fav.php?")
    Call<ResponseBody> Restaurant_Fav(@Body RequestBody requestBody);

    @POST("restaurant_addfav.php?")
    Call<ResponseBody> RestaurantADD_Fav(@Body RequestBody requestBody);


    @POST("restaurant_addcart.php?")
    Call<ResponseBody> Restaurant_add_Cart(@Body RequestBody requestBody);

    @POST("restaurant_deletecart.php?")
    Call<ResponseBody> Restaurant_Remove_Cart(@Body RequestBody requestBody);

    @POST("restaurant_getcart.php?")
    Call<ResponseBody> Restaurent_Get_Cart(@Body RequestBody requestBody);

    @POST("bookorder.php?")
    Call<ResponseBody> Book_ORder(@Body RequestBody requestBody);

    @POST("user_getaddress.php?")
    Call<AddressResponse> userGetAddress(@Body RequestBody requestBody);

    @POST("user_addaddress.php?")
    Call<UserAddAddress> userAddAddress(@Body RequestBody requestBody);

    @POST("user_allorder.php?")
    Call<OrderHistoryResponse> UserAllorder(@Body RequestBody requestBody);

    @POST("user_deleteaddress.php?")
    Call<UserDeleteResponse> UserDeleteAddress(@Body RequestBody requestBody);

    @POST("user_editaddress.php")
    Call<UserEditAddressResponse> UserEditAddress(@Body RequestBody requestBody);

    @POST("user_getprofile.php?")
    Call<UserProfileResponse> UserGetProfile(@Body RequestBody requestBody);

    @Multipart
    @POST("user_updateprofile.php")
    Call<ResponseBody> EditMenu(@Part("user_id") RequestBody user_id, @Part("user_name") RequestBody user_name,
                                @Part("user_email") RequestBody user_email, @Part("user_bio") RequestBody user_bio,
                                @Part("user_address") RequestBody user_address, @Part("image") RequestBody image,
                                @Part MultipartBody.Part file);


}
