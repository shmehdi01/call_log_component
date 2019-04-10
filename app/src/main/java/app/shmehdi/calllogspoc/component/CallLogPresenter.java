package app.shmehdi.calllogspoc.component;

public interface CallLogPresenter {

    void loadCallLogs(long fromDateMilis , long toDateMilis);
    void paginate(int loadDays);

}
