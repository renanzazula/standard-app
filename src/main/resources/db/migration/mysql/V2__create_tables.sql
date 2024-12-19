CREATE TABLE user
(
    id                     BIGINT AUTO_INCREMENT,
    account_non_expired    TINYINT(1),
    account_non_locked     TINYINT(1),
    credential_non_expired TINYINT(1),
    enable                 TINYINT(1),
    username               VARCHAR(255),
    customer_id            BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE user_pass
(
    user_id  BIGINT NOT NULL,
    password VARCHAR(255),
    PRIMARY KEY (user_id)
);

CREATE TABLE user_session
(
    user_id         VARCHAR(64) NOT NULL,
    active_sessions VARCHAR(1024),
    application     VARCHAR(1),
    timestamp       DATETIME,
    PRIMARY KEY (user_id)
);

CREATE TABLE role
(
    id   BIGINT AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE user_role
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE authority
(
    id         BIGINT AUTO_INCREMENT,
    permission VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE role_authority
(
    role_id      BIGINT NOT NULL,
    authority_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, authority_id)
);

CREATE TABLE user_authority
(
    authority_id INT NOT NULL,
    user_id      INT NOT NULL,
    PRIMARY KEY (authority_id, user_id)
);

CREATE TABLE login_failure
(
    id                 BIGINT AUTO_INCREMENT,
    created_date       DATETIME,
    last_modified_date DATETIME,
    source_ip          VARCHAR(255),
    username           VARCHAR(255),
    user_id            BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE login_success
(
    id                 BIGINT AUTO_INCREMENT,
    created_date       DATETIME,
    last_modified_date DATETIME,
    source_ip          VARCHAR(255),
    username           VARCHAR(255),
    user_id            BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE withdrawal
(
    id                 BIGINT AUTO_INCREMENT,
    created_by         BIGINT,
    creation_date      DATE,
    creation_time      TIME,
    last_modified_by   BIGINT,
    last_modified_date DATETIME,
    version            BIGINT,
    amount             DOUBLE       NOT NULL,
    description        VARCHAR(255) NOT NULL,
    pos                BIGINT       NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE brand
(
    id                 BIGINT AUTO_INCREMENT,
    created_by         BIGINT,
    creation_date      DATE,
    creation_time      TIME,
    last_modified_by   BIGINT,
    last_modified_date DATETIME,
    version            BIGINT,
    description        VARCHAR(45) NOT NULL,
    name               VARCHAR(45) NOT NULL,
    status             VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE category
(
    id                 BIGINT AUTO_INCREMENT,
    created_by         BIGINT,
    creation_date      DATE,
    creation_time      TIME,
    last_modified_by   BIGINT,
    last_modified_date DATETIME,
    version            BIGINT,
    description        VARCHAR(45),
    name               VARCHAR(45) NOT NULL,
    status             VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE category_has_subcategory
(
    category_id    BIGINT NOT NULL,
    subcategory_id BIGINT NOT NULL,
    PRIMARY KEY (category_id, subcategory_id)
);

CREATE TABLE config_param
(
    id    VARCHAR(45)  NOT NULL,
    value VARCHAR(150) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE customer
(
    id                 BIGINT AUTO_INCREMENT,
    created_by         BIGINT,
    creation_date      DATE,
    creation_time      TIME,
    last_modified_by   BIGINT,
    last_modified_date DATETIME,
    version            BIGINT,
    api_key            VARCHAR(255),
    customer_name      VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE domain
(
    id                 BIGINT AUTO_INCREMENT,
    created_by         BIGINT,
    creation_date      DATE,
    creation_time      TIME,
    last_modified_by   BIGINT,
    last_modified_date DATETIME,
    version            BIGINT,
    description        VARCHAR(45) NOT NULL,
    name               VARCHAR(45) NOT NULL,
    status             VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE items_type_measure
(
    id                 BIGINT AUTO_INCREMENT,
    created_by         BIGINT,
    creation_date      DATE,
    creation_time      TIME,
    last_modified_by   BIGINT,
    last_modified_date DATETIME,
    version            BIGINT,
    amount             VARCHAR(255),
    brand_id           BIGINT,
    category_id        BIGINT,
    measure_id         BIGINT,
    subcategory_id     BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE measure
(
    id                 BIGINT AUTO_INCREMENT,
    created_by         BIGINT,
    creation_date      DATE,
    creation_time      TIME,
    last_modified_by   BIGINT,
    last_modified_date DATETIME,
    version            BIGINT,
    description        VARCHAR(45) NOT NULL,
    name               VARCHAR(45) NOT NULL,
    status             VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE `order`
(
    id                   BIGINT AUTO_INCREMENT,
    created_by           BIGINT,
    creation_date        DATE,
    creation_time        TIME,
    last_modified_by     BIGINT,
    last_modified_date   DATETIME,
    version              BIGINT,
    changing             DOUBLE,
    discount             DOUBLE,
    paid_amount          DOUBLE,
    payment              DOUBLE,
    pending_amount       DOUBLE,
    quantity             INT    NOT NULL,
    status               VARCHAR(255),
    sub_total            DOUBLE,
    total_amount         DOUBLE,
    total_amount_to_paid DOUBLE,
    customer_id          BIGINT NOT NULL,
    payment_method_id    BIGINT NOT NULL,
    pos_id               BIGINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE order_has_item_product
(
    id                                      BIGINT AUTO_INCREMENT,
    created_by                              BIGINT,
    creation_date                           DATE,
    creation_time                           TIME,
    last_modified_by                        BIGINT,
    last_modified_date                      DATETIME,
    version                                 BIGINT,
    quantity                                INT,
    unit_value                              DOUBLE,
    order_id                                BIGINT,
    product_has_items_items_type_measure_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE payback
(
    id                 BIGINT AUTO_INCREMENT,
    created_by         BIGINT,
    creation_date      DATE,
    creation_time      TIME,
    last_modified_by   BIGINT,
    last_modified_date DATETIME,
    version            BIGINT,
    amount             DOUBLE,
    description        VARCHAR(255),
    name               VARCHAR(255),
    customer_id        BIGINT,
    pos_id             BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE payment_method
(
    id                 BIGINT AUTO_INCREMENT,
    created_by         BIGINT,
    creation_date      DATE,
    creation_time      TIME,
    last_modified_by   BIGINT,
    last_modified_date DATETIME,
    version            BIGINT,
    description        VARCHAR(45) NOT NULL,
    discount_percent   INT         NOT NULL CHECK (discount_percent <= 100 AND discount_percent >= 0),
    name               VARCHAR(45) NOT NULL,
    status             VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE pos
(
    id                 BIGINT AUTO_INCREMENT,
    created_by         BIGINT,
    creation_date      DATE,
    creation_time      TIME,
    last_modified_by   BIGINT,
    last_modified_date DATETIME,
    version            BIGINT,
    close_amount       DOUBLE DEFAULT 0,
    close_date         DATE,
    close_time         TIME,
    open_amount        DOUBLE DEFAULT 0 NOT NULL,
    open_date          DATE             NOT NULL,
    open_time          TIME             NOT NULL,
    status             VARCHAR(255),
    total              DOUBLE DEFAULT 0,
    total_discount     DOUBLE DEFAULT 0,
    total_order        DOUBLE DEFAULT 0,
    PRIMARY KEY (id)
);

CREATE TABLE product
(
    id                 BIGINT AUTO_INCREMENT,
    created_by         BIGINT,
    creation_date      DATE,
    creation_time      TIME,
    last_modified_by   BIGINT,
    last_modified_date DATETIME,
    version            BIGINT,
    bar_code           VARCHAR(255) NOT NULL,
    cost_price         DOUBLE       NOT NULL,
    description        VARCHAR(45)  NOT NULL,
    discount           DOUBLE       NOT NULL,
    discount_percent   INT          NOT NULL CHECK (discount_percent <= 100 AND discount_percent >= 0),
    discount_price     DOUBLE       NOT NULL,
    name               VARCHAR(45)  NOT NULL,
    percent            INT          NOT NULL CHECK (percent <= 100 AND percent >= 0),
    photo              BLOB,
    price              DOUBLE,
    sale_price         DOUBLE       NOT NULL,
    status             VARCHAR(255) NOT NULL,
    weight             DOUBLE       NOT NULL,
    brand_id           BIGINT       NOT NULL,
    category_id        BIGINT       NOT NULL,
    measure_id         BIGINT       NOT NULL,
    provider_id        BIGINT       NOT NULL,
    subcategory_id     BIGINT       NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE product_has_items_type_measure
(
    id                    BIGINT AUTO_INCREMENT,
    created_by            BIGINT,
    creation_date         DATE,
    creation_time         TIME,
    last_modified_by      BIGINT,
    last_modified_date    DATETIME,
    version               BIGINT,
    quantity              INT,
    unit_value            DOUBLE,
    items_type_measure_id BIGINT,
    product_id            BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE product_has_items_type_measure_has_domain
(
    product_has_items_type_measure_id BIGINT NOT NULL,
    domain_id                         BIGINT NOT NULL,
    PRIMARY KEY (product_has_items_type_measure_id, domain_id)
);

CREATE TABLE provider
(
    id                 BIGINT AUTO_INCREMENT,
    created_by         BIGINT,
    creation_date      DATE,
    creation_time      TIME,
    last_modified_by   BIGINT,
    last_modified_date DATETIME,
    version            BIGINT,
    description        VARCHAR(45) NOT NULL,
    name               VARCHAR(45) NOT NULL,
    status             VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE subcategory
(
    id                 BIGINT AUTO_INCREMENT,
    created_by         BIGINT,
    creation_date      DATE,
    creation_time      TIME,
    last_modified_by   BIGINT,
    last_modified_date DATETIME,
    version            BIGINT,
    description        VARCHAR(45) NOT NULL,
    name               VARCHAR(45) NOT NULL,
    status             VARCHAR(255),
    PRIMARY KEY (id)
);

