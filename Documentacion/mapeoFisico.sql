DROP DATABASE SistemaHospital;
CREATE DATABASE SistemaHospital;
USE SistemaHospital;

CREATE TABLE  admin (
  codigo VARCHAR(20) NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  cui VARCHAR(15) NOT NULL,
  password VARCHAR(40) NOT NULL,
  PRIMARY KEY (codigo));

CREATE TABLE  medico (
  codigo VARCHAR(20) NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  noColegiado VARCHAR(20) NOT NULL,
  cui VARCHAR(15) NOT NULL,
  telefono VARCHAR(10) NOT NULL,
  correo VARCHAR(45) NOT NULL,
  horaInicio TIME NOT NULL,
  horaFinal TIME NOT NULL,
  fechaInicioLabores DATE NOT NULL,
  password VARCHAR(40) NOT NULL,
  PRIMARY KEY (codigo));

CREATE TABLE  especialidad (
  id INT AUTO_INCREMENT NOT NULL,
  nombre VARCHAR(45) UNIQUE NOT NULL,
  costo FLOAT NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE  asignacionEspecialidad (
  codigoMedico VARCHAR(20) NOT NULL,
  idEspecialidad INT NOT NULL,
  PRIMARY KEY (codigoMedico, idEspecialidad),
  CONSTRAINT FK_CODIGO_MEDICO
    FOREIGN KEY (codigoMedico)
    REFERENCES medico (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT FK_ID_ESPECIALIDAD
    FOREIGN KEY (idEspecialidad)
    REFERENCES especialidad (id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);

CREATE TABLE  paciente (
  codigo VARCHAR(20) NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  sexo VARCHAR(8) NOT NULL,
  birth DATE NOT NULL,
  cui VARCHAR(15) NOT NULL,
  telefono VARCHAR(10) NOT NULL,
  peso FLOAT NOT NULL,
  tipoSangre VARCHAR(5) NOT NULL,
  correo VARCHAR(45) NOT NULL,
  password VARCHAR(40) NOT NULL,
  PRIMARY KEY (codigo));

CREATE TABLE  tipoExamen (
  codigo VARCHAR(10) NOT NULL,
  nombre VARCHAR(45) UNIQUE NOT NULL,
  requiereOrden TINYINT NOT NULL DEFAULT 0,
  descripcion VARCHAR(1200) NOT NULL,
  costo FLOAT NOT NULL,
  tipoInforme VARCHAR(3) NOT NULL,
  PRIMARY KEY (codigo));

CREATE TABLE  laboratorista (
  codigo VARCHAR(20) NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  registroSalud VARCHAR(45) NOT NULL,
  cui VARCHAR(15) NOT NULL,
  telefono VARCHAR(10) NOT NULL,
  correo VARCHAR(45) NOT NULL,
  fechaInicioLabores DATE NOT NULL,
  codigoTipoExamen VARCHAR(10) NOT NULL,
  password VARCHAR(40) NOT NULL,
  PRIMARY KEY (codigo),
  CONSTRAINT FK_TIPO_EXAMEN
    FOREIGN KEY (codigoTipoExamen)
    REFERENCES tipoExamen (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);

CREATE TABLE  turno (
  codigoLaboratorista VARCHAR(20) NOT NULL,
  dia VARCHAR(10) NOT NULL,
  PRIMARY KEY (codigoLaboratorista, dia),
  CONSTRAINT FK_CODIGO_LABORATORISTA
    FOREIGN KEY (codigoLaboratorista)
    REFERENCES laboratorista (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);

CREATE TABLE  examen (
  codigo INT AUTO_INCREMENT NOT NULL,
  codigoPaciente VARCHAR(20) NOT NULL,
  codigoTipoExamen VARCHAR(10) NOT NULL,
  codigoMedico VARCHAR(20) NULL,
  orden MEDIUMBLOB NULL,
  fecha DATE NOT NULL,
  hora TIME NOT NULL,
  estado TINYINT NOT NULL,
  solicitoMedico TINYINT NOT NULL,
  total FLOAT NOT NULL,
  PRIMARY KEY (codigo),
  CONSTRAINT FK_EXAMEN_TO_CODIGO_PACIENTE
    FOREIGN KEY (codigoPaciente)
    REFERENCES paciente (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT FK_EXAMEN_TO_TIPO_EXAMEN
    FOREIGN KEY (codigoTipoExamen)
    REFERENCES tipoExamen (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT FK_EXAMEN_TO_CODIGO_MEDICO
    FOREIGN KEY (codigoMedico)
    REFERENCES medico (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);

CREATE TABLE  consulta (
  codigo INT AUTO_INCREMENT NOT NULL,
  codigoPaciente VARCHAR(20) NOT NULL,
  codigoMedico VARCHAR(20) NOT NULL,
  idEspecialidad INT NOT NULL,
  fecha DATE NOT NULL,
  hora TIME NOT NULL,
  estado TINYINT NOT NULL,
  total VARCHAR(45) NOT NULL,
  PRIMARY KEY (codigo),
  CONSTRAINT FK_CONSULTA_TO_CODIGO_PACIENTE
    FOREIGN KEY (codigoPaciente)
    REFERENCES paciente (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT FK_CONSULTA_TO_CODIGO_MEDICO
    FOREIGN KEY (codigoMedico)
    REFERENCES medico (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT FK_CONSULTA_TO_ID_ESPECIALIDAD
    FOREIGN KEY (idEspecialidad)
    REFERENCES especialidad (id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);

CREATE TABLE  resultado (
  codigoExamen INT NOT NULL,
  codigoLaboratorista VARCHAR(20) NOT NULL,
  resultado MEDIUMBLOB NOT NULL,
  fecha DATE NOT NULL,
  hora TIME NOT NULL,
  PRIMARY KEY (codigoExamen),
  CONSTRAINT FK_CODIGO_EXAMEN
    FOREIGN KEY (codigoExamen)
    REFERENCES examen (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT FK_RESULTADO_TO_CODIGO_LABORATORISTA
    FOREIGN KEY (codigoLaboratorista)
    REFERENCES laboratorista (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);


CREATE TABLE  informe (
  codigoConsulta INT NOT NULL,
  informe VARCHAR(1200) NOT NULL,
  fecha DATE NOT NULL,
  hora TIME NOT NULL,
  PRIMARY KEY (codigoConsulta),
  CONSTRAINT FK_CODIGO_CONSULTA
    FOREIGN KEY (codigoConsulta)
    REFERENCES consulta (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);
