package hello.controllers;


import hello.models.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.ArrayList;


@RestController
public class PetController {
    private List<Pet> pets = new ArrayList<Pet>();

    PetController() {
        Pet pet1 = new Pet(1, "Husky", "Dog", 5);
        Pet pet2 = new Pet(2, "Labrador", "Dog",4);
        Pet pet3 = new Pet(3, "Bulldog", "Dog",3);

        pets.add(pet1);
        pets.add(pet2);
        pets.add(pet3);
    }

    @RequestMapping(value="/pet", method = RequestMethod.GET)
    public List<Pet> index() {
        return this.pets;
    }

    @RequestMapping(value="/pet/{id}", method = RequestMethod.GET)
    public ResponseEntity show(@PathVariable("id") int id) {
        for(Pet p : this.pets) {
            if(p.getId() == id) {
                return new ResponseEntity<Pet>(p, new HttpHeaders(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/pet/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable("id") int id) {
        for(Pet p : this.pets) {
            if(p.getId() == id) {
                this.pets.remove(p);
                return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/pet/{id}/{name}/{species}/{age}", method = RequestMethod.POST)
    public ResponseEntity add(@PathVariable("id") int id, @PathVariable("name") String name, @PathVariable("species") String species, @PathVariable("age") int age) {
        Pet p = new Pet(id,name,species,age);
        this.pets.add(p);
        return new ResponseEntity<List<Pet>>(this.pets, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value="/pet/{id}/{name}/{species}/{age}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("id") int id, @PathVariable("name") String name, @PathVariable("species") String species, @PathVariable("age") int age) {
        for (Pet p : this.pets) {
            if (p.getId() == id) {
                p.setName(name);
                p.setSpecies(species);
                p.setAge(age);
            }
        }
        return new ResponseEntity<List<Pet>>(this.pets, new HttpHeaders(), HttpStatus.OK);
    }
}

