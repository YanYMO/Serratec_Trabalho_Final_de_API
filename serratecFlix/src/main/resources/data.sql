-- =====================================================
-- SCHEMA.SQL - Massa de dados completa do SerratecFlix
-- =====================================================

-- =====================================================
-- USUARIOS
-- Perfil:
-- USUARIO senha -> usuario1234
-- ADMINISTRADOR -> administrador1234
-- =====================================================

INSERT INTO usuario
(nome, data_de_nascimento, email, username, senha, data_de_criacao, perfil)
VALUES
('Usuario', '2000-05-12', 'usuario@email.com', 'usuario', '$2a$12$lNc5FWFfs0nS00RzLSMehuBJvcNGy4RyN9/uuLma0c60MRg4waWZe', '2026-01-10', 'USUARIO'),
('Menor', '2011-05-12', 'menor@email.com', 'menor', '$2a$12$lNc5FWFfs0nS00RzLSMehuBJvcNGy4RyN9/uuLma0c60MRg4waWZe', '2026-01-12', 'USUARIO'),
('Administrador', '2000-09-22', 'administrador@email.com', 'administrador', '$2a$12$FUisLQqLDaWpHZhuCZOcXeBRCacbkRUpLa9tqaCZ0IwFbHYSXsg4u', '2026-01-11', 'ADMINISTRADOR');

-- =====================================================
-- EXPERIENCIA
-- =====================================================

INSERT INTO experiencia
(usuario_id, xp, nivel)
VALUES
(1, 2500, 31),
(2, 1200, 15),
(3, 1200, 15);

-- =====================================================
-- CATEGORIAS
-- =====================================================

INSERT INTO categoria
(titulo, descricao)
VALUES
('Ação', 'Filmes e séries com cenas intensas e adrenalina'),
('Comédia', 'Conteúdo focado em humor e entretenimento'),
('Romance', 'Histórias românticas e emocionantes'),
('Terror', 'Conteúdo sombrio e assustador'),
('Biografia', 'Histórias inspiradas em fatos reais'),
('Musical', 'Produções musicais e apresentações artísticas');

-- =====================================================
-- FILMES
-- Classificação:
-- 0 = LIVRE
-- 1 = 10 ANOS
-- 2 = 12 ANOS
-- 3 = 14 ANOS
-- 4 = 16 ANOS
-- 5 = 18 ANOS
-- =====================================================

INSERT INTO filme
(titulo, descricao, duracao_em_minutos, data_de_lancamento, classificacao_indicativa, nota_media)
VALUES
('A Última Fronteira', 'Um grupo enfrenta uma guerra futurista.', 145, '2022-08-15', 4, 8.5),
('Risos em Dobro', 'Uma dupla se envolve em situações absurdas.', 98, '2021-03-10', 1, 7.4),
('Noites Sombrias', 'Uma família descobre segredos sobrenaturais.', 120, '2023-10-31', 5, 8.9),
('Corações de Outono', 'Uma história de amor inesperada.', 110, '2020-06-18', 2, 7.8),
('O Maestro', 'A trajetória de um músico lendário.', 130, '2019-11-02', 3, 8.1);

-- =====================================================
-- SERIES
-- =====================================================

INSERT INTO serie
(titulo, descricao, temporadas, episodios, data_de_lancamento, nota_media, classificacao_indicativa)
VALUES
('Cidade Perdida', 'Investigadores exploram mistérios urbanos.', 4, 42, '2021-02-12', 8.6, 4),
('Humor Sem Limites', 'Uma sitcom recheada de situações engraçadas.', 6, 120, '2018-07-01', 7.9, 1),
('Pesadelos', 'Eventos paranormais desafiam a realidade.', 3, 24, '2024-01-20', 9.1, 5),
('Amor em Paris', 'Casais vivem dramas e paixões na França.', 5, 50, '2020-09-09', 8.0, 2),
('Som da Vida', 'Uma banda luta pelo sucesso musical.', 2, 18, '2022-04-14', 7.7, 3);

-- =====================================================
-- RELACIONAMENTO FILME_CATEGORIA
-- =====================================================

INSERT INTO filme_categoria (id_filme, id_categoria)
VALUES
(1, 1),
(2, 2),
(3, 4),
(4, 3),
(5, 5),
(5, 6);

-- =====================================================
-- RELACIONAMENTO SERIE_CATEGORIA
-- =====================================================

INSERT INTO serie_categoria (id_serie, id_categoria)
VALUES
(1, 1),
(1, 5),
(2, 2),
(3, 4),
(4, 3),
(5, 6);

-- =====================================================
-- AVALIACOES DE FILMES
-- =====================================================

INSERT INTO avaliacao_filme
( nota, comentario, data_da_avaliacao, usuario_id, filme_id)
VALUES
(9, 'Excelente filme de ação.', '2026-01-15', 2, 1),
(8, 'Muito divertido.', '2026-01-16', 1, 2),
(10, 'Um dos melhores terrores que já assisti.', '2026-01-16', 1, 3),
(7, 'Romance bem construído.', '2026-01-17', 1, 4),
(8, 'História emocionante.', '2026-01-18', 1, 5);

-- =====================================================
-- AVALIACOES DE SERIES
-- =====================================================

INSERT INTO avaliacao_serie
(nota, comentario, data_da_avaliacao, usuario_id, serie_id)
VALUES
(9, 'Muito envolvente.', '2026-01-19', 1, 1),
(8, 'Ótima para relaxar.', '2026-01-19', 2, 2),
(10, 'Série assustadora e incrível.', '2026-01-20', 1, 3),
(7, 'Boa construção de personagens.', '2026-01-21', 1, 4),
(8, 'Trilha sonora excelente.', '2026-01-21', 1, 5);

-- =====================================================
-- LISTAS DE FAVORITOS
-- =====================================================

INSERT INTO lista_de_favoritos
(nome_lista, privada, data_de_criacao, usuario_id)
VALUES
('Favoritos de Ação', false, '2026-01-10', 1),
('Maratonas de Terror', true, '2026-01-11', 2),
('Romances Preferidos', false, '2026-01-12', 1);

-- =====================================================
-- FAVORITOS - FILMES
-- =====================================================

INSERT INTO favoritos_filmes
(id_lista_de_favoritos, id_filme)
VALUES
(1, 1),
(1, 5),
(2, 3),
(1, 4);

-- =====================================================
-- FAVORITOS - SERIES
-- =====================================================

INSERT INTO favoritos_series
(id_lista_de_favoritos, id_serie)
VALUES
(1, 1),
(2, 3),
(3, 4);

-- =====================================================
-- PREMIOS
-- =====================================================

INSERT INTO premios
(nome, categoria, ano, filme_id, serie_id)
VALUES
('Melhor Filme', 'Cinema Nacional', 2023, 1, NULL),
('Melhor Roteiro', 'Festival Internacional', 2024, 3, NULL),
('Melhor Série Dramática', 'Streaming Awards', 2025, NULL, 1),
('Melhor Série de Terror', 'Horror Awards', 2025, NULL, 3),
('Melhor Trilha Sonora', 'Music Awards', 2022, 5, NULL);

-- =====================================================
-- FIM DO ARQUIVO
-- =====================================================
