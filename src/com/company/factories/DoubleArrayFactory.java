package com.company.factories;

import com.company.Constants;
import com.company.utils.DatabaseConnection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class DoubleArrayFactory {

    private enum SourceType {
        MEMORY {
            @Override
            List<Double> getDoubleArray() {
                List<Double> results = new ArrayList<>();
                results.add(3.4);
                results.add(4.5);
                results.add(1.2);
                results.add(6.3);
                results.add(7.1);
                results.add(4.2);
                results.add(2.4);
                results.add(2.5);
                results.add(6.1);
                results.add(9.9);
                results.add(6.9);
                results.add(7.8);
                results.add(4.7);
                results.add(9.0);
                results.add(1.0);
                results.add(6.5);
                return results;
            }
        },
        DB {

            private static final String CLASS_NAME = "com.mysql.jdbc.Driver";

            @Override
            List<Double> getDoubleArray() {

                List<Double> results = new ArrayList<>();

                try {
                    Class.forName(CLASS_NAME);

                    Connection connection = null;
                    Statement sSelectNumbers = null;
                    ResultSet resultSet = null;
                    try {
                        DatabaseConnection db = DatabaseConnection.getInstance();
                        connection = db.getConnection();
                        sSelectNumbers = connection.createStatement();
                        resultSet = sSelectNumbers.executeQuery(Constants.SELECT_QUERY);
                        while (resultSet.next()) {
                            double number = resultSet.getDouble(Constants.NUMBER_FIELD_NAME);
                            results.add(number);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        DatabaseConnection.closeStatement(sSelectNumbers);
                        DatabaseConnection.closeResultSet(resultSet);
                        DatabaseConnection.closeConnection(connection);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                return results;
            }
        },
        CSV{

            private static final String FILE_NAME = "/numbers.csv";

            @Override
            List<Double> getDoubleArray() {

                List<Double> results = new ArrayList<>();

                URL url = getClass().getClassLoader().getResource(FILE_NAME);

                try(Scanner scanner = new Scanner(new FileReader(Objects.requireNonNull(url).getFile()))) {
                    while (scanner.hasNextDouble()){
                        results.add(scanner.nextDouble());
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                return results;
            }
        };

        abstract List<Double> getDoubleArray();
    }

    public static List<Double> createDoubleArray(String sourceType) {
        return SourceType.valueOf(sourceType.toUpperCase()).getDoubleArray();
    }
}
