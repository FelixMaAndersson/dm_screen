package se.yrgo.controllers;

import org.springframework.web.bind.annotation.*;
import se.yrgo.domain.campaign.Campaign;
import se.yrgo.dto.campaign.CampaignResponse;
import se.yrgo.dto.campaign.UpdateCampaignRequest;
import se.yrgo.exceptions.campaign.CampaignNotFoundException;
import se.yrgo.exceptions.user.UserNotFoundException;
import se.yrgo.services.campaign.CampaignService;
import se.yrgo.dto.campaign.CreateCampaignRequest;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
@CrossOrigin(origins = "http://localhost:5173")
public class CampaignController {

    private final CampaignService service;

    public CampaignController(CampaignService service) {
        this.service = service;
    }

    // CREATE

    @PostMapping
    public CampaignResponse createCampaign(
            @RequestBody CreateCampaignRequest request)
            throws UserNotFoundException {

        return service.createCampaign(request);
    }

    // READ

    @GetMapping
    public List<CampaignResponse> getAllCampaigns() {
        return service.getAllCampaigns();
    }

    @GetMapping("/{id}")
    public CampaignResponse getCampaignById(@PathVariable Long id) throws CampaignNotFoundException {
        return service.getCampaignById(id);
    }

    // UPDATE

    @PutMapping("/{id}")
    public CampaignResponse updateCampaign(
            @PathVariable Long id,
            @RequestBody UpdateCampaignRequest request)
            throws CampaignNotFoundException, UserNotFoundException {

        return service.updateCampaign(
                id,
                request
        );
    }

    // DELETE

    @DeleteMapping("/{id}")
    public void deleteCampaign(@PathVariable Long id) {
        service.deleteCampaign(id);
    }
}
