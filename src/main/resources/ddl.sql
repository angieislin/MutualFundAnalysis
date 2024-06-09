CREATE TABLE `asset_values` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `date` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                `value` double DEFAULT NULL,
                                `fund_id` bigint DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                KEY `fk_av_fund_id` (`fund_id`),
                                CONSTRAINT `fk_av_fund_id` FOREIGN KEY (`fund_id`) REFERENCES `fund_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21743 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
;
CREATE TABLE `data_config` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `resource_main_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                               `fund_id` bigint DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `UKd44gvqtbsq42pxxgcg0q6aouk` (`fund_id`),
                               CONSTRAINT `fk_dc_fund_id` FOREIGN KEY (`fund_id`) REFERENCES `fund_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
;

CREATE TABLE `fund_info` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             `establish_date` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             `manager` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
;

CREATE TABLE `latest_fund_detail` (
                                      `id` bigint NOT NULL AUTO_INCREMENT,
                                      `create_timestamp` datetime(6) DEFAULT NULL,
                                      `current_nav_date` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                      `last_one_month_return` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                      `last_one_year_return` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                      `last_six_month_return` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                      `last_three_month_return` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                      `last_three_year_return` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                      `latest_net_asset_value` double NOT NULL,
                                      `since_establishment_return` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                      `fund_id` bigint DEFAULT NULL,
                                      PRIMARY KEY (`id`),
                                      KEY `fk_lfd_fund_id` (`fund_id`),
                                      CONSTRAINT `fk_lfd_fund_id` FOREIGN KEY (`fund_id`) REFERENCES `fund_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
;
ALTER TABLE `latest_fund_detail` ADD INDEX `idx_fund_id_create_timestamp` (`fund_id`, `create_timestamp`)
;

CREATE TABLE `stock_holding` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `percent` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                 `quote_change` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                 `stock_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                 `lfd_id` bigint DEFAULT NULL,
                                 PRIMARY KEY (`id`),
                                 KEY `fk_lfd_holding_id` (`lfd_id`),
                                 CONSTRAINT `fk_lfd_holding_id` FOREIGN KEY (`lfd_id`) REFERENCES `latest_fund_detail` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=251 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
;