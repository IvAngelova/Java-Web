package com.example.mobilelele.web;

import com.example.mobilelele.model.binding.OfferUpdateBindingModel;
import com.example.mobilelele.model.entity.enums.EngineEnum;
import com.example.mobilelele.model.entity.enums.TransmissionEnum;
import com.example.mobilelele.model.service.OfferUpdateServiceModel;
import com.example.mobilelele.model.view.OfferDetailsView;
import com.example.mobilelele.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.rsocket.server.RSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class OffersController {

    private final OfferService offerService;
    private final ModelMapper modelMapper;

    public OffersController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/offers/all")
    public String allOffers(Model model) {
        model.addAttribute("offers", offerService.getAllOffers());
        return "offers";
    }

    @GetMapping("/offers/{id}/details")
    public String showOffer(@PathVariable Long id, Model model) {

        model.addAttribute("offerDetails", offerService.getOfferDetails(id));

        return "details";
    }

    @DeleteMapping("offers/{id}")
    public String deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }

    @GetMapping("/offers/{id}/edit")
    public String editOffer(@PathVariable Long id, Model model) {
        OfferDetailsView offerDetailsView = offerService.getOfferDetails(id);
        OfferUpdateBindingModel offerUpdateBindingModel = modelMapper
                .map(offerDetailsView, OfferUpdateBindingModel.class);
        model.addAttribute("offerModel", offerUpdateBindingModel);
        model.addAttribute("engines", EngineEnum.values());
        model.addAttribute("transmissions", TransmissionEnum.values());
        return "update";
    }

    @GetMapping("/offers/{id}/edit/errors")
    public String editOfferError(@PathVariable Long id, Model model) {
        model.addAttribute("engines", EngineEnum.values());
        model.addAttribute("transmissions", TransmissionEnum.values());
        return "update";
    }

    @PatchMapping("/offers/{id}/edit")
    public String updateOffer(@PathVariable Long id,
                              @Valid OfferUpdateBindingModel offerModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offerModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.offerModel",
                            bindingResult);

            return "redirect:/offers/" + id + "/edit/errors";
        }

        OfferUpdateServiceModel offerUpdateServiceModel = modelMapper
                .map(offerModel, OfferUpdateServiceModel.class);
        offerUpdateServiceModel.setId(id);
        offerService.updateOffer(offerUpdateServiceModel);

        return "redirect:/offers/" + id + "/details";
    }


}
