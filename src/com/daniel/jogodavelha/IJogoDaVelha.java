package com.daniel.jogodavelha;

import java.util.List;
import java.util.Map;

public interface IJogoDaVelha {

    boolean jogaJogador(int posicao);
    void jogaMaquina();
    boolean terminou();
    int getResultado();
    String getSimbolo();
    String getFoto();
    List<Integer> getPosicoesDisponiveis();
    Map<Integer, String> getHistorico();
    
}

