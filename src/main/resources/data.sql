INSERT INTO usuario (pk_correo, apellidos, contrasenna, nombre, telefono) VALUES
('estudiante1@estudiante.com', 'Araya Jimémenez', 'contrasenna', 'Juan', '88888888'),
('estudiante2@estudiante.com', 'Zuñiga Barrantes', 'contrasenna', 'Arturo', '88888888'),
('estudiante3@estudiante.com', 'Castro Fallas', 'contrasenna', 'David', '88888888'),
('estudiante4@estudiante.com', 'Hidalgo López', 'contrasenna', 'Francisco', '88888888'),
('teo@revisor.com', 'Willink', 'contrasenna', 'Teodoro', '88888888'),
('mariam@revisor.com', 'Mejías', 'contrasenna', 'Mariam', '88888888'),
('fabian@coordinador', 'Fabian', 'contrasenna', 'Meléndez', '8888888');

INSERT INTO estudiante (pk_correo_usuario, carne, estado, fecha_final, fecha_inicio, horas_totales, tipo) VALUES
('estudiante1@estudiante.com', 'B64895', 'Activo', '2020-05-20', '2019-05-20', 4, 'Regular'),
('estudiante2@estudiante.com', 'B47512', 'Activo', '2020-05-20', '2019-05-20', 2, 'Regular'),
('estudiante3@estudiante.com', 'B02541', 'Activo', '2020-05-20', '2019-05-20', 1, 'Regular'),
('estudiante4@estudiante.com', 'A94512', 'Activo', '2020-05-20', '2019-05-20', 30, 'Regular');

INSERT INTO revisor (pk_correo_usuario, es_coordinador) VALUES
('teo@revisor.com', false),
('mariam@revisor.com', false),
('fabian@coordinador', true);

INSERT INTO categoria (pk_nombre) VALUES
('Reunión'),
('Trabajo'),
('Gira');

INSERT INTO proyecto (pk_nombre, descripcion) VALUES
('Mapa de Osa', 'Proyecto para desarollar la aplicación del mapa de Osa'),
('Ingreso a la U', 'Proyecto encargado de divulgar información sobre la UCR a comunidades de Osa'),
('Factura electrónica', 'Proyecto encargado de informar a la comunidades de Osa sobre la factura electrónica'),
('RRSS', 'Talleres de redes sociales para las comunidades y emprendimientos');

INSERT INTO estudiante_participa_proyecto (fk_estudiante_correo, fk_proyecto_nombre) VALUES
('estudiante1@estudiante.com', 'Mapa de Osa'),
('estudiante1@estudiante.com', 'Ingreso a la U'),
('estudiante1@estudiante.com', 'Factura electrónica'),
('estudiante1@estudiante.com', 'RRSS'),
('estudiante2@estudiante.com', 'Ingreso a la U'),
('estudiante3@estudiante.com', 'Factura electrónica'),
('estudiante3@estudiante.com', 'RRSS'),
('estudiante4@estudiante.com', 'RRSS');

INSERT INTO actividad (fk_correo_estudiante, id_generado, decision, detalles, estado, fecha, horas, justificacion_rechazo, fk_nombre_categoria, fk_nombre_proyecto, fk_correo_revisor) VALUES
('estudiante1@estudiante.com', 1, null, 'Desarrollar aplicación', 'Pendiente', '2019-07-12', 4, null, 'Trabajo', 'Mapa de Osa', null),
('estudiante2@estudiante.com', 1, null, 'Redacción de material', 'Pendiente', '2019-07-12', 2, null, 'Trabajo', 'Ingreso a la U', null),
('estudiante3@estudiante.com', 1, null, 'Reunión de planificación', 'Pendiente', '2019-07-12', 1, null, 'Reunión', 'Factura electrónica', null),
('estudiante4@estudiante.com', 1, true, 'Gira', 'Aprobada', '2019-06-20', 30, '', 'Gira', 'RRSS', 'mariam@revisor.com');

