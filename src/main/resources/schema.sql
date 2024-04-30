CREATE TABLE dice(
	id  int NOT NULL AUTO_INCREMENT,
	diceNumber  int NOT NULL,
    diceFaces   int NOT NULL,
    diceTotal   int NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE roll (
    id int AUTO_INCREMENT PRIMARY KEY,
    rollNumber int,
    rollValue int
)