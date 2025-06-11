package com.daniel.jogodavelha;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;

public class TelaJogo extends Tela {

    private JButton[] botoes = new JButton[9];
    private final JogoDaVelha jogoDaVelha;

    public TelaJogo(JogoDaVelha jogoDaVelha) {
        super("Jogo da Velha", 400, 400, new Color(245, 245, 245));
        this.jogoDaVelha = jogoDaVelha;
        montarTela();
        setVisible(true);
    }

    @Override
    protected void montarTela() {
        painelPrincipal.setLayout(new GridLayout(3, 3, 5, 5));
        for (int i = 0; i < 9; i++) {
            int index = i;
            JButton botao = criarBotao(" ", null, () -> jogar(index));
            adicionarComponente(
                botao
            );
            this.botoes[i] = botao;
        }
    }

    private void jogar(int posicao) {
        if (jogoDaVelha.terminou()) {
            
            return;
        }

        if (!jogoDaVelha.isMultiplayer()) {

            jogoDaVelha.jogaJogador(posicao);
            atualizarTela();

            try {
                Thread.sleep(300);
            } catch(InterruptedException ignored) {}
            
            jogoDaVelha.jogaMaquina();
            atualizarTela();
        } else {

            jogoDaVelha.jogaJogador(posicao);
            atualizarTela();

        }
    }

    private void atualizarTela() {
        for (int i = 0; i < 9; i++) {
            botoes[i].setText(jogoDaVelha.getHistorico().getOrDefault(i, " "));
            botoes[i].setEnabled(jogoDaVelha.getHistorico().get(i) == null);
        }
    }


}
