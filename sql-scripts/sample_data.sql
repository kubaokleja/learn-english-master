SELECT * FROM court_reservation_manager.user;
INSERT INTO court_reservation_manager.booking (time_start, time_end, players, user_id)
VALUES 
('2019-09-15 15:00:00','2019-09-15 15:00:00',4,1);
SELECT * FROM court_reservation_manager.booking;
INSERT INTO `users_roles` (user_id,role_id)
VALUES 
(1, 1),
(1, 2),
(2, 2);