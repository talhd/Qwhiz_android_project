package com.example.Qwhiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ScoresAdapter extends RecyclerView.Adapter<ScoresAdapter.ScoreViewHolder>
{
    private ArrayList<Score> scores;

    public ScoresAdapter(ArrayList< Score > scores, Context context) {
        this.scores = scores;
    }

    @NonNull
    @NotNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item,parent,false);
        ScoreViewHolder missionViewHolder = new ScoreViewHolder(view);
        return missionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ScoresAdapter.ScoreViewHolder holder, int position) {
        Score score = scores.get(position);
        holder.categoryTextView.setText(score.getCategory()+"");
        holder.scoreTextView.setText(score.getScore()+"");
        holder.timeTextView.setText(score.getTime()+"");
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    public class ScoreViewHolder extends RecyclerView.ViewHolder {
        TextView scoreTextView,timeTextView,categoryTextView;

        public ScoreViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            scoreTextView = itemView.findViewById(R.id.pointsTextView);
            timeTextView = itemView.findViewById(R.id.TimeTXTView);
            categoryTextView = itemView.findViewById(R.id.categoryId);


        }
    }
}