package com.watermelon.service;

import com.watermelon.entity.Query;

import java.util.List;

public interface SearchService {

    List searchAll();

    List searchByQuery(Query query);

    List getCity();
}
