package com.giancarlomeza.pingpongtracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

class GroupCardsAdapter extends RecyclerView.Adapter<GroupCardsAdapter.ViewHolder> {

        private final Context context;
        private final String[] names;

        @SuppressWarnings("WeakerAccess")
        public GroupCardsAdapter(Context context, String[] names) {
            this.context = context;
            this.names = names;
        }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.group_card, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
            final TextView textPlayerOneName;
            final TextView textPlayerTwoName;

        ViewHolder(View layout) {
            super(layout);

            this.textPlayerOneName = (TextView) layout.findViewById(R.id.textPlayerOneName);
            this.textPlayerTwoName = (TextView) layout.findViewById(R.id.textPlayerTwoName);




        }
    }

}
