package project.airbnb.bnb;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BnbRepository extends JpaRepository<Bnb, Long>, BnbRepositoryCustom {

}
