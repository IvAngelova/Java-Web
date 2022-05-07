package com.example.mobilelele.service.impl;

import com.example.mobilelele.model.entity.Offer;
import com.example.mobilelele.model.view.OfferSummaryView;
import com.example.mobilelele.repository.OfferRepository;
import com.example.mobilelele.service.OfferService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public void initializeOffers() {
       //TODO
    }

    @Override
    public List<OfferSummaryView> getAllOffers() {
        return offerRepository
                .findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    private OfferSummaryView map(Offer offer) {
       // TODO
        return new OfferSummaryView();
    }
}
