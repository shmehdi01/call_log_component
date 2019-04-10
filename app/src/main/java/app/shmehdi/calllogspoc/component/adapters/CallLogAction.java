package app.shmehdi.calllogspoc.component.adapters;

import app.shmehdi.calllogspoc.component.models.CallLogRecentModel;

public interface CallLogAction {

    void onItemClick();
    void onCall(String phoneNumber);
    void onMessage(String phoneNumber);
    void onDetail(CallLogRecentModel callLogModel);

}
