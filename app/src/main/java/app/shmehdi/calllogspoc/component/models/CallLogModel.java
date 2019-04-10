package app.shmehdi.calllogspoc.component.models;

public class CallLogModel {

    private String name;
    private String number;
    private String date;
    private String duration;
    private int callType; //INCOMING ,OUTGOING, MISSED CALL, REJECTED, BLOCKED
    private int isRead;
    private String imgUri;
    private String simName;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = Integer.parseInt(callType);
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public String getSimName() {
        return simName;
    }

    public void setSimName(String simId) {
        this.simName = simId;
    }
}
