package project.airbnb.wishlist;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WishServiceTest {

	@Autowired
	WishRepository wishRepository;

	@Test
	void findAll() {
		List<Wish> wishlist = wishRepository.findByWishlist(1L);

		for (Wish wish : wishlist) {
			System.out.println(wish);
		}
	}

	@Test
	void findWishDto() {
		List<WishDto> wishDto = wishRepository.findWishDto(2L);

		for (WishDto dto : wishDto) {
			System.out.println(dto);
		}
	}

}
