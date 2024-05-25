package lt.viko.eif.asilaikis.bookstore_consuming.transform;

import eif.viko.lt.asilaikis.book.wsdl.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lt.viko.eif.asilaikis.bookstore_consuming.client.Client;
import org.apache.fop.apps.*;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.file.Files;

public class TransformToPDF {

    private static void deleteFileIfExists(File file) {
        try {
            if (file.exists())
            {
                Files.deleteIfExists(file.toPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void convertToPDF(String xmlFilePath, String xslFilePath, String outputPdfPath) throws IOException, JAXBException {
        File fileXSLT = new File(xslFilePath);
        StreamSource source = new StreamSource(new File(xmlFilePath));
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        File pdfFile = new File(outputPdfPath);
        deleteFileIfExists(pdfFile);
        OutputStream out = null;
        try {
            out = new java.io.FileOutputStream(pdfFile);
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xslFilePath));
            //File foFile = new File("src/main/resources/Orders.fo");
            //Result res = new StreamResult(new FileWriter(foFile));
            Result res = new SAXResult((fop.getDefaultHandler()));
            transformer.transform(source, res);
        } catch (FOPException | TransformerException e) {
            throw new RuntimeException(e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static <T> void convertToXML(T response, String filePath) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(response.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            marshaller.marshal(response, fileWriter);
        }
    }

    public static void convertToGetOrderClient(String firstName, String lastName) throws IOException, JAXBException {
        Client order = new Client();
        GetClientOrdersResponse response = order.getClientOrders(firstName, lastName);
        String xmlFilePath = "Orders.xml";
        convertToXML(response, xmlFilePath);
        convertToPDF(xmlFilePath, "Orders.xsl", "src/main/resources/Orders.pdf");
    }

    public static void convertToGetBook(String bookName) throws IOException, JAXBException {
        Client book = new Client();
        GetBookResponse response = book.getBookOrder(bookName);
        String xmlFilePath = "Orders.xml";
        convertToXML(response, xmlFilePath);
        convertToPDF(xmlFilePath, "Orders.xsl", "src/main/resources/Orders.pdf");
    }

    public static void convertToGetAllBooks(String bookName) throws IOException, JAXBException {
        Client allBooks = new Client();
        GetBookOrdersResponse response = allBooks.getAllBookOrders(bookName);
        String xmlFilePath = "Orders.xml";
        convertToXML(response, xmlFilePath);
        convertToPDF(xmlFilePath, "Orders.xsl", "src/main/resources/Orders.pdf");
    }
}