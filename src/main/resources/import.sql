INSERT INTO authority (name) VALUES ('USER');
INSERT INTO authority (name) VALUES ('ADMIN');

INSERT INTO user (address, login, password, username) VALUES ('login1', 'login1', '$2a$12$VxmXy7AK8SA6SdioMiCtKen1LjiRMJ3qk5k56sWiX9BvjSGteD9L.','login1');

INSERT INTO user_authorities (user_id, authority_name) VALUES (1, 'USER');
INSERT INTO user_authorities (user_id, authority_name) VALUES (1, 'ADMIN');

INSERT INTO goods (name, price) VALUES ('sofa', 3000);
INSERT INTO goods (name, price) VALUES ('lamp', 300);
INSERT INTO goods (name, price) VALUES ('brush', 50);
INSERT INTO goods (name, price) VALUES ('toothpaste', 100);
INSERT INTO goods (name, price) VALUES ('chair', 1200);