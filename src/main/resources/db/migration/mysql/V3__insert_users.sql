INSERT INTO `user`(`id`, `username`, `account_non_expired`, `account_non_locked`, `credential_non_expired`, `enable`) VALUES (1, 'admin',    true, true, true, true);
INSERT INTO `user`(`id`, `username`, `account_non_expired`, `account_non_locked`, `credential_non_expired`, `enable`) VALUES (2, 'user',     true, true, true, true);
INSERT INTO `user`(`id`, `username`, `account_non_expired`, `account_non_locked`, `credential_non_expired`, `enable`) VALUES (3, 'customer', true, true, true, true);

INSERT INTO `user_pass`(`password`, `user_id`) VALUES ('{bcrypt}$2a$10$3w//CgAicchkVo0iCYJ0W.oXv911VdgfHsuS0hFks5joDpFFakUS2', 1);
INSERT INTO `user_pass`(`password`, `user_id`) VALUES ('{bcrypt}$2a$10$3w//CgAicchkVo0iCYJ0W.oXv911VdgfHsuS0hFks5joDpFFakUS2', 2);
INSERT INTO `user_pass`(`password`, `user_id`) VALUES ('{bcrypt}$2a$10$3w//CgAicchkVo0iCYJ0W.oXv911VdgfHsuS0hFks5joDpFFakUS2', 3);

INSERT INTO `role` (`id`, `name`) VALUES (1, 'ADMIN');
INSERT INTO `role` (`id`, `name`) VALUES (2, 'USER'); -- user application internal
INSERT INTO `role` (`id`, `name`) VALUES (3, 'CUSTOMER'); -- user application external or second role

INSERT INTO `authority`(`id`, `permission`) VALUES (1, 'ORDER_OPEN');
INSERT INTO `authority`(`id`, `permission`) VALUES (2, 'ORDER_ADD');
INSERT INTO `authority`(`id`, `permission`) VALUES (3, 'ORDER_REMOVE');
INSERT INTO `authority`(`id`, `permission`) VALUES (4, 'ORDER_CLOSE');
INSERT INTO `authority`(`id`, `permission`) VALUES (5, 'ORDER_PRINT');
INSERT INTO `authority`(`id`, `permission`) VALUES (6, 'ORDER_DELETE');
INSERT INTO `authority`(`id`, `permission`) VALUES (7, 'ORDER_REOPEN');
INSERT INTO `authority`(`id`, `permission`) VALUES (8, 'ORDER_SEARCH');
INSERT INTO `authority`(`id`, `permission`) VALUES (9, 'CUSTOMER_SEARCH');
INSERT INTO `authority`(`id`, `permission`) VALUES (10, 'PRODUCT_PRINT');
INSERT INTO `authority`(`id`, `permission`) VALUES (11, 'PRODUCT_SEARCH');
INSERT INTO `authority`(`id`, `permission`) VALUES (12, 'PRODUCT_ADD');
INSERT INTO `authority`(`id`, `permission`) VALUES (13, 'PRODUCT_DELETE');
INSERT INTO `authority`(`id`, `permission`) VALUES (14, 'PRODUCT_UPDATE');

INSERT INTO `role_authority` (`authority_id`, `role_id`) VALUES (1, 1);
INSERT INTO `role_authority` (`authority_id`, `role_id`) VALUES (2, 1);
INSERT INTO `role_authority` (`authority_id`, `role_id`) VALUES (3, 1);
INSERT INTO `role_authority` (`authority_id`, `role_id`) VALUES (4, 1);
INSERT INTO `role_authority` (`authority_id`, `role_id`) VALUES (5, 1);
INSERT INTO `role_authority` (`authority_id`, `role_id`) VALUES (6, 1);
INSERT INTO `role_authority` (`authority_id`, `role_id`) VALUES (7, 1);
INSERT INTO `role_authority` (`authority_id`, `role_id`) VALUES (8, 1);
INSERT INTO `role_authority` (`authority_id`, `role_id`) VALUES (9, 1);
INSERT INTO `role_authority` (`authority_id`, `role_id`) VALUES (9, 3);
INSERT INTO `role_authority` (`authority_id`, `role_id`) VALUES (10, 1);
INSERT INTO `role_authority` (`authority_id`, `role_id`) VALUES (11, 1);

INSERT INTO `role_authority` (`authority_id`, `role_id`) VALUES (12, 1);
INSERT INTO `role_authority` (`authority_id`, `role_id`) VALUES (13, 1);
INSERT INTO `role_authority` (`authority_id`, `role_id`) VALUES (14, 1);

INSERT INTO `user_role` (`role_id`, `user_id`) VALUES (1, 1);
INSERT INTO `user_role` (`role_id`, `user_id`) VALUES (2, 2);
INSERT INTO `user_role` (`role_id`, `user_id`) VALUES (3, 3);

INSERT INTO `user_authority`(`user_id`, `authority_id`) VALUES (1, 1);
INSERT INTO `user_authority`(`user_id`, `authority_id`) VALUES (2, 1);

