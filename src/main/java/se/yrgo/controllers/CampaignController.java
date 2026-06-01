package se.yrgo.controllers;

import org.springframework.web.bind.annotation.*;
import se.yrgo.domain.Campaign;
import se.yrgo.exceptions.CampaignNotFoundException;
import se.yrgo.exceptions.UserNotFoundException;
import se.yrgo.services.campaign.CampaignService;
import se.yrgo.dto.CreateCampaignRequest;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    private final CampaignService service;

    public CampaignController(CampaignService service) {
        this.service = service;
    }

    @GetMapping
    public List<Campaign> getAllCampaigns() {
        return service.getAllCampaigns();
    }

    @GetMapping("/{id}")
    public Campaign getCampaignById(@PathVariable Long id) throws CampaignNotFoundException {
        return service.getCampaignById(id);
    }

    @PostMapping
    public Campaign createCampaign(@RequestBody CreateCampaignRequest request) throws UserNotFoundException {
        return service.createCampaign(request.name(), request.dmId());
    }

    @DeleteMapping("/{id}")
    public void deleteCampaign(@PathVariable Long id) {
        service.deleteCampaign(id);
    }


}
