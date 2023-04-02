package nus.iss.day21workshop.Controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import nus.iss.day21workshop.Service.CustomerService;
import nus.iss.day21workshop.model.Customer;
import nus.iss.day21workshop.model.Order;

@RestController
@RequestMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerRestController {

    @Autowired
    CustomerService customerSvc;

    //To Return as a List of Object
    @GetMapping(value = "/api/all/list")
    public List<Customer> findAllCustomerList() {

        List<Customer> customers = new LinkedList<Customer>();
        customers = customerSvc.findAllCustomer();
        return customers;
    }


    //To Return as ResponseEntity with JSON String body
    @GetMapping(value = "/api/all")
    public ResponseEntity<String> findAllCustomer() {

        List<Customer> customers = new LinkedList<Customer>();
        customers = customerSvc.findAllCustomer();

        JsonObject payload = Json.createObjectBuilder()
                .add("customers", customers.toString())
                .build();

        return ResponseEntity.ok(payload.toString());
    }

    //Task 3.1 , test with  >>>> http://127.0.0.1:8080/api/customers?limit=10&offset=2
    @GetMapping(value = "/api/customers")
    public ResponseEntity<String> findAllCustomerWithLimit(@RequestParam("limit") Integer limit , @RequestParam("offset") Integer offset ){
        List<Customer> customers = new LinkedList<Customer>();

        customers = customerSvc.findAllCustomerWithLimit(limit , offset);
        JsonObject payload = Json.createObjectBuilder()
                .add("customers", customers.toString())
                .build();

        return ResponseEntity.ok(payload.toString());
    }


    //Task 3.2
    @GetMapping(value = "/api/customer/{id}")
    public ResponseEntity<String> findCustomerById(@PathVariable("id") Integer id){
        Customer customer = new Customer();
        customer = customerSvc.findCustomerById(id);

        if (customer == null){
            JsonObject payload = Json.createObjectBuilder()
            .add("customer", "null")
            .build();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error 404: Customer Id=" + id + " was not found...");
        }else{
            JsonObject payload = Json.createObjectBuilder()
            .add("customer", customer.toString())
            .build();

            return ResponseEntity.ok(payload.toString());
        }
    }


    //Task 3.3
    @GetMapping(value = "/api/customer/{customerId}/order")
    public ResponseEntity<String> findCustomerOrder(@PathVariable("customerId") Integer customerId){
        List<Order> orders = new LinkedList<Order>();
        orders = customerSvc.findCustomerOrder(customerId);

        JsonObject payload = Json.createObjectBuilder()
        .add("orders", orders.toString())
        .build();


        return ResponseEntity.status(HttpStatus.OK).body(payload.toString());

    }

}
