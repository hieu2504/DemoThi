package vn.lucifer.demothi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public  interface RetrofitService {
    @GET("api/unknown/")
    Call<Example> getData(@Query("page") int page);
}
