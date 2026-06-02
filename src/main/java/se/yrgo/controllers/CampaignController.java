package se.yrgo.controllers;

import org.springframework.web.bind.annotation.*;
import se.yrgo.domain.Campaign;
import se.yrgo.dto.campaign.CampaignResponse;
import se.yrgo.dto.campaign.UpdateCampaignRequest;
import se.yrgo.exceptions.CampaignNotFoundException;
import se.yrgo.exceptions.UserNotFoundException;
import se.yrgo.services.campaign.CampaignService;
import se.yrgo.dto.campaign.CreateCampaignRequest;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
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

        Campaign campaign =
                service.createCampaign(request.name(), request.dmId());

        return new CampaignResponse(
                campaign.getId(),
                campaign.getName(),
                campaign.getDm().getName()
        );
    }

    // READ

    @GetMapping
    public List<CampaignResponse> getAllCampaigns() {
        return service.getAllCampaigns()
                .stream()
                .map(campaign -> new CampaignResponse(
                        campaign.getId(),
                        campaign.getName(),
                        campaign.getDm().getName()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public Campaign getCampaignById(@PathVariable Long id) throws CampaignNotFoundException {
        return service.getCampaignById(id);
    }

    // UPDATE

    @PutMapping("/{id}")
    public CampaignResponse updateCampaign(
            @PathVariable Long id,
            @RequestBody UpdateCampaignRequest request)
            throws CampaignNotFoundException, UserNotFoundException  {
        Campaign updatedCampaign = service.updateCampaign(
                id,
                request.name(),
                request.description(),
                request.dmId()
        );
        return new CampaignResponse(
                updatedCampaign.getId(),
                updatedCampaign.getName(),
                updatedCampaign.getDm().getName()
        );
    }

    // DELETE

    @DeleteMapping("/{id}")
    public void deleteCampaign(@PathVariable Long id) {
        service.deleteCampaign(id);
    }


}
