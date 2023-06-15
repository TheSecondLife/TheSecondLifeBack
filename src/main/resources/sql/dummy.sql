use secondlife;
-- DROP DATABASE secondlife;
-- CREATE DATABASE secondlife;

-- select
SELECT * FROM users;
SELECT * FROM post;
SELECT * FROM comment;
SELECT * FROM reply;

-- insert
INSERT INTO users (user_id, created_date, modified_date, age, email, grade, name, nickname, password, profile_img)
VALUES
(1, "2023-06-08 00:37:11", "2023-06-08 00:37:11", 29, "jiminsung@naver.com", "씨앗" ,"민성"," ms", 1234, "https://fitsta-bucket.s3.ap-northeast-2.amazonaws.com/57b3f1c6-057b-4170-83aa-5997a0b51ae9-jyj.png"),
(2, "2023-06-08 00:37:11", "2023-06-08 00:37:11", 27, "sdy1213@gmail.com", "씨앗" ,"다엔", "daen", 1234, "https://fitsta-bucket.s3.ap-northeast-2.amazonaws.com/de45b98d-980f-4cd4-b9ae-405e2b76b3a9-nara_1.jpeg"),
(3, "2023-06-08 00:37:11", "2023-06-08 00:37:11", 26, "tksgk2598@gmail.com" , "씨앗" ,"산하", "tksgk", 1234, "https://fitsta-bucket.s3.ap-northeast-2.amazonaws.com/57b3f1c6-057b-4170-83aa-5997a0b51ae9-jyj.png"),
(4, "2023-06-08 00:37:11", "2023-06-08 00:37:11", 26, "seyun12312@gmail.com", "씨앗" ,"세윤","seyun", 1234, "https://fitsta-bucket.s3.ap-northeast-2.amazonaws.com/e5ca1fea-61e0-4253-bcba-efeaf0c6877a-%EC%83%88%EC%9C%A4.png");

INSERT INTO post (post_id, created_date, modified_date, user_id, img, content, category, title) VALUES
(1, "2023-06-08 00:37:11", "2023-06-09 00:37:11", 1, "https://fitsta-bucket.s3.ap-northeast-2.amazonaws.com/de45b98d-980f-4cd4-b9ae-405e2b76b3a9-nara_1.jpeg", "안녕하세요~~~~~~~~~~~~~~", "JOB", "안녕하세요"),
(2, "2023-06-08 00:39:13", "2023-06-09 00:39:13", 2, "https://fitsta-bucket.s3.ap-northeast-2.amazonaws.com/de45b98d-980f-4cd4-b9ae-405e2b76b3a9-nara_1.jpeg", "좋은 아침입니다 ~~~~~~~~~~~~~~~~~~~~", "CULTURE", "좋은 아침입니다 ~"),
(3, "2023-06-08 00:41:45", "2023-06-09 00:41:45", 3, "https://fitsta-bucket.s3.ap-northeast-2.amazonaws.com/de45b98d-980f-4cd4-b9ae-405e2b76b3a9-nara_1.jpeg", "날씨가 너무 좋아요~~~~~~~~~~~~~~~~~~~~~~~~~~~~", "HEALTH", "날씨가 너무 좋아요"),
(4, "2023-06-08 00:42:51", "2023-06-09 00:42:51", 4, "https://fitsta-bucket.s3.ap-northeast-2.amazonaws.com/de45b98d-980f-4cd4-b9ae-405e2b76b3a9-nara_1.jpeg", "딱 퇴근하고 싶은 날씨 !!!!!!!!!!!!!!!?", "COMMUNICATION", "딱 퇴근하고 싶은 날씨 !");

INSERT INTO comment (comment_id, created_date, modified_date, content, user_id, post_id)
VALUES
(1,"2023-06-08 00:37:11", "2023-06-09 00:37:11", "좋습니다", 2, 1),
(2,"2023-06-08 00:39:11", "2023-06-09 00:39:11", "최고입니다", 3, 1),
(3,"2023-06-08 00:40:11", "2023-06-09 00:40:11", "굿~~", 4, 1),
(4,"2023-06-08 00:37:11", "2023-06-09 00:37:11", "좋습니다22", 1, 1);
