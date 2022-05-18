package com.example.mobilelele.service.impl;

import com.example.mobilelele.model.entity.Offer;
import com.example.mobilelele.model.entity.enums.EngineEnum;
import com.example.mobilelele.model.entity.enums.TransmissionEnum;
import com.example.mobilelele.model.view.OfferDetailsView;
import com.example.mobilelele.model.view.OfferSummaryView;
import com.example.mobilelele.repository.ModelRepository;
import com.example.mobilelele.repository.OfferRepository;
import com.example.mobilelele.repository.UserRepository;
import com.example.mobilelele.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(UserRepository userRepository, ModelRepository modelRepository, OfferRepository offerRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initializeOffers() {
        if (offerRepository.count() == 0) {
            Offer offer1 = new Offer();
            offer1
                    .setModel(modelRepository.findById(1L).orElse(null))
                    .setEngine(EngineEnum.GASOLINE)
                    .setTransmission(TransmissionEnum.MANUAL)
                    .setMileage(22500)
                    .setPrice(14300)
                    .setYear(2019)
                    .setDescription("Used, but well services and in good condition.")
                    .setSeller(userRepository.findByUsername("pesho")
                            .orElse(null)) // or currentUser.getUserName()
                    .setImageUrl(
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcXp1KBpDKgYs6VqndkBpX8twjPOZbHV86yg&usqp=CAU");

            Offer offer2 = new Offer();
            offer2
                    .setModel(modelRepository.findById(2L).orElse(null))
                    .setEngine(EngineEnum.DIESEL)
                    .setTransmission(TransmissionEnum.AUTOMATIC)
                    .setMileage(209000)
                    .setPrice(5500)
                    .setYear(2000)
                    .setDescription("After full maintenance, insurance, new tires...")
                    .setSeller(userRepository.findByUsername("admin")
                            .orElse(null)) // or currentUser.getUserName()
                    .setImageUrl(
                            "https://www.picclickimg.com/d/l400/pict/283362908243_/FORD-ESCORT-MK5-16L-DOHC-16v-ZETEC.jpg");

            offerRepository.saveAll(List.of(offer1, offer2));
        }
    }

    @Override
    public List<OfferSummaryView> getAllOffers() {
        return offerRepository
                .findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public OfferDetailsView getOfferDetails(Long id) {
        return offerRepository.findById(id)
                .stream()
                .map(offer -> {
                    OfferDetailsView offerDetailsView = modelMapper.map(offer, OfferDetailsView.class);
                    offerDetailsView.setBrand(offer.getModel().getBrand().getName());
                    offerDetailsView.setSeller(offer.getSeller().getFirstName() + " " + offer.getSeller().getLastName());
                    offerDetailsView.setModel(offer.getModel().getName());
                    return offerDetailsView;
                })
                .findAny()
                .orElse(null);

    }

    @Override
    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    private OfferSummaryView map(Offer offer) {
        OfferSummaryView summaryView = this.modelMapper
                .map(offer, OfferSummaryView.class);

        summaryView.setModel(offer.getModel().getName());
        summaryView.setBrand(offer.getModel().getBrand().getName());
        return summaryView;
    }
}
