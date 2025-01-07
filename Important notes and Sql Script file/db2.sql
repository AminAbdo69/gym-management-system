-- Creation Person Table 
CREATE TABLE Person (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    FName VARCHAR(255),
    LName VARCHAR(255),
    Age INT,
    Phone VARCHAR(15),
    Address VARCHAR(255),
    Gender ENUM('Male', 'Female', 'Other')
);

-- Creation Member Table 
CREATE TABLE Member (
    ID INT PRIMARY KEY,
    AssignedTrainerID INT,
    Weight FLOAT,
    ExercisePlanID INT,
    ScheduleID INT,
    FOREIGN KEY (ID) REFERENCES Person(ID),
    FOREIGN KEY (AssignedTrainerID) REFERENCES Trainer(ID),
    FOREIGN KEY (ExercisePlanID) REFERENCES ExercisePlan(ID),
    FOREIGN KEY (ScheduleID) REFERENCES Schedule(ID)
);
ALTER TABLE member DROP FOREIGN KEY member_ibfk_2;

ALTER TABLE member
ADD CONSTRAINT member_ibfk_2 FOREIGN KEY (AssignedTrainerID) 
REFERENCES trainer(ID) ON DELETE SET NULL;

-- Creation Payments Table 
CREATE TABLE Payments (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    MemberID INT,
    Amount FLOAT,
    PaymentDate DATE,
    DueDate DATE,
    DaysRemaining INT,
    DiscountRate FLOAT,
    Status VARCHAR(50),
    FOREIGN KEY (MemberID) REFERENCES Member(ID)
);


-- Creation Trainer Table 
CREATE TABLE Trainer (
    ID INT PRIMARY KEY,
    Specialist VARCHAR(255),
    FOREIGN KEY (ID) REFERENCES Person(ID)
);

CREATE TABLE Trainer_Customer (
    trainerId INT NOT NULL,
    customerId INT NOT NULL,
    PRIMARY KEY (trainerId, customerId),
    FOREIGN KEY (trainerId) REFERENCES Trainer(ID) ON DELETE CASCADE,
    FOREIGN KEY (customerId) REFERENCES Member(ID) ON DELETE CASCADE
);

INSERT INTO Trainer_Customer (trainerId, customerId)
VALUES (3, 12);


-- Creation GymHall Table 
CREATE TABLE GymHall (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) UNIQUE,
    Capacity INT
);



-- Creation Equipment Table
CREATE TABLE Equipment (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    GymHallID INT,  -- Foreign key to reference GymHall
    Name VARCHAR(255),
    Type VARCHAR(255),
    state VARCHAR(255),
     UNIQUE (GymHallID, Name), 
    FOREIGN KEY (GymHallID) REFERENCES GymHall(ID) ON DELETE CASCADE
);

-- Creation Exercise Table
CREATE TABLE Exercise (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255),
    Sets INT,
    Reps INT
);


-- Creation ExercisePlan Table
CREATE TABLE ExercisePlan (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    PlanName VARCHAR(255),
    Type VARCHAR(255)
);


CREATE TABLE ExercisePlan_Exercise (
    ExerciseID INT NOT NULL,
    ExercisePlanID INT NOT NULL,
    PRIMARY KEY (ExerciseID, ExercisePlanID),
    FOREIGN KEY (ExerciseID) REFERENCES Exercise(ID),
    FOREIGN KEY (ExercisePlanID) REFERENCES ExercisePlan(ID)
);
INSERT INTO ExercisePlan_Exercise (ExerciseID, ExercisePlanID) 
VALUES (1, 1); 

-- Creation Schedule Table
CREATE TABLE Schedule (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Day ENUM('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday') NOT NULL
);

CREATE TABLE Schedule_Exercise (
    ScheduleID INT,              -- Foreign key to reference Schedule
    ExerciseID INT,              -- Foreign key to reference Exercise
    PRIMARY KEY (ScheduleID, ExerciseID),  -- Composite primary key
    FOREIGN KEY (ScheduleID) REFERENCES Schedule(ID) ON DELETE CASCADE,
    FOREIGN KEY (ExerciseID) REFERENCES Exercise(ID) ON DELETE CASCADE
);

