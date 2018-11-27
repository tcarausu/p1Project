create schema p1project;

create table p1project.admin (
                               id       serial,
                               username varchar(255),
                               password varchar(255),
                               primary key (id, username, password)
);

insert into p1project.admin
values (default ,'admin1', 'admin');


create table p1project.user (
                              id       serial,
                              username varchar(255) unique,
                              password varchar(255),
                              primary key (id, username, password)
);

create table p1project.difficulty (
  difficulty varchar(6) CONSTRAINT onlyDifficulties CHECK
    (difficulty IN ('easy', 'hard', 'medium')) primary key
);

insert into p1project.difficulty
values ('easy'),
       ('medium'),
       ('hard');

create table p1project.questions (
                                   id              serial unique,
                                   subject         varchar(255),
                                   typeOfQuestion  varchar(12) CONSTRAINT typeOfQuestions CHECK
                                     (typeOfQuestion IN ('history', 'culture', 'civil', 'literature')),
  -- all types of question we are going to use /
  -- economics and geography
                                   difficultylevel varchar(6) references p1project.difficulty (difficulty),
                                   region          varchar(25) CONSTRAINT denmarkRegion CHECK
                                     (region IN ('capital', 'midtjuland', 'nordjyland', 'southDenmark', ' zealand')), -- add real life regions
  --non null

                                   primary key (id, subject, typeOfQuestion, difficultylevel)
);
create table p1project.answer (
                                id              serial unique,
                                givenAnswer     varchar(255),
                                difficultylevel varchar(6) references p1project.difficulty (difficulty),
                                primary key (id, givenAnswer, difficultylevel)
);

create table p1project.questions_answers (
                                           questionsId    int references p1project.questions (id),
                                           answersId      int references p1project.answer (id),
                                           valityOfAnswer bool , --default false
                                           primary key (questionsId, answersId, valityOfAnswer)
);


create table p1project.highscore (
                                   usernameOfPlayer      varchar(255) references p1project.user (username),
                                   timespent             int,
                                   nrOfQuestionsAnswered int,
                                   nrOfQuestionsTotal    int,
                                   difficultylevel       varchar(6) references p1project.difficulty (difficulty),
                                   score                 int,
                                   primary key (usernameOfPlayer, difficultylevel, score)
);
--   create constraint for both correct answer referring to ANSWER table and appropriate question to QUESTION table
