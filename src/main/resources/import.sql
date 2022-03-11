INSERT INTO authority (id, name) VALUES (1, 'USER');
INSERT INTO authority (id, name) VALUES (2, 'ADMIN');

INSERT INTO user (id, address, login, password, username) VALUES (1, 'login1', 'login1', '$2a$12$VxmXy7AK8SA6SdioMiCtKen1LjiRMJ3qk5k56sWiX9BvjSGteD9L.','login1');

INSERT INTO user_authorities (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authorities (user_id, authority_id) VALUES (1, 2);