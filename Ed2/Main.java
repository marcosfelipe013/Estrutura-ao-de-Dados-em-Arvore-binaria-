
import java.util.Random;
import java.util.Scanner;

public class Main {

    
    public static boolean estaOrdenado(int[] vetor) {
        for (int i = 0; i < vetor.length - 1; i++) {
            if (vetor[i] > vetor[i + 1]) {
                return false;
            }
        }
        return true;
    }

    
    public static void embaralhar(int[] vetor) {
        Random random = new Random();
        for (int i = 0; i < vetor.length; i++) {
            int j = random.nextInt(vetor.length);
            
            int temp = vetor[i];
            vetor[i] = vetor[j];
            vetor[j] = temp;
        }
    }

    public static void imprimirVetor(int[] vetor) {
        System.out.print("[ ");
        for (int i = 0; i < vetor.length; i++) {
            System.out.print(vetor[i] + (i == vetor.length - 1 ? "" : ", "));
        }
        System.out.println(" ]");
    }

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Bogo Sort Simulator ===");
        System.out.print("Digite o tamanho do vetor (recomendado entre 2 e 10): ");
        int n = scanner.nextInt();
        
        int[] vetor = new int[n];
        Random gerador = new Random();

        
        for (int i = 0; i < n; i++) {
            vetor[i] = gerador.nextInt(100);
        }

        System.out.print("\nVetor original: ");
        imprimirVetor(vetor);
        System.out.println("Ordenando... Aguarde (pode demorar dependendo da sorte!).\n");

        long inicioTempo = System.currentTimeMillis();
        long tentativas = 0;

        
        while (!estaOrdenado(vetor)) {
            embaralhar(vetor);
            tentativas++;
            
           
            if (tentativas % 1000000 == 0) {
                System.out.println("Tentativas: " + tentativas + "...");
            }
        }

        long fimTempo = System.currentTimeMillis();
        
        System.out.println("------------------------------------");
        System.out.print("Vetor ordenado: ");
        imprimirVetor(vetor);
        System.out.println("Total de tentativas: " + tentativas);
        System.out.println("Tempo decorrido: " + (fimTempo - inicioTempo) + "ms");
        System.out.println("------------------------------------");
        
        scanner.close();
    }
}