-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	8.0.2-dmr


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema inventory
--

CREATE DATABASE IF NOT EXISTS inventory;
USE inventory;

--
-- Definition of table `atax_tax_breakdown`
--

DROP TABLE IF EXISTS `atax_tax_breakdown`;
CREATE TABLE `atax_tax_breakdown` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `initial_date` date DEFAULT NULL,
  `final_date` date DEFAULT NULL,
  `room_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `board_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tax_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `included_in_price` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `maximum_number_of_nights` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `minimum_age` int(11) DEFAULT NULL,
  `maximum_age` int(11) DEFAULT NULL,
  `is_per_night` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_per_pax` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `amount` decimal(10,0) DEFAULT NULL,
  `percentage` decimal(10,0) DEFAULT NULL,
  `currency` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `apply_over` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Indicates if the tax is applied over net price or over amount (only applicable/available when tax is a percentage):N: Applies over net priceA: Applies over amount',
  `market_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `atax_tax_breakdown`
--

/*!40000 ALTER TABLE `atax_tax_breakdown` DISABLE KEYS */;
/*!40000 ALTER TABLE `atax_tax_breakdown` ENABLE KEYS */;


--
-- Definition of table `ccon_contract_header`
--

DROP TABLE IF EXISTS `ccon_contract_header`;
CREATE TABLE `ccon_contract_header` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `external_inventory` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `destination_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `office_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `contract_number` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Contract number and office code are unique identifier for a contract. Contract numbers can repeat for different office codes.  Null when External Inventory is set to true',
  `contract_name` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `company_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Should be noted the standing data for Meal Plans (board) and Room Type descriptions is dependent on the company code',
  `type_of_service` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Will be always ''H'' [Hotel]. In the future it could specify different types of product: Transfers, Excursions, etc.',
  `hotel_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `giata_hotel_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `initial_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `no_hotel` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Y: Roulette contract. There is no specific accommodation code defined. For example, "3 star hotels in London". N: The hotel code field will be always informed',
  `currency` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `base_board` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `classification` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `payment_model` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'M: Merchant model. Net price will always appear and gross price will appear only when Recommended Retail Price (RRP) is required by Hotelier. P: Direct Payment model. Gross price. In the sales process the end client pays the amount at Hotel.',
  `daily_price` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Daily price is price for the first day of booking. Y: Daily price is based on the first night of stay i.e. taken from the first night and multiplied by the number of nights N: Daily price is based on a nightly basis (pro-rata) i.e. taken from each night of the stay',
  `release_days_each_night` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'N:Release days values are taken for release for first day of booking. Check release date only for the first night of the stayY: Check release date for each night of the stay',
  `minimum_child_age` int(11) DEFAULT NULL,
  `maximum_child_age` int(11) DEFAULT NULL,
  `opaque` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'This field indicates if rates are for packaging purposes or not: B: This contract can be sold as hotel only or packaged. P: This contract can be sold only when packaging. Package discount in the CNSU section appears, it needs to be applied when packaging',
  `fix_rate` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Yes or no(only no now)',
  `contract_type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Always U',
  `maximum_rooms` int(11) DEFAULT NULL,
  `hotel_content` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'For roulette contracts inform which hotel code to take the content description from',
  `selling_price_mandatory` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Y: Price appearing at [CNCT.Price] is the mandatory final price for this product when sold to public N: Price appearing at [CNCT.Price] is not mandatory',
  `internal_field` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `internal_field_data` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `internal_classification` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_total_price_per_stay` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'This field indicates if it is necessary to sum several price lines from [SIAP.Amount] in order to get the final price for reaching the length of stay desired or not. Example:  Y: Not possible to do the sum over other lines to get the final price with the length of stay required as it is returning the lengths of stay available for this room.  N: Possible to do the sum over other lines to get the final price with the length of stay required',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `ccon_contract_header`
--

