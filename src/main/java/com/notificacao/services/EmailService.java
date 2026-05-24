package com.notificacao.services;

import com.notificacao.entities.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void enviarEmail(Pedido pedido) {
        SimpleMailMessage sms = new SimpleMailMessage();

        sms.setFrom("pedidos@kabum.com");
        sms.setTo(pedido.getEmailNotificacao());
        sms.setSubject("Pedido de Compra");
        sms.setText(gerarMensagem(pedido));

        mailSender.send(sms);
    }

    public String gerarMensagem(Pedido pedido) {
        Long id = pedido.getId();
        String cliente = pedido.getCliente();
        Double valor = pedido.getValorTotal();
        String status = pedido.getStatus().name();

        return "Olá " + cliente + " seu pedido " + id + " com valor total de R$" + valor + " está " + status.toUpperCase();
    }
}
