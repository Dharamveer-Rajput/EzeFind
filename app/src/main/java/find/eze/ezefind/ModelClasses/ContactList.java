package find.eze.ezefind.ModelClasses;

/**
 * Created by dharamveer on 21/11/17.
 */

public class ContactList {


    public String imageId;
    public String tvHeaderAlphabets;
    public int tvHeaderCoun;
    public String tvName;
    public String tvContact;


    public int getTvHeaderCoun() {
        return tvHeaderCoun;
    }

    public void setTvHeaderCoun(int tvHeaderCoun) {
        this.tvHeaderCoun = tvHeaderCoun;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getTvHeaderAlphabets() {
        return tvHeaderAlphabets;
    }

    public void setTvHeaderAlphabets(String tvHeaderAlphabets) {
        this.tvHeaderAlphabets = tvHeaderAlphabets;
    }



    public String getTvName() {
        return tvName;
    }

    public void setTvName(String tvName) {
        this.tvName = tvName;
    }

    public String getTvContact() {
        return tvContact;
    }

    public void setTvContact(String tvContact) {
        this.tvContact = tvContact;
    }
}
