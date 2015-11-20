
package hello.models;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

public class Bar {
  private int id;
  private String name;
  private String location;
  private int max_number_customers;
  private List<String> drinks;  

  public Bar() {
      this.drinks = new ArrayList<String>();
  }

  public Bar(int id, String name, String location, int max_number_customers, List<String> drinks) {
      this.name = name;
      this.id = id;
      this.location = location;
      this.max_number_customers = max_number_customers;
      this.drinks = new ArrayList<String>();
      for(String drink : drinks){
          this.drinks.add(drink);
      }
  }

  public String getName() {
      return this.name;
  }

  public int getId() {
    return this.id;
  }

  public String getLocation() {
      return this.location;
  }

  public int getMaxNumberCustomers() {
      return this.max_number_customers;
  }
  
  public List<String> getDrinks() {
      return this.drinks;
  }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location){
      this.location = location;
  }

    public void setMaxNumberCustomers(int max_number_customers) {
        this.max_number_customers = max_number_customers;
    }

    public void setDrinks(List<String> drinks) {
       int i=0;
       for(ListIterator<String> it = drinks.listIterator();it.hasNext();i++){
            this.drinks.set(i,it.next());
        }
       //Or we could use  --   Collections.copy(this.drinks,drinks);
    }
}




