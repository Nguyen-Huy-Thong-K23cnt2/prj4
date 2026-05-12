CREATE DATABASE IF NOT EXISTS DTKT_db
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE DTKT_db;

-- 1. ROLE
CREATE TABLE role (
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO role (name) VALUES ('ADMIN'), ('USER');

-- 2. USERS
CREATE TABLE users (
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    username          VARCHAR(100) NOT NULL UNIQUE,
    password          VARCHAR(255) NOT NULL,
    email             VARCHAR(100) NOT NULL,
    full_name         VARCHAR(150),
    avatar            VARCHAR(255),
    role_id           BIGINT NOT NULL,
    phone             VARCHAR(20),
    address           VARCHAR(255),
    is_active         BIT          DEFAULT 1,
    reset_token       VARCHAR(255),
    reset_expired_at  DATETIME,
    created_at        DATETIME     DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (role_id) REFERENCES role(id)
);

INSERT INTO users (username, password, email, full_name, role_id, phone, address, is_active)
VALUES
('admin',   '$2a$10$exampleHashedPassword1', 'admin@dtkt.vn',  'Admin DTKT', 1, '0987654321', 'Hà Nội', 1),
('nguyen',  '$2a$10$exampleHashedPassword2', 'nguyen@gmail.com','Nguyễn Văn A', 2, '0123456789', 'Hà Nội', 1);

-- 3. CATEGORY
CREATE TABLE category (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    image_url   VARCHAR(255),
    parent_id   BIGINT DEFAULT NULL,
    FOREIGN KEY (parent_id) REFERENCES category(id)
);

INSERT INTO category (id, name, description, parent_id) VALUES
(1,  'Áo',           'Tất cả các loại áo nam',         NULL),
(2,  'Quần',         'Tất cả các loại quần nam',        NULL),
(3,  'Phụ kiện',     'Phụ kiện thời trang nam',         NULL),
(4,  'Sale',         'Sản phẩm đang giảm giá',          NULL),
(5,  'Áo thun',      'Áo thun nam các loại',            1),
(6,  'Áo sơ mi',     'Áo sơ mi nam công sở & casual',  1),
(7,  'Áo polo',      'Áo polo nam',                     1),
(8,  'Áo khoác',     'Áo khoác & áo bomber',            1),
(9,  'Quần jean',    'Quần jean nam các kiểu',          2),
(10, 'Quần kaki',    'Quần kaki & chino',               2),
(11, 'Quần jogger',  'Quần jogger & thể thao',          2),
(12, 'Thắt lưng',    'Thắt lưng da & vải',              3),
(13, 'Mũ nón',       'Mũ lưỡi trai & bucket hat',      3),
(14, 'Tất vớ',       'Tất nam các loại',                3);


-- 4. PRODUCT
CREATE TABLE product (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(255)   NOT NULL,
    price        DECIMAL(10,2)  NOT NULL,
    discount     DECIMAL(5,2)   DEFAULT 0,
    quantity     INT            NOT NULL DEFAULT 0,
    sold_count   INT            DEFAULT 0,
    size         VARCHAR(100),
    color        VARCHAR(100),
    description  TEXT,
    category_id  BIGINT         NOT NULL,
    is_active    BIT            DEFAULT 1,
    is_featured  BIT            DEFAULT 0,
    created_at   DATETIME       DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES category(id)
);

INSERT INTO product (name, price, discount, quantity, sold_count, size, color, description, category_id, is_active, is_featured)
VALUES
('Áo thun basic trắng DTKT',     299000,  0,  50, 120, 'S,M,L,XL',     'Trắng',         'Áo thun cotton 100%, form regular fit',          5, 1, 1),
('Áo thun oversize đen DTKT',    350000, 15,  40,  80, 'M,L,XL,XXL',   'Đen',           'Áo thun oversize chất cotton dày dặn',           5, 1, 1),
('Áo sơ mi kẻ casual DTKT',      379000,  0,  30,  45, 'S,M,L,XL',     'Xanh kẻ',       'Áo sơ mi kẻ vải thoáng mát',                     6, 1, 0),
('Áo sơ mi trắng công sở DTKT',  420000, 10,  25,  60, 'S,M,L,XL',     'Trắng',         'Áo sơ mi trắng form slim fit',                   6, 1, 1),
('Áo polo DTKT classic',         419000,  0,  35,  90, 'S,M,L,XL',     'Navy',          'Áo polo pique cotton cao cấp',                   7, 1, 1),
('Áo bomber DTKT street',        599000, 20,  20,  35, 'M,L,XL',        'Đen',           'Áo bomber chất liệu nylon cao cấp',              8, 1, 0),
('Quần jean slim fit DTKT',      489000,  0,  40,  75, '28,29,30,31,32','Xanh đậm',      'Quần jean denim co giãn nhẹ',                    9, 1, 1),
('Quần jean rách DTKT',          459000, 20,  30,  50, '28,29,30,31',   'Xanh nhạt',     'Quần jean rách gối phong cách',                  9, 1, 0),
('Quần chino slim DTKT',         459000,  0,  35,  65, '28,29,30,31,32','Kem',           'Quần chino kaki form slim',                      10, 1, 1),
('Quần jogger DTKT sport',       349000,  0,  50,  95, 'S,M,L,XL',     'Xám',           'Quần jogger thể thao co giãn 4 chiều',           11, 1, 1),
('Thắt lưng da DTKT classic',    199000,  0,  60,  40, 'Free size',     'Đen',           'Thắt lưng da PU cao cấp khóa bạc',               12, 1, 0),
('Mũ lưỡi trai DTKT logo',       149000,  0,  80,  55, 'Free size',     'Đen/Trắng',     'Mũ lưỡi trai thêu logo DTKT',                   13, 1, 0);

-- 5. PRODUCT IMAGE
CREATE TABLE product_image (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    image_url   VARCHAR(255) NOT NULL,
    is_main     BIT          DEFAULT 0,
    product_id  BIGINT       NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id)
);

