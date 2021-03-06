DROP TABLE IF EXISTS `ast_Item_M`;

CREATE TABLE `ast_Item_M` ( `itemId` VARCHAR(256) NOT NULL, `productId` VARCHAR(256) NOT NULL, `categoryId` VARCHAR(256) NOT NULL, `brandId` VARCHAR(256) NOT NULL, `itemName` VARCHAR(256) NOT NULL, `itemPrice` DOUBLE(10,2) NOT NULL, `itemStock` INT(10) NOT NULL, `itemIcon` TEXT NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`itemId`));

