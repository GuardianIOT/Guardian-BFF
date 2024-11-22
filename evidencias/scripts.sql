CREATE TABLE GUA_PROPRIETARIA(
    -- PRIMARY KEY
    id_proprietaria INT,
    PRIMARY KEY (id_proprietaria),
    -- NOT NULL
    razao_social VARCHAR(120) NOT NULL,
    cnpj VARCHAR(15) NOT NULL,
    -- NULLABLE
    endereco_principal VARCHAR(150),
    telefone_principal VARCHAR(20),
    email_contato VARCHAR(120)
);


CREATE TABLE GUA_USUARIO(
    -- PRIMARY KEY
    id_usuario INT,
    PRIMARY KEY(id_usuario),
    -- NOT NULL
    nome VARCHAR(30) NOT NULL,
    sobrenome VARCHAR(120),
    cargo VARCHAR(60) NOT NULL,
    cpf VARCHAR(15) NOT NULL,
    email VARCHAR(120) NOT NULL,
    senha VARCHAR(20) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    -- FOREIGN KEY
    id_empresa INT,
    FOREIGN KEY(id_empresa) REFERENCES GUA_PROPRIETARIA(id_empresa)
);


CREATE TABLE GUA_LOCALIZACAO(
    -- PRIMARY KEY
    id_localizacao INT,
    PRIMARY KEY (id_localizacao),
    -- NOT NULL
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR (50) NOT NULL,
    regiao VARCHAR(20) NOT NULL,
    pais VARCHAR(50) NOT NULL,
    latitude DECIMAL(8,6) NOT NULL,
    longitude DECIMAL(9,6) NOT NULL
);

CREATE TABLE GUA_PARQUE_EOLICO(
    -- PRIMARY KEY
    id_parque_eolico INT,
    PRIMARY KEY (id_parque_eolico),
    -- NOT NULL
    nome VARCHAR(75) NOT NULL,
    ano_inauguracao INT NOT NULL,
    area_km FLOAT NOT NULL,
    tecnologia VARCHAR(20) NOT NULL,
    status_operacao VARCHAR(65) NOT NULL,
    -- NULLABLE
    numero_aerogeradores INT,
    -- FOREIGN KEYS
    id_proprietaria INT,
    FOREIGN KEY (id_proprietaria) REFERENCES GUA_PROPRIETARIA(id_proprietaria),
    id_localizacao INT,
    FOREIGN KEY (id_localizacao) REFERENCES GUA_LOCALIZACAO(id_localizacao)
);

CREATE TABLE GUA_TORRE(
    -- PRIMARY KEY
    id_torre INT,
    PRIMARY KEY (id_torre),
    -- NOT NULL
    nome VARCHAR(60) NOT NULL,
    data_instalacao DATE,
    status_operacao VARCHAR(20),
    ultima_leitura TIMESTAMP
);

CREATE TABLE GUA_AEROGERADOR(
    -- PRIMARY KEY
    id_aerogerador INT,
    PRIMARY KEY (id_aerogerador),
    -- NOT NULL
    modelo VARCHAR(50),
    tecnologia VARCHAR(50),
    capacidade_mw FLOAT,
    altura_mastro FLOAT,
    velocidade_corte FLOAT,
    velocidade_maxima FLOAT,
    diametro_rotor FLOAT,
    data_instalacao DATE,
    garantia RAW(1),
    status_operacao VARCHAR(45)
);

CREATE TABLE GUA_SENSOR_RAIOS(
    -- PRIMARY KEY
    id_sensor_raio INT,
    PRIMARY KEY (id_sensor_raio),
    -- NULLABLE
    data_hora TIMESTAMP,
    tipo_raio VARCHAR(15),
    amplitude VARCHAR(15),
    latitude DECIMAL(8,6),
    longitude DECIMAL(9,6),
    -- FOREIGN KEY
    id_torre INT,
    FOREIGN KEY (id_torre) REFERENCES GUA_TORRE(id_torre)
);

CREATE TABLE GUA_SENSOR_CLIMA(
    -- PRIMARY KEY
    id_sensor_clima INT,
    PRIMARY KEY (id_sensor_clima),
    -- NULLABLE
    timezone VARCHAR(50),
    data_hora TIMESTAMP,
    temperatura FLOAT,
    umidade FLOAT,
    direcao_vento VARCHAR(5),
    velocida_vento FLOAT,
    descricao_clima VARCHAR(20),
    -- FOREIGN KEY
    id_torre INT,
    FOREIGN KEY (id_torre) REFERENCES GUA_TORRE(id_torre)
);

CREATE TABLE GUA_FALHA(
    -- PRIMARY KEY
    id_falha INT,
    PRIMARY KEY(id_falha),
    -- NOT NULL
    data_hora TIMESTAMP,
    descricao VARCHAR(200),
    status VARCHAR(20),
    prioridade VARCHAR(20),
    tipo VARCHAR(20),
    equipe_manutencao_responsavel VARCHAR(75),
    data_resolucao DATE
);

CREATE TABLE GUA_FALHA_AEROGERADOR(
    -- FOREIGN KEYS
    id_falha INT,
    FOREIGN KEY(id_falha) REFERENCES GUA_FALHA(id_falha),
    id_aerogerador INT,
    FOREIGN KEY(id_aerogerador) REFERENCES GUA_AEROGERADOR(id_aerogerador)
);