SELECT * FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'SEQ_TODOS'

INSERT INTO TODOS
(
	TNO
	,TITLE 
	,DUEDATE 
	,WRITER 
	,INDONE 
)
VALUES
(
	SEQ_TODOS.NEXTVAL
	,'피자먹기'
	,TO_DATE('2024-06-13 21:00:00' , 'YYYY-MM-DD HH24:MI:SS')
	,'홍길동'
	,0
);


SELECT 
	TNO
	,TITLE
	,DUEDATE
	,WRITER
	,INDONE
FROM TODOS;

SELECT * FROM TODOS WHERE TNO = 1;

UPDATE TODOS SET
	TITLE = '피자두조각 먹기'
	,INDONE = 1
WHERE TNO = 3;

DELETE FROM TODOS WHERE TNO = 5;

SELECT SEQ_TODOS.CURRVAL FROM DUAL;

SELECT LAST_NUMBER  FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'SEQ_TODOS';

SELECT * FROM BOARD;


UPDATE board SET 	 
	writer_mid = 153;
	

SELECT DISTINCT 
       b.BNO
     , b.TITLE 
    , b.CONTENT
    , b.CREATE_DATE 
    , b.MODIFY_DATE 
    , b.WRITER_MID 
  FROM BOARD b
  LEFT OUTER JOIN MEMBER m ON b.WRITER_MID = m.MID 
  LEFT OUTER JOIN REPLY r ON b.BNO = r.BOARD_BNO 
 WHERE b.title LIKE '%123%'
    OR b.content LIKE '%123%'
    OR r.content LIKE '%123%' 
    
    
    







