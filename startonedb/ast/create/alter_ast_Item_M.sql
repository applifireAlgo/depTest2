

ALTER TABLE `ast_Item_M` ADD CONSTRAINT FOREIGN KEY (`categoryId`) REFERENCES `ast_Category_M`(`categoryId`);



ALTER TABLE `ast_Item_M` ADD CONSTRAINT FOREIGN KEY (`productId`) REFERENCES `ast_Product_M`(`productId`);



ALTER TABLE `ast_Item_M` ADD CONSTRAINT FOREIGN KEY (`brandId`) REFERENCES `ast_Brand_M`(`brandId`);

