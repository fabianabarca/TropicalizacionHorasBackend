-- Todas las contraseñas son "contrasenna"
INSERT INTO usuario (pk_correo, apellidos, contrasenna, nombre, telefono, borrado, activado) VALUES
('estudiante1@estudiante.com', 'Araya Jimémenez', '$2a$10$tsqRGNas84QPuzIWfRFeeug4SR2PdnuZpkLHqPsiQc7DiPfGRQWpi', 'Juan', '88888888', FALSE, TRUE),
('estudiante2@estudiante.com', 'Zuñiga Barrantes', '$2a$10$tsqRGNas84QPuzIWfRFeeug4SR2PdnuZpkLHqPsiQc7DiPfGRQWpi', 'Arturo', '88888888', FALSE, TRUE),
('estudiante3@estudiante.com', 'Castro Fallas', '$2a$10$tsqRGNas84QPuzIWfRFeeug4SR2PdnuZpkLHqPsiQc7DiPfGRQWpi', 'David', '88888888', FALSE, TRUE),
('estudiante4@estudiante.com', 'Hidalgo López', '$2a$10$tsqRGNas84QPuzIWfRFeeug4SR2PdnuZpkLHqPsiQc7DiPfGRQWpi', 'Francisco', '88888888', FALSE, FALSE),
('teo@revisor.com', 'Willink Apellido', '$2a$10$tsqRGNas84QPuzIWfRFeeug4SR2PdnuZpkLHqPsiQc7DiPfGRQWpi', 'Teodoro', '88888888', FALSE, TRUE),
('mariam@revisor.com', 'Mejías Apellido', '$2a$10$tsqRGNas84QPuzIWfRFeeug4SR2PdnuZpkLHqPsiQc7DiPfGRQWpi', 'Mariam', '88888888', FALSE, TRUE),
('fabian@coordinador.com', 'Meléndez Apellido', '$2a$10$tsqRGNas84QPuzIWfRFeeug4SR2PdnuZpkLHqPsiQc7DiPfGRQWpi', 'Fabián', '88888888', FALSE, TRUE);

INSERT INTO estudiante (pk_correo_usuario, carne, estado, fecha_final, fecha_inicio, horas_totales, tipo, borrado) VALUES
('estudiante1@estudiante.com', 'B64895', 'Activo', '2020-05-20', '2019-05-20', 4, 'Regular', FALSE),
('estudiante2@estudiante.com', 'B47512', 'Activo', '2020-05-20', '2019-05-20', 2, 'Regular', FALSE),
('estudiante3@estudiante.com', 'B02541', 'Activo', '2020-05-20', '2019-05-20', 1, 'Regular', FALSE),
('estudiante4@estudiante.com', 'A94512', 'Activo', '2020-05-20', '2019-05-20', 30, 'Regular', FALSE);

INSERT INTO revisor (pk_correo_usuario, es_coordinador, borrado) VALUES
('teo@revisor.com', FALSE, FALSE),
('mariam@revisor.com', FALSE, FALSE),
('fabian@coordinador.com', TRUE, FALSE);

INSERT INTO categoria (pk_nombre, borrado) VALUES
('Reunión', FALSE),
('Trabajo', FALSE),
('Gira', FALSE);

INSERT INTO proyecto (pk_nombre, descripcion, borrado) VALUES
('Mapa de Osa', 'Desarollar la aplicación del mapa de Osa', FALSE),
('Ingreso a la U', 'Encargado de divulgar información sobre la UCR a comunidades de Osa', FALSE),
('Factura electrónica', 'Encargado de informar a la comunidades de Osa sobre la factura electrónica', FALSE),
('RRSS', 'Talleres de redes sociales para las comunidades y emprendimientos', FALSE);

INSERT INTO estudiante_participa_proyecto (fk_estudiante_correo, fk_proyecto_nombre) VALUES
('estudiante1@estudiante.com', 'Mapa de Osa'),
('estudiante1@estudiante.com', 'Ingreso a la U'),
('estudiante1@estudiante.com', 'Factura electrónica'),
('estudiante1@estudiante.com', 'RRSS'),
('estudiante2@estudiante.com', 'Ingreso a la U'),
('estudiante3@estudiante.com', 'Factura electrónica'),
('estudiante3@estudiante.com', 'RRSS'),
('estudiante4@estudiante.com', 'RRSS');

INSERT INTO actividad (fk_correo_estudiante, id_generado, detalles, estado, fecha, horas, justificacion_rechazo, fk_nombre_categoria, fk_nombre_proyecto, fk_correo_revisor) VALUES
('estudiante1@estudiante.com', 1, 'Desarrollar aplicación', 'Pendiente', '2019-07-12', 4, null, 'Trabajo', 'Mapa de Osa', null),
('estudiante1@estudiante.com', 3, 'Desarrollar aplicación', 'Pendiente', '2019-06-14', 8, null, 'Trabajo', 'Mapa de Osa', null),
('estudiante1@estudiante.com', 4, 'Desarrollar aplicación', 'Pendiente', '2019-07-15', 10, null, 'Trabajo', 'Mapa de Osa', null),
('estudiante1@estudiante.com', 5, 'Desarrollar aplicación', 'Pendiente', '2019-05-15', 10, null, 'Trabajo', 'Mapa de Osa', null),
('estudiante1@estudiante.com', 6, 'Desarrollar aplicación', 'Pendiente', '2019-06-15', 10, null, 'Trabajo', 'Mapa de Osa', null),
('estudiante1@estudiante.com', 7, 'Desarrollar aplicación', 'Pendiente', '2019-02-15', 10, null, 'Trabajo', 'Mapa de Osa', null),
('estudiante2@estudiante.com', 8, 'Redacción de material', 'Pendiente', '2019-07-12', 2, null, 'Trabajo', 'Ingreso a la U', null),
('estudiante3@estudiante.com', 9, 'Reunión de planificación', 'Pendiente', '2019-07-12', 1, null, 'Reunión', 'Factura electrónica', null),
('estudiante4@estudiante.com', 10, 'Gira', 'Aprobada', '2019-06-20', 30, '', 'Gira', 'RRSS', 'mariam@revisor.com');

