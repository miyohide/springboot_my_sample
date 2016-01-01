CREATE TABLE Country (
  Code varchar(3) NOT NULL DEFAULT '',
  Name varchar(52) NOT NULL DEFAULT '',
  Continent varchar(20) NOT NULL DEFAULT 'Asia',
  Region varchar(26) NOT NULL DEFAULT '',
  Surface_Area decimal(10,2) NOT NULL DEFAULT '0.00',
  Indep_Year bigint DEFAULT NULL,
  Population bigint NOT NULL DEFAULT '0',
  Life_Expectancy decimal(3,1) DEFAULT NULL,
  GNP decimal(10,2) DEFAULT NULL,
  GNPOld decimal(10,2) DEFAULT NULL,
  Local_Name varchar(45) NOT NULL DEFAULT '',
  Government_Form varchar(45) NOT NULL DEFAULT '',
  Head_Of_State varchar(60) DEFAULT NULL,
  Capital bigint DEFAULT NULL,
  Code2 varchar(2) NOT NULL DEFAULT '',
  PRIMARY KEY (`Code`)
)
