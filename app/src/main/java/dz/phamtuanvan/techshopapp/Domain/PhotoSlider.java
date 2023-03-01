package dz.phamtuanvan.techshopapp.Domain;

public class PhotoSlider {
    String resource;
    String id;

    public PhotoSlider() {
    }

    public PhotoSlider(String resource, String id) {
        this.resource = resource;
        this.id = id;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
