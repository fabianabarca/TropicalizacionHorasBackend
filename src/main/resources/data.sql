-- Todas las contraseñas son "contrasenna"
INSERT INTO usuario (pk_correo, apellidos, contrasenna, nombre, telefono, habilitado) VALUES
('estudiante1@estudiante.com', 'Araya Jimémenez', '$2a$10$tsqRGNas84QPuzIWfRFeeug4SR2PdnuZpkLHqPsiQc7DiPfGRQWpi', 'Juan', '88888888', TRUE),
('estudiante2@estudiante.com', 'Zuñiga Barrantes', '$2a$10$tsqRGNas84QPuzIWfRFeeug4SR2PdnuZpkLHqPsiQc7DiPfGRQWpi', 'Arturo', '88888888', TRUE),
('estudiante3@estudiante.com', 'Castro Fallas', '$2a$10$tsqRGNas84QPuzIWfRFeeug4SR2PdnuZpkLHqPsiQc7DiPfGRQWpi', 'David', '88888888', TRUE),
('estudiante4@estudiante.com', 'Hidalgo López', '$2a$10$tsqRGNas84QPuzIWfRFeeug4SR2PdnuZpkLHqPsiQc7DiPfGRQWpi', 'Francisco', '88888888', TRUE),
('teo@revisor.com', 'Willink', '$2a$10$tsqRGNas84QPuzIWfRFeeug4SR2PdnuZpkLHqPsiQc7DiPfGRQWpi', 'Teodoro', '88888888', TRUE),
('mariam@revisor.com', 'Mejías', '$2a$10$tsqRGNas84QPuzIWfRFeeug4SR2PdnuZpkLHqPsiQc7DiPfGRQWpi', 'Mariam', '88888888', TRUE),
('fabian@coordinador.com', 'Fabian', '$2a$10$tsqRGNas84QPuzIWfRFeeug4SR2PdnuZpkLHqPsiQc7DiPfGRQWpi', 'Meléndez', '8888888', TRUE),
('josed1608@gmail.com', 'Jose David', '$2a$10$tsqRGNas84QPuzIWfRFeeug4SR2PdnuZpkLHqPsiQc7DiPfGRQWpi', 'Vargas', '8888888', TRUE);

INSERT INTO estudiante (pk_correo_usuario, carne, estado, fecha_final, fecha_inicio, horas_totales, tipo) VALUES
('estudiante1@estudiante.com', 'B64895', 'Activo', '2020-05-20', '2019-05-20', 4, 'Regular'),
('estudiante2@estudiante.com', 'B47512', 'Activo', '2020-05-20', '2019-05-20', 2, 'Regular'),
('estudiante3@estudiante.com', 'B02541', 'Activo', '2020-05-20', '2019-05-20', 1, 'Regular'),
('estudiante4@estudiante.com', 'A94512', 'Activo', '2020-05-20', '2019-05-20', 30, 'Regular'),
('josed1608@gmail.com', 'B67437', 'Activo', '2020-05-20', '2019-05-20', 30, 'Regular');

INSERT INTO revisor (pk_correo_usuario, es_coordinador) VALUES
('teo@revisor.com', false),
('mariam@revisor.com', false),
('fabian@coordinador.com', true);

INSERT INTO categoria (pk_nombre, habilitado) VALUES
('Reunión', TRUE),
('Trabajo', TRUE),
('Gira', TRUE);

INSERT INTO proyecto (pk_nombre, descripcion, habilitado) VALUES
('Mapa de Osa', 'ProyectoEntidad para desarollar la aplicación del mapa de Osa', TRUE),
('Ingreso a la U', 'ProyectoEntidad encargado de divulgar información sobre la UCR a comunidades de Osa', TRUE),
('Factura electrónica', 'ProyectoEntidad encargado de informar a la comunidades de Osa sobre la factura electrónica', TRUE),
('RRSS', 'Talleres de redes sociales para las comunidades y emprendimientos', TRUE);

INSERT INTO estudiante_participa_proyecto (fk_estudiante_correo, fk_proyecto_nombre) VALUES
('estudiante1@estudiante.com', 'Mapa de Osa'),
('estudiante1@estudiante.com', 'Ingreso a la U'),
('estudiante1@estudiante.com', 'Factura electrónica'),
('estudiante1@estudiante.com', 'RRSS'),
('estudiante2@estudiante.com', 'Ingreso a la U'),
('estudiante3@estudiante.com', 'Factura electrónica'),
('estudiante3@estudiante.com', 'RRSS'),
('estudiante4@estudiante.com', 'RRSS'),
('josed1608@gmail.com', 'RRSS');

INSERT INTO actividad (fk_correo_estudiante, id_generado, detalles, estado, fecha, horas, justificacion_rechazo, fk_nombre_categoria, fk_nombre_proyecto, fk_correo_revisor) VALUES
('estudiante1@estudiante.com', 1, 'Desarrollar aplicación', 'Pendiente', '2019-07-12', 4, null, 'Trabajo', 'Mapa de Osa', null),
('estudiante1@estudiante.com', 2, 'Desarrollar aplicación', 'Aprobada', '2019-07-13', 3, null, 'Trabajo', 'Mapa de Osa', null),
('estudiante1@estudiante.com', 3, 'Desarrollar aplicación', 'Pendiente', '2019-06-14', 8, null, 'Trabajo', 'Mapa de Osa', null),
('estudiante1@estudiante.com', 4, 'Desarrollar aplicación', 'Pendiente', '2019-07-15', 10, null, 'Trabajo', 'Mapa de Osa', null),
('estudiante2@estudiante.com', 1, 'Redacción de material', 'Pendiente', '2019-07-12', 2, null, 'Trabajo', 'Ingreso a la U', null),
('estudiante3@estudiante.com', 1, 'Reunión de planificación', 'Pendiente', '2019-07-12', 1, null, 'Reunión', 'Factura electrónica', null),
('estudiante4@estudiante.com', 1, 'Gira', 'Aprobada', '2019-06-20', 30, '', 'Gira', 'RRSS', 'mariam@revisor.com');

