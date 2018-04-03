package find.eze.ezefind.ModelClasses;

/**
 * Created by dharamveer on 20/11/17.
 */

public class RecyclerDataExisting {


    public int imageId;
    public String uppertext;
    public String date;
    public String number;


    public RecyclerDataExisting(String uppertext, String date, String number) {
        this.uppertext = uppertext;
        this.date = date;
        this.number = number;
    }


    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
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
