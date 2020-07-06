package com.watermelon.service;

import com.watermelon.entity.Flight;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CSVParseServiceImpl {

    public List parseCSV(String url) throws IOException, ParseException {

        //解析csv
        Reader in = new FileReader(url);
        CSVParser parse = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        Map<String, Integer> headerMap = parse.getHeaderMap();
        List<CSVRecord> records = parse.getRecords();
        Iterator<String> iterator = headerMap.keySet().iterator();
        List<String> headers = new ArrayList<>();
        while (iterator.hasNext()) headers.add(iterator.next());
//        Map<String,Object> map = new HashMap<>();
//        map.put("airlineName",record.get(headers.get(0)));
        List<Flight> flights = new ArrayList<>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int count = 0;
        for (CSVRecord record : records) {
            long id = count;
            String airlineName = record.get(headers.get(0));
            String flightNumber = record.get(headers.get(1));
            String craftTypeName = record.get(headers.get(2));
            Date departureDate = format.parse(record.get(headers.get(3)));
            Date arrivalDate = format.parse(record.get(headers.get(4)));
            String departureCityTlc = record.get(headers.get(5));
            String departureCityName = record.get(headers.get(6));
            String departureAirportTlc = record.get(headers.get(7));
            String departureAirportName = record.get(headers.get(8));
            String departureAirportTerminal = record.get(headers.get(9));
            String arrivalCityTlc = record.get(headers.get(10));
            String arrivalCityName = record.get(headers.get(11));
            String arrivalAirportTlc = record.get(headers.get(12));
            String arrivalAirportName = record.get(headers.get(13));
            String arrivalAirportTerminal = record.get(headers.get(14));
            int price = Integer.parseInt(record.get(headers.get(headers.size()-1)));
            Flight flight = new Flight(id,
                    airlineName,
                    flightNumber,
                    craftTypeName,
                    departureDate,
                    arrivalDate,
                    departureCityTlc,
                    departureCityName,
                    departureAirportTlc,
                    departureAirportName,
                    departureAirportTerminal,
                    arrivalCityTlc,
                    arrivalCityName,
                    arrivalAirportTlc,
                    arrivalAirportName,
                    arrivalAirportTerminal,
                    price,
                    false);
            flights.add(flight);
            count++;
            if (count==20){
                break;
            }
        }
        return flights;
    }

}
