package nus.iss.day21workshop.Repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import nus.iss.day21workshop.model.Customer;
import nus.iss.day21workshop.model.Order;

@Repository
public class CustomerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    private final String GETALLCUSTOMERSQL = "select id, company, last_name , email_address,job_title,business_phone from customers";
    private final String GETALLCUSTOMERWITHLIMIT = "select id, company, last_name , email_address,job_title,business_phone from customers LIMIT ? OFFSET ?";
    private final String GETCUSTOMERBYID = "select id, company, last_name , email_address,job_title,business_phone from customers where id = ?";
    private final String GETCUSTOMERORDER = "select id,customer_id, order_date,shipped_date,ship_name from orders where customer_id = ?";

    public List<Customer> findAllCustomers(){

        List<Customer> customers = new LinkedList<Customer>();
        customers = jdbcTemplate.query(GETALLCUSTOMERSQL, BeanPropertyRowMapper.newInstance(Customer.class));
        return customers;
    }

    public List<Customer> findAllCustomerWithLimit(Integer limit , Integer offset){
        List<Customer> customers = new LinkedList<Customer>();
        customers = jdbcTemplate.query(GETALLCUSTOMERWITHLIMIT, BeanPropertyRowMapper.newInstance(Customer.class), limit , offset); 
        return customers;
    }

    public Customer findCustomerByID(Integer id){
        Customer customer = new Customer();
        try {
            customer = jdbcTemplate.queryForObject(GETCUSTOMERBYID, BeanPropertyRowMapper.newInstance(Customer.class), id);
            
        } catch (Exception e) {
            customer = null;
        }
      
        System.out.println("#################");
        System.out.println(customer);

        return customer;
    }


    public List<Order> findCustomerOrder(Integer customerId){
        List<Order> orders = new LinkedList<Order>();
        orders = jdbcTemplate.query(GETCUSTOMERORDER, BeanPropertyRowMapper.newInstance(Order.class),customerId);     

        return orders;

    }





    
}
