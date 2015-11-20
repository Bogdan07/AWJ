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
public class BarController {
    private List<Bar> bars = new ArrayList<Bar>();

    BarController() {
        List<String> drinks = new ArrayList<String>();
        drinks.add("Beer");
        drinks.add("Whiskey");
        drinks.add("Shots");
        drinks.add("Cocktails");
        drinks.add("Juices");

        Bar bar1 = new Bar(1, "Quantic", "Bucharest", 200, drinks);
        Bar bar2 = new Bar(2, "Black Helmets", "Bucharest", 50, drinks);
        Bar bar3 = new Bar(3, "Harley Bar", "Bucharest", 30, drinks);
        Bar bar4 = new Bar(4, "Metal Jack", "Bucharest", 30, drinks);

        bars.add(bar1);
        bars.add(bar2);
        bars.add(bar3);
        bars.add(bar4);
    }

    @RequestMapping(value="/bar", method = RequestMethod.GET)
    public List<Bar> index() {
        return this.bars;
    }

    @RequestMapping(value="/bar/{id}", method = RequestMethod.GET)
    public ResponseEntity show(@PathVariable("id") int id) {
        for(Bar p : this.bars) {
            if(p.getId() == id) {
                return new ResponseEntity<Bar>(p, new HttpHeaders(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/bar/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable("id") int id) {
        for(Bar p : this.bars) {
            if(p.getId() == id) {
                this.bars.remove(p);
                return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/bar/{id}/{name}/{location}/{max_number_customers}/{drinks}", method = RequestMethod.POST)
    public ResponseEntity add(@PathVariable("id") int id, @PathVariable("name") String name, @PathVariable("location") String location, @PathVariable("max_number_customers") int max_number_customers, @PathVariable("drinks") List<String> drinks) {
        Bar p = new Bar(id,name,location,max_number_customers,drinks);
        this.bars.add(p);
        return new ResponseEntity<List<Bar>>(this.bars, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value="/bar/{id}/{name}/{location}/{max_number_customers}/{drinks}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("id") int id, @PathVariable("name") String name, @PathVariable("location") String location, @PathVariable("max_number_customers") int max_number_customers, @PathVariable("drinks") List<String> drinks) {
        for (Bar p : this.bars) {
            if (p.getId() == id) {
                p.setName(name);
                p.setLocation(location);
                p.setMaxNumberCustomers(max_number_customers);
                p.setDrinks(drinks);
            }
        }
        return new ResponseEntity<List<Bar>>(this.bars, new HttpHeaders(), HttpStatus.OK);
    }
}

