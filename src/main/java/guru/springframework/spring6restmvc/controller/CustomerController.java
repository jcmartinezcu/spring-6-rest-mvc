package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.Customer;
import guru.springframework.spring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @PatchMapping("/{customerId}")
    public ResponseEntity updatePatchById(@PathVariable UUID customerId, @RequestBody Customer customer){

        customerService.patchCustomerById(customerId,customer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity deleteById(@PathVariable UUID customerId){
        customerService.deleteById(customerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity updateById(@PathVariable UUID customerId, @RequestBody Customer customer){

        customerService.updateCustomerById(customerId,customer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity handlerPost(@RequestBody Customer customer){
        log.debug("Ini HandlerPost - controller");
        Customer savedCustomer = customerService.saveNewCustomer(customer);
        log.debug("Saved HandlerPost - controller");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedCustomer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Customer> listClient(){
        log.debug("Get List client - in controller");

        return customerService.listCustomer();
    }

    @GetMapping("/{id}")
    public Customer getClientById(@PathVariable UUID id){
        log.debug("Get client by Id - in Controller");

        return customerService.getCustomerById(id);
    }
}
