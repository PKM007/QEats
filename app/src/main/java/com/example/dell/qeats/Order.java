package com.example.dell.qeats;

public class Order {
    private String restaurantName;
    private String location;
    private String items;
    private String date1;
    private String total;
    private String status;

    public String getImageURL() {
        return imageURL;
    }

    private String imageURL;

    public Order(String restaurantName, String location, String items, String date1, String total, String status,String imageURL) {
        this.restaurantName = restaurantName;
        this.location = location;
        this.items = items;
        this.date1 = date1;
        this.total = total;
        this.status = status;
        this.imageURL=imageURL;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getLocation() {
        return location;
    }

    public String getItems() {
        return items;
    }

    public String getDate() {
        return date1;
    }

    public String getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }


}
