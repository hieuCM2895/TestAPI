package com.fsoft.fa.controller;

import com.fsoft.fa.dao.implement.CustomerDALImpl;
import com.fsoft.fa.dao.implement.LineItemDALImpl;
import com.fsoft.fa.model.Customer;
import com.fsoft.fa.model.LineItem;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


/**
 * 
 * @author haimh
 *
 */
@RestController // annotation
@RequestMapping(path = "/api", produces = APPLICATION_JSON_VALUE)
//localhost:8080/api/
public class APIController {

    CustomerDALImpl customerDAL = new CustomerDALImpl();
    LineItemDALImpl lineItemDAL = new LineItemDALImpl();

	/**
	 * 
	 * @return
	 */
    @GetMapping(value = "/test")
    public List<Customer> getHumans() {
        try {
//        	Integer.parseInt("a");
//        	Human human = new Human();
//        	human.setName("Hai");
//        	Human human2 = new Human();
//        	human2.setName("Hai-2");
//        	List<Human> humans = new ArrayList<Human>();
//        	humans.add(human);
//        	humans.add(human2);
            return customerDAL.getAllCustomer();
        } catch (Exception e) {
        	// 5xx thuộc về server /501, 502, 503
        	// 4xx thuóc về client server
        	// - 404 là lỗi: Not found url 
        	// - 400 là lỗi sai body
        	// - 405 là sai method
        	
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @GetMapping(value = "/test/{id}")
    public Customer getCustomerById(@PathVariable(name = "id") int id) {
        try {
            return customerDAL.getCustomerById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PutMapping(value = "/update-user/{id}")
    public Customer updateUser(@PathVariable(name = "id") String id, @RequestBody Customer customer) {
        try {
            customerDAL.updateCustomerDAL(customer);
            return customer;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PostMapping(value = "/create-user")
    public int createUser(@RequestBody List<Customer> listOfAllCustomer) {
        try {
//        	System.out.println("Insert into DB user: " + customer.getCustomerName());
            int count = 0;
            for (Customer customer : listOfAllCustomer) {
                customerDAL.addCustomerDAL(customer);
                count++;
            }
        	return count;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @DeleteMapping(value = "/delete-user/{id}")
    public String deleteUser(@PathVariable(name = "id") String id) {
        try {
            if (!customerDAL.checkCustomerIdExits(Integer.parseInt(id))) {
                throw new Exception("Customer id hasn't exist in database.");
            }
            customerDAL.deleteCustomerDAL(Integer.parseInt(id));
            return id + " deleted";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @GetMapping(value = "/lineitem/{id}")
    public List<LineItem> getAllLineItem(@PathVariable(name = "id") int id) {
        try {
            return lineItemDAL.getAllItemsByOrderId(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PutMapping(value = "/lineitem/{order_id}-{product_id}")
    public String updateLineItem(@PathVariable(name = "order_id") int order_id, @PathVariable(name = "product_id") int product_id, @RequestBody LineItem lineItem) {
        try {
            int result = lineItemDAL.updateLineItemAPI(order_id, product_id, lineItem);
            if (result == 1) {
                return "Successful";
            }
            return "Fail";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
