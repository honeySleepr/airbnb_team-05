INSERT INTO bnb (city, country, district, road, bathroom_count, bed_count, room_count, bnb_type,
                 description, fee, host, max_guest_number, name, rating, review_count, check_in,
                 check_out)
VALUES ('서울', '한국', '송파구', '오금로', 1, 1, 1, 'HOUSE', '좋아요', 50000, 'bc', 1, '최고숙소', 3.5, 123,
        '15:00',
        '11:00');

INSERT INTO member (name)
VALUES ('nori');

INSERT INTO wish (bnb_id, member_id)
VALUES (1, 1);