-- Assigning Deadlift to Tuesday
INSERT INTO Schedule_Exercise (ScheduleID, ExerciseID) 
VALUES (2, 2);  -- ScheduleID = 2 (Tuesday), ExerciseID = 3 (Deadlift)


Insert into Schedule (Day , ExerciseID) values ("Monday" , 1);

-- Creation DailySchedule Table
-- CREATE TABLE DailySchedule (
--     ID INT PRIMARY KEY AUTO_INCREMENT,
--     Day ENUM('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'),
--     ScheduleID INT,
--     FOREIGN KEY (ScheduleID) REFERENCES Schedule(ID)
-- );


-- Insert Some Data

INSERT INTO Person (FName, LName, Age, Phone, Address, Gender)
VALUES ('amin', 'abdo', 21, '1234567890', '123 Gym Street', 'Male');

INSERT INTO Person (FName, LName, Age, Phone, Address, Gender)
VALUES ('ahmed', 'hakeem', 22, '0987654321', '456 Fitness Ave', 'Male');

INSERT INTO Person (FName, LName, Age, Phone, Address, Gender)
VALUES ('trainer1', 'one', 30, '12367890', '123 Gym Street5', 'Male');

INSERT INTO Person (FName, LName, Age, Phone, Address, Gender)
VALUES ('trainer2', 'two', 25, '0987654321', '456 Fitness Ave5', 'Male');




INSERT INTO Member ( AssignedTrainerID, Weight, ExercisePlanID, ScheduleID)
VALUES ( 3, 75.5, 1, 1);

INSERT INTO Member (ID, AssignedTrainerID, Weight, ExercisePlanID, ScheduleID)
VALUES (2, 4, 65.0, 2, 2);




INSERT INTO Trainer (ID, Specialist)
VALUES (3, 'Strength Training');

INSERT INTO Trainer (ID, Specialist)
VALUES (4, 'Cardio');


INSERT INTO GymHall (Name, Capacity)
VALUES ('Main Hall', 50);

INSERT INTO GymHall (Name, Capacity)
VALUES ('Cardio Room', 30);


INSERT INTO Equipment (GymHallID,Name, Type, state)
VALUES (1,'Treadmill', 'Cardio', 'Good');

INSERT INTO Equipment (GymHallID,Name, Type, state)
VALUES ( 1,'Dumbbell Set', 'Strength', 'Excellent');


INSERT INTO ExercisePlan (PlanName, Type)
VALUES ('Weight Loss Plan', 'Cardio');

INSERT INTO ExercisePlan (PlanName, Type)
VALUES ('Muscle Gain Plan', 'Strength');


INSERT INTO Exercise (Name, Sets, Reps)
VALUES ('Push Ups', 3, 12);

INSERT INTO Exercise (Name, Sets, Reps)
VALUES ('Squats', 4, 15);



INSERT INTO Payments (MemberID, Amount, PaymentDate, DueDate, DaysRemaining, DiscountRate, Status)
VALUES (1, 100.00, '2024-10-01', '2024-11-01', 30, 0.10, 'Paid');

INSERT INTO Payments (MemberID, Amount, PaymentDate, DueDate, DaysRemaining, DiscountRate, Status)
VALUES (2, 200.00, '2024-10-10', '2024-11-10', 30, 0.05, 'Due');


-- Testing The Database 

select * from Member ;

SELECT 
    M.ID AS MemberID, 
    P1.FName AS MemberFirstName, 
    P1.LName AS MemberLastName, 
    P1.Age AS MemberAge, 
    P1.Phone AS MemberPhone, 
    P2.FName AS TrainerFirstName, 
    P2.LName AS TrainerLastName, 
    T.Specialist AS TrainerSpecialty
FROM 
    Member M
JOIN 
    Person P1 ON M.ID = P1.ID
JOIN 
    Trainer T ON M.AssignedTrainerID = T.ID
JOIN 
    Person P2 ON T.ID = P2.ID;

select * from person;