/*!40000 ALTER TABLE `ccon_contract_header` DISABLE KEYS */;
/*!40000 ALTER TABLE `ccon_contract_header` ENABLE KEYS */;


--
-- Definition of table `cncf_cancellation_fees`
--

DROP TABLE IF EXISTS `cncf_cancellation_fees`;
CREATE TABLE `cncf_cancellation_fees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `application_date` date DEFAULT NULL COMMENT 'Earliest booking creation date',
  `initial_date` date DEFAULT NULL,
  `final_date` date DEFAULT NULL,
  `rate` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `days` int(11) DEFAULT NULL COMMENT 'Days before the booking client check in where the cancellation fee applies',
  `hours` int(11) DEFAULT NULL,
  `amount_first_night` decimal(10,0) DEFAULT NULL,
  `percentage_first_night` decimal(10,0) DEFAULT NULL,
  `amount_per_night` decimal(10,0) DEFAULT NULL,
  `percentage_per_night` decimal(10,0) DEFAULT NULL,
  `application_end_date` date DEFAULT NULL COMMENT 'Last booking creation date',
  `type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Cancellation type: EN: Per check in ES: Per stay',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cncf_cancellation_fees`
--

/*!40000 ALTER TABLE `cncf_cancellation_fees` DISABLE KEYS */;
/*!40000 ALTER TABLE `cncf_cancellation_fees` ENABLE KEYS */;


--
-- Definition of table `cncl_valid_markets`
--

DROP TABLE IF EXISTS `cncl_valid_markets`;
CREATE TABLE `cncl_valid_markets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `country_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `valid_for_country` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Indicates if the contract is valid for this country. Y: The market is activated for the client and Cache files are calculated for this market.N: The market is NOT activated for the client therefore, Cache files have not been calculated for this market but the market is valid for this contract.',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cncl_valid_markets`
--

/*!40000 ALTER TABLE `cncl_valid_markets` DISABLE KEYS */;
/*!40000 ALTER TABLE `cncl_valid_markets` ENABLE KEYS */;


--
-- Definition of table `cnct_price`
--

DROP TABLE IF EXISTS `cnct_price`;
CREATE TABLE `cnct_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `initial_date` date DEFAULT NULL,
  `final_date` date DEFAULT NULL,
  `room_type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `characteristic` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `generic_rate` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `market_price` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Price per origin market (only available for clients that works on several countries)',
  `is_price_per_pax` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `net_price` decimal(10,0) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `specific_rate` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `base_board` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cnct_price`
--

/*!40000 ALTER TABLE `cnct_price` DISABLE KEYS */;
/*!40000 ALTER TABLE `cnct_price` ENABLE KEYS */;


--
-- Definition of table `cnem_min_max_stay`
--

DROP TABLE IF EXISTS `cnem_min_max_stay`;
CREATE TABLE `cnem_min_max_stay` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `application_date` date DEFAULT NULL COMMENT 'Earliest booking creation date where the minimum stay applies. If null the rule always applies',
  `initial_date` date DEFAULT NULL,
  `final_date` date DEFAULT NULL,
  `type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Rule types: T: Check in date if between the travels date period. Check min/max stay on check in date E: Check in and check out dates minus 1 is between the travels date period. Check min/max stay on check in date',
  `rate` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `room_type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `characteristic` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `board` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `minimum_days` int(11) DEFAULT NULL COMMENT 'Minimum days for the stay duration. If this field is empty, Maximum days cannot be empty',
  `maximum_days` int(11) DEFAULT NULL,
  `on_monday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'True if check in required on Monday. False if not',
  `on_tuesday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_wednesday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_thursday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_friday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_saturday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_sunday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cnem_min_max_stay`
--

/*!40000 ALTER TABLE `cnem_min_max_stay` DISABLE KEYS */;
/*!40000 ALTER TABLE `cnem_min_max_stay` ENABLE KEYS */;


