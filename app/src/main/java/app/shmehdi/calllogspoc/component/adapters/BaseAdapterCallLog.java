package app.shmehdi.calllogspoc.component.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import app.shmehdi.calllogspoc.component.models.CallLogRecentModel;

public class BaseAdapterCallLog extends RecyclerView.Adapter<AdapterViewHolder> {

    protected Context context;
    protected List<CallLogRecentModel> callLogModels;
    private int itemViewLayout;
    protected CallLogAction callLogAction;


    public BaseAdapterCallLog(Context context, List<CallLogRecentModel> callLogModels, int itemViewLayout) {
        this.context = context;
        this.callLogModels = callLogModels;
        this.itemViewLayout = itemViewLayout;

        callLogAction = new CallLogActionBuilder();
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AdapterViewHolder(LayoutInflater.from(context).inflate(itemViewLayout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int i) {


    }

    @Override
    public int getItemCount() {
        return callLogModels.size();
    }


}

