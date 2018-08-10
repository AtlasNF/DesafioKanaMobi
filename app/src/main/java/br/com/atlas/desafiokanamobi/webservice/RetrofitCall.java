package br.com.atlas.desafiokanamobi.webservice;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.atlas.desafiokanamobi.R;
import br.com.atlas.desafiokanamobi.activity.PullActivity;
import br.com.atlas.desafiokanamobi.adapter.RepositoresAdapter;
import br.com.atlas.desafiokanamobi.model.Item;
import br.com.atlas.desafiokanamobi.model.Pull;
import br.com.atlas.desafiokanamobi.model.Repositories;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCall {

    private List<Item> itens;
    private List<Pull> pulls;

    public void callRetrofitListPull(String criador, String repositorio, final Activity activity){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitAPI.base_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);


        Call<List<Pull>> call = retrofitAPI.callPulls(criador, repositorio);

       call.enqueue(new Callback<List<Pull>>() {
           @Override
           public void onResponse(Call<List<Pull>> call, Response<List<Pull>> response) {
               int resposta = response.code();
               if (resposta == 404) {
                   Toast.makeText(activity.getApplicationContext(), R.string.Erro404, Toast.LENGTH_LONG).show();
               }
               if (resposta == 500) {
                   Toast.makeText(activity.getApplicationContext(), R.string.Erro500, Toast.LENGTH_LONG).show();
               }
               if (resposta == 502) {
                   Toast.makeText(activity.getApplicationContext(), R.string.Erro502, Toast.LENGTH_LONG).show();
               }
               if (resposta == 503) {
                   Toast.makeText(activity.getApplicationContext(), R.string.Erro503, Toast.LENGTH_LONG).show();
               }
               if (resposta == 504) {
                   Toast.makeText(activity.getApplicationContext(), R.string.Erro504, Toast.LENGTH_LONG).show();
               }
               if (resposta == 200) {
                   Intent intent = new Intent(activity, PullActivity.class);
                   intent.putExtra("Pulls", (Serializable) response.body());
                   activity.startActivity(intent);


               }
           }

           @Override
           public void onFailure(Call<List<Pull>> call, Throwable t) {

           }
       });
    }



    public List<Item> callRetrofitRepositoresOnMore(Integer page, final Context context) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitAPI.base_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<Repositories> call = retrofitAPI.callRepositores(page);
        try {

            itens = call.execute().body().getItens();
        } catch (IOException e) {
            e.printStackTrace();

        }catch (NullPointerException e){
            return itens;
        }
        return itens;
    }
}
