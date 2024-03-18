package com.brainetrainer.taskmanager.adapters;

import static com.brainetrainer.taskmanager.db.Constants.BOX_WITH_ITEM_INTENT;
import static com.brainetrainer.taskmanager.db.Constants.STATE_EDIT_OR_NOT;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brainetrainer.taskmanager.R;
import com.brainetrainer.taskmanager.WriteNewTaskActivity;
import com.brainetrainer.taskmanager.db.MyDbManager;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    private Context context;
    private List<BoxWithTasks> boxWithTasks;

    public MainAdapter(Context context) {
        this.context = context;
        boxWithTasks = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View hereWeWriteView = LayoutInflater.from(context).inflate(R.layout.design_every_from_list_view, parent, false);
        return new MyViewHolder(hereWeWriteView, context, boxWithTasks);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setData(boxWithTasks.get(position).getNameTask());
    }

    @Override
    public int getItemCount() {
        return boxWithTasks.size();
    }


    // Here are MyViewGolder
    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtNameTask;
        private Context context;
        private List<BoxWithTasks> boxWithTasks;


        public MyViewHolder(@NonNull View itemView, Context context, List<BoxWithTasks> boxWithTasks) {
            super(itemView);
            this.context = context;
            this.boxWithTasks = boxWithTasks;
            txtNameTask = itemView.findViewById(R.id.txtTasks);
            itemView.setOnClickListener(this);
        }

        public void setData(String nameTask) {
            txtNameTask.setText(nameTask);
        }

        @Override
        public void onClick(View view) {
            Intent editTask = new Intent(context, WriteNewTaskActivity.class);
            editTask.putExtra(BOX_WITH_ITEM_INTENT, boxWithTasks.get(getAdapterPosition()));
            // We are want to edit our task? "false" - mean that we are don`t wanna do that
            editTask.putExtra(STATE_EDIT_OR_NOT, false);
            context.startActivity(editTask);
        }
    }

    public void updateAdapter(List<BoxWithTasks> listWithTasks) {
        boxWithTasks.clear();
        boxWithTasks.addAll(listWithTasks);
        notifyDataSetChanged();
    }

    public void removeAndUpList(int position, MyDbManager manager) {
        String nameTask = boxWithTasks.get(position).getNameTask();
        manager.deleteFromDb(boxWithTasks.get(position).getId());
        boxWithTasks.remove(position);
        notifyItemRangeChanged(0, boxWithTasks.size());
        notifyItemRemoved(position);
        Toast.makeText(context, context.getString(R.string.txt_delete_success_first) + " " + nameTask + " " + context.getString(R.string.txt_delete_success_second), Toast.LENGTH_SHORT).show();
    }
}