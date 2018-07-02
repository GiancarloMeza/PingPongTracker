package com.giancarlomeza.pingpongtracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

class NameInputsAdapter extends RecyclerView.Adapter<NameInputsAdapter.ViewHolder> {

    private final Context context;
    private final TextChangeListener listener;
    private final List<String> names;

    @SuppressWarnings("WeakerAccess")
    public NameInputsAdapter(Context context, TextChangeListener listener) {
        this.context = context;
        this.listener = listener;
        this.names = new ArrayList<>();
    }

    @NonNull
    @Override
    public NameInputsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View layout = LayoutInflater.from(context)
                .inflate(R.layout.name_input_cell, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull final NameInputsAdapter.ViewHolder viewHolder, int position) {
        viewHolder.inputName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listener.onTextChanged(charSequence, viewHolder.getAdapterPosition());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size() + 1;
    }

    public List<String> getNames() {
        return names;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final EditText inputName;

        ViewHolder(View layout) {
            super(layout);

            this.inputName = layout.findViewById(R.id.inputName);
        }
    }

    public interface TextChangeListener {
        void onTextChanged(CharSequence charSequence, int position);
    }
}
