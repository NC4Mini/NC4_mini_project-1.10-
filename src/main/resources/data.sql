-- 테스트 계정 (studydb --> 본인 스키마 선택)
insert into studydb.user_account (user_id, user_pw, user_name, user_tel, user_birth, user_gender, user_profile, user_email) values
('testuserid1', 'testpassword1', '이름1', '010-0000-0000', now(), '남자', '프로필주소1', 'id1@mail.com'),
('testuserid2', 'testpassword2', '이름2', '010-0000-1111', now(), '여자', '프로필주소2', 'id2@mail.com'),
('testuserid3', 'testpassword3', '이름3', '010-0000-2222', now(), '남자', '프로필주소3', 'id3@mail.com'),
('testuserid4', 'testpassword4', '이름4', '010-0000-3333', now(), '여자', '프로필주소4', 'id4@mail.com')
;

-- 테스트용 상품 (studydb --> 본인 스키마 선택)
INSERT INTO studydb.item (item_price, item_status, item_stock, item_id, item_category, item_description, item_name) VALUES (1000, 'y', 1, 1, '채소', '이거 감자임', '감자');
INSERT INTO studydb.item (item_price, item_status, item_stock, item_id, item_category, item_description, item_name) VALUES (2000, 'y', 1, 2, '채소', '이거 양파임', '양파');
INSERT INTO studydb.item (item_price, item_status, item_stock, item_id, item_category, item_description, item_name) VALUES (3000, 'y', 1, 3, '채소', '이거 피망임', '피망');
INSERT INTO studydb.item (item_price, item_status, item_stock, item_id, item_category, item_description, item_name) VALUES (4000, 'y', 1, 4, '채소', '이거 호박임', '호박');
INSERT INTO studydb.item (item_price, item_status, item_stock, item_id, item_category, item_description, item_name) VALUES (5000, 'y', 1, 5, '채소', '이거 당근임', '당근');
INSERT INTO studydb.item (item_price, item_status, item_stock, item_id, item_category, item_description, item_name) VALUES (6000, 'y', 1, 6, '채소', '이거 토마토임', '토마토');
INSERT INTO studydb.item (item_price, item_status, item_stock, item_id, item_category, item_description, item_name) VALUES (7000, 'y', 1, 7, '채소', '이거 가지임', '가지');
INSERT INTO studydb.item (item_price, item_status, item_stock, item_id, item_category, item_description, item_name) VALUES (8000, 'y', 1, 8, '채소', '이거 오이임', '오이');
INSERT INTO studydb.item (item_price, item_status, item_stock, item_id, item_category, item_description, item_name) VALUES (9000, 'y', 1, 9, '채소', '이거 버섯임', '버섯');
INSERT INTO studydb.item (item_price, item_status, item_stock, item_id, item_category, item_description, item_name) VALUES (10000, 'y', 1, 10, '채소', '이거 대파임', '대파');


--