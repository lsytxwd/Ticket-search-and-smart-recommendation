package com.watermelon.service;

import com.watermelon.entity.Flight;
import com.watermelon.entity.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface SearchService {

    Page searchAll(PageRequest pageRequest);

    List searchByQuery(Query query, PageRequest pageRequest);

    Flight searchById(Long id);

    Page getCity(PageRequest pageRequest);

    void saveFlight(Flight flight);
}
