package com.daniel.jogodavelha;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public class JogoDaVelha implements IJogoDaVelha {

    private static final String[] simbolos = {"X", "O"};
    private static final String[] celulas = new String[9];
    private final Map<Integer, String> historico = new LinkedHashMap<>();
    private final boolean multiplayer;
    private String simbolo;
    private int nivel; 
    private int jogadas;

    public JogoDaVelha(int nivel) {
        if (0 > nivel || nivel > 2) throw new IllegalArgumentException("O nivel do jogo só pode ser 1 ou 1");
        Arrays.fill(celulas, " ");
        this.nivel = nivel;
        this.multiplayer = false;
        this.simbolo = simbolos[0];
    }

    public JogoDaVelha() {
        this.simbolo = simbolos[0];
        Arrays.fill(celulas, " ");
        this.multiplayer = true;
    }

    @Override
    public boolean jogaJogador(int posicao) {
        if (posicao < 0 || posicao > 8 || !celulas[posicao].equals(" ")) return false;
        celulas[posicao] = simbolo;
        historico.put(posicao, simbolo);
        jogadas++;
        trocarJogador();
        return true;
    }

    @Override
    public void jogaMaquina() {
        if (multiplayer) throw new UnsupportedOperationException("Não é possivel chamar esse metodo em um jogo multiplayer");
        if (terminou()) return;

        int posicao;
        List<Integer> livres = getPosicoesDisponiveis();
        if (nivel == 2) {
            
            posicao = jogadaEsperta();

        } else {
            if (livres.size() < 1) return;
            posicao = livres.get(new Random().nextInt(livres.size()));
        }

        jogaJogador(posicao);

    }

    @Override
    public boolean terminou() {
        return ganhou("X") || ganhou("O") || jogadas > 9;
    }

    @Override
    public int getResultado() {
        if (!terminou()) return -1;
        if (ganhou("X")) return 1;
        if (ganhou("O")) return 2;
        return 0;
    }

    @Override
    public String getSimbolo() {
        return simbolo;
    }

    @Override
    public String getFoto() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            builder.append(celulas[i]).append((i % 3 == 2) ? "\n" : " ");
        }
        return builder.toString();
    }

    @Override
    public List<Integer> getPosicoesDisponiveis() {
        return IntStream.range(0, celulas.length).filter(i -> celulas[i].equals(" ")).boxed().toList();
    }

    @Override
    public Map<Integer, String> getHistorico() {
        return historico;
    }

    public boolean ganhou(String simbolo) {
        int[][] combinacoes = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };
        for (int[] seq : combinacoes) {
            if (simbolo.equals(celulas[seq[0]]) &&
                simbolo.equals(celulas[seq[1]]) &&
                simbolo.equals(celulas[seq[2]])) {
                return true;
            }
        }
        return false;

    }

    public void trocarJogador() {
        if (simbolo == "X") {
            simbolo = "O";
        } else {
            simbolo = "X";
        }
    }

    public int jogadaEsperta() {
        List<Integer> livres = getPosicoesDisponiveis();

        for (int pos : livres) {
            celulas[pos] = simbolos[1];
            if (ganhou(simbolos[1])) {
                celulas[pos] = " ";
                return pos;
            }
            celulas[pos] = " ";
        }

        for (int pos : livres) {
            celulas[pos] = simbolos[0];
            if (ganhou(simbolos[0])) {
                celulas[pos] = " ";
                return pos;
            }
            celulas[pos] = " ";
        }

        return livres.get(new Random().nextInt(livres.size()));
    }


    public boolean isMultiplayer() {
        return multiplayer;
    }
}
