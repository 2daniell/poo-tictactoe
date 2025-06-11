package com.daniel.jogodavelha;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TelaPrincipal extends Tela {

    public TelaPrincipal() {
        super("Jogo da Velha - Menu", 400, 400, new Color(245, 245, 245));
        montarTela();
        setVisible(true);
    }

    @Override
    protected void montarTela() {
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));

        painelPrincipal.add(Box.createVerticalStrut(15));
        JLabel titulo = new JLabel("Jogo da Velha", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(titulo);

        painelPrincipal.add(Box.createVerticalStrut(55));

        adicionarComponente(
            criarBotao("Multiplayer", new Dimension(250, 50), () -> {
                new TelaJogo(new JogoDaVelha());
                dispose();
            })
        );

        painelPrincipal.add(Box.createVerticalStrut(15));

        adicionarComponente(
            criarBotao("Contra Máquina - Nível 1", new Dimension(250, 50), () -> {
                new TelaJogo(new JogoDaVelha(1));
                dispose();
            })
        );

        painelPrincipal.add(Box.createVerticalStrut(15));

        adicionarComponente(
            criarBotao("Contra Máquina - Nível 2", new Dimension(250, 50), () -> {
                new TelaJogo(new JogoDaVelha(2));
                dispose();
            })
        );
    }
    
}
