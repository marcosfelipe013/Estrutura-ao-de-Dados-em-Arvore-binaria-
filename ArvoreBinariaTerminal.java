import java.util.*;

// Classe que representa um nó da árvore
class Node {
    int valor;
    Node esquerdo, direito;

    public Node(int item) {
        valor = item;
        esquerdo = direito = null;
    }
}

// Classe principal da Árvore Binária de Busca
public class ArvoreBinariaTerminal {
    Node raiz;

    // Método para inserir um novo valor
    void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }

    Node inserirRecursivo(Node raiz, int valor) {
        if (raiz == null) {
            raiz = new Node(valor);
            return raiz;
        }
        if (valor < raiz.valor)
            raiz.esquerdo = inserirRecursivo(raiz.esquerdo, valor);
        else if (valor > raiz.valor)
            raiz.direito = inserirRecursivo(raiz.direito, valor);
        return raiz;
    }

    // Método para remover um valor
    void remover(int valor) {
        raiz = removerRecursivo(raiz, valor);
    }

    Node removerRecursivo(Node raiz, int valor) {
        if (raiz == null) return raiz;

        // Percorre a árvore para encontrar o nó
        if (valor < raiz.valor)
            raiz.esquerdo = removerRecursivo(raiz.esquerdo, valor);
        else if (valor > raiz.valor)
            raiz.direito = removerRecursivo(raiz.direito, valor);
        else {
            // Nó com apenas um filho ou nenhum
            if (raiz.esquerdo == null) return raiz.direito;
            else if (raiz.direito == null) return raiz.esquerdo;

            // Nó com dois filhos: pega o menor valor da subárvore direita
            raiz.valor = valorMinimo(raiz.direito);
            // Remove o sucessor
            raiz.direito = removerRecursivo(raiz.direito, raiz.valor);
        }
        return raiz;
    }

    int valorMinimo(Node raiz) {
        int minv = raiz.valor;
        while (raiz.esquerdo != null) {
            minv = raiz.esquerdo.valor;
            raiz = raiz.esquerdo;
        }
        return minv;
    }

    // 1) Pré-ordem (Raiz, Esquerda, Direita)
    void preOrdem(Node no) {
        if (no != null) {
            System.out.print(no.valor + " ");
            preOrdem(no.esquerdo);
            preOrdem(no.direito);
        }
    }

    // 2) In-ordem (Esquerda, Raiz, Direita)
    void inOrdem(Node no) {
        if (no != null) {
            inOrdem(no.esquerdo);
            System.out.print(no.valor + " ");
            inOrdem(no.direito);
        }
    }

    // 3) Pós-ordem (Esquerda, Direita, Raiz)
    void posOrdem(Node no) {
        if (no != null) {
            posOrdem(no.esquerdo);
            posOrdem(no.direito);
            System.out.print(no.valor + " ");
        }
    }

    // 4) Em nível (Busca em Largura / BFS)
    void emNivel() {
        if (raiz == null) return;
        Queue<Node> fila = new LinkedList<>();
        fila.add(raiz);
        while (!fila.isEmpty()) {
            Node noTemp = fila.poll();
            System.out.print(noTemp.valor + " ");
            if (noTemp.esquerdo != null) fila.add(noTemp.esquerdo);
            if (noTemp.direito != null) fila.add(noTemp.direito);
        }
    }

    // --- MÉTODO PRINCIPAL (MAIN) ---
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArvoreBinariaTerminal arvore = new ArvoreBinariaTerminal();
        Random rand = new Random();
        
        // Usamos um Set para garantir que teremos 20 números únicos entre 0 e 100
        Set<Integer> numerosSorteados = new LinkedHashSet<>();
        while (numerosSorteados.size() < 20) {
            numerosSorteados.add(rand.nextInt(101)); // 0 a 100
        }

        // Lista auxiliar para sabermos quais números estão na árvore (útil para a remoção)
        List<Integer> listaNumeros = new ArrayList<>(numerosSorteados);

        System.out.println("Sorteando e inserindo 20 números na árvore...");
        for (int num : listaNumeros) {
            System.out.print(num + " ");
            arvore.inserir(num);
        }
        System.out.println("\n");

        int opcao;
        do {
            System.out.println("\n=================================");
            System.out.println("   ESCOLHA O TIPO DE IMPRESSÃO");
            System.out.println("=================================");
            System.out.println("1) Pré-ordem");
            System.out.println("2) In-ordem");
            System.out.println("3) Pós-ordem");
            System.out.println("4) Em nível");
            System.out.println("5) Remover 5 números aleatórios e refazer todas as impressões");
            System.out.println("0) Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Pré-ordem: ");
                    arvore.preOrdem(arvore.raiz);
                    System.out.println();
                    break;
                case 2:
                    System.out.print("In-ordem: ");
                    arvore.inOrdem(arvore.raiz);
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Pós-ordem: ");
                    arvore.posOrdem(arvore.raiz);
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Em nível: ");
                    arvore.emNivel();
                    System.out.println();
                    break;
                case 5:
                    if (listaNumeros.size() < 5) {
                        System.out.println("Não há números suficientes para remover.");
                        break;
                    }
                    System.out.println("\n--- Removendo 5 elementos aleatórios ---");
                    Collections.shuffle(listaNumeros); // Embaralha para pegar 5 aleatórios
                    for (int i = 0; i < 5; i++) {
                        int numRemover = listaNumeros.get(0);
                        arvore.remover(numRemover);
                        listaNumeros.remove(0);
                        System.out.println("Removido: " + numRemover);
                    }
                    
                    System.out.println("\nRefazendo as impressões solicitadas após a remoção:");
                    System.out.print("1) Pré-ordem: ");
                    arvore.preOrdem(arvore.raiz);
                    
                    System.out.print("\n2) In-ordem: ");
                    arvore.inOrdem(arvore.raiz);
                    
                    System.out.print("\n3) Pós-ordem: ");
                    arvore.posOrdem(arvore.raiz);
                    
                    System.out.print("\n4) Em nível: ");
                    arvore.emNivel();
                    System.out.println();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}