--
-- Definition of table `cnes_check_in_check_out`
--

DROP TABLE IF EXISTS `cnes_check_in_check_out`;
CREATE TABLE `cnes_check_in_check_out` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `application_date` date DEFAULT NULL,
  `initial_date` date DEFAULT NULL,
  `final_date` date DEFAULT NULL,
  `type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'I: Check in O: Check out',
  `room_type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `characteristic` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rate` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_monday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'True if the rule applies. False if not',
  `on_tuesday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_wednesday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_thursday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_friday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_saturday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_sunday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `board` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cnes_check_in_check_out`
--

/*!40000 ALTER TABLE `cnes_check_in_check_out` DISABLE KEYS */;
/*!40000 ALTER TABLE `cnes_check_in_check_out` ENABLE KEYS */;


--
-- Definition of table `cngr_frees`
--

DROP TABLE IF EXISTS `cngr_frees`;
CREATE TABLE `cngr_frees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `initial_date` date DEFAULT NULL,
  `final_date` date DEFAULT NULL,
  `application_initial_date` date DEFAULT NULL,
  `application_final_date` date DEFAULT NULL,
  `minimum_days` int(11) DEFAULT NULL,
  `maximum_days` int(11) DEFAULT NULL,
  `rate` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `room_type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `characteristic` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `board` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `frees` int(11) DEFAULT NULL COMMENT 'Number of free nights',
  `free_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'It is used in conjunction with the data held in the CNOE',
  `discount` decimal(10,0) DEFAULT NULL COMMENT 'Percentage amount of discount',
  `application_base_type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'B: Applies to the base price [without supplement and discounts CNSU] D: Applies to the base price with base supplements and discounts [CNSU.Type = N, C or I] T: Applies to the total price',
  `application_board_type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'B: Applies to the base price [without board supplements and discounts CNSR] R: Applies to the price with board supplements and discounts',
  `application_discount_type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Which night the free offer applies B: Cheapest night C: Most expensive night M: Average night P: First night U: Last night',
  `application_stay_type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'E: Check in date must be in travel date period S: Check in date and check out date must be in the travel date period P: Only applies if no E or S has been applied. The check in and check out (minus 1) dates must overlap with the travel date period. The number of nights stay is taken from the overlap period and validated against the min/max duration. Multiple P offers can be applied. However max of 1 offer for each nightP only applies if no E or S offers have been applied',
  `on_monday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_tuesday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_wednesday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_thursday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_friday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_saturday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_sunday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `week day_validation_type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'E: Check in date must be valid for the week day S: The travel stay overlaps with at least one valid week day',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cngr_frees`
--

/*!40000 ALTER TABLE `cngr_frees` DISABLE KEYS */;
/*!40000 ALTER TABLE `cngr_frees` ENABLE KEYS */;


--
-- Definition of table `cnha_room_types`
--

DROP TABLE IF EXISTS `cnha_room_types`;
CREATE TABLE `cnha_room_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `room_type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `room_name` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'add',
  `characteristic` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `standard_capacity` int(11) DEFAULT NULL,
  `minimum_pax` int(11) DEFAULT NULL,
  `maximum_pax` int(11) DEFAULT NULL,
  `maximum_adult` int(11) DEFAULT NULL,
  `maximum_children` int(11) DEFAULT NULL,
  `maximum_infants` int(11) DEFAULT NULL,
  `minimum_adults` int(11) DEFAULT NULL,
  `minimum_children` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cnha_room_types`
--

/*!40000 ALTER TABLE `cnha_room_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `cnha_room_types` ENABLE KEYS */;


--
-- Definition of table `cnhf_handling_fee`
--

