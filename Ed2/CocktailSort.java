import java.util.Scanner;
import java.util.Random;

public class CocktailSort {

    private int[] vetor;
    private int tamanho;

    public CocktailSort(int tamanho) {
        this.tamanho = tamanho;
        this.vetor = new int[tamanho];
    }

    public void preencherVetor(Scanner scanner) {
        for (int i = 0; i < tamanho; i++) {
            System.out.print("Digite o valor para a posição " + i + ": ");
            vetor[i] = scanner.nextInt();
        }
    }

    public int ordenar() {
        boolean trocado = true;
        int inicio = 0;
        int fim = tamanho - 1;
        int tentativas = 0;

        while (trocado) {
            trocado = false;

            for (int i = inicio; i < fim; i++) {
                tentativas++;
                if (vetor[i] > vetor[i + 1]) {
                    int temp = vetor[i];
                    vetor[i] = vetor[i + 1];
                    vetor[i + 1] = temp;
                    trocado = true;
                }
            }

            if (!trocado) break;

            fim--;
            trocado = false;

            for (int i = fim; i > inicio; i--) {
                tentativas++;
                if (vetor[i] < vetor[i - 1]) {
                    int temp = vetor[i];
                    vetor[i] = vetor[i - 1];
                    vetor[i - 1] = temp;
                    trocado = true;
                }
            }

            inicio++;
        }

        return tentativas;
    }

    public void exibirVetor() {
        for (int v : vetor) {
            System.out.print(v + " ");
        }
        System.out.println();
    }

    public static void sortearDoisIndices(int[] vetor) {
        Random random = new Random();
    
        int indice1 = random.nextInt(vetor.length);
        int indice2;
    
        do {
            indice2 = random.nextInt(vetor.length);
        } while (indice2 == indice1);
    
        System.out.println("Índice 1: " + indice1 + " -> " + vetor[indice1]);
        System.out.println("Índice 2: " + indice2 + " -> " + vetor[indice2]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Digite o tamanho do vetor: ");
        int n = scanner.nextInt();

        CocktailSort ordenacao = new CocktailSort(n);
        ordenacao.preencherVetor(scanner);

        long inicio = System.currentTimeMillis();
        int tentativas = ordenacao.ordenar();
        long fim = System.currentTimeMillis();

        System.out.print("Vetor ordenado: ");
        ordenacao.exibirVetor();

        int indiceSorteado = random.nextInt(n);
        System.out.println("Índice sorteado: " + indiceSorteado);
        System.out.println("Valor na posição sorteada: " + ordenacao.vetor[indiceSorteado]);
        

        System.out.println("Tentativas: " + tentativas);
        System.out.println("Tempo decorrido: " + (fim - inicio) + " ms");

        scanner.close();
    }
}