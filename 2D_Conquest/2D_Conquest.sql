create schema p1project;

create table p1project.admin
(
  id       serial,
  username varchar(255),
  password varchar(255),
  primary key (id, username, password)
);

insert into p1project.admin
values (default, 'admin', 'admin');


create table p1project.user
(
  id       serial,
  username varchar(255) unique,
  password varchar(255),
  primary key (id, username, password)
);

create table p1project.difficulty
(
  difficulty varchar(6)
    CONSTRAINT onlyDifficulties CHECK
      (difficulty IN ('easy', 'hard', 'medium')) primary key
);

insert into p1project.difficulty
values ('easy'),
       ('medium'),
       ('hard');

create table p1project.questions
(
  id              serial unique,
  subject         varchar(255),
  typeOfQuestion  varchar(12)
    CONSTRAINT typeOfQuestions CHECK
      (typeOfQuestion IN ('history', 'culture', 'civil', 'literature', 'economics', 'geography')),
  difficultyLevel varchar(6) references p1project.difficulty (difficulty),
  region          varchar(25)
    CONSTRAINT denmarkRegion CHECK
      (region IN ('capital', lower('midDenmark'), lower('northDenmark'), lower('southDenmark'))) not null,
  primary key (id, subject, typeOfQuestion, difficultyLevel)
);
create table p1project.answer
(
  id              serial unique,
  givenAnswer     varchar(255),
  difficultyLevel varchar(6) references p1project.difficulty (difficulty),
  primary key (id, givenAnswer, difficultyLevel)
);

create table p1project.questions_answers
(
  questionId       int references p1project.questions (id),
  answersId        int references p1project.answer (id),
  validityOfAnswer bool default false,
  primary key (questionId, answersId, validityOfAnswer)
);


create table p1project.highScore
(
  usernameOfPlayer      varchar(255) references p1project.user (username),
  timeSpent             int,
  nrOfQuestionsAnswered int,
  nrOfQuestionsTotal    int,
  difficultyLevel       varchar(6) references p1project.difficulty (difficulty),
  score                 int,
  primary key (usernameOfPlayer, timeSpent, difficultyLevel, score)
);
