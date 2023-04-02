package nus.iss.day21workshop.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.day21workshop.Repository.CustomerRepository;
import nus.iss.day21workshop.model.Customer;
import nus.iss.day21workshop.model.Order;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepo;

    public List<Customer> findAllCustomer(){
        return customerRepo.findAllCustomers();
    }
    
    public List<Customer> findAllCustomerWithLimit(Integer limit, Integer offset){
        return customerRepo.findAllCustomerWithLimit(limit, offset);
    }

    public Customer findCustomerById(Integer id){
        return customerRepo.findCustomerByID(id);
    }

    public List<Order> findCustomerOrder(Integer customerId){
        return customerRepo.findCustomerOrder(customerId);
    }

}
