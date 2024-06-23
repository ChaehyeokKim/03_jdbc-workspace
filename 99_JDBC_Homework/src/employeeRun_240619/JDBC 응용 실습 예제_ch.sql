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

INSERT INTO BOOK (SEQ_BNO, '�Ҿ���� ���Ը� ã�Ƽ�', '��� �ڻ�', '��� ���ǻ�', 8500, '�����潺��, ���� ���塻 ���� 20���� �ְ��� å');
INSERT INTO BOOK (SEQ_BNO, '�������� �ڼ���', '�����̰� �ű�', '������ �������ǻ�', 8500, '��ȭ��� ��ä���� ��õ�� ������ ��ġ�� ��ȥ�� �ڼ���');
INSERT INTO BOOK (SEQ_BNO, '������� �ڵ�����", "�ڵ�õ�� ������", "�����̳� ����", 9900, "�� 13ȸ �̿��� �ִ� ���л� ������');
INSERT INTO BOOK (SEQ_BNO, '���Ѻ��� �̶�Ŭ���', '���ν� �ڻ�', '�����̳� ���ǻ�', 6300, '�����̵� 2�� ������ �̶�Ŭ���');
INSERT INTO BOOK (SEQ_BNO, '�������� �鸸���� �丮������', '���￡�� �� ��ȣ', '�����ּ��� �ֽ±� ���ǻ�', 6300, '���̵� Ȳ������ ���� ��õ�� �ְ��� ������');

-----------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO BOOK (SEQ_BNO, '���°� ���е���', '������ ���� ����', '��� ���̾�', 8500, '���� ��Ʃ�꿡 �Ұ��� ���и�ȭ ����', '24-05');
INSERT INTO BOOK (SEQ_BNO, '�������� �����ϱ�', '������', '����21', 3800, '��ȭ���� ����ȣ ������ ��ȭ ���� ����', '21-O4'); 
INSERT INTO BOOK (SEQ_BNO,, '���ֿ��� ���ϸ���', '���÷�� ���ؿ�', '�����̳� å��', 12000, '�ְ� ��ÿ��� ������� ���� ����', '24-10');