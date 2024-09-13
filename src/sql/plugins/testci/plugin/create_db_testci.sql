
--
-- Structure for table testci_testci
--

DROP TABLE IF EXISTS testci_testci;
CREATE TABLE testci_testci (
id_test_c_i int NOT NULL,
name varchar(50) default '' NOT NULL,
PRIMARY KEY (id_test_c_i)
);


-- Typo on purpose to check that jenkins shows ant failures
CREATE TOBLE test_testci2 (
id_test_c_i int NOT NULL,
name varchar(50) default '' NOT NULL,
PRIMARY KEY (id_test_c_i)
);
