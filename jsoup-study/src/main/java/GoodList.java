public class GoodList {
    private String title;
    private String  img;
    private String price;

    public GoodList(String title, String img, String price) {
        this.title = title;
        this.img = img;
        this.price = price;
    }

    @Override
    public String toString() {
        return "GoodList{" +
                "title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}