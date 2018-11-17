package com.example.noarah.botPress.Service;
import com.example.noarah.botPress.Model.Messages;


import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface APIService {


    @GET("respond")
    Call<List<Messages>> getBotResponse(@Query("q") String message);





}


