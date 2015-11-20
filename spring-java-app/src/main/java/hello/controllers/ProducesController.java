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
public class ProducesController {
  private List<Produces> produse = new ArrayList<Produces>();

  ProducesController() {
    Produces prod1 = new Produces(1, "Guitar",20);
    Produces prod2 = new Produces(2, "Bread",60);
    Produces prod3 = new Produces(3, "SparklingWater",100);

    produse.add(prod1);
    produse.add(prod2);
    produse.add(prod3);
  }

  @RequestMapping(value="/produces", method = RequestMethod.GET)
  public List<Produces> index() {
    return this.produse;
  }

  @RequestMapping(value="/produces/{id}", method = RequestMethod.GET)
  public ResponseEntity show(@PathVariable("id") int id) {
    for(Produces p : this.produse) {
      if(p.getId() == id) {
        return new ResponseEntity<Produces>(p, new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value="/produces/{id}", method = RequestMethod.DELETE)
  public ResponseEntity remove(@PathVariable("id") int id) {
    for(Produces p : this.produse) {
      if(p.getId() == id) {
        this.produse.remove(p);
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value="/produces/{id}/{name}/{quantity}", method = RequestMethod.POST)
  public ResponseEntity add(@PathVariable("id") int id, @PathVariable("name") String name, @PathVariable("quantity") int quantity) {
    Produces p = new Produces(id,name,quantity);
        this.produse.add(p);
        return new ResponseEntity<List<Produces>>(this.produse, new HttpHeaders(), HttpStatus.OK);
  }

  @RequestMapping(value="/produces/{id}/{name}/{quantity}", method = RequestMethod.PUT)
  public ResponseEntity update(@PathVariable("id") int id, @PathVariable("name") String name, @PathVariable("quantity") int quantity) {
    for (Produces p : this.produse) {
      if (p.getId() == id) {
        p.setName(name);
        p.setQuantity(quantity);
      }
    }
    return new ResponseEntity<List<Produces>>(this.produse, new HttpHeaders(), HttpStatus.OK);
  }
}

