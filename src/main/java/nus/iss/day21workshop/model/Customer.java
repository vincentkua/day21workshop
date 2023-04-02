package nus.iss.day21workshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Integer id;
    private String company;
    private String lastName;
    private String emailAddress;
    private String jobTitle;
    private String businessPhone;    
}
