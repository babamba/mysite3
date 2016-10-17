
drop SEQUENCE user_seq;

create sequence user_seq
start with 1
increment by 1
maxvalue 9999999999;

drop SEQUENCE guestbook_seq;

create sequence guestbook_seq
start with 1
increment by 1
maxvalue 9999999999;


drop SEQUENCE board_seq;

create sequence board_seq
start with 1
increment by 1
maxvalue 9999999999;