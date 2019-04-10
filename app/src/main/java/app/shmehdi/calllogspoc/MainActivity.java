package app.shmehdi.calllogspoc;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.shmehdi.calllogspoc.component.Pagination;
import app.shmehdi.calllogspoc.component.CallLogComponent;
import app.shmehdi.calllogspoc.component.CallLogPresenter;
import app.shmehdi.calllogspoc.component.models.CallLogRecentModel;
import app.shmehdi.calllogspoc.component.utils.CallLogUtils;
import app.shmehdi.calllogspoc.component.CallLogsView;

public class MainActivity extends AppCompatActivity implements CallLogsView, Pagination.PaginationListener {

    private CallLogAdapter adapterTest;
    private RecyclerView recyclerView;
    private CallLogPresenter callLogPresenter;
    private Pagination pagination;
    private List<CallLogRecentModel> callLogList = new ArrayList<>();
    //private List<CallLogModel> callLogList = new ArrayList<>();
    private long fromDate;
    private long toDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        pagination = new Pagination(linearLayoutManager,this);
        recyclerView.setOnScrollListener(pagination);

        fromDate = CallLogUtils.getLastDateFromToday(10);
        toDate = CallLogUtils.getCurrentDate();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 11);

        }


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, 1);

        } else {
            callLogPresenter = new CallLogComponent(this, this);
            callLogPresenter.loadCallLogs(fromDate,toDate);
            //callLogPresenter.test(fromDate,toDate);
        }

    }


    @Override
    public void onCallLogLoaded(List<CallLogRecentModel> callLogList) {

        pagination.isLoading = true;
        if(callLogList == null){
            return;
        }

        this.callLogList.addAll(callLogList);
        if (adapterTest == null) {
            adapterTest = new CallLogAdapter(this, this.callLogList,R.layout.row_call_log);
            recyclerView.setAdapter(adapterTest);
        } else {
            adapterTest.notifyDataSetChanged();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1) {
            if (callLogPresenter == null) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                callLogPresenter = new CallLogComponent(this, this);
            }
            callLogPresenter.loadCallLogs(fromDate,toDate);
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void perfomPagination(int loadDays) {
        callLogPresenter.paginate(loadDays);
    }
}