DROP TABLE IF EXISTS `cnhf_handling_fee`;
CREATE TABLE `cnhf_handling_fee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `initial_date` date DEFAULT NULL,
  `final_date` date DEFAULT NULL,
  `code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rate` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'G: Percentage of the service R: Amount of the service P: Once per pax D: Per pax and day',
  `amount` double DEFAULT NULL,
  `percentage` double DEFAULT NULL,
  `adult_amount` int(11) DEFAULT NULL,
  `child_amount` int(11) DEFAULT NULL,
  `minimum_age` int(11) DEFAULT NULL,
  `maximum_age` int(11) DEFAULT NULL,
  `age_amount` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Amount. It could be positive (supplement) and negative (discount)',
  `is_per_service` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Y: Per service N: Per booking',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cnhf_handling_fee`
--

/*!40000 ALTER TABLE `cnhf_handling_fee` DISABLE KEYS */;
/*!40000 ALTER TABLE `cnhf_handling_fee` ENABLE KEYS */;


--
-- Definition of table `cnin_inventory`
--

DROP TABLE IF EXISTS `cnin_inventory`;
CREATE TABLE `cnin_inventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `initial_date` date DEFAULT NULL,
  `final_date` date DEFAULT NULL,
  `room_type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `characteristic` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rate` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `release` int(11) DEFAULT NULL COMMENT 'Release days. 0 is day of departure (i.e. no release date)',
  `allotment` int(11) DEFAULT NULL COMMENT 'Remaining allotment from 0 to 10. 10 will indicate the number of rooms available is equal or greater than 10',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cnin_inventory`
--

/*!40000 ALTER TABLE `cnin_inventory` DISABLE KEYS */;
/*!40000 ALTER TABLE `cnin_inventory` ENABLE KEYS */;


--
-- Definition of table `cnoe_combinable_offers`
--

DROP TABLE IF EXISTS `cnoe_combinable_offers`;
CREATE TABLE `cnoe_combinable_offers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `code1` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Free or supplement/discount code',
  `code2` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Free or supplement/discount code',
  `is_excluded` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Y: If Code1 applies then Code2 is excluded N: If Code1 applies then Code2 is also mandatory required',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cnoe_combinable_offers`
--

/*!40000 ALTER TABLE `cnoe_combinable_offers` DISABLE KEYS */;
/*!40000 ALTER TABLE `cnoe_combinable_offers` ENABLE KEYS */;


--
-- Definition of table `cnpr_promotions`
--

DROP TABLE IF EXISTS `cnpr_promotions`;
CREATE TABLE `cnpr_promotions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `initial_date` date DEFAULT NULL,
  `final_date` date DEFAULT NULL,
  `application_initial_date` date DEFAULT NULL,
  `application_final_date` date DEFAULT NULL,
  `is_included` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cnpr_promotions`
--

/*!40000 ALTER TABLE `cnpr_promotions` DISABLE KEYS */;
/*!40000 ALTER TABLE `cnpr_promotions` ENABLE KEYS */;


--
-- Definition of table `cnpv_stop_sales`
--

DROP TABLE IF EXISTS `cnpv_stop_sales`;
CREATE TABLE `cnpv_stop_sales` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `initial_date` date DEFAULT NULL,
  `final_date` date DEFAULT NULL,
  `rate` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `room_type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `characteristic` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `board` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cnpv_stop_sales`
--

/*!40000 ALTER TABLE `cnpv_stop_sales` DISABLE KEYS */;
/*!40000 ALTER TABLE `cnpv_stop_sales` ENABLE KEYS */;


--
-- Definition of table `cnsr_board_supplements_discounts`
--

DROP TABLE IF EXISTS `cnsr_board_supplements_discounts`;
CREATE TABLE `cnsr_board_supplements_discounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `initial_date` date DEFAULT NULL,
  `final_date` date DEFAULT NULL,
  `board_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_per_pax` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `amount` decimal(10,0) DEFAULT NULL,
  `percentage` decimal(10,0) DEFAULT NULL,
  `rate` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `room_type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `characteristic` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `minimum_age` int(11) DEFAULT NULL,
  `maximum_age` int(11) DEFAULT NULL,
  `on_monday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_tuesday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_wednesday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_thursday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_friday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_saturday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_sunday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `internal_field` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `net_price` decimal(10,0) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `market_price` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cnsr_board_supplements_discounts`
