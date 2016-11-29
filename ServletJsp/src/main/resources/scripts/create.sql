create table IF NOT EXISTS PAIS(
	idPais int IDENTITY(1,1) PRIMARY KEY,
	nombrePais VARCHAR(25),
	nombreIdioma VARCHAR(25),
	);
	
create table IF NOT EXISTS IDIOMA(
	idIdioma int IDENTITY(1,1) PRIMARY KEY,
	nombreIdioma VARCHAR(25),
	);
	
	