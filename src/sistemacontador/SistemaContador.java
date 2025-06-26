package sistemacontador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaContador {

    public static void main(String[] args) {
        List<Candidato> candidatos = analisarCandidatos();

        List<Candidato> selecionados = selecionarCandidatos(candidatos);

        imprimirSelecionados(selecionados);

        fazerLigacoes(selecionados);

        contadorInterativo();
    }

    static class Candidato {
        String nome;
        double nota;

        Candidato(String nome, double nota) {
            this.nome = nome;
            this.nota = nota;
        }
    }

    static List<Candidato> analisarCandidatos() {
        List<Candidato> lista = new ArrayList<>();
        lista.add(new Candidato("Ana", 8.5));
        lista.add(new Candidato("Bruno", 6.0));
        lista.add(new Candidato("Carlos", 9.2));
        lista.add(new Candidato("Diana", 4.7));
        lista.add(new Candidato("Eduardo", 7.8));
        return lista;
    }

    static List<Candidato> selecionarCandidatos(List<Candidato> candidatos) {
        List<Candidato> aprovados = new ArrayList<>();
        for (Candidato c : candidatos) {
            if (c.nota >= 7.0) {
                aprovados.add(c);
            }
        }
        return aprovados;
    }

    static void imprimirSelecionados(List<Candidato> selecionados) {
        System.out.println("\nCandidatos selecionados:");
        for (Candidato c : selecionados) {
            System.out.println("- " + c.nome + " (nota: " + c.nota + ")");
        }
    }

    static void fazerLigacoes(List<Candidato> selecionados) {
        System.out.println("\nFazendo ligações...");
        for (Candidato c : selecionados) {
            System.out.println("Ligando para " + c.nome + "...");
            try {
                Thread.sleep(1000); 
                System.out.println("Contato feito com " + c.nome);
            } catch (InterruptedException e) {
                System.out.println("Erro ao ligar para " + c.nome);
            }
        }
    }

    static void contadorInterativo() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("\nDigite o número inicial: ");
            int inicio = scanner.nextInt();

            System.out.print("Digite o número final: ");
            int fim = scanner.nextInt();

            if (fim < inicio) {
                throw new IllegalArgumentException("O número final deve ser maior ou igual ao inicial.");
            }

            System.out.println("Contando...");
            for (int i = inicio; i <= fim; i++) {
                System.out.println(i);
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
