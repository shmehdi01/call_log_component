package app.shmehdi.calllogspoc.component.models;

import java.util.List;

public class CallLogRecentModel {

    private CallLogModel callLogModel;
    private List<CallLogModel> history;


    public CallLogModel getCallLogModel() {
        return callLogModel;
    }

    public void setCallLogModel(CallLogModel callLogModel) {
        this.callLogModel = callLogModel;
    }

    public List<CallLogModel> getHistory() {
        return history;
    }

    public void setHistory(List<CallLogModel> history) {
        this.history = history;
    }
}
