INSERT INTO `categoria` (`codigo`, `descricao`, `nome`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES (1,'HARDWARE','HARDWARE', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `categoria` (`codigo`, `descricao`, `nome`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES (2,'CLOTHING','CLOTHING', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `categoria` (`codigo`, `descricao`, `nome`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES (3,'FOOTWEAR','FOOTWEAR', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);

INSERT INTO `subcategoria` (`codigo`,`descricao`,`nome`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES (1,'0','0', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `subcategoria` (`codigo`,`descricao`,`nome`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES (6,'ACCESSORIES','ACCESSORIES', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `subcategoria` (`codigo`,`descricao`,`nome`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES (7,'BEARINGS','BEARINGS', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `subcategoria` (`codigo`,`descricao`,`nome`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES (8,'DECKS','DECKS', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `subcategoria` (`codigo`,`descricao`,`nome`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES (9,'GRIPTAPE','GRIPTAPE', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `subcategoria` (`codigo`,`descricao`,`nome`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES (10,'TRUCKS','TRUCKS', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `subcategoria` (`codigo`,`descricao`,`nome`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES (11,'WHEELS','WHEELS', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `subcategoria` (`codigo`,`descricao`,`nome`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES (12,'CAPS','CAPS', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `subcategoria` (`codigo`,`descricao`,`nome`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES (13,'HOODIE','HOODIE', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `subcategoria` (`codigo`,`descricao`,`nome`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES (14,'JACKETS','JACKETS', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `subcategoria` (`codigo`,`descricao`,`nome`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES (15,'T-SHIRT','T-SHIRT', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `subcategoria` (`codigo`,`descricao`,`nome`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES (16,'MENS SHOE SIZE','MENS SHOE', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `subcategoria` (`codigo`,`descricao`,`nome`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES (17,'MENS SHOE SIZE','WOMENS SHOE', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);

INSERT INTO `categoria_has_subcategoria` (`categoria_codigo`,`subcategoria_codigo`) VALUES (1,6);
INSERT INTO `categoria_has_subcategoria` (`categoria_codigo`,`subcategoria_codigo`) VALUES (1,7);
INSERT INTO `categoria_has_subcategoria` (`categoria_codigo`,`subcategoria_codigo`) VALUES (1,8);
INSERT INTO `categoria_has_subcategoria` (`categoria_codigo`,`subcategoria_codigo`) VALUES (1,9);
INSERT INTO `categoria_has_subcategoria` (`categoria_codigo`,`subcategoria_codigo`) VALUES (1,10);
INSERT INTO `categoria_has_subcategoria` (`categoria_codigo`,`subcategoria_codigo`) VALUES (1,11);
INSERT INTO `categoria_has_subcategoria` (`categoria_codigo`,`subcategoria_codigo`) VALUES (2,12);
INSERT INTO `categoria_has_subcategoria` (`categoria_codigo`,`subcategoria_codigo`) VALUES (2,13);
INSERT INTO `categoria_has_subcategoria` (`categoria_codigo`,`subcategoria_codigo`) VALUES (2,14);
INSERT INTO `categoria_has_subcategoria` (`categoria_codigo`,`subcategoria_codigo`) VALUES (2,15);
INSERT INTO `categoria_has_subcategoria` (`categoria_codigo`,`subcategoria_codigo`) VALUES (3,16);
INSERT INTO `categoria_has_subcategoria` (`categoria_codigo`,`subcategoria_codigo`) VALUES (3,17);

INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('ADIDAS','ADIDAS', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('ADIDASxHARDIES','ADIDASxHARDIES', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('ADIDASxHELAS','ADIDASxHELAS', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('AL CARRER','AL CARRER', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('ALLTIMERS','ALLTIMERS', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('ANDALÉ','ANDALÉ', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('ANTI HERO','ANTI HERO', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('BAKER','BAKER', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('BLURS','BLURS', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('BONES','BONES', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('BOULEVARD','BOULEVARD', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('BUTTERGOODS','BUTTERGOODS', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('CHOCOLATE','CHOCOLATE', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('CHRYSTIE','CHRYSTIE', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('CREATURE','CREATURE', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('CRUPIÊ','CRUPIÊ', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('DIAMOND','DIAMOND', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('DOGTOWN','DOGTOWN', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('EMERICA','EMERICA', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('ENJOI','ENJOI', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('ETNIES','ETNIES', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('FEEL','FEEL', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('FLIP','FLIP', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('FUCKING AWESOME','FUCKING AWESOME', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('GIRL','GIRL', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('GRIZZLY','GRIZZLY', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('HARDIES','HARDIES', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('HARDWEAR','HARDWEAR', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('HELAS','HELAS', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('HOCKEY','HOCKEY', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('HOODIE','HOODIE', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('HUF','HUF', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('INDEPENDENT','INDEPENDENT', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('ISLE','ISLE', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('KROOKED','KROOKED', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('MAGENTA','MAGENTA', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('MOB GRIP','MOB GRIP', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('NEW BALANCE NB#','NEW BALANCE NB#', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('NIKE SB','NIKE SB', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('NUMBERS','NUMBERS', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('OJ`S','OJ`S', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('PALACE','PALACE', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('POLAR','POLAR', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('POWELL PERALTA','POWELL PERALTA', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('PRIMITIVE','PRIMITIVE', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('QUASI','QUASI', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('REAL','REAL', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('RED BONES','RED BONES', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('ROYAL','ROYAL', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('SANTA CRUZ','SANTA CRUZ', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('SILVER','SILVER', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('SOULJAH','SOULJAH', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('SOUR','SOUR', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('SPITFIRE','SPITFIRE', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('THEORIES','THEORIES', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('THRASHER','THRASHER', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('THUNDER','THUNDER', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('TIRED','TIRED', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('VENTURE','VENTURE', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('WELCOME','WELCOME', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `marca` (`nome`,`descricao`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('WESTERN EDITION','WESTERN EDITION', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);

INSERT INTO `dominio` (`descricao`,`nome`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('LOJA FÍSICA','LOJA FÍSICA', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);

INSERT INTO `forma_de_pagamento` (`codigo`,`descricao`,`nome`,`porcentagem_desconto`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES (1,'DINHEIRO ','DINHEIRO ',0, 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `forma_de_pagamento` (`codigo`,`descricao`,`nome`,`porcentagem_desconto`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES (2,'CARTÃO ','CARTÃO ',0, 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);

INSERT INTO `medida` (`codigo`, `descricao`, `nome`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) values (1, 'US MENS SHOES',   'US', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);

INSERT INTO `itens_tipo_medida` (categoria_codigo, marca_codigo, medida_codigo, subcategoria_codigo, valor) values (3, null, 1, 16, '6');
INSERT INTO `itens_tipo_medida` (categoria_codigo, marca_codigo, medida_codigo, subcategoria_codigo, valor) values (3, null, 1, 16, '6.5');
INSERT INTO `itens_tipo_medida` (categoria_codigo, marca_codigo, medida_codigo, subcategoria_codigo, valor) values (3, null, 1, 16, '7');
-- INSERT INTO `itens_tipo_medida` (categoria_codigo, marca_codigo, medida_codigo, subcategoria_codigo, valor) values (3, null, 1, 16, '7.5');
-- INSERT INTO `itens_tipo_medida` (categoria_codigo, marca_codigo, medida_codigo, subcategoria_codigo, valor) values (3, null, 1, 16, '8');
-- INSERT INTO `itens_tipo_medida` (categoria_codigo, marca_codigo, medida_codigo, subcategoria_codigo, valor) values (3, null, 1, 16, '8.5');
-- INSERT INTO `itens_tipo_medida` (categoria_codigo, marca_codigo, medida_codigo, subcategoria_codigo, valor) values (3, null, 1, 16, '9');
-- INSERT INTO `itens_tipo_medida` (categoria_codigo, marca_codigo, medida_codigo, subcategoria_codigo, valor) values (3, null, 1, 16, '9.5');
-- INSERT INTO `itens_tipo_medida` (categoria_codigo, marca_codigo, medida_codigo, subcategoria_codigo, valor) values (3, null, 1, 16, '10');
-- INSERT INTO `itens_tipo_medida` (categoria_codigo, marca_codigo, medida_codigo, subcategoria_codigo, valor) values (3, null, 1, 16, '10.5');
-- INSERT INTO `itens_tipo_medida` (categoria_codigo, marca_codigo, medida_codigo, subcategoria_codigo, valor) values (3, null, 1, 16, '11');
-- INSERT INTO `itens_tipo_medida` (categoria_codigo, marca_codigo, medida_codigo, subcategoria_codigo, valor) values (3, null, 1, 16, '11.5');
-- INSERT INTO `itens_tipo_medida` (categoria_codigo, marca_codigo, medida_codigo, subcategoria_codigo, valor) values (3, null, 1, 16, '12');
-- INSERT INTO `itens_tipo_medida` (categoria_codigo, marca_codigo, medida_codigo, subcategoria_codigo, valor) values (3, null, 1, 16, '12.5');
-- INSERT INTO `itens_tipo_medida` (categoria_codigo, marca_codigo, medida_codigo, subcategoria_codigo, valor) values (3, null, 1, 16, '13');
INSERT INTO `fornecedor` (`descricao`,`nome`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES ('FORNECEDOR','FORNECEDOR', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `cliente` (`codigo`, `alterado_por`, `criado_por`, `data_alteracao`, `data_criacao`, `hora_criacao`, `version`) VALUES (1, 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `caixa` (`codigo`,`alterado_por`,`criado_por`,`data_alteracao`,`data_criacao`,`hora_criacao`,`version`,`data_abertura`,`data_fechamento`,`hora_abertura`,`hora_fechamento`,`status`,`total`,`total_desconto`,`total_vendas`,`valor_final`,`valor_inicial`)VALUES (null,null,null,null,CURRENT_TIMESTAMP(),null,0,CURRENT_TIMESTAMP(),null,CURRENT_TIMESTAMP(),null,'A',0,0,0,0,5.0);

INSERT INTO `authority`(`ID`,`ROLE`) VALUES(0, 'ADMIN');
INSERT INTO `authority`(`ID`,`ROLE`) VALUES(1, 'USER');
INSERT INTO `authority`(`ID`,`ROLE`) VALUES(2, 'CUSTUMER');

INSERT INTO `user`(`ID`,`ACCOUNT_NON_EXPIRED`,`ACCOUNT_NON_LOCKED`,`CREDENTIALS_NON_EXPIRED`,`ENABLED`,`PASSWORD`,`USERNAME`) VALUES (1, true,true,true,true, 'standard','{bcrypt10}$2a$10$Aype7wLEB5fMRUUEImlcnuCEtxhoAe2vmCmfbBUzs3qF3Qhwuyksm');
INSERT INTO `user_authority`(`USER_ID`, `AUTHORITY_ID`) VALUES (1, 0);



