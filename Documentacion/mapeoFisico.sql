DROP SCHEMA SistemaHospital
CREATE SCHEMA SistemaHospital;
USE SistemaHospital;

CREATE TABLE  admin (
  codigo VARCHAR(20) NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  nit VARCHAR(45) NOT NULL,
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
  id INT NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  costo VARCHAR(45) NULL,
  PRIMARY KEY (id));

CREATE TABLE  asignacionEspecialidad (
  codigoMedico VARCHAR(20) NOT NULL,
  idEspecialidad INT NOT NULL,
  PRIMARY KEY (codigoMedico, idEspecialidad),
  INDEX FK_ID_ESPECIALIDAD_idx (idEspecialidad ASC) VISIBLE,
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
  nombre VARCHAR(45) NOT NULL,
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
  INDEX FK_TIPO_EXAMEN_idx (codigoTipoExamen ASC) VISIBLE,
  CONSTRAINT FK_TIPO_EXAMEN
    FOREIGN KEY (codigoTipoExamen)
    REFERENCES tipoExamen (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);

CREATE TABLE  turno (
  codigoLaboratorista VARCHAR(20) NOT NULL,
  dia INT NOT NULL,
  PRIMARY KEY (codigoLaboratorista, dia),
  CONSTRAINT FK_CODIGO_LABORATORISTA
    FOREIGN KEY (codigoLaboratorista)
    REFERENCES laboratorista (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);

CREATE TABLE  examen (
  codigo VARCHAR(15) NOT NULL,
  codigoPaciente VARCHAR(20) NOT NULL,
  codigoLaboratorista VARCHAR(20) NOT NULL,
  codigoTipoExamen VARCHAR(10) NOT NULL,
  codigoMedico VARCHAR(20) NULL,
  orden BLOB NULL,
  fecha DATE NOT NULL,
  hora TIME NOT NULL,
  estado TINYINT NOT NULL DEFAULT 0,
  total FLOAT NOT NULL,
  PRIMARY KEY (codigo),
  INDEX FK_CODIGO_PACIENTE_idx (codigoPaciente ASC) VISIBLE,
  INDEX FK_CODIGO_LABORATORISTA_idx (codigoLaboratorista ASC) VISIBLE,
  INDEX FK_TIPO_EXAMEN_idx (codigoTipoExamen ASC) VISIBLE,
  INDEX FK_CODIGO_MEDICO_idx (codigoMedico ASC) VISIBLE,
  CONSTRAINT FK_CODIGO_PACIENTE
    FOREIGN KEY (codigoPaciente)
    REFERENCES paciente (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT FK_CODIGO_LABORATORISTA
    FOREIGN KEY (codigoLaboratorista)
    REFERENCES laboratorista (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT FK_TIPO_EXAMEN
    FOREIGN KEY (codigoTipoExamen)
    REFERENCES tipoExamen (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT FK_CODIGO_MEDICO
    FOREIGN KEY (codigoMedico)
    REFERENCES medico (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);

CREATE TABLE  consulta (
  codigo VARCHAR(15) NOT NULL,
  codigoPaciente VARCHAR(20) NOT NULL,
  codigoMedico VARCHAR(20) NOT NULL,
  idEspecialidad INT NOT NULL,
  fecha DATE NOT NULL,
  hora TIME NOT NULL,
  estado TINYINT NOT NULL,
  total VARCHAR(45) NULL,
  PRIMARY KEY (codigo),
  INDEX FK_CODIGO_PACIENTE_idx (codigoPaciente ASC) VISIBLE,
  INDEX FK_CODIGO_MEDICO_idx (codigoMedico ASC) VISIBLE,
  INDEX FK_ID_ESPECIALIDAD_idx (idEspecialidad ASC) VISIBLE,
  CONSTRAINT FK_CODIGO_PACIENTE
    FOREIGN KEY (codigoPaciente)
    REFERENCES paciente (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
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

CREATE TABLE  resultado (
  codigoExamen VARCHAR(15) NOT NULL,
  resultado BLOB NOT NULL,
  fecha DATE NOT NULL,
  hora TIME NOT NULL,
  PRIMARY KEY (codigoExamen),
  CONSTRAINT FK_CODIGO_EXAMEN
    FOREIGN KEY (codigoExamen)
    REFERENCES examen (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);


CREATE TABLE  informe (
  codigoConsulta VARCHARtipoExamen(15) NOT NULL,
  informe VARCHAR(1200) NOT NULL,
  fecha DATE NOT NULL,
  hora TIME NOT NULL,
  PRIMARY KEY (codigoConsulta),
  CONSTRAINT FK_CODIGO_CONSULTA
    FOREIGN KEY (codigoConsulta)
    REFERENCES consulta (codigo)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);
