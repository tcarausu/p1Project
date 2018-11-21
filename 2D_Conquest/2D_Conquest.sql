create schema p1project;

create table p1project.admin (
  username varchar(255) unique,
  password varchar(255) unique,
  primary key (username, password)
);

create table p1project.user (
  username varchar(255) unique,
  password varchar(255) unique,
  primary key (username, password)
);

create table p1project.difficulty (
  difficulty varchar(6) CONSTRAINT onlyDifficulties CHECK
  (difficulty IN ('easy', 'hard', 'medium'))unique
);

create table p1project.highscore (
  --   usernameOfAdmin      varchar(255) references p1project.Admin (username) ,  !!!!!!!!!!!! TO BE DISCUSSED
  usernameOfPlayer      varchar(255) references p1project.user (username),
  timespent             int,
  nrOfQuestionsAnswered int,
  nrOfQuestionsTotal    int,
  difficultylevel       varchar(6) references p1project.difficulty (difficulty),
  score                 int,
  primary key (usernameOfPlayer, difficultylevel, score)
);

create table p1project.questions (
  subject         varchar(255),
  correctanswer   varchar(255),
  -- add all questions(bad and good answers)
  typeOfQuestion  varchar(12) CONSTRAINT typeOfQuestions CHECK
  (typeOfQuestion IN ('history', 'culture', 'civil','literature')), -- all types of question we are going to use
  difficultylevel varchar(6) references p1project.difficulty (difficulty),
  region          varchar(25), -- add real life regions
  primary key (subject, correctanswer, difficultylevel)
);
create table p1project.answer (
  id serial,
  question        varchar(255),
  correct         varchar(255) unique,
  difficultylevel varchar(6) references p1project.difficulty (difficulty),
  primary key (question, correct, difficultylevel)
);

create table p1project.questions_answers(
  questionsId int,
  answersId int  ,
  correctAnswer varchar(255) references p1project.answer(correct),
  primary key (questionsId,answersId,correctAnswer)
)
--   create constraint for both correct answer referring to ANSWER table and appropriate question to QUESTION table
