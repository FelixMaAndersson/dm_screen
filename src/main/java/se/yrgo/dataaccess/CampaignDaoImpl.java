package se.yrgo.dataaccess;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import se.yrgo.domain.Campaign;
import se.yrgo.domain.PlayerCharacter;
import se.yrgo.exceptions.CampaignNotFoundException;

import java.util.List;


@Repository
public class CampaignDaoImpl implements CampaignDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Campaign campaign) {

    }

    @Override
    public Campaign getById(int CampaignId) throws CampaignNotFoundException {
        return null;
    }

    @Override
    public Campaign getByName(String name) throws CampaignNotFoundException {
        return null;
    }

    @Override
    public void update(Campaign campaign) throws CampaignNotFoundException {

    }

    @Override
    public void delete(Campaign campaign) throws CampaignNotFoundException {

    }

    @Override
    public List<Campaign> getAllLeagues() {
        return List.of();
    }
}



