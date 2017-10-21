-- -----------------------------------------------------
-- Schema seoulDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `seoulDB` DEFAULT CHARACTER SET utf8 ;
USE `seoulDB`;

-- -----------------------------------------------------
-- Table `seoulDB`.`concern`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `seoulDB`.`concern` (
  `cno` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cno`))
ENGINE = InnoDB;

INSERT INTO concern (name) VALUES ('영화');
INSERT INTO concern (name) VALUES ('레저/스포츠');
INSERT INTO concern (name) VALUES ('봉사활동');
INSERT INTO concern (name) VALUES ('등산');
INSERT INTO concern (name) VALUES ('스터디/자기계발');
INSERT INTO concern (name) VALUES ('음악/악기');
INSERT INTO concern (name) VALUES ('사진/카메라');
INSERT INTO concern (name) VALUES ('요리/레시피');
INSERT INTO concern (name) VALUES ('여행/캠핑');
INSERT INTO concern (name) VALUES ('전공/커리어');
INSERT INTO concern (name) VALUES ('책/독서');
INSERT INTO concern (name) VALUES ('자전거');
INSERT INTO concern (name) VALUES ('사교/인맥');
INSERT INTO concern (name) VALUES ('공연/전시');
INSERT INTO concern (name) VALUES ('낚시');
INSERT INTO concern (name) VALUES ('여성');
INSERT INTO concern (name) VALUES ('남성');
INSERT INTO concern (name) VALUES ('차/오토바이');
INSERT INTO concern (name) VALUES ('패션/쇼핑');
INSERT INTO concern (name) VALUES ('리빙/라이프');
INSERT INTO concern (name) VALUES ('기타');

-- -----------------------------------------------------
-- Table `seoulDB`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `seoulDB`.`user` (
  `uno` INT NOT NULL AUTO_INCREMENT,
  `id` VARCHAR(45) NOT NULL,
  `pwd` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `nickName` VARCHAR(45) NOT NULL,
  `sex` INT NOT NULL COMMENT '1은 남자, 2는 여자',
  `introduce` TINYTEXT NULL,
  `birth` TIMESTAMP NOT NULL,
  `storedFolder` VARCHAR(8) NULL,
  `storedFile` VARCHAR(22) NULL,
  `originalFileName` VARCHAR(260) NULL,
  `uploadDate` TIMESTAMP NULL ON UPDATE now(),
  `fileSize` INT NULL,
  `location` VARCHAR(100) NOT NULL,
  `concern` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`uno`))
ENGINE = InnoDB;

-- 유저 test data
INSERT INTO user(id, pwd, name, nickName, sex, introduce, birth, location, concern) 
VALUES ('hrnoh24', 'passwd', '노형래', '곰', 1, '안녕하세요 노형래입니다', now(), '강서구, 어쩌구, 저쩌구', '여행, 스터디, 영화');

-- -----------------------------------------------------
-- Table `seoulDB`.`club`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `seoulDB`.`club` (
  `cno` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `introduce` TINYTEXT NOT NULL,
  `description` TEXT NULL,
  `currentpeople` INT NOT NULL DEFAULT 0,
  `maxpeople` INT NOT NULL,
  `isadmission` TINYINT NOT NULL DEFAULT 0,
  `isprivate` TINYINT NOT NULL DEFAULT 0,
  `storedFolder` VARCHAR(8) NULL,
  `storedFile` VARCHAR(22) NULL,
  `originalFileName` VARCHAR(260) NULL,
  `uploadDate` TIMESTAMP NULL ON UPDATE now(),
  `fileSize` INT NULL,
  `location` VARCHAR(100) NOT NULL,
  `concern` VARCHAR(100) NOT NULL,
  `uno` INT NOT NULL,
  PRIMARY KEY (`cno`, `uno`),
  INDEX `fk_club_user1_idx` (`uno` ASC),
  CONSTRAINT `fk_club_user1`
    FOREIGN KEY (`uno`)
    REFERENCES `seoulDB`.`user` (`uno`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO club (name, introduce, description, maxpeople, location, concern, uno) 
VALUES ('앱공모전', '앱공모전 준비를 위한 모임', '안녕하세요 앱공모전 준비를 위한 어쩌구 저쩌구', 5, '강서구', '여행, 레저, 스포츠', 1);

-- -----------------------------------------------------
-- Table `seoulDB`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `seoulDB`.`board` (
  `bno` INT NOT NULL AUTO_INCREMENT,
  `isnotice` TINYINT NOT NULL DEFAULT 0,
  `title` VARCHAR(45) NOT NULL,
  `content` TEXT NOT NULL,
  `writer` VARCHAR(100) NOT NULL,
  `cno` INT NOT NULL,
  PRIMARY KEY (`bno`, `cno`),
  INDEX `fk_board_club1_idx` (`cno` ASC),
  CONSTRAINT `fk_board_club1`
    FOREIGN KEY (`cno`)
    REFERENCES `seoulDB`.`club` (`cno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO board (isnotice, title, content, writer, cno)
VALUES (0, '가입인사', '안녕하세요 오늘 가입했어요', '노형래', 1);

-- -----------------------------------------------------
-- Table `seoulDB`.`attend`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `seoulDB`.`attend` (
  `uno` INT NOT NULL,
  `cno` INT NOT NULL,
  `level` VARCHAR(10) NOT NULL DEFAULT '회원',
  `status` VARCHAR(10) NOT NULL DEFAULT '신청',
  PRIMARY KEY (`uno`, `cno`),
  INDEX `fk_user_has_club_club1_idx` (`cno` ASC),
  INDEX `fk_user_has_club_user1_idx` (`uno` ASC),
  CONSTRAINT `fk_user_has_club_user1`
    FOREIGN KEY (`uno`)
    REFERENCES `seoulDB`.`user` (`uno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_club_club1`
    FOREIGN KEY (`cno`)
    REFERENCES `seoulDB`.`club` (`cno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO attend(uno, cno, level, status) VALUES (1, 1, '회원', '신청');

-- -----------------------------------------------------
-- Table `seoulDB`.`timeline`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `seoulDB`.`timeline` (
  `tno` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `content` TEXT NOT NULL,
  `regDate` TIMESTAMP NULL DEFAULT now(),
  `modDate` TIMESTAMP NULL DEFAULT now() ON UPDATE now(),
  `writer` VARCHAR(100) NOT NULL,
  `cno` INT NOT NULL,
  PRIMARY KEY (`tno`, `cno`),
  INDEX `fk_timeline_club1_idx` (`cno` ASC),
  CONSTRAINT `fk_timeline_club1`
    FOREIGN KEY (`cno`)
    REFERENCES `seoulDB`.`club` (`cno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO timeline (title, content, writer, cno)
VALUES ('가입인사', '안녕하세요 오늘 가입했어요', '노형래', 1);

-- -----------------------------------------------------
-- 회원 가입
-- -----------------------------------------------------
-- 관심사 리스트
SELECT * FROM concern;
-- 아이디 중복 체크
SELECT COUNT(id) from user where id='hrnoh24';
-- 회원 등록
INSERT INTO user(id, pwd, name, nickName, sex, introduce, birth, storedFolder, storedFile, originalFileName, uploadDate, fileSize, location, concern) 
VALUES (`#{id}`, `#{pwd}`, `#{name}`, `#{nickName}`, `#{sex}`, `#{introduce}`, `#{birth}`, `#{storedFolder}`, `#{storedFile}`, `#{originalFileName}`, now(), `#{fileSize}`, `#{location}`, `#{concern}`);

-- -----------------------------------------------------
-- 전체 모임
-- -----------------------------------------------------
-- 모임 리스트
SELECT 
	club.cno, 
	club.name, 
    club.introduce, 
    club.currentpeople, 
    club.maxpeople, 
    club.location, 
    club.concern,
    club.storedFolder,
    club.storedFile,
    CONCAT("/photo/", club.storedFolder, "/", club.storedFile) as imgurl
FROM club
JOIN timeline ON club.cno = timeline.cno
ORDER BY timeline.regDate DESC
LIMIT 30;

-- -----------------------------------------------------
-- 관심사, 지역별 모임
-- -----------------------------------------------------
SELECT
	cno,
    name,
    introduce,
    currentpeople,
    maxpeople,
    location,
    concern,
    storedFolder,
    storedFile,
    CONCAT("/photo/", club.storedFolder, "/", club.storedFile) as imgurl
FROM club
WHERE location like CONCAT("%","강서구","%") OR concern like CONCAT("%", "여행", "%")
ORDER BY name ASC
LIMIT 30;
-- -----------------------------------------------------
-- 내 모임
-- -----------------------------------------------------
SELECT
	cno,
    name,
    introduce,
    currentpeople,
    maxpeople,
    location,
    concern,
    storedFolder,
    storedFile,
    CONCAT("/photo/", club.storedFolder, "/", club.storedFile) as imgurl
FROM club
WHERE uno = `#{uno}`
ORDER BY name ASC;

-- -----------------------------------------------------
-- 내 모임
-- -----------------------------------------------------
INSERT INTO club(
	name, 
    introduce, 
    description, 
    maxpeople, 
    isadmission, 
    isprivate, 
    storedFolder, 
    storedFile, 
    originalFileName, 
    fileSize, 
    location, 
    concern, 
    uno)
VALUES (
    `#{name}`,
    `#{introduce}`,
    `#{description}`,
    `#{maxpeople}`,
    `#{isadmission}`,
    `#{isprivate}`,
    `#{storedFolder}`,
    `#{storedFile}`,
    `#{originalFileName}`,
    `#{fileSize}`,
    `#{location}`,
    `#{concern}`,
    `#{uno}`
    );