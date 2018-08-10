package br.com.atlas.desafiokanamobi.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import br.com.atlas.desafiokanamobi.R;
import br.com.atlas.desafiokanamobi.adapter.PullAdapter;
import br.com.atlas.desafiokanamobi.model.Pull;

public class PullActivity extends AppCompatActivity {

    private List<Pull> pullList;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private Pull pullSel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);
        recyclerView = this.findViewById(R.id.activity_pull_recyclerView_pulls);
        Bundle extra = getIntent().getExtras();
        pullList = (List<Pull>) extra.get("Pulls");
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new PullAdapter(getApplicationContext(), pullList , pullOnClickListner()));

    }

    public PullAdapter.PullOnClickListner pullOnClickListner(){
        return new PullAdapter.PullOnClickListner() {
            @Override
            public void onClicPull(View view, int idx) {
                pullSel = pullList.get(idx);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(pullSel.getUrl()));
                startActivity(intent);
            }
        };
    }

}
