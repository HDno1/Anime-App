package dz.phamtuanvan.techshopapp.Model;

public class PushFavoriteFilm {
    private String name;
    private String imageCover;
    private String imgSmall;

    public PushFavoriteFilm() {
    }

    public PushFavoriteFilm(String name, String imageCover, String imgSmall) {
        this.name = name;
        this.imageCover = imageCover;
        this.imgSmall = imgSmall;
    }

    public String getImageCover() {
        return imageCover;
    }

    public void setImageCover(String imageCover) {
        this.imageCover = imageCover;
    }

    public String getImgSmall() {
        return imgSmall;
    }

    public void setImgSmall(String imgSmall) {
        this.imgSmall = imgSmall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
