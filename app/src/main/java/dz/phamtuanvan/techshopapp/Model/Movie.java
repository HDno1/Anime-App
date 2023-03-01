package dz.phamtuanvan.techshopapp.Model;

public class Movie {
    String image;
    String id;
    public Movie() {
    }

    public Movie(String image) {
        this.image = image;
    }

    public Movie(String image, String id) {
        this.image = image;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
