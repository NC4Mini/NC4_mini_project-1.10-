-- 테스트 계정
insert into user_account (user_id, user_pw, user_name, user_tel, user_addr, user_birth, user_gender, user_profile, user_email) values
('testUserId1', '{noop}testUserPw1', '이름1', '010-0000-0000', '서울시 강남구', now(), '남자', '프로필주소1', "id1@mail.com"),
('testUserId2', '{noop}testUserPw2', '이름2', '010-0000-1111', '서울시 중랑구', now(), '여자', '프로필주소2', "id2@mail.com"),
('testUserId3', '{noop}testUserPw3', '이름3', '010-0000-2222', '서울시 금천구', now(), '남자', '프로필주소3', "id3@mail.com"),
('testUserId4', '{noop}testUserPw4', '이름4', '010-0000-3333', '서울시 서초구', now(), '여자', '프로필주소4', "id4@mail.com")
;