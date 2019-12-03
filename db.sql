DROP TABLE IF EXISTS attraction;
DROP TABLE IF EXISTS location;

CREATE TABLE location (
    location_id INT,
    name VARCHAR(200),
    parent_location_id INT,
    PRIMARY KEY (location_id),
    FOREIGN KEY (parent_location_id) REFERENCES location(location_id)
);

CREATE TABLE attraction (
    attraction_id INT,
    name VARCHAR(200),
    description VARCHAR(200),
    height VARCHAR(200),
    start_hour VARCHAR(4),
    end_hour VARCHAR(4),
    availability CHAR(1),
    location_id INT,
    PRIMARY KEY (attraction_id),
    FOREIGN KEY (location_id) REFERENCES location(location_id)
);

INSERT INTO location (location_id, name, parent_location_id) VALUES (1, 'Hong Kong Disneyland Park', null);
INSERT INTO location (location_id, name, parent_location_id) VALUES (2, 'Adventureland', 1);
INSERT INTO location (location_id, name, parent_location_id) VALUES (3, 'Fantasyland', 1);
INSERT INTO location (location_id, name, parent_location_id) VALUES (4, 'Grizzly Gulch', 1);
INSERT INTO location (location_id, name, parent_location_id) VALUES (5, 'Main Street, U.S.A.', 1);
INSERT INTO location (location_id, name, parent_location_id) VALUES (6, 'Mystic Point', 1);
INSERT INTO location (location_id, name, parent_location_id) VALUES (7, 'Tomorrowland', 1);
INSERT INTO location (location_id, name, parent_location_id) VALUES (8, 'Toy Story Land', 1);

INSERT INTO attraction (attraction_id, name, description, height, start_hour, end_hour, availability, location_id) VALUES (1, 'Jungle River Cruise', 'Slow Rides, Water Rides, Loud, Scary, Discovery, Outdoor, Interactive', null, '1030', '1900', 'Y', 1);
INSERT INTO attraction (attraction_id, name, description, height, start_hour, end_hour, availability, location_id) VALUES (2, 'Karibuni Marketplace', 'Character Experience, Outdoor, Interactive', null, '1100', '1830', 'Y', '1');