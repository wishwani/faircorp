INSERT INTO ROOM(id, name, floor, current_temperature, target_temperature) VALUES(-10, 'Room1', 1, 22.3, 20.0);
INSERT INTO ROOM(id, name, floor, current_temperature, target_temperature) VALUES(-9, 'Room2', 1, 10, 10);
INSERT INTO ROOM(id, name, floor, current_temperature, target_temperature) VALUES(-8, 'Room2', 1, 10, 10);

INSERT INTO HEATER(id, heater_status, name, power, room_id) VALUES(-10, 'ON', 'Heater1', 2000, -10);
INSERT INTO HEATER(id, heater_status, name, power, room_id) VALUES(-9, 'ON', 'Heater2', null, -10);

INSERT INTO RWINDOW(id, window_status, name, room_id) VALUES(-10, 'CLOSED', 'Window 1', -10);
INSERT INTO RWINDOW(id, window_status, name, room_id) VALUES(-9, 'CLOSED', 'Window 2', -10);
INSERT INTO RWINDOW(id, window_status, name, room_id) VALUES(-8, 'OPEN', 'Window 1', -9);
INSERT INTO RWINDOW(id, window_status, name, room_id) VALUES(-7, 'CLOSED', 'Window 2', -9);

INSERT INTO BUILDING(id, name) VALUES(1, 'Building 1');
INSERT INTO BUILDING(id, name) VALUES(2, 'Building 2');

UPDATE ROOM SET BUILDING_ID = 1 WHERE id = -10;
UPDATE ROOM SET BUILDING_ID = 2 WHERE id = -9;
UPDATE ROOM SET BUILDING_ID = 2 WHERE id = -8;


