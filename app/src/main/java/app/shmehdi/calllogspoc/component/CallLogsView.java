package app.shmehdi.calllogspoc.component;

import java.util.List;

import app.shmehdi.calllogspoc.component.models.CallLogRecentModel;

public interface CallLogsView {

    void onCallLogLoaded(List<CallLogRecentModel> callLogList);
}
