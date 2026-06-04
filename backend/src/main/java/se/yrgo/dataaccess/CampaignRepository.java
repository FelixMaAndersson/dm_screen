package se.yrgo.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import se.yrgo.domain.Campaign;

import java.util.Optional;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    Optional<Campaign> findByName(String name);

}
