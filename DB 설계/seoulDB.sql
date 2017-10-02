CREATE SCHEMA IF NOT EXISTS `seouldb` DEFAULT CHARACTER SET utf8;
USE seouldb;

-- 거주지
CREATE TABLE IF NOT EXISTS location (
	lno INT NOT NULL AUTO_INCREMENT,
    lname VARCHAR(45) NOT NULL,
    PRIMARY KEY(lno)
);

INSERT INTO location (lname) VALUES ('강남구');
INSERT INTO location (lname) VALUES ('강동구');
INSERT INTO location (lname) VALUES ('강북구');
INSERT INTO location (lname) VALUES ('강서구');
INSERT INTO location (lname) VALUES ('관악구');
INSERT INTO location (lname) VALUES ('광진구');
INSERT INTO location (lname) VALUES ('구로구');
INSERT INTO location (lname) VALUES ('금천구');
INSERT INTO location (lname) VALUES ('노원구');
INSERT INTO location (lname) VALUES ('도봉구');
INSERT INTO location (lname) VALUES ('동대문구');
INSERT INTO location (lname) VALUES ('동작구');
INSERT INTO location (lname) VALUES ('마포구');
INSERT INTO location (lname) VALUES ('서대문구');
INSERT INTO location (lname) VALUES ('서초구');
INSERT INTO location (lname) VALUES ('성동구');
INSERT INTO location (lname) VALUES ('성북구');
INSERT INTO location (lname) VALUES ('송파구');
INSERT INTO location (lname) VALUES ('양천구');
INSERT INTO location (lname) VALUES ('영등포구');
INSERT INTO location (lname) VALUES ('용산구');
INSERT INTO location (lname) VALUES ('은평구');
INSERT INTO location (lname) VALUES ('종로구');
INSERT INTO location (lname) VALUES ('중구');
INSERT INTO location (lname) VALUES ('중랑구');


-- 관심사
CREATE TABLE IF NOT EXISTS concern (
	cno INT NOT NULL AUTO_INCREMENT,
    cname VARCHAR(45) NOT NULL,
    PRIMARY KEY(cno)
);

INSERT INTO concern (cname) VALUES ('영화');
INSERT INTO concern (cname) VALUES ('레저/스포츠');
INSERT INTO concern (cname) VALUES ('봉사활동');
INSERT INTO concern (cname) VALUES ('등산');
INSERT INTO concern (cname) VALUES ('스터디/자기계발');
INSERT INTO concern (cname) VALUES ('음악/악기');
INSERT INTO concern (cname) VALUES ('사진/카메라');
INSERT INTO concern (cname) VALUES ('요리/레시피');
INSERT INTO concern (cname) VALUES ('여행/캠핑');
INSERT INTO concern (cname) VALUES ('전공/커리어');
INSERT INTO concern (cname) VALUES ('책/독서');
INSERT INTO concern (cname) VALUES ('자전거');
INSERT INTO concern (cname) VALUES ('사교/인맥');
INSERT INTO concern (cname) VALUES ('공연/전시');
INSERT INTO concern (cname) VALUES ('낚시');
INSERT INTO concern (cname) VALUES ('여성');
INSERT INTO concern (cname) VALUES ('남성');
INSERT INTO concern (cname) VALUES ('차/오토바이');
INSERT INTO concern (cname) VALUES ('패션/쇼핑');
INSERT INTO concern (cname) VALUES ('리빙/라이프');
INSERT INTO concern (cname) VALUES ('기타');

-- 유저
CREATE TABLE IF NOT EXISTS user (
	uno INT NOT NULL AUTO_INCREMENT,
    id VARCHAR(45) NOT NULL,
    pwd VARCHAR(45) NOT NULL,
    name VARCHAR(45) NOT NULL,
    nickName VARCHAR(45) NOT NULL,
    sex VARCHAR(5) NOT NULL,
    introduce TINYTEXT,
    birth VARCHAR(45) NOT NULL,
    picturePath VARCHAR(45),
    lno INT NOT NULL,
    PRIMARY KEY(uno),
    CONSTRAINT fk_lno FOREIGN KEY (lno) REFERENCES location (lno) ON DELETE CASCADE
);

-- 유저 test data
INSERT INTO user(id, pwd, name, nickName, sex, birth, lno) 
VALUES ('hrnoh24', 'passwd', '노형래', '곰', '남', now(), 1);

-- 모임
CREATE TABLE IF NOT EXISTS club (
	cno INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    description TEXT,
    introduce TINYTEXT,
    picturePath VARCHAR(45),
    maxPeople INT NOT NULL,
    isprivate TINYINT NOT NULL DEFAULT 0,
    isadmission TINYINT NOT NULL DEFAULT 0,
    lno INT NOT NULL,
    concern INT NOT NULL,
    PRIMARY KEY(cno),
    CONSTRAINT fk_lno_club FOREIGN KEY (lno) REFERENCES location (lno) ON DELETE CASCADE,
    CONSTRAINT fk_concern FOREIGN KEY (concern) REFERENCES concern (cno) ON DELETE CASCADE
);

INSERT INTO club (name, maxPeople, lno, concern) 
VALUES ('앱공모전', 5, 1, 5);

-- 모임에 참여를 나타냄
CREATE TABLE IF NOT EXISTS belongs (
	uno INT NOT NULL,
    cno INT NOT NULL,
    position VARCHAR(45) NOT NULL,
    CONSTRAINT fk_uno_belongs FOREIGN KEY (uno) REFERENCES user (uno) ON DELETE CASCADE,
    CONSTRAINT fk_cno_belongs FOREIGN KEY (cno) REFERENCES club (cno) ON DELETE CASCADE
);

-- 모임 참여 test data
INSERT INTO belongs (uno, cno, position)
VALUES (1, 1, '모임장');

-- 유저의 관심사
CREATE TABLE IF NOT EXISTS includes (
	uno INT NOT NULL,
    cno INT NOT NULL,
    CONSTRAINT fk_uno_includes FOREIGN KEY (uno) REFERENCES user (lno) ON DELETE CASCADE,
    CONSTRAINT fk_cno_includes FOREIGN KEY (cno) REFERENCES concern (cno) ON DELETE CASCADE
);

-- 유저 관심사 test data
INSERT INTO includes (uno, cno) VALUES (1, 1);
INSERT INTO includes (uno, cno) VALUES (1, 5);
INSERT INTO includes (uno, cno) VALUES (1, 6);

-- 모임 게시판
CREATE TABLE IF NOT EXISTS board (
	bno INT NOT NULL AUTO_INCREMENT,
    isnotice TINYINT DEFAULT 0,
    title VARCHAR(45) NOT NULL,
    content TEXT NOT NULL,
    writer VARCHAR(45) NOT NULL,
    regdate TIMESTAMP NOT NULL DEFAULT now(),
    moddate TIMESTAMP NOT NULL DEFAULT now(),
    cno INT NOT NULL,
    PRIMARY KEY(bno),
    CONSTRAINT fk_cno_board FOREIGN KEY (cno) REFERENCES club (cno) ON DELETE CASCADE
);

-- 모임 게시판 test data
INSERT INTO board (title, content, writer, cno) VALUES ('가입인사입니다', '안녕하세요 ㅇㅇㅇ입니다.', 'hrnoh24', 1);