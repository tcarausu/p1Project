create schema p1project;

create table p1project.admin (
  username varchar(255),
  password varchar(255),
  primary key (username, password)
);

create table p1project.user (
  username varchar(255),
  password varchar(255),
  primary key (username, password)
);

create table p1project.difficulty(
  difficulty varchar(6) CONSTRAINT onlyDifficulties CHECK (difficulty IN ('easy', 'hard','medium'))
);