package hello.models;

import java.util.List;
import java.util.ArrayList;

public class Produces {
    private int id;
	private String name;
    private int quantity;

    public Produces() {
    }

    public Produces(int id, String name, int quantity) {
        this.id = id;
		this.name = name;
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

