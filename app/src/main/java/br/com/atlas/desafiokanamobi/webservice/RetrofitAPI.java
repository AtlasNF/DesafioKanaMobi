package br.com.atlas.desafiokanamobi.webservice;

import java.util.List;

import br.com.atlas.desafiokanamobi.model.Pull;
import br.com.atlas.desafiokanamobi.model.Repositories;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {
    String base_Url = "https://api.github.com";

    @GET("/search/repositories?q=language:Java&sort=stars")
    Call<Repositories> callRepositores(@Query("page") int page);

    @GET("repos/{criador}/{repositorio}/pulls")
    Call<List<Pull>> callPulls(@Path("criador") String criador, @Path("repositorio") String repositorio);

}
