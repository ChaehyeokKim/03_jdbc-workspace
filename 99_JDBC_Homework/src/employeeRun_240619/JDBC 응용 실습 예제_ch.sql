CREATE TABLE BOOK (
    BNO NUMBER PRIMARY KEY,
    TITLE VARCHAR2(180) NOT NULL,
    AUTHOR VARCHAR2(100) NOT NULL,
    PUBLISHER VARCHAR(180) NOT NULL,
    PRICE NUMBER,
    DESCRIPTION VARCHAR(180)    
);

CREATE TABLE MAGAZINE (
BNO NUMBER PRIMARY KEY,
    TITLE VARCHAR2(180) NOT NULL,
    AUTHOR VARCHAR2(100) NOT NULL,
    PUBLISHER VARCHAR(180) NOT NULL,
    PRICE NUMBER,
    DESCRIPTION VARCHAR(180),
    PUB_DATE DATE
);

CREATE SEQUENCE SEQ_BNO START WITH 100
NOCACHE;

INSERT INTO BOOK (SEQ_BNO, '잃어버린 동규를 찾아서', '김민 박사', '상우 출판사', 8500, '『서경스』, 『르 몽드』 선정 20세기 최고의 책');
INSERT INTO BOOK (SEQ_BNO, '종열이의 자서전', '지영이가 옮김', '빛나는 진아출판사', 8500, '영화배우 김채혁이 추천한 젊음에 바치는 영혼의 자서전');
INSERT INTO BOOK (SEQ_BNO, '명식이의 코딩스쿨", "코딩천재 유현동", "기훈이네 동네", 9900, "제 13회 미예가 주는 문학상 수상작');
INSERT INTO BOOK (SEQ_BNO, '이한별의 미라클모닝', '정민식 박사', '지운이네 출판사', 6300, '택은이도 2번 성공한 미라클모닝');
INSERT INTO BOOK (SEQ_BNO, '윤경이의 백만가지 요리레시피', '서울에서 온 규호', '집밥최선생 최승균 출판사', 6300, '아이돌 황영찬이 적극 추천한 최고의 레시피');

-----------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO BOOK (SEQ_BNO, '김태경 과학동아', '편집부 김희섭 선생', '경원 사이언스', 8500, '광현 유튜브에 소개된 과학만화 잡지', '24-05');
INSERT INTO BOOK (SEQ_BNO, '승헌이의 여행일기', '은지쓰', '씨네21', 3800, '영화감독 이종호 집필한 영화 전문 잡지', '21-O4'); 
INSERT INTO BOOK (SEQ_BNO,, '이주영의 데일리룩', '인플루언서 이준영', '태형이네 책방', 12000, '핫걸 김시연의 샤라웃을 받은 잡지', '24-10');