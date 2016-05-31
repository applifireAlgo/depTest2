

ALTER TABLE `ast_OrderTransaction_M` ADD CONSTRAINT FOREIGN KEY (`orderId`) REFERENCES `ast_OrderMain_T`(`orderId`);

