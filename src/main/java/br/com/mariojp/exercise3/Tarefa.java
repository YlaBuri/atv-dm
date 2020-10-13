package br.com.mariojp.exercise3;

public class Tarefa implements Comparable<Tarefa>{
    private String descricao;
    private int prioridade;

    public Tarefa(String descricao, int prioridade) {
        this.descricao = descricao;
        this.prioridade = prioridade;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    @Override
    public String toString() {
        return  descricao + "\nPrioridade: "  + prioridade;
    }


    @Override
    public int compareTo(Tarefa o) {
         if (prioridade > o.prioridade) {
         return 1;
         } else if (prioridade < o.prioridade) {
         return -1;
         } else {
         return 0;
         }

    }
}