--

/*!40000 ALTER TABLE `cnsr_board_supplements_discounts` DISABLE KEYS */;
/*!40000 ALTER TABLE `cnsr_board_supplements_discounts` ENABLE KEYS */;


--
-- Definition of table `cnsu_supplements_discounts`
--

DROP TABLE IF EXISTS `cnsu_supplements_discounts`;
CREATE TABLE `cnsu_supplements_discounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `initial_date` date DEFAULT NULL,
  `final_date` date DEFAULT NULL,
  `application_initial_date` date DEFAULT NULL,
  `application_final_date` date DEFAULT NULL,
  `supplement_or_discount_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'N: Child discount F: Infant C: Additional/Extra Bed I: Double for individual use L: Long stay B: Early booking E: Fixed stay M: Minimum stay O: Operation days G: General K: Turbo early booking V: Apply supplement based on arrival date times number of nights if arrival date is in the Travel Start / End date period U: Last minute Q: Only available for Airline discounts',
  `is_per_pax` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Applies per pax or per service: Y: Per person N: Per room',
  `opaque` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'If true, only applies for packaging',
  `order` int(11) DEFAULT NULL,
  `application_type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'B: On base board R: On board supplement N: Per night, base board plus supplement A: Absolute amount T: Only once. Check in date must be within the travel start and end date. Apply once to check in date. U: Only once type if any date of the booking overlaps with the period. The day to apply the offer is the first one that overlaps. M: Absolute amount + board supplement',
  `amount` decimal(10,0) DEFAULT NULL,
  `percentage` decimal(10,0) DEFAULT NULL,
  `is_cumulative` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rate` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `room_type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `characteristic` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `board` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `adults` int(11) DEFAULT NULL,
  `pax_order` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Indicates adult to which the rule applies: N and C',
  `minimum_age` int(11) DEFAULT NULL,
  `maximum_age` int(11) DEFAULT NULL,
  `number_of_days` int(11) DEFAULT NULL,
  `length_of_stay` int(11) DEFAULT NULL,
  `limit_date` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_monday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_tuesday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_wednesday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_thursday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_friday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_saturday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `on_sunday` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `net_price` decimal(10,0) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `market_price` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cnsu_supplements_discounts`
--

/*!40000 ALTER TABLE `cnsu_supplements_discounts` DISABLE KEYS */;
/*!40000 ALTER TABLE `cnsu_supplements_discounts` ENABLE KEYS */;


--
-- Definition of table `cnta_rate_codes`
--

DROP TABLE IF EXISTS `cnta_rate_codes`;
CREATE TABLE `cnta_rate_codes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rate` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `order` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Rate order for cancellation policy',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cnta_rate_codes`
--

/*!40000 ALTER TABLE `cnta_rate_codes` DISABLE KEYS */;
/*!40000 ALTER TABLE `cnta_rate_codes` ENABLE KEYS */;


--
-- Definition of table `siap_availability_price`
--