INSERT INTO product_image (image_url, is_main, product_id) VALUES
('/images/products/ao-thun-trang-1.jpg',    1, 1),
('/images/products/ao-thun-trang-2.jpg',    0, 1),
('/images/products/ao-thun-den-1.jpg',      1, 2),
('/images/products/ao-somi-ke-1.jpg',       1, 3),
('/images/products/ao-somi-trang-1.jpg',    1, 4),
('/images/products/ao-polo-navy-1.jpg',     1, 5),
('/images/products/ao-bomber-den-1.jpg',    1, 6),
('/images/products/quan-jean-slim-1.jpg',   1, 7),
('/images/products/quan-jean-rach-1.jpg',   1, 8),
('/images/products/quan-chino-kem-1.jpg',   1, 9),
('/images/products/quan-jogger-xam-1.jpg',  1, 10),
('/images/products/that-lung-den-1.jpg',    1, 11),
('/images/products/mu-luoi-trai-1.jpg',     1, 12);

-- 6. PRODUCT COMMENT (REVIEW)
CREATE TABLE product_comment (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    content     TEXT     NOT NULL,
    star        INT      CHECK (star >= 1 AND star <= 5),
    is_visible  BIT      DEFAULT 1,
    product_id  BIGINT   NOT NULL,
    user_id     BIGINT   NOT NULL,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (user_id)    REFERENCES users(id)
);

INSERT INTO product_comment (content, star, is_visible, product_id, user_id) VALUES
('Áo đẹp, chất vải mềm mịn, mặc rất thoải mái!', 5, 1, 1, 2),
('Form áo chuẩn, đúng size, giao hàng nhanh.',    4, 1, 1, 2),
('Quần jean chất lượng tốt, co giãn nhẹ rất ok.', 5, 1, 7, 2);


-- 7. WISHLIST 
CREATE TABLE wishlist (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT   NOT NULL,
    product_id  BIGINT   NOT NULL,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id)    REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES product(id),
    UNIQUE KEY uq_wishlist (user_id, product_id)
);


