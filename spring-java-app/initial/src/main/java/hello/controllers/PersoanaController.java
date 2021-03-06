
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

@org.springframework.web.bind.annotation.CrossOrigin
@RestController
public class PersoanaController {
  private List<Persoana> persoane = new ArrayList<Persoana>();

  PersoanaController() {
    Persoana p1 = new Persoana(1, "John");
    Persoana p2 = new Persoana(2, "Paul");
    Persoana p3 = new Persoana(3, "George");

    persoane.add(p1);
    persoane.add(p2);
    persoane.add(p3);
  }
	
  @RequestMapping(value="/persoana", method = RequestMethod.GET)
  public List<Persoana> index() {
    return this.persoane;
  }

  @RequestMapping(value="/persoana/{id}", method = RequestMethod.GET)
  public ResponseEntity show(@PathVariable("id") int id) {
    for(Persoana p : this.persoane) {
      if(p.getId() == id) {
        return new ResponseEntity<Persoana>(p, new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value="/persoana/delete/{id}", method = RequestMethod.DELETE)
  public ResponseEntity remove(@PathVariable("id") int id) {
    for(Persoana p : this.persoane) {
      if(p.getId() == id) {
        this.persoane.remove(p);
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value="/persoana/add", method = RequestMethod.POST)
  public ResponseEntity add(@RequestBody Person p1) {
    this.persoane.add(p1);
    return new ResponseEntity<List<Persoana>>(this.persoane, new HttpHeaders(), HttpStatus.OK);
  }
@CrossOrigin
  @RequestMapping(value="/persoana/update", method = RequestMethod.PUT)
  public ResponseEntity update(@RequestBody Persoana p1) {
    for (Persoana p : this.persoane) {
      if (p.getId() == p1.getId()) {
        p.setName(p1.getName());
      }
    }
    return new ResponseEntity<List<Persoana>>(this.persoane, new HttpHeaders(), HttpStatus.OK);
  }

 

