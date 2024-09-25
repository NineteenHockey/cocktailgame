CREATE TABLE IF NOT EXISTS temp(
   id                   BIGINT AUTO_INCREMENT PRIMARY KEY
  ,temp                 VARCHAR(256) NULL
);

CREATE TABLE IF NOT EXISTS gamedata(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY
    ,name                VARCHAR(100) NOT NULL
   ,val           INT
);
