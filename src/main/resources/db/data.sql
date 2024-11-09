-- Brand
insert into brands(id, name, created_at, updated_at) values (1, 'A', current_timestamp, current_timestamp);
insert into brands(id, name, created_at, updated_at) values (2, 'B', current_timestamp, current_timestamp);
insert into brands(id, name, created_at, updated_at) values (3, 'C', current_timestamp, current_timestamp);
insert into brands(id, name, created_at, updated_at) values (4, 'D', current_timestamp, current_timestamp);
insert into brands(id, name, created_at, updated_at) values (5, 'E', current_timestamp, current_timestamp);
insert into brands(id, name, created_at, updated_at) values (6, 'F', current_timestamp, current_timestamp);
insert into brands(id, name, created_at, updated_at) values (7, 'G', current_timestamp, current_timestamp);
insert into brands(id, name, created_at, updated_at) values (8, 'H', current_timestamp, current_timestamp);
insert into brands(id, name, created_at, updated_at) values (9, 'I', current_timestamp, current_timestamp);

-- Category
insert into categories(id, name, created_at, updated_at) values (1, '상의', current_timestamp, current_timestamp);
insert into categories(id, name, created_at, updated_at) values (2, '아우터', current_timestamp, current_timestamp);
insert into categories(id, name, created_at, updated_at) values (3, '바지', current_timestamp, current_timestamp);
insert into categories(id, name, created_at, updated_at) values (4, '스니커즈', current_timestamp, current_timestamp);
insert into categories(id, name, created_at, updated_at) values (5, '가방', current_timestamp, current_timestamp);
insert into categories(id, name, created_at, updated_at) values (6, '모자', current_timestamp, current_timestamp);
insert into categories(id, name, created_at, updated_at) values (7, '양말', current_timestamp, current_timestamp);
insert into categories(id, name, created_at, updated_at) values (8, '액세서리', current_timestamp, current_timestamp);

-- Product
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('A_상의', 11200, 1, 1, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('B_상의', 10500, 2, 1, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('C_상의', 10000, 3, 1, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('D_상의', 10100, 4, 1, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('E_상의', 10700, 5, 1, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('F_상의', 11200, 6, 1, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('G_상의', 10500, 7, 1, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('H_상의', 10800, 8, 1, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('I_상의', 11400, 9, 1, current_timestamp, current_timestamp);

insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('A_아우터', 5500, 1, 2, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('B_아우터', 5900, 2, 2, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('C_아우터', 6200, 3, 2, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('D_아우터', 5100, 4, 2, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('E_아우터', 5000, 5, 2, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('F_아우터', 7200, 6, 2, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('G_아우터', 5800, 7, 2, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('H_아우터', 6300, 8, 2, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('I_아우터', 6700, 9, 2, current_timestamp, current_timestamp);

insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('A_바지', 4200, 1, 3, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('B_바지', 3800, 2, 3, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('C_바지', 3300, 3, 3, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('D_바지', 3000, 4, 3, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('E_바지', 3800, 5, 3, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('F_바지', 4000, 6, 3, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('G_바지', 3900, 7, 3, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('H_바지', 3100, 8, 3, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('I_바지', 3200, 9, 3, current_timestamp, current_timestamp);

insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('A_스니커즈', 9000, 1, 4, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('B_스니커즈', 9100, 2, 4, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('C_스니커즈', 9200, 3, 4, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('D_스니커즈', 9500, 4, 4, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('E_스니커즈', 9900, 5, 4, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('F_스니커즈', 9300, 6, 4, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('G_스니커즈', 9000, 7, 4, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('H_스니커즈', 9700, 8, 4, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('I_스니커즈', 9500, 9, 4, current_timestamp, current_timestamp);

insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('A_가방', 2000, 1, 5, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('B_가방', 2100, 2, 5, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('C_가방', 2200, 3, 5, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('D_가방', 2500, 4, 5, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('E_가방', 2300, 5, 5, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('F_가방', 2100, 6, 5, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('G_가방', 2200, 7, 5, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('H_가방', 2100, 8, 5, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('I_가방', 2400, 9, 5, current_timestamp, current_timestamp);

insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('A_모자', 1700, 1, 6, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('B_모자', 2000, 2, 6, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('C_모자', 1900, 3, 6, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('D_모자', 1500, 4, 6, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('E_모자', 1800, 5, 6, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('F_모자', 1600, 6, 6, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('G_모자', 1700, 7, 6, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('H_모자', 1600, 8, 6, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('I_모자', 1700, 9, 6, current_timestamp, current_timestamp);

insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('A_양말', 1800, 1, 7, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('B_양말', 2000, 2, 7, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('C_양말', 2200, 3, 7, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('D_양말', 2400, 4, 7, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('E_양말', 2100, 5, 7, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('F_양말', 2300, 6, 7, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('G_양말', 2100, 7, 7, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('H_양말', 2000, 8, 7, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('I_양말', 1700, 9, 7, current_timestamp, current_timestamp);

insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('A_액세서리', 2300, 1, 8, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('B_액세서리', 2200, 2, 8, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('C_액세서리', 2100, 3, 8, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('D_액세서리', 2000, 4, 8, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('E_액세서리', 2100, 5, 8, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('F_액세서리', 1900, 6, 8, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('G_액세서리', 2000, 7, 8, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('H_액세서리', 2000, 8, 8, current_timestamp, current_timestamp);
insert into products(name, price, brand_id, category_id, created_at, updated_at) values ('I_액세서리', 2400, 9, 8, current_timestamp, current_timestamp);
