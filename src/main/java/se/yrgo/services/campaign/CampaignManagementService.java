package se.yrgo.services.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.yrgo.dataaccess.CampaignRepository;
import se.yrgo.dataaccess.PlayerCharacterRepository;


@Service
public class CampaignManagementService {

    private final CampaignRepository dao;
    private final PlayerCharacterRepository playerCharacterRepository;


    @Autowired
    public CampaignManagementService(CampaignRepository dao, PlayerCharacterRepository playerCharacterRepository) {
        this.dao = dao;
        this.playerCharacterRepository = playerCharacterRepository;
    }

}
