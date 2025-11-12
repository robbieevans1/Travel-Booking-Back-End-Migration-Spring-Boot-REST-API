package com.robertevans.backend.bootstrap;

import com.robertevans.backend.dao.CustomerRepository;
import com.robertevans.backend.dao.DivisionRepository;
import com.robertevans.backend.entities.Customer;
import com.robertevans.backend.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BoostrapData implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BoostrapData(CustomerRepository customerRepository, DivisionRepository divisionRepositoryRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepositoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Division division = divisionRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Division not found"));


        Customer michelle = new Customer();
        michelle.setAddress("7189 Court st");
        michelle.setFirstName("Michelle");
        michelle.setLastName("Court");
        michelle.setPostal_code("11232");
        michelle.setPhone("8092225656");
        michelle.setDivision(division);


        Customer alexander = new Customer();
        alexander.setAddress("1842 Maple Ave");
        alexander.setFirstName("Alexander");
        alexander.setLastName("Reed");
        alexander.setPostal_code("10027");
        alexander.setPhone("9175553289");
        alexander.setDivision(division);


        Customer timothy = new Customer();
        timothy.setAddress("54 Oak Hill Rd");
        timothy.setFirstName("Timothy");
        timothy.setLastName("Nguyen");
        timothy.setPostal_code("11375");
        timothy.setPhone("7184907721");
        timothy.setDivision(division);

        Customer linus = new Customer();
        linus.setAddress("202 Willowbrook Dr");
        linus.setFirstName("Linus");
        linus.setLastName("Keller");
        linus.setPostal_code("10463");
        linus.setPhone("6462819327");
        linus.setDivision(division);

        Customer dorothy = new Customer();
        dorothy.setAddress("890 Hudson Blvd");
        dorothy.setFirstName("Dorothy");
        dorothy.setLastName("Fields");
        dorothy.setPostal_code("11209");
        dorothy.setPhone("3476001188");
        dorothy.setDivision(division);

        if (customerRepository.count() == 1){
        customerRepository.save(michelle);
        customerRepository.save(alexander);
        customerRepository.save(timothy);
        customerRepository.save(linus);
        customerRepository.save(dorothy);

        }
        System.out.println("Number of customers" + " " + customerRepository.count());
    }
}
