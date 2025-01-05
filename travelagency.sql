
create database travelagency

use travelagency

#create tables for travelagency 
CREATE TABLE User (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE Hotel (
    hotelId INT AUTO_INCREMENT PRIMARY KEY,
    hotelName VARCHAR(255) NOT NULL,
    hotelLocation VARCHAR(255),
    status VARCHAR(50) DEFAULT 'Available'	
);

CREATE TABLE Event (
    eventId INT AUTO_INCREMENT PRIMARY KEY,
    eventName VARCHAR(255) NOT NULL,
    eventDate DATE NOT NULL,
    eventLocation VARCHAR(255),
    availableSeats int NOT NULL,
    ticketPrice double NOT NULL,
    eventDescription TEXT,
    status VARCHAR(50) DEFAULT 'Scheduled'
);

CREATE TABLE HotelBooking (
    bookingId INT AUTO_INCREMENT PRIMARY KEY,
    userId INT NOT NULL,
    hotelId INT NOT NULL,
    checkInDate VARCHAR(50) NOT NULL,
    checkOutDate VARCHAR(50) NOT NULL,
    status VARCHAR(50) DEFAULT 'Pending',
    FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE,
    FOREIGN KEY (hotelId) REFERENCES Hotel(hotelId) ON DELETE CASCADE
);


CREATE TABLE EventBooking (
    bookingId INT AUTO_INCREMENT PRIMARY KEY,
    userId INT NOT NULL,
    eventId INT NOT NULL,
    eventDate VARCHAR(50) NOT NULL,
    seatNumber int NOT NULL,
    status VARCHAR(50) DEFAULT 'Pending',
    FOREIGN KEY (userId) REFERENCES User(userId),
    FOREIGN KEY (eventId) REFERENCES Event(eventId)
);

CREATE TABLE Notification (
    notificationId INT AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(255) NOT NULL,
    recipient VARCHAR(255) NOT NULL,  -- Fix the typo here
    notificationChannel VARCHAR(50) NOT NULL,
    status VARCHAR(50) DEFAULT 'Pending'
);

CREATE TABLE NotificationTemplate(
	templateId INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50) NOT NULL, 
    content VARCHAR(255) NOT NULL ,
    channel VARCHAR(50) NOT NULL
    );

-- Insertion of users
INSERT INTO User (userName, status, email) VALUES
('John Doe', 'Active', 'john.doe@example.com'),
('Jane Smith', 'Inactive', 'jane.smith@example.com'),
('Alice Johnson', 'Active', 'alice.johnson@example.com'),
('Bob Brown', 'Suspended', 'bob.brown@example.com'),
('Charlie White', 'Active', 'charlie.white@example.com');


-- insertion of hotels
INSERT INTO Hotel (hotelName, hotelLocation, status) 
VALUES ('Grand Plaza Hotel', 'New York', 'Available');

INSERT INTO Hotel (hotelName, hotelLocation, status) 
VALUES ('Sunset Resort', 'California', 'Occupied');

INSERT INTO Hotel (hotelName, hotelLocation, status) 
VALUES ('Ocean Breeze Hotel', 'Miami', 'Available');

-- insertion of events
INSERT INTO Event (eventName, eventDate, eventLocation, availableSeats, ticketPrice, eventDescription, status) 
VALUES ('Music Concert', '2024-12-25', 'New York City', 200, 75.00, 'An exciting live music concert featuring top artists.', 'Scheduled');

INSERT INTO Event (eventName, eventDate, eventLocation, availableSeats, ticketPrice, eventDescription, status) 
VALUES ('Art Exhibition', '2024-12-26', 'Los Angeles', 150, 50.00, 'A showcase of contemporary art from emerging artists.', 'Scheduled');

INSERT INTO Event (eventName, eventDate, eventLocation, availableSeats, ticketPrice, eventDescription, status) 
VALUES ('Tech Conference', '2024-12-31', 'San Francisco', 300, 150.00, 'A gathering of leading tech innovators and professionals.', 'Scheduled');

INSERT INTO Event (eventName, eventDate, eventLocation, availableSeats, ticketPrice, eventDescription, status) 
VALUES ('Comedy Show', '2025-01-01', 'Chicago', 100, 40.00, 'A night of laughter with the funniest comedians.', 'Scheduled');

INSERT INTO Event (eventName, eventDate, eventLocation, availableSeats, ticketPrice, eventDescription, status) 
VALUES ('Food Festival', '2025-01-15', 'Miami', 500, 25.00, 'A celebration of delicious food from around the world.', 'Scheduled');


-- insertion of template
INSERT INTO NotificationTemplate (type, content, channel)
VALUES ('Booking Confirmation', 'Dear {x}, your booking of the {y} is confirmed. Thanks for using our store :)', 'Email');


