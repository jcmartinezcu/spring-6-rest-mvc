
package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, Customer> customerMap;

    public CustomerServiceImpl(){
        this.customerMap = new HashMap<>();

        Customer client1 = Customer.builder()
                .customerName("Pepe uno")
                .id(UUID.randomUUID())
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer client2 = Customer.builder()
                .customerName("Juan Dos")
                .id(UUID.randomUUID())
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer client3 = Customer.builder()
                .customerName("Maria tres")
                .id(UUID.randomUUID())
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(client1.getId(), client1);
        customerMap.put(client2.getId(), client2);
        customerMap.put(client3.getId(), client3);
    }
    @Override
    public List<Customer> listCustomer() {
        log.debug("Get list clients - in service");
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer getCustomerById(UUID id) {

        log.debug("Get client by id - in service");

        return customerMap.get(id);
    }

    @Override
    public Customer saveNewCustomer(Customer customer) {
        Customer saveCustomer = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .customerName(customer.getCustomerName())
                .build();

        customerMap.put(saveCustomer.getId(), saveCustomer);
        log.debug(">>>>>>>>>> saveNewCustomer");
        customerMap.values().stream().forEach(System.out::println);
        return saveCustomer;
    }

    @Override
    public void updateCustomerById(UUID customerId, Customer customer) {

        Customer existing = customerMap.get(customerId);
        existing.setCustomerName(customer.getCustomerName());

        customerMap.put(existing.getId(), existing);
    }

    @Override
    public void deleteById(UUID customerId) {
        customerMap.remove(customerId);
    }

    @Override
    public void patchCustomerById(UUID customerId, Customer customer) {
        Customer existing = customerMap.get(customerId);

        if(StringUtils.hasText(customer.getCustomerName())){
            existing.setCustomerName(customer.getCustomerName());
        }
    }
}
