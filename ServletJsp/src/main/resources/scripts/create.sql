create table IF NOT EXISTS PAIS(
	IdPais int auto_increment primary key,
	PAIS VARCHAR(25),
	IDIOMA int
	);
	
create table IF NOT EXISTS IDIOMA(
	IdIdioma int auto_increment primary key,
	IDIOMA VARCHAR(25)
	);
	
	