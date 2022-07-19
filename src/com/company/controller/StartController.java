package com.company.controller;

import com.company.ConstantsJSP;
import com.company.exceptions.InitException;
import com.company.factories.DoubleArrayFactory;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        urlPatterns = {"/start"},
        initParams = {
                @WebInitParam(name = "min.size", value = "2"),
                @WebInitParam(name = "factory.number", value = "csv")
        }
)
public class StartController extends HttpServlet {

    @Override
    public void init(ServletConfig sc) throws ServletException {
        super.init(sc);
        try {
            final int MIN_SIZE = Integer.parseInt(sc.getInitParameter("min.size"));
            final String SOURCE_TYPE = sc.getInitParameter("factory.number");
            List<Double> numbers = DoubleArrayFactory.createDoubleArray(SOURCE_TYPE);
            if (numbers.size() < MIN_SIZE) {
                throw new InitException("Few numbers found...");
            }
            getServletContext().setAttribute(ConstantsJSP.NUMBERS_NAME, numbers);
            getServletContext().setAttribute(ConstantsJSP.MAX_VALUE_NAME, numbers.size());
        } catch (InitException e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int statsNumber = Integer.parseInt(request.getParameter(ConstantsJSP.NUMBER_NAME));
        request.setAttribute(ConstantsJSP.NUMBER_NAME, statsNumber);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(ConstantsJSP.START_PAGE_URL);
        rd.forward(request, response);
    }
}
