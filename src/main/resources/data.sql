-- 테스트 계정
insert into user_account (user_id, user_pw, user_name, user_tel, user_birth, user_gender, user_profile, user_email) values
('testuserid1', '{noop}testpassword1', '이름1', '010-0000-0000', now(), '남자', '프로필주소1', 'id1@mail.com'),
('testuserid2', '{noop}testpassword2', '이름2', '010-0000-1111', now(), '여자', '프로필주소2', 'id2@mail.com'),
('testuserid3', '{noop}testpassword3', '이름3', '010-0000-2222', now(), '남자', '프로필주소3', 'id3@mail.com'),
('testuserid4', '{noop}testpassword4', '이름4', '010-0000-3333', now(), '여자', '프로필주소4', 'id4@mail.com')
;

insert into board (created_time, updated_time, board_contents, board_category, board_title, board_hits, board_writer) values
('2024-01-01', '2024-01-01', '사이즈가 작아요', 'test1', '반품신청합니다', '0', '신지우'),
('2024-01-01', '2024-01-01', '사이즈가 작아요', 'test2', '교환신청합니다', '0', '김종범'),
('2024-01-01', '2024-01-01', 'ㅇㄴㅇㄴㅇㄴㅇ', 'test3', '환불신청합니다', '0', '아무개')
;
