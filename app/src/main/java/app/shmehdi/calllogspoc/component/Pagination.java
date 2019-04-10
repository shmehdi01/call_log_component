package app.shmehdi.calllogspoc.component;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class Pagination extends RecyclerView.OnScrollListener {

    private int firstVisibleItem, visibleItemCount, totalItemCount;
    private LinearLayoutManager linearLayoutManager;
    private int laodDays = 0;
    public boolean isLoading = false;
    public int nextLoadDaysCount = 10;
    private PaginationListener paginationListener;

    public Pagination(@NonNull LinearLayoutManager linearLayoutManager, @NonNull PaginationListener paginationListener) {
        this.linearLayoutManager = linearLayoutManager;
        this.paginationListener = paginationListener;
    }


    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = linearLayoutManager.getItemCount();
        firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();


        if (isLoading) {
            if (firstVisibleItem + visibleItemCount == totalItemCount) {
                laodDays = laodDays + nextLoadDaysCount;
                isLoading = false;
                paginationListener.perfomPagination(laodDays);

            }

        }

    }

    public interface PaginationListener{
        void perfomPagination(int loadDays);
    }
}
