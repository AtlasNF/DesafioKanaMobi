package br.com.atlas.desafiokanamobi.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.atlas.desafiokanamobi.R;
import br.com.atlas.desafiokanamobi.adapter.EndlessRecyclerViewScrollListener;
import br.com.atlas.desafiokanamobi.adapter.RepositoresAdapter;
import br.com.atlas.desafiokanamobi.model.Item;
import br.com.atlas.desafiokanamobi.webservice.RetrofitCall;


public class MainActivity extends AppCompatActivity {

    private List<Item> itens;
    private RecyclerView repositorios;
    private LinearLayoutManager linearLayoutManager;
    private RetrofitCall retrofitCall;
    private EndlessRecyclerViewScrollListener scrollListener;
    private RepositoresAdapter adapter;
    private Item itemSel;
    private Activity activity;
    private int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofitCall = new RetrofitCall();
        activity = this;
        itens = new ArrayList<>();
        repositorios = this.findViewById(R.id.activity_main_recyclerView_repositores);
        linearLayoutManager = new LinearLayoutManager(this);
        repositorios.setLayoutManager(linearLayoutManager);
        repositorios.setItemAnimator(new DefaultItemAnimator());
        repositorios.setHasFixedSize(true);
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                RepositoresAsyncTask repositoresAsyncTask = new RepositoresAsyncTask();
                repositoresAsyncTask.execute(page);
                size = adapter.getItemCount();
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyItemRangeInserted(size, itens.size() - 1);
                    }
                });

            }
        };
        repositorios.addOnScrollListener(scrollListener);
        RepositoresAsyncTask repositoresAsyncTask = new RepositoresAsyncTask();
        repositoresAsyncTask.execute(1);

    }


    public class RepositoresAsyncTask extends AsyncTask<Integer, Integer, List<Item>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected List<Item> doInBackground(Integer... integers) {
            return retrofitCall.callRetrofitRepositoresOnMore(integers[0], getApplicationContext());
        }

        @Override
        protected void onPostExecute(List<Item> items) {
            super.onPostExecute(items);
            if (items == null || items.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Fim", Toast.LENGTH_LONG);
            } else {
                itens.addAll(items);
                if (adapter == null) {
                    adapter = new RepositoresAdapter(getApplicationContext(), itens, onClickListener());
                    repositorios.setAdapter(adapter);
                }
            }


        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private RepositoresAdapter.RepositoresOnClickListener onClickListener() {
        return new RepositoresAdapter.RepositoresOnClickListener() {
            @Override
            public void onClickRepositorio(View view, int idx) {
                itemSel = itens.get(idx);
                retrofitCall.callRetrofitListPull(itemSel.getOwner().getLogin(), itemSel.getNome(), activity);
            }
        };
    }


}



