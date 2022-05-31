package project.airbnb.wishlist;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Long> {

	@EntityGraph(attributePaths = {"bnb", "member"})
	List<Wish> findWishlistByMemberId(Long id);

}
