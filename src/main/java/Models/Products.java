package Models;

public class Products {
    private String productName;
    private int Quatity;
    private  double Price;

    public Products(){}
    public String getProductName() {
        return productName;
    }
    public Products(String productName, int quatity, double price) {
        this.productName = productName;
        Quatity = quatity;
        Price = price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuatity() {
        return Quatity;
    }

    public void setQuatity(int quatity) {
        Quatity = quatity;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }


}
