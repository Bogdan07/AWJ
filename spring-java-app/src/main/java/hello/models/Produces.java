package hello.models;

import java.util.List;
import java.util.ArrayList;

public class Produces {
    private String name;
    private int id;
    private int quantity;

    public Produces() {
    }

    public Produces(int id, String name, int quantity) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}

