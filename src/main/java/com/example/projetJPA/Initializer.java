package com.example.projetJPA;

        import com.example.projetJPA.model.Client;
        import com.example.projetJPA.model.Appartment;
        import com.example.projetJPA.model.Address;
        import com.example.projetJPA.model.AppartmentRepository;
        import org.springframework.boot.CommandLineRunner;
        import org.springframework.stereotype.Component;

        import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

    private final AppartmentRepository repository;

    public Initializer(AppartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        Client e1 = Client.builder()
                .name("Samuel")
                .email("samuel@test.fr")
                .build();
        Client e2 = Client.builder()
                .name("Jack")
                .email("jack@test.fr")
                .build();
        Client e3 = Client.builder()
                .name("David")
                .email("david@test.fr")
                .build();

        Address a1 = Address.builder()
                .streetName("route d'Arlon")
                .streetNumber(23)
                .city("Luxembourg")
                .build();
        Address a2 = Address.builder()
                .streetName("Oxford street")
                .streetNumber(13)
                .city("London")
                .build();
        Address a3 = Address.builder()
                .streetName("rue Champs-Elysées")
                .streetNumber(33)
                .city("Paris")
                .build();


        Appartment app1 = new Appartment("Appartement Luxembourg N1",a1,e1);
//        System.out.println(app1);
        Appartment app2 = new Appartment("Appartement Londres N1",a2,e2);
//        System.out.println(app2);
        Appartment app3 = new Appartment("Appartement Paris N1",a3, e3);
//        System.out.println(app3);

        repository.save(app1);
        repository.save(app2);
        repository.save(app3);

//        Appartment aprt = repository.findByName("Appartement Luxembourg N1");
//        Appartment aprt2 = repository.findByName("Appartement Londres N1");
//        Appartment aprt3 = repository.findByName("Appartement Paris N1");
//        System.out.println(aprt);
        repository.findAll().forEach(System.out::println);
    }
}