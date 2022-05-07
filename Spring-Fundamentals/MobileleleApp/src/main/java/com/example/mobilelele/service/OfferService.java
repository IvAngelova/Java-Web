package com.example.mobilelele.service;

import com.example.mobilelele.model.view.OfferSummaryView;

import java.util.List;

public interface OfferService {
    void initializeOffers();

    List<OfferSummaryView> getAllOffers();
}
