package app.shmehdi.calllogspoc.component.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import app.shmehdi.calllogspoc.R;

public class AdapterViewHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView simid;
    public TextView time;
    public TextView duration;
    public TextView type;

    public AdapterViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.name);
        simid = itemView.findViewById(R.id.simid);
        time = itemView.findViewById(R.id.time);
        duration = itemView.findViewById(R.id.duration);
        type = itemView.findViewById(R.id.type);
    }
}
