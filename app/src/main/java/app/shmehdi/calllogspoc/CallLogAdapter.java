package app.shmehdi.calllogspoc;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

import app.shmehdi.calllogspoc.component.adapters.AdapterViewHolder;
import app.shmehdi.calllogspoc.component.adapters.BaseAdapterCallLog;
import app.shmehdi.calllogspoc.component.models.CallLogModel;
import app.shmehdi.calllogspoc.component.models.CallLogRecentModel;
import app.shmehdi.calllogspoc.component.utils.CallLogUtils;

public class CallLogAdapter extends BaseAdapterCallLog {


    public CallLogAdapter(Context context, List<CallLogRecentModel> callLogModels, int itemViewLayout) {
        super(context, callLogModels, itemViewLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int i) {
        CallLogRecentModel recentModel = callLogModels.get(i);
        CallLogModel callLogModel = recentModel.getCallLogModel();


        if(callLogModel.getName() != null){
            holder.name.setText(callLogModel.getName());
        }
        else{
            holder.name.setText(callLogModel.getNumber());
        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
//            holder.simid.setText(CallLogUtils.getSimNameBySimID(context,callLogModel.getSimName()));
//        }
        holder.duration.setText(CallLogUtils.formatDuration(callLogModel.getDuration()));
        holder.time.setText(CallLogUtils.calculateTiming(new Date(Long.valueOf(callLogModel.getDate()))));
        holder.type.setText(CallLogUtils.getCallTypeName(callLogModel.getCallType()));


    }


}
