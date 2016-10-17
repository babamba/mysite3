--select(login)
select no, name 
from users 
where email = 'babamba@gmail.com'
and password ='1';

select * from users;

UPDATE users SET name =? password = ? gender =? where no=?;

UPDATE users SET name = '멍청이', password = '123', gender = 'male' where no= 4;

	
insert into users values( user_seq.nextval, ?, ?, ?, ?)