package dz.phamtuanvan.techshopapp.Model;

public class CastFilmModel {
    String image;
    String nameCast;

    public CastFilmModel() {
    }

    public CastFilmModel(String image, String nameCast) {
        this.image = image;
        this.nameCast = nameCast;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNameCast() {
        return nameCast;
    }

    public void setNameCast(String nameCast) {
        this.nameCast = nameCast;
    }
}
