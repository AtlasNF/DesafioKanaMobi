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
import br.com.atlas.desafiokanamobi.model.Item;

public class RepositoresAdapter extends RecyclerView.Adapter<RepositoresAdapter.RepositoresViewHolder> {

    private final Context context;
    private List<Item> items;
    private RepositoresOnClickListener repositoresOnClickListener;

    public RepositoresAdapter(Context context, List<Item> items , RepositoresOnClickListener onClickListener) {
        this.repositoresOnClickListener = onClickListener;
        this.context = context;
        this.items = items;
    }


    @NonNull
    @Override
    public RepositoresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_repositores, parent, false);
        RepositoresViewHolder holder = new RepositoresViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RepositoresViewHolder holder, final int position) {
        final Item item = items.get(position);
        holder.nameRepositorio.setText(item.getNome());
        holder.stars.setText(item.getStargazers_count().toString());
        holder.forks.setText(item.getForks_count().toString());
        holder.descriscao.setText(item.getDescription());
        holder.username.setText(item.getOwner().getLogin());
        Picasso.get().load(item.getOwner().getAvatar_url()).into(holder.profileImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repositoresOnClickListener.onClickRepositorio(holder.itemView, position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return this.items != null ? this.items.size() : 0;
    }

    public class RepositoresViewHolder extends RecyclerView.ViewHolder {

        private TextView username;
        private TextView descriscao;
        private TextView forks;
        private TextView stars;
        private TextView nameRepositorio;
        private ImageView profileImage;

        public RepositoresViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.adapter_repositores_txt_username);
            descriscao = itemView.findViewById(R.id.adapter_repositores_txt_descricao);
            forks = itemView.findViewById(R.id.adapter_repositores_txt_forks);
            stars = itemView.findViewById(R.id.adapter_repositores_txt_stars);
            nameRepositorio = itemView.findViewById(R.id.adapter_repositores_txt_name_repositores);
            profileImage = itemView.findViewById(R.id.adapter_repositores_profileImage);
        }

    }

    public interface RepositoresOnClickListener {
        public void onClickRepositorio(View view, int idx);

    }

}
