package dz.phamtuanvan.techshopapp.Model;

public class EspoideList {
    String image;
    String nameEspoide;

    public EspoideList() {
    }

    public EspoideList(String image, String nameEspoide) {
        this.image = image;
        this.nameEspoide = nameEspoide;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNameEspoide() {
        return nameEspoide;
    }

    public void setNameEspoide(String nameEspoide) {
        this.nameEspoide = nameEspoide;
    }
}
