
show tables;

create table students(
	studentID varchar(50) primary key,
    studentName varchar(50) not null,
    studentPhone varchar(50) unique,
    studentPassword varchar(50) not null
)Engine = InnoDB;

alter table students modify studentPhone varchar(50) not null;

describe students;

create table admins (
	adminID varchar(50) primary key,
    adminName varchar(50) not null,
    adminPhone varchar(50) unique,
    adminPassword varchar(50) not null
) engine = InnoDB;
alter table admins modify adminPhone varchar(50) not null;

describe admins;

create table books(
	bookID Int primary key auto_increment,
    title varchar(50) not null,
    author varchar(50),
    year int,
    category varchar(50),
    page int,
    quality Enum('old', 'meduim', 'new', 'unreadable'),
    bookshelf varchar(50),
    quantity int
) engine InnoDB;

alter table books add remain int;
describe books;

create table borrowlist(
	bookID int,
    foreign key bID_fk (bookID) references books(bookID),
    borrower varchar(50),
    foreign key sID_fk (borrower) references students(studentID),
    borrowDate date default (current_date),
    returnDate date,
    isReturned Enum('1','0') default '0'
) engine = InnoDB;

describe borrowlist;

describe students;
select * from students;
insert into students value ("e20200469", "VEN Dara", "0963180249", "123");
insert into students value ("e20200123", "Jonh Jambone", "012345678", "123");
insert into students value ("e20200345", "Uzumaki Naruto", "09424380249", "123");
