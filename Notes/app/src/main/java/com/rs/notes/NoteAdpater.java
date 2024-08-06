package com.rs.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdpater extends RecyclerView.Adapter<NoteAdpater.NoteViewHolder> {
    private List<Note> notes = new ArrayList<>();

    class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;
        private TextView priority;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_txtVw);
            description = itemView.findViewById(R.id.desc_txtVw);
            priority = itemView.findViewById(R.id.priority_txtVw);
        }
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.note_item, parent, false);

        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        Note currentNote = notes.get(position);

        holder.title.setText(currentNote.getTitle());
        holder.description.setText(currentNote.getDescription());
        holder.priority.setText(String.valueOf(currentNote.getPriority()));
    }


    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }
}
