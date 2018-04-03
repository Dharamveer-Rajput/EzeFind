package find.eze.ezefind.ModelClasses;

/**
 * Created by dharamveer on 18/11/17.
 */

public class RecyclerData {


    public int imageId;
    public String uppertext;
    public String date;
    public String number;


    public RecyclerData(String uppertext, String date, String number) {
        this.uppertext = uppertext;
        this.date = date;
        this.number = number;
    }

    public String getUppertext() {
        return uppertext;
    }

    public void setUppertext(String uppertext) {
        this.uppertext = uppertext;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
