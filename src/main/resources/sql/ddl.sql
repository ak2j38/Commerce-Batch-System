CREATE TABLE order_origin
(
    order_no bigint auto_increment comment '주문번호 (pk)',
    created_at datetime default CURRENT_TIMESTAMP not null comment '주문 생성일 ',
    updated_at datetime default CURRENT_TIMESTAMP not null,
    deleted_at datetime null comment '삭제된 시간 ',
    paid_confirmed_at datetime default CURRENT_TIMESTAMP null comment '결제 완료 날짜 ',
    paid_pg_amount decimal(14, 5) default 0.00000 not null comment ' 결제 금액 ',
    used_mileage_amount decimal(14, 5) default 0.00000 not null comment '마일리지 사용 금액 ',
    coupon_discount_amount decimal(14, 5) default 0.00000 not null,
    PRIMARY KEY(order_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='주문 테이블'

CREATE TABLE order_item
(
    order_item_no bigint auto_increment comment '주문 상품 no',
    order_no bigint not null comment '주문원장 ',
    order_item_snapshot_no bigint not null comment '주문 상품 스냅샷 no ',
    order_count int default 1 not null comment '주문수량 ',
    purchase_confirmed_at datetime null comment '구매확정시간 ',
    item_delivery_status int null comment '아이템 배송 상태 ',
    created_at datetime default CURRENT_TIMESTAMP not null,
    deleted_at datetime default CURRENT_TIMESTAMP null comment '삭제시간 ',
    updated_at datetime default CURRENT_TIMESTAMP not null comment '수정날짜',
    shipped_complete_at datetime null comment '배송완료 시간 ',
    PRIMARY KEY(order_item_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='주문 상품 테이블'

CREATE TABLE order_item_snapshot
(
    order_item_snapshot_no  bigint auto_increment comment '주문 상품 스냅샷 테이블 Pk',
    product_no              bigint                                   not null comment '상품번호',
    seller_no               bigint                                   not null comment '셀러 no ',
    sell_price              decimal(14, 5) default 0.00000           not null comment '판매가 ',
    supply_price            decimal(14, 5) default 0.00000           null comment '공급가 ',
    promotion_amount        decimal(14, 5) default 0.00000           null comment '할인 가격 ',
    default_delivery_amount decimal(14, 5) default 3000.00000        null comment '3000',
    tax_rate                int            default 3                 not null comment '세금',
    tax_type                varchar(4)     default 'TAX'             not null comment '세금유형 ',
    item_category           int                                      not null comment '상품 유형 ',
    created_at              datetime       default CURRENT_TIMESTAMP not null,
    updated_at              datetime       default CURRENT_TIMESTAMP not null,
    deleted_at              datetime       default CURRENT_TIMESTAMP null,
    mileage_usage_amount    decimal(14, 5)                           null,
    PRIMARY KEY(order_item_snapshot_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='주문 상품 스냅샷 테이블'

create table claim_item
(
    claim_item_no    bigint                             not null
        primary key,
    claim_receipt_no bigint                             not null,
    created_at       datetime default CURRENT_TIMESTAMP not null,
    updated_at       datetime default CURRENT_TIMESTAMP not null,
    deleted_at       datetime                           null,
    order_item_no    bigint                             not null,
    claim_count      int                                null
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='클레임 상품';

create table claim_receipt
(
    claim_receipt_no bigint                             not null
        primary key,
    order_no         bigint                             not null,
    created_at       datetime default CURRENT_TIMESTAMP not null,
    updated_at       datetime default CURRENT_TIMESTAMP not null,
    deleted_at       datetime                           null,
    completed_at     datetime                           null,
    request_type     varchar(50)                        not null,
    claim_status     int                                not null,
    extra_fee_payer  int                                not null,
    claim_reason     int                                not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='클레임 원장';
