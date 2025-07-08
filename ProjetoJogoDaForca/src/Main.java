import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> palavrasSecretas = new ArrayList<>();

        palavrasSecretas.add("cobra");
        palavrasSecretas.add("elefante");
        palavrasSecretas.add("girafa");

        Random random = new Random();
        int tamanhoDoArrayList = palavrasSecretas.size();
        int indiceDaPalavraAleatoria = random.nextInt(tamanhoDoArrayList);
        String palavraSecreta = palavrasSecretas.get(indiceDaPalavraAleatoria); //Seleciona a palavra aleatoriamente

        ArrayList<Character> letrasDescobertas = new ArrayList<>();

        for (int i = 0; i < palavraSecreta.length(); i++){
            letrasDescobertas.add('_');
        }

        int tentativas = 6;
        boolean palavraDescoberta = false;

        while (!palavraDescoberta && tentativas > 0){
            System.out.println();
            System.out.println("Palavra: " + letrasDescobertas);
            System.out.println("Chute uma letra: ");
            char chute = scanner.next().charAt(0);

            boolean acertou = false;
            for (int i = 0; i < palavraSecreta.length(); i++){
                if (palavraSecreta.charAt(i) == chute){
                    letrasDescobertas.set(i, chute);
                    acertou = true;
                }
            }
            if (!acertou){
                tentativas--;
                System.out.println("Você tem mais " + tentativas + " tentativas.");
            }

            //Verificar se a palavra foi descoberta
            palavraDescoberta = !letrasDescobertas.contains('_');
        }

        if (palavraDescoberta){
            System.out.println("Parabéns, você acertou a palavra era: " + palavraSecreta);
        } else {
            System.out.println("Você perdeu, a palavra era " + palavraSecreta);
        }
    }
}