-- 8. ADDRESS
CREATE TABLE address (
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id        BIGINT       NOT NULL,
    receiver_name  VARCHAR(100) NOT NULL,
    phone          VARCHAR(20)  NOT NULL,
    address        VARCHAR(255) NOT NULL,
    ward           VARCHAR(100),
    district       VARCHAR(100),
    province       VARCHAR(100),
    is_default     BIT          DEFAULT 0,
    created_at     DATETIME     DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO address (user_id, receiver_name, phone, address, ward, district, province, is_default) VALUES
(2, 'Nguyễn Văn A', '0123456789', '123 Phố Huế', 'Phường Hàng Bài', 'Hoàn Kiếm', 'Hà Nội', 1);


-- 9. COUPON 
CREATE TABLE coupon (
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    code              VARCHAR(50)   NOT NULL UNIQUE,
    discount_percent  DECIMAL(5,2)  DEFAULT NULL,
    discount_amount   DECIMAL(10,2) DEFAULT NULL,
    min_order_value   DECIMAL(10,2) DEFAULT 0,
    max_uses          INT           DEFAULT 100,
    used_count        INT           DEFAULT 0,
    is_active         BIT           DEFAULT 1,
    expired_at        DATETIME,
    created_at        DATETIME      DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO coupon (code, discount_percent, discount_amount, min_order_value, max_uses, is_active, expired_at) VALUES
('DTKT10',   10,   NULL,    200000, 100, 1, '2025-12-31 23:59:59'),
('WELCOME',  NULL, 50000,   150000, 200, 1, '2025-12-31 23:59:59'),
('SALE20',   20,   NULL,    500000,  50, 1, '2025-09-30 23:59:59');

-- 10. CART
CREATE TABLE cart (
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id  BIGINT NOT NULL UNIQUE,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE cart_item (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    cart_id     BIGINT   NOT NULL,
    product_id  BIGINT   NOT NULL,
    quantity    INT      NOT NULL DEFAULT 1,
    size        VARCHAR(20),
    color       VARCHAR(50),
    FOREIGN KEY (cart_id)    REFERENCES cart(id),
    FOREIGN KEY (product_id) REFERENCES product(id),
    UNIQUE KEY uq_cart_item (cart_id, product_id, size, color)
);

-- 11. ORDERS
CREATE TABLE orders (
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id           BIGINT        NOT NULL,
    coupon_id         BIGINT        DEFAULT NULL,
    total_price       DECIMAL(10,2),
    discount_value    DECIMAL(10,2) DEFAULT 0,
    final_price       DECIMAL(10,2),
    status            VARCHAR(50)   NOT NULL DEFAULT 'PENDING',
    shipping_address  VARCHAR(255),
    note              TEXT,
    created_at        DATETIME      DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id)   REFERENCES users(id),
    FOREIGN KEY (coupon_id) REFERENCES coupon(id)
);

-- Trạng thái đơn hàng: PENDING | CONFIRMED | SHIPPING | DELIVERED | CANCELLED

CREATE TABLE order_detail (
    id          BIGINT        AUTO_INCREMENT PRIMARY KEY,
    order_id    BIGINT        NOT NULL,
    product_id  BIGINT        NOT NULL,
    quantity    INT           NOT NULL,
    price       DECIMAL(10,2) NOT NULL,
    size        VARCHAR(20),
    color       VARCHAR(50),
    FOREIGN KEY (order_id)   REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

-- 12. PAYMENT
CREATE TABLE payment (
    id          BIGINT        AUTO_INCREMENT PRIMARY KEY,
    order_id    BIGINT        NOT NULL,
    method      VARCHAR(50)   NOT NULL,
    status      VARCHAR(50)   NOT NULL DEFAULT 'PENDING',
    amount      DECIMAL(10,2) NOT NULL,
    transaction_id VARCHAR(100),
    created_at  DATETIME      DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES orders(id)
);

-- Phương thức thanh toán: COD | BANK_TRANSFER | VNPAY | MOMO
-- Trạng thái thanh toán: PENDING | PAID | FAILED | REFUNDED

-- 13. BANNER (MỚI)
CREATE TABLE banner (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(255),
    image_url   VARCHAR(255) NOT NULL,
    link_url    VARCHAR(255),
    is_active   BIT          DEFAULT 1,
    sort_order  INT          DEFAULT 0,
    created_at  DATETIME     DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO banner (title, image_url, link_url, is_active, sort_order) VALUES
('Bộ sưu tập Hè 2025',        '/images/banners/banner-he-2025.jpg',   '/product?category=1', 1, 1),
('Sale cuối mùa giảm đến 50%', '/images/banners/banner-sale.jpg',      '/product?tag=sale',   1, 2),
('Ưu đãi hội viên DTKT',       '/images/banners/banner-member.jpg',    '/about',               1, 3);

-- 14. CONTACT 
CREATE TABLE contact (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name   VARCHAR(100) NOT NULL,
    email       VARCHAR(100) NOT NULL,
    subject     VARCHAR(255),
    message     TEXT         NOT NULL,
    is_read     BIT          DEFAULT 0,
    created_at  DATETIME     DEFAULT CURRENT_TIMESTAMP
);
