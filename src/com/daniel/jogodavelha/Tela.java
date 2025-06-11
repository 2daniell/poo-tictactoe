package com.daniel.jogodavelha;

import javax.swing.*;
import java.awt.*;

public abstract class Tela extends JFrame {

    protected JPanel painelPrincipal;

    public Tela(String titulo, int largura, int altura, Color background) {
        super(titulo);
        setSize(largura, altura);
        setBackground(background);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        painelPrincipal = new JPanel();
        add(painelPrincipal);
        setResizable(false);
    }

    protected JButton criarBotao(String texto, Dimension maximumSize, Runnable acao) {
        JButton botao = new JButton(texto);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        if (maximumSize != null) botao.setMaximumSize(maximumSize);
        botao.setFont(new Font("Arial", Font.PLAIN, 18));
        botao.setFocusPainted(false);
        botao.setBackground(new Color(16, 135, 245));
        botao.setForeground(Color.WHITE);
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.setBorderPainted(false);
        if (acao != null) botao.addActionListener(e -> acao.run());
        return botao;
    }

    protected void adicionarComponente(Component comp) {
        painelPrincipal.add(comp);
    }

    protected abstract void montarTela();

    
}
