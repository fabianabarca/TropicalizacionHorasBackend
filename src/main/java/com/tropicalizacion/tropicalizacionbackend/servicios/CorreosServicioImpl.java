package com.tropicalizacion.tropicalizacionbackend.servicios;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class CorreosServicioImpl implements CorreosServicio {
    private JavaMailSender emailSender;

    public CorreosServicioImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void enviarContrasennaNueva(String contrasenna, String correo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(correo);
        message.setSubject("Bienvenido al TCU de Tropicalización De La Tecnología");
        message.setText("Su contraseña para ingresar al sistema de registro de horas del TCU es " + contrasenna);
        emailSender.send(message);
    }
}
