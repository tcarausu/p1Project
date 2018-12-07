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

insert into p1project.user
values (default, 'admin', 'admin');


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
      (region IN ('capital', 'midDenmark', 'northDenmark', 'southDenmark')) not null,
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
  id                    serial primary key ,
  usernameOfPlayer      varchar(255) references p1project.user (username),
  timeSpent             int,
  nrOfQuestionsAnswered int,
  nrOfQuestionsTotal    int,
  difficultyLevel       varchar(6) references p1project.difficulty (difficulty),
  score                 int
);
insert into p1project.questions
values
 (default,'Where was Hans Christian Andersen born?','history','medium','southDenmark'),
       (default,'When was Hans Christian Andersen born?','history','medium','southDenmark'),
       (default,'Where in Copenhagen is The Little Mermaid statue located?','geography','easy','capital'),
      (default, 'The Little Mermaid statue is based off a book of the same name. Who is the author of that book?', 'history', 'easy', 'capital'),
(default,'When was The Little Mermaid statue erected?','history','medium','capital'),
(default,'Who commissioned The Little Mermaid statue?','history','hard','capital'),
(default,'Who created The Little Mermaid statue?','history','medium','capital'),
(default,'Who modeled the pose of The Little Mermaid statue?','history','hard','capital'),
(default,'Han Christian Andersen has written a multitude of stories. It is documented that he’s written over…?','history','easy','southDenmark'),
(default,'What was the first tale written by Andersen?','history','hard','southDenmark'),
(default,'Where is Hans Christian Andersen’s gravestone located?','geography','easy','capital'),
(default,'Pop Culture phenomenon, Frozen, by Disney, was initially inspired by which Andersen story?','history','medium','southDenmark'),
(default,'Hans Christian Andersen’s birthday is celebrated on which holiday?','history','easy','capital'),
(default,'What year did Andersen pass away?','history','easy','capital'),
(default,'Funen connects to which regions?','geography','easy','southDenmark'),
(default,'Funen is connected to Jutland by which bridge?','geography','easy','southDenmark'),
(default,'Funen has a population of…?','geography','medium','southDenmark'),
(default,'Aalborg University was founded in what region?','geography','easy','northDenmark'),
(default,'The largest city of Jutland is…?','geography','easy','northDenmark'),
(default,'Jutland is comprised of a large territory and is also part of which country outside of Denmark?','geography','medium','southDenmark'),
(default,'What is the largest bridge in Denmark?','geography','easy','southDenmark'),
(default,'Norreport was originally known as…? ','history','medium','capital'),
(default,'DSB stands for…?','economics','easy','capital'),
(default,'DSB’s commuter system comprises of…?','economics','easy','capital'),
(default,'When was DSB founded?','history','medium','capital'),
(default,'Statistically, how many inhabitants of Copenhagen use a bicycle?','culture','easy','capital'),
(default,'Legally, who has the highest right of way(priority) in Danish traffic?','culture','easy','capital'),
(default,'Who is the PM of Denmark?','civil','easy','capital'),
(default,'The PM of Denmark belongs to which political party?','civil','easy','capital'),
(default,'What is the Danish parliament referred to as?','civil','easy','capital'),
(default,'Who is the Monarch of Denmark?','civil','easy','capital'),
(default,'Who is the Heir Apparent(next Monarch) of Denmark?','civil','medium','capital'),
(default,'How many political parties are involved in Danish parliament?','civil','medium','capital'),
(default,'Which political parties hold the highest majorities?','civil','medium','capital'),
(default,'Denmark is…?','civil','easy','capital'),
(default,'The design of Denmark’s flag was created in…?','history','hard','capital'),
(default,'What is Dannebrog?','history','medium','capital'),
(default,'The Great Belt Fixed Link connects which regions?','geography','medium','capital'),
(default,'The Great Belt Fixed Link connects which regions?','geography','medium','southDenmark'),
(default,'Denmark originally had this country as a part of their own… ','history','medium','capital'),
(default,'Denmark lost the territory of Norway to…?','history','medium','capital'),
(default,'Denmark participated in which of these wars?','history','hard','capital'),
(default,'When did Denmark relinquish territory as a result of the Napoleonic Wars?','history','hard','northDenmark'),
(default,'How many official regions is Denmark split into?','geography','medium','capital'),
(default,'How many official regions is Denmark split into?','geography','medium','southDenmark'),
(default,'How many official regions is Denmark split into?','geography','medium','midDenmark'),
(default,'How many official regions is Denmark split into?','geography','medium','northDenmark'),
(default,'Which political party was founded in 1954?','history','hard','capital'),
(default,'When was the Danish constitution originally written?','history','hard','capital'),
(default,'When was the current constitution put into effect?','history','medium','capital'),
(default,'How does Denmark revise its constitution?','civil','medium','capital');



