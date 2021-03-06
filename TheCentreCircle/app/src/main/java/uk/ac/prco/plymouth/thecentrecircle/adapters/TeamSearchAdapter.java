package uk.ac.prco.plymouth.thecentrecircle.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import uk.ac.prco.plymouth.thecentrecircle.R;
import uk.ac.prco.plymouth.thecentrecircle.classes.Team;

/**
 * Created by charliewaite on 06/05/2016.
 */
public class TeamSearchAdapter extends RecyclerView.Adapter<TeamSearchAdapter.ViewHolder> {

    ArrayList<Team> teams;

    private static ClickListener clickListener;


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public CardView view;
        public ViewHolder(CardView v) {
            super(v);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
            view = v;
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }

    public TeamSearchAdapter(ArrayList<Team> teams) {
        this.teams = teams;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()) .inflate(R.layout.team_search_result, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView textView = (TextView) holder.view.findViewById(R.id.team_search_name);
        textView.setText(teams.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        TeamSearchAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }


}


