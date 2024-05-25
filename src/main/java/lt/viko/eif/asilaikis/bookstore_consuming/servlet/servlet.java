package lt.viko.eif.asilaikis.bookstore_consuming.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lt.viko.eif.asilaikis.bookstore_consuming.transform.TransformToPDF;

import java.io.*;

@WebServlet(name="servlet",urlPatterns = {"/pdf/*"})
public class servlet extends HttpServlet {
    private void servePDFFile(HttpServletResponse response) throws IOException {
        File file = new File("src/main/resources/Orders.pdf");
        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=Orders.pdf");
        response.setContentLength((int) file.length());

        try (InputStream is = new FileInputStream(file); OutputStream os = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        }
    }
    protected void servetHTMLBook(HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang='lt'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Client Order</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; height: 100vh; }");
        out.println(".container { background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); max-width: 400px; width: 100%; }");
        out.println("h1 { font-size: 24px; margin-bottom: 20px; }");
        out.println("form { display: flex; flex-direction: column; }");
        out.println("label { margin-bottom: 8px; font-weight: bold; }");
        out.println("input[type='text'] { padding: 10px; margin-bottom: 20px; border: 1px solid #ccc; border-radius: 4px; }");
        out.println("input[type='submit'] { padding: 10px 15px; background-color: #28a745; color: #fff; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; }");
        out.println("input[type='submit']:hover { background-color: #218838; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>Enter book name</h1>");
        out.println("<form action='/pdf/GetBookRequest' method='post'>");
        out.println("<label for='bookName'>Book name:</label>");
        out.println("<input type='text' id='BookName' name='BookName' required>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    protected void servetHTMLClientBook(HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang='lt'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Client Order</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; height: 100vh; }");
        out.println(".container { background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); max-width: 400px; width: 100%; }");
        out.println("h1 { font-size: 24px; margin-bottom: 20px; }");
        out.println("form { display: flex; flex-direction: column; }");
        out.println("label { margin-bottom: 8px; font-weight: bold; }");
        out.println("input[type='text'] { padding: 10px; margin-bottom: 20px; border: 1px solid #ccc; border-radius: 4px; }");
        out.println("input[type='submit'] { padding: 10px 15px; background-color: #28a745; color: #fff; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; }");
        out.println("input[type='submit']:hover { background-color: #218838; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>Enter book name</h1>");
        out.println("<form action='/pdf/GetBookOrdersRequest' method='post'>");
        out.println("<label for='bookName'>Book name:</label>");
        out.println("<input type='text' id='bookName' name='bookName' required>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    protected void servetHTMLClient(HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang='lt'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Client Order</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; height: 100vh; }");
        out.println(".container { background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); max-width: 400px; width: 100%; }");
        out.println("h1 { font-size: 24px; margin-bottom: 20px; }");
        out.println("form { display: flex; flex-direction: column; }");
        out.println("label { margin-bottom: 8px; font-weight: bold; }");
        out.println("input[type='text'] { padding: 10px; margin-bottom: 20px; border: 1px solid #ccc; border-radius: 4px; }");
        out.println("input[type='submit'] { padding: 10px 15px; background-color: #28a745; color: #fff; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; }");
        out.println("input[type='submit']:hover { background-color: #218838; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>Enter First name</h1>");
        out.println("<form action='/pdf/GetClientOrdersRequest' method='post'>");
        out.println("<label for='firstName'>First name:</label>");
        out.println("<input type='text' id='firstName' name='firstName' required>");
        out.println("<h1>Enter Last name</h1>");
        out.println("<label for='lastName'>Last name:</label>");
        out.println("<input type='text' id='lastName' name='lastName' required>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().endsWith("GetClientOrdersRequest")) {
            try {
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                if (firstName == null || lastName == null) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
                TransformToPDF.convertToGetOrderClient(firstName, lastName);
                servePDFFile(response);
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        if (request.getRequestURI().endsWith("GetBookOrdersRequest")) {
            try {
                String bookName = request.getParameter("bookName");
                if (bookName == null) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
                TransformToPDF.convertToGetAllBooks(bookName);
                servePDFFile(response);
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
        if (request.getRequestURI().endsWith("GetBookRequest")) {
            try {
                String bookName = request.getParameter("BookName");
                if (bookName == null) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
                TransformToPDF.convertToGetBook(bookName);
                servePDFFile(response);
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().endsWith("Orders.pdf")) {
            servePDFFile(response);
        } else if (request.getRequestURI().endsWith("GetClientOrdersRequest")) {
            servetHTMLClient(response);
        } else if (request.getRequestURI().endsWith("GetBookOrdersRequest")) {
            servetHTMLClientBook(response);
        } else if (request.getRequestURI().endsWith("GetBookRequest")) {
            servetHTMLBook(response);
        } else {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>PDF</title>");
            out.println("<style>");
            out.println("html, body { height: 100%; margin: 0; padding: 0; display: flex; align-items: center; justify-content: center; font-family: Arial, sans-serif; }");
            out.println("body { background-color: #f4f4f9; }");
            out.println(".container { text-align: center; }");
            out.println("a { background-color: #007bff; color: white; padding: 20px 30px; text-decoration: none; border-radius: 5px; display: inline-block; margin: 10px 0; }");
            out.println("a:hover { background-color: #0056b3; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h1>Book Store to PDF</h1>");
            out.println("<p><a href='/pdf/GetClientOrdersRequest'>GetClientOrdersRequest</a></p>");
            out.println("<p><a href='/pdf/GetBookOrdersRequest'>GetBookOrdersRequest</a></p>");
            out.println("<p><a href='/pdf/GetBookRequest'>GetBookRequest</a></p>");
        }
    }

}
