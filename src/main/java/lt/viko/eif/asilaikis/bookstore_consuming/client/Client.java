package lt.viko.eif.asilaikis.bookstore_consuming.client;

import eif.viko.lt.asilaikis.book.wsdl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class Client extends WebServiceGatewaySupport {
    private static final Logger log = LoggerFactory.getLogger(Client.class);
    public GetBookResponse getBookOrder(String bookName)
    {
        GetBookRequest request = new GetBookRequest();
        request.setBookName(bookName);
        log.info("Requesting: " + bookName);
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("eif.viko.lt.asilaikis.book.wsdl");
        setMarshaller(marshaller);
        setUnmarshaller(marshaller);

        GetBookResponse response = (GetBookResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/orders", request,
                        new SoapActionCallback("http://eif.viko.lt.asilaikis/springsoap/gen/GetBookRequest"));
        return response;
    }
    public GetBookOrdersResponse getAllBookOrders(String bookName)
    {
        GetBookOrdersRequest request = new GetBookOrdersRequest();
        request.setBook(bookName);
        log.info("Requesting: " + bookName);
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("eif.viko.lt.asilaikis.book.wsdl");
        setMarshaller(marshaller);
        setUnmarshaller(marshaller);

        GetBookOrdersResponse response = (GetBookOrdersResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/orders", request,
                        new SoapActionCallback("http://eif.viko.lt.asilaikis/springsoap/gen/GetBookOrdersRequest"));
        return response;
    }
    public GetClientOrdersResponse getClientOrders(String firstName, String lastName)
    {
        GetClientOrdersRequest request = new GetClientOrdersRequest();
        request.setFirstName(firstName);
        request.setLastName(lastName);
        log.info("Requesting first name: {} and last name: {}", firstName, lastName);
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("eif.viko.lt.asilaikis.book.wsdl");
        setMarshaller(marshaller);
        setUnmarshaller(marshaller);

        GetClientOrdersResponse response = (GetClientOrdersResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/orders", request,
                        new SoapActionCallback("http://eif.viko.lt.asilaikis/springsoap/gen/GetClientOrdersRequest"));
        return response;
    }
}
