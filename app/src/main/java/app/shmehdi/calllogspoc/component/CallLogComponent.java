package app.shmehdi.calllogspoc.component;

import android.Manifest;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.annotation.RequiresPermission;

import java.util.ArrayList;
import java.util.List;

import app.shmehdi.calllogspoc.component.models.CallLogModel;
import app.shmehdi.calllogspoc.component.models.CallLogRecentModel;
import app.shmehdi.calllogspoc.component.utils.CallLogUtils;

public class CallLogComponent implements CallLogPresenter {

    private Context context;
    private CallLogsView callLogsView;
    private long lastLoadedDate;

    @RequiresPermission(value = Manifest.permission.READ_CALL_LOG)
    public CallLogComponent(Context context, CallLogsView callLogsView) {
        this.context = context;
        this.callLogsView = callLogsView;
    }

    @Override
    public void loadCallLogs(long fromDateMilis , long toDateMilis) {

        lastLoadedDate = fromDateMilis;

        List<CallLogModel> callLogModelList = queryForCallLogs(fromDateMilis,toDateMilis);
        List<CallLogRecentModel> list = getCallLogRecent(callLogModelList);
//        callLogsView.temp(list);
//
        if(callLogsView != null){
            callLogsView.onCallLogLoaded(list);
        }

    }

    @Override
    public void paginate(int loadDays) {
        loadCallLogs(CallLogUtils.getLastDateFromToday(loadDays),lastLoadedDate);
    }


    private List<CallLogModel> queryForCallLogs(long fromDateMilis, long toDateMilis) {
        Cursor managedCursor;
        managedCursor = context.getContentResolver().query(
            CallLog.Calls.CONTENT_URI,
            null,
            CallLog.Calls.DATE + " BETWEEN ? AND ?", new String[]{String.valueOf(fromDateMilis), String.valueOf(toDateMilis)},
            CallLog.Calls.DATE + " DESC");


        assert managedCursor != null;

        int numberIndex = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int nameIndex = managedCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int typeIndex = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int dateIndex = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int readIndex = managedCursor.getColumnIndex(CallLog.Calls.IS_READ);
        int durationIndex = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        int simIdIndex =managedCursor.getColumnIndex(CallLog.Calls.PHONE_ACCOUNT_ID);
        int imgIndex = managedCursor.getColumnIndex(CallLog.Calls.CACHED_PHOTO_URI);

        List<CallLogModel> callLogModelList = new ArrayList<>();

        while (managedCursor.moveToNext()) {

            String name = managedCursor.getString(nameIndex);
            String nummber = managedCursor.getString(numberIndex);
            String type = managedCursor.getString(typeIndex);
            String date = managedCursor.getString(dateIndex);
            int readOrNot = managedCursor.getInt(readIndex);
            String duration = managedCursor.getString(durationIndex);
            String simId = managedCursor.getString(simIdIndex);
            String imgUri =  managedCursor.getString(imgIndex);

            CallLogModel callLogModel = new CallLogModel();

            callLogModel.setName(name);
            callLogModel.setNumber(nummber);
            callLogModel.setCallType(type);
            callLogModel.setDate(date);
            callLogModel.setIsRead(readOrNot);
            callLogModel.setDuration(duration);
            callLogModel.setSimName(simId);
            callLogModel.setImgUri(imgUri);

            callLogModelList.add(callLogModel);

        }

        return callLogModelList;
    }

    private List<CallLogRecentModel> getCallLogRecent(List<CallLogModel> callLogs){

        if(callLogs.size() == 0){
            return null;
        }
        List<CallLogModel> historyCallLogs = new ArrayList<>();
        List<CallLogRecentModel> mainList = new ArrayList<>();


        CallLogModel logs = callLogs.get(0);

        CallLogRecentModel recentModel = new CallLogRecentModel();
        recentModel.setCallLogModel(logs);
        historyCallLogs.add(logs);
        recentModel.setHistory(historyCallLogs);

        mainList.add(recentModel);

        String prevNumber = logs.getNumber();

        for(int i=1;i<callLogs.size();i++){
            CallLogModel tempLogModel = callLogs.get(i);

            if(prevNumber.equals(tempLogModel.getNumber())){
                historyCallLogs.add(tempLogModel);
            }
            else{

                historyCallLogs = new ArrayList<>();
                CallLogRecentModel callLogRecentModel = new CallLogRecentModel();
                callLogRecentModel.setCallLogModel(tempLogModel);
                historyCallLogs.add(tempLogModel);
                callLogRecentModel.setHistory(historyCallLogs);
                mainList.add(callLogRecentModel);
            }

            prevNumber = tempLogModel.getNumber();
        }


        return mainList;
    }
}
