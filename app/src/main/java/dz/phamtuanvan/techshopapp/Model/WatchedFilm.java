package dz.phamtuanvan.techshopapp.Model;

public class WatchedFilm {
    private String name;
    private String image;
    private String nameEp;
    private String id;

    public WatchedFilm() {
    }

    public WatchedFilm(String name, String image, String nameEp) {
        this.name = name;
        this.image = image;
        this.nameEp = nameEp;
    }

    public WatchedFilm(String name, String image, String nameEp, String id) {
        this.name = name;
        this.image = image;
        this.nameEp = nameEp;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNameEp() {
        return nameEp;
    }

    public void setNameEp(String nameEp) {
        this.nameEp = nameEp;
    }
}
