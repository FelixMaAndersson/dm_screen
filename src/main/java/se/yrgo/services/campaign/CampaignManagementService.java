package se.yrgo.services.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.yrgo.dataaccess.CampaignDao;
import se.yrgo.dataaccess.PlayerCharacterDao;


@Service
public class CampaignManagementService {

    private final CampaignDao dao;
    private final PlayerCharacterDao playerCharacterDao;


    @Autowired
    public CampaignManagementService(CampaignDao dao, PlayerCharacterDao playerCharacterDao) {
        this.dao = dao;
        this.playerCharacterDao = playerCharacterDao;
    }

}
