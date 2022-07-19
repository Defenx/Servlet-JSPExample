package com.company.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.ConstantsJSP;
import com.company.model.Operation;

@WebServlet("/result")
public class ResultController extends HttpServlet {

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String strOperation = request.getParameter(ConstantsJSP.OPERATION_NAME);
        String[] strNumbers = request.getParameterValues(ConstantsJSP.STAT_NAME);
        List<Double> allNumbers = (List<Double>) getServletContext().getAttribute(ConstantsJSP.NUMBERS_NAME);
        int[] indices = Arrays.stream(strNumbers).mapToInt(Integer::parseInt).toArray();
        double[] numbers = new double[indices.length];
        for (int i = 0; i < indices.length; i++) {
            numbers[i] = allNumbers.get(indices[i]);
        }
        Operation operation = Operation.valueOf(strOperation.toUpperCase());
        double result = operation.getResult(numbers);
        request.setAttribute(ConstantsJSP.OPERATION_NAME, strOperation);
        request.setAttribute(ConstantsJSP.STAT_NAME, numbers);
        request.setAttribute(ConstantsJSP.RESULT_NAME, result);

        RequestDispatcher rd = getServletContext().getRequestDispatcher(ConstantsJSP.RESULT_PAGE_URL);
        rd.forward(request, response);
    }
}