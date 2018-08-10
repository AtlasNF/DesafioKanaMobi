package br.com.atlas.desafiokanamobi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.atlas.desafiokanamobi.R;
import br.com.atlas.desafiokanamobi.model.Pull;

public class PullAdapter extends RecyclerView.Adapter<PullAdapter.PullViewHolder> {

        private final Context context;
        private List<Pull> pulls;
        private PullOnClickListner pullOnClickListner;

    public PullAdapter (Context context, List<Pull> pulls , PullOnClickListner pullOnClickListner) {
            this.pullOnClickListner = pullOnClickListner;
            this.context = context;
            this.pulls = pulls;
        }



        @NonNull
        @Override
        public PullAdapter.PullViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.adapter_pulls, parent, false);
            PullAdapter.PullViewHolder holder = new PullAdapter.PullViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull final PullViewHolder holder, final int position) {
            final Pull pull = pulls.get(position);
            holder.title.setText(pull.getTitle());
            holder.dataCriacao.setText(pull.getCreated_at());
            holder.body.setText(pull.getBody());
            holder.username.setText(pull.getUser().getLogin());
            Picasso.get().load(pull.getUser().getAvatar_url()).into(holder.profileImage);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pullOnClickListner.onClicPull(holder.itemView, position);
                }
            });

        }

        @Override
        public int getItemCount() {
            return this.pulls != null ? this.pulls.size() : 0;
        }

        public class PullViewHolder extends RecyclerView.ViewHolder{

            private TextView username;
            private TextView body;
            private TextView dataCriacao;
            private TextView title;
            private ImageView profileImage;

            public PullViewHolder(View itemView) {
                super(itemView);
                username = itemView.findViewById(R.id.adapter_pull_txt_username);
                body = itemView.findViewById(R.id.adapter_pull_txt_body);
                dataCriacao = itemView.findViewById(R.id.adapter_pull_txt_data_criacao);
                title = itemView.findViewById(R.id.adapter_pull_txt_title);
                profileImage = itemView.findViewById(R.id.adapter_pull_profileImage);
            }
        }

        public interface  PullOnClickListner{
            public void onClicPull(View view, int idx);

        }
}
