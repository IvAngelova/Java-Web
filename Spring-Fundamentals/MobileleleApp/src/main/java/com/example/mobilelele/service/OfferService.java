package com.example.mobilelele.service;

import com.example.mobilelele.model.binding.OfferAddBindingModel;
import com.example.mobilelele.model.service.OfferAddServiceModel;
import com.example.mobilelele.model.service.OfferUpdateServiceModel;
import com.example.mobilelele.model.view.OfferDetailsView;
import com.example.mobilelele.model.view.OfferSummaryView;

import java.util.List;

public interface OfferService {
    void initializeOffers();

    List<OfferSummaryView> getAllOffers();

    OfferDetailsView getOfferDetails(Long id);

    void deleteOffer(Long id);

    void updateOffer(OfferUpdateServiceModel offerUpdateServiceModel);

    OfferAddServiceModel addOffer(OfferAddBindingModel offerAddBindingModel);
}
