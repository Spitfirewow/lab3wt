package by.bsuir.wt_lab.servlet;

import by.bsuir.wt_lab.entity.*;
import by.bsuir.wt_lab.parser.DomParser;
import by.bsuir.wt_lab.parser.SaxHandler;
import by.bsuir.wt_lab.parser.StaXParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainServlet extends HttpServlet {
    
    private final static String xmlFile = "D:\\desk\\epam-wt-lab-master\\Lab3\\src\\main\\resources\\coffeeData.xml";

    private final Logger logger = LogManager.getLogger();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        String type = request.getParameter("type");

        int rows, nOfPages;

        switch(type) {
            case "coffees":
                DomParser domParser = DomParser.getInstance();
                List<Coffee> coffeeList =
                        domParser.getCoffees(xmlFile);

                rows = coffeeList.size();

                if (currentPage * recordsPerPage >= rows) {
                    request.setAttribute("coffees", coffeeList.subList((currentPage - 1) * recordsPerPage, rows));
                } else {
                    request.setAttribute("coffees", coffeeList.subList((currentPage - 1) * recordsPerPage, currentPage * recordsPerPage));
                }

                nOfPages = rows / recordsPerPage;

                if (nOfPages % recordsPerPage > 0) {
                    nOfPages++;
                }

                request.setAttribute("noOfPages", nOfPages);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("recordsPerPage", recordsPerPage);
                request.setAttribute("type", type);

                request.getRequestDispatcher("jsp/coffees.jsp").forward(request, response);
                break; 
            case "orders":
                List<Order> orders = SaxHandler.getInstance().getOrderList();
                rows = orders.size();

                if (currentPage * recordsPerPage >= rows) {
                    request.setAttribute("orders", orders.subList((currentPage - 1) * recordsPerPage, rows));
                } else {
                    request.setAttribute("orders", orders.subList((currentPage - 1) * recordsPerPage, currentPage * recordsPerPage));
                }

                nOfPages = rows / recordsPerPage;

                if (nOfPages % recordsPerPage > 0) {
                    nOfPages++;
                }

                request.setAttribute("noOfPages", nOfPages);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("recordsPerPage", recordsPerPage);
                request.setAttribute("type", type);

                request.getRequestDispatcher("jsp/orders.jsp").forward(request, response);
                break;
            case "extras":
                XMLInputFactory inputFactory = XMLInputFactory.newInstance();
                List<Extra> extras = new ArrayList<>();
                try{
                    InputStream input = new FileInputStream(xmlFile);
                    XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
                    extras = StaXParser.processExtras(reader);
                } catch (XMLStreamException e){
                    logger.error(e.getMessage());
                }
                rows = extras.size();

                if (currentPage * recordsPerPage >= rows) {
                    request.setAttribute("extras", extras.subList((currentPage - 1) * recordsPerPage, rows));
                } else {
                    request.setAttribute("extras", extras.subList((currentPage - 1) * recordsPerPage, currentPage * recordsPerPage));
                }

                nOfPages = rows / recordsPerPage;

                if (nOfPages % recordsPerPage > 0) {
                    nOfPages++;
                }

                request.setAttribute("noOfPages", nOfPages);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("recordsPerPage", recordsPerPage);
                request.setAttribute("type", type);

                request.getRequestDispatcher("jsp/extras.jsp").forward(request, response);
                break;
        }
    }
}
