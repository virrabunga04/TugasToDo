package com.example.tugastodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AktivitasAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Aktivitas> aktivitass;
    private final Context context;

    public AktivitasAdapter(Context context, List<Aktivitas> aktivitass){
        this.context = context;
        this.aktivitass = aktivitass;
    }

    public class VH extends RecyclerView.ViewHolder {
        private final TextView tvId;
        private final TextView tvWhat;
        private final TextView tvTime;

        public VH(@NonNull View itemView) {
            super(itemView);
            this.tvId = itemView.findViewById(R.id.tvId);
            this.tvWhat = itemView.findViewById(R.id.tvWhat);
            this.tvTime = itemView.findViewById(R.id.tvTime);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vh = LayoutInflater.from(this.context)
                .inflate(R.layout.row_aktivitas, parent, false);
        return new VH(vh);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Aktivitas v = this.aktivitass.get(position);
        VH vh = (VH) holder;
        vh.tvId.setText(v.getId());
        vh.tvWhat.setText(v.getWhat());
        vh.tvTime.setText(v.getTime());
    }

    @Override
    public int getItemCount() {
        return this.aktivitass.size();
    }
}
