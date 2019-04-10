package app.shmehdi.calllogspoc.component.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.CallLog;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CallLogUtils {

    private static Date getCurrentFullDate(){
        return new Date(System.currentTimeMillis());
    }

    public static String calculateTiming(Date eventDate){
        String TAG = "SyedHussainMehdi";
        Date currentDate = getCurrentFullDate();

        Log.d(TAG, "Event Time : " + eventDate);
        Log.d(TAG, "Current Time : " + currentDate);

        String ago = "";


        if(eventDate.getMonth() == currentDate.getMonth()){

            if(eventDate.getDate() == currentDate.getDate()){

                if(eventDate.getHours() == currentDate.getHours()){

                    if(eventDate.getMinutes() == currentDate.getMinutes()){

                        Log.d(TAG, currentDate.getSeconds()-eventDate.getSeconds() + "seconds ago");
                        ago = currentDate.getSeconds()-eventDate.getSeconds() + " sec ago";
                    }
                    else {
                        ago = currentDate.getMinutes()-eventDate.getMinutes() + " min ago";
                        Log.d(TAG, currentDate.getMinutes()-eventDate.getMinutes() + "minutes ago");
                    }
                }
                else {
                    ago = currentDate.getHours()-eventDate.getHours() + " hr ago ";
                    Log.d(TAG, currentDate.getHours()-eventDate.getHours() + "Hours ago ");
                }

            }
            else{
                int d = currentDate.getDate() - eventDate.getDate();

                Log.d(TAG,  d + " Days ago");
                ago = d + " days ago";

                if(d > 6){
                    ago = new SimpleDateFormat("dd MMM ", Locale.getDefault()).format(eventDate);
                }
                if(d == 1){
                    Log.d(TAG, "Yesterday");
                    ago = "Yesterday";
                }
            }
        }else {
            int m = currentDate.getMonth() - eventDate.getMonth();
            ago = new SimpleDateFormat("dd MMM ", Locale.getDefault()).format(eventDate);
            Log.d(TAG, m+ " Months ago");
        }

        return ago;
    }

    public static String formatDuration(String  duration){
        int sec = Integer.parseInt(duration);

        int min = 0;
        int hr = 0;


        if(sec > 59) {
            min = sec / 60;
            sec = sec%60;
            //System.out.print(min + "Min " + sec + " Sec" );
            return min + "m " + sec + "s";
        }
        if(sec > 59) {
            hr = sec / min;
            //System.out.print(hr + "H "  + min + "Min " + sec + " Sec" );
            return hr + "h "  + min + "m " + sec + "s";
        }

        return duration + "s";
    }

    public static String  getCallTypeName(int type){
        switch (type){
            case CallLog.Calls.OUTGOING_TYPE:
                return "Outgoing Call";
            case CallLog.Calls.INCOMING_TYPE:
                return "Incoming Call";
            case CallLog.Calls.MISSED_TYPE:
                return "Missed Call";
            case CallLog.Calls.REJECTED_TYPE:
                return "Rejected";
            case CallLog.Calls.BLOCKED_TYPE:
                return "Blocked";
        }

        return "";
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    public static String getSimNameBySimID(Context context, String simID){
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return "";
        }
        SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);

        if(subscriptionManager == null){
            return  "";
        }

        for(SubscriptionInfo subscriptionInfo : subscriptionManager.getActiveSubscriptionInfoList()){
            if(subscriptionInfo.getIccId().equals(simID)){
                return subscriptionInfo.getDisplayName().toString();
            }
        }

        return "";
    }

    public static long getCurrentDate(){
        Calendar currentDateCal=Calendar.getInstance();
        return currentDateCal.getTimeInMillis();
    }

    public static long getLastDateFromToday(int lastdays){
        Date currentDate1=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate1);//setting time in calender
        calendar.add(Calendar.DAY_OF_MONTH, -lastdays);
        return calendar.getTimeInMillis();
    }
}
