package com.example.dictionary.Api;

import com.example.dictionary.Models.APIResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CallDictionary {
    @GET("entries/en/{word}")
    Call<List<APIResponse>> callMeanings(
            @Path("word") String word
    );
}
