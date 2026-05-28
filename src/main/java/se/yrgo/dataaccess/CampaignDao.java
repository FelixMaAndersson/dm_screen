package se.yrgo.dataaccess;

import se.yrgo.domain.Campaign;
import se.yrgo.domain.PlayerCharacter;
import se.yrgo.exceptions.CampaignNotFoundException;

import java.util.List;

public interface CampaignDao {

    public void create(Campaign campaign);

    public void update(Campaign campaign) throws CampaignNotFoundException;

    public void delete(Campaign campaign) throws CampaignNotFoundException;

    public Campaign getById(int CampaignId) throws CampaignNotFoundException;

    public Campaign getByName(String name) throws CampaignNotFoundException;

    public List<Campaign> getAllLeagues();


}
