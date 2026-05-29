package se.yrgo.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import se.yrgo.domain.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

}
