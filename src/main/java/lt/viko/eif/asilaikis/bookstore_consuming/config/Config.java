package lt.viko.eif.asilaikis.bookstore_consuming.config;

import lt.viko.eif.asilaikis.bookstore_consuming.client.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class Config {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("eif.viko.lt.asilaikis.book.wsdl");
        return marshaller;
    }
    @Bean
    public Client orderClient(Jaxb2Marshaller marshaller) {
        Client client = new Client();
        client.setDefaultUri("http://localhost:8080/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