DROP TABLE IF EXISTS `siap_availability_price`;
CREATE TABLE `siap_availability_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `initial_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `room_type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `characteristic` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `board_type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `length_of_stay` int(11) DEFAULT NULL,
  `adults` int(11) DEFAULT NULL COMMENT 'Number of adults',
  `children` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'If a room admits children, it informs the range of age for each child where first value indicates number of children which the rule applies to and second and third values indicates the minimum and maximum age for those children. Example: (1,2,12)(2,0,1) indicates: (1,2,12) just allowed one child that must have an age between 2 to 12 years old. (2,0,1) just allowed two children that must have an age between 0 to 1 year old. If a room does not admit children, it informs the range of age for each child (minimum and maximum age), so only paxes above this maximum age will be allowed (adults). These ages need to bear in mind in order to include adults in the room (if the maximum age does not comply (the pax is younger than the maximum age), then it is not possible to include this pax in the room). Example: (0,0,12) Adults allowed from 13 years old (children between 0 and 12 are not allowed)',
  `occupancy_from_room_type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Indicates how to manage/build the occupancy per room:Y: It is necessary to check [CNHA] in order to know the occupancy allowed and build the occupancy for every room for every price line. N: Occupancy is informed in [SIAP] and price in [SIAP.Amount] will belong to this occupancy. [CNHA] will be empty.',
  `net_price` decimal(10,0) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL COMMENT 'Contains the final price for this product when sold to public  When [CCON.Selling price] value is true indicates this price is mandatory',
  `amount` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='paxes and price';

--
-- Dumping data for table `siap_availability_price`
--

/*!40000 ALTER TABLE `siap_availability_price` DISABLE KEYS */;
/*!40000 ALTER TABLE `siap_availability_price` ENABLE KEYS */;


--
-- Definition of table `siem_min_max_stay`
--

DROP TABLE IF EXISTS `siem_min_max_stay`;
CREATE TABLE `siem_min_max_stay` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `initial_date` date DEFAULT NULL,
  `final_date` date DEFAULT NULL,
  `room_type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `characteristic` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `board` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_per_check_in_date` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Indicates if validation is for check in date or for the whole stay:  Y: Per check-in date  N: Per whole stay',
  `is_per_range` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Indicates if validation is per range or per list of days:  Y: Per range  N: Per list of days',
  `days` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'If Is per range = Y, data provided will indicate the minimum and maximum stays separated by comma. Example: (1,5) where bookings for 1, 2, 3, 4, and 5 days are possible.  If Is per range = N, data provided will indicate the list of days separated by comma. Example: (1,2,5) where bookings for 1, 2 and 5 days are possible (for bookings for 3 and 4 days there is not availability)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='rules of stay';

--
-- Dumping data for table `siem_min_max_stay`
--

/*!40000 ALTER TABLE `siem_min_max_stay` DISABLE KEYS */;
/*!40000 ALTER TABLE `siem_min_max_stay` ENABLE KEYS */;


--
-- Definition of table `siin_inventory`
--

DROP TABLE IF EXISTS `siin_inventory`;
CREATE TABLE `siin_inventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `initial_date` date DEFAULT NULL,
  `final_date` date DEFAULT NULL,
  `room_type` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `characteristic` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `board` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `release_min` int(11) DEFAULT NULL COMMENT 'Minimum and maximum number of days necessary to do the booking in advance, where Release Min would be the minimum days in advance to do the booking before check-in and Release Max would be the maximum days in advance to do the booking before check-in',
  `release_max` int(11) DEFAULT NULL,
  `allotment` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='allotment and release rules';

--
-- Dumping data for table `siin_inventory`
--

/*!40000 ALTER TABLE `siin_inventory` DISABLE KEYS */;
/*!40000 ALTER TABLE `siin_inventory` ENABLE KEYS */;


--
-- Definition of table `sys_chain_code`
--

DROP TABLE IF EXISTS `sys_chain_code`;
CREATE TABLE `sys_chain_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `integration` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `code` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `sys_chain_code`
--

/*!40000 ALTER TABLE `sys_chain_code` DISABLE KEYS */;
INSERT INTO `sys_chain_code` (`id`,`integration`,`code`) VALUES 
 (1,'HILTON','15'),
 (2,'BONOTEL','19'),
 (3,'ACCOR','20'),
 (4,'HBSi','21'),
 (5,'TRADYSO','24'),
 (6,'DINGUS','25'),
 (7,'DERBYSOFT','26'),
 (8,'DOTW','31');
/*!40000 ALTER TABLE `sys_chain_code` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
