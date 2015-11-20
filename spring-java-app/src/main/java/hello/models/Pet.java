package hello.models;

import java.util.List;
import java.util.ArrayList;

public class Pet {
  private String name;
  private int id;
  private String species;
  private int age;  

  public Pet() {}

  public Pet(int id, String name, String species, int age) {
      this.name = name;
      this.id = id;
      this.species = species;
      this.age = age;
  }

  public String getName() {
      return this.name;
  }

  public int getId() {
    return this.id;
  }

  public String getSpecies() {
      return this.species;
  }

  public int getAge() {
      return this.age;
  }

  public void setName(String name){
      this.name = name;
  }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSpecies(String species){
        this.species = species;
    }
}


