

--board

--view
select no, title, content from board where no = 2; --내용보여주서 뽑아내고 
update board set hit = hit + 1 where no = 2; --뽑아낸거 업데이트로 조회수 늘리기

--list 
select count(*) from board;

select *
from
(select no, title, hit, depth, reg_date, name, users_no, rownum as rn
 from( select a.no,
	    	  a.title, 
	   		  a.hit, 
	  		  to_char(a.reg_date, 'yyyy-mm-dd hh:mi:ss') as reg_date,
	   		  b.name,
			  a.users_no,
			  a.depth
    	 from board a, users b
  	 	where a.users_no = b.no
	
	 order by group_no desc, order_no asc))
where (1-1)*5+1 <= rn 
  and rn <= 1*5;
   --   and title like '%kwd%' or content like '%kwd%' --키워드 서치인 경우
  
--1은 current page현재 페이지 // 현재 페이지가 1이면 // 페이지 사이즈가 2인 경우  // 5가 페이지 사이즈
select * from board;
-- 원글은 group_no 내림차순 답글은 원글 그룹내 order_no 올림차순; 

--board 
select max(group_no) from board;


--insert1 (새글)
insert
 into board
 values(board_seq.nextval,'안녕' , '안녕', sysdate, 0,
		nvl((select max(group_no) from board),0) + 1, 1, 0, 2);

insert
 into board
 values(board_seq.nextval,'배고프다 그만해' , '냉무', sysdate, 0,
		nvl((select max(group_no) from board),0) + 1, 1, 0, 2);

--insert2(답글이 들어갈 때 )
update board
   set order_no = order_no + 1
 where group_no = 2
   and order_no > 1; --부모 글 순서

select*from users;

insert
 into board
 values(board_seq.nextval,
 		'짜장' , '냉무', sysdate, 0,
		2, --부모글의 그룹 
		2, --부모글의 오더(순서)에 +1
		1, --부모글 깊이에 		+1
		2);


select * from board;

commit;
