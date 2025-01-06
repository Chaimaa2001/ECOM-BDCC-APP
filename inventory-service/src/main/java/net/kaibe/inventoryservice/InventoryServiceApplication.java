package net.kaibe.inventoryservice;

import net.kaibe.inventoryservice.entities.Product;
import net.kaibe.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository){
        return args -> {
            productRepository.save(Product.builder()
                            .id("P01")
                            .name("Computer").price(2300.0).quantity(12).build());

            productRepository.save(Product.builder()
                    .id("P02")
                    .name("Printer").price(1200.0).quantity(10).build());

            productRepository.save(Product.builder()
                    .id("P03")
                    .name("Smart Phone").price(4200.0).quantity(34).build());
        };
    }

}
