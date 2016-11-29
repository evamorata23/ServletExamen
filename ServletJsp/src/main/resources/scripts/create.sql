create table IF NOT EXISTS PAIS(
	idPais int IDENTITY(1,1) PRIMARY KEY,
	nombrePais VARCHAR(25),
	idIdioma int
	);
	
create table IF NOT EXISTS IDIOMA(
	idIdioma int IDENTITY(1,1) PRIMARY KEY,
	nombreIdioma VARCHAR(25),
	);
	
	