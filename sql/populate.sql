-- -----------------------------------------------------
-- Inserir dados na tabela `lib`.`author`
-- -----------------------------------------------------
INSERT INTO `lib`.`author` (`description`, `name`) VALUES
('Autor especializado em literatura infantil.', 'Ana Maria Machado'),
('Autor conhecido por romances históricos.', 'José de Alencar'),
('Autor de ficção científica e fantasia.', 'Carlos Orsi');

-- -----------------------------------------------------
-- Inserir dados na tabela `lib`.`book`
-- -----------------------------------------------------
INSERT INTO `lib`.`book` (`name`, `description`, `isbn`) VALUES
('O Menino Maluquinho', 'História de um menino muito divertido e suas aventuras.', '9788511005235'),
('Senhora', 'Romance que explora temas de amor e status social.', '9788520911134'),
('A Máquina de Fazer Espanhóis', 'Narrativa sobre um homem que revisita o passado em um lar de idosos.', '9788535917323');

-- -----------------------------------------------------
-- Inserir dados na tabela `lib`.`category`
-- -----------------------------------------------------
INSERT INTO `lib`.`category` (`name`) VALUES
('Infantil'),
('Romance'),
('Ficção Científica');

-- -----------------------------------------------------
-- Inserir dados na tabela `lib`.`publisher`
-- -----------------------------------------------------
INSERT INTO `lib`.`publisher` (`name`) VALUES
('Companhia das Letras'),
('Record'),
('Rocco');

-- -----------------------------------------------------
-- Inserir dados na tabela `lib`.`books_authors`
-- -----------------------------------------------------
INSERT INTO `lib`.`books_authors` (`book_id`, `author_id`) VALUES
(1, 1), -- O Menino Maluquinho por Ana Maria Machado
(2, 2), -- Senhora por José de Alencar
(3, 3); -- A Máquina de Fazer Espanhóis por Carlos Orsi

-- -----------------------------------------------------
-- Inserir dados na tabela `lib`.`books_categories`
-- -----------------------------------------------------
INSERT INTO `lib`.`books_categories` (`book_id`, `category_id`) VALUES
(1, 1), -- O Menino Maluquinho é Infantil
(2, 2), -- Senhora é Romance
(3, 3); -- A Máquina de Fazer Espanhóis é Ficção Científica

-- -----------------------------------------------------
-- Inserir dados na tabela `lib`.`books_publishers`
-- -----------------------------------------------------
INSERT INTO `lib`.`books_publishers` (`book_id`, `publisher_id`) VALUES
(1, 1), -- O Menino Maluquinho pela Companhia das Letras
(2, 2), -- Senhora pela Record
(3, 3); -- A Máquina de Fazer Espanhóis pela Rocco