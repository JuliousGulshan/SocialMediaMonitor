package socialmediamonitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ContadorFrequencia {
    private static Map<String, Integer> mapa;

    // Cria Mapa baseado na entrada de usuario
    public static void criarMapa(String _str) {
        
            mapa = new HashMap<String, Integer>(); // Cria o HashMap

            // frase do usuario
            String input = _str;

            // Cria StringTokenizer para a frase de entrada
            StringTokenizer quebraFrase = new StringTokenizer(input);

            // Processamento de texto de entrada
            while (quebraFrase.hasMoreTokens())// Enquanto houver mais entradas
            {
                String palavra = quebraFrase.nextToken().toLowerCase(); 

                // Se o mapa tiver a palavra
                if (mapa.containsKey(palavra)) // Palavras esta no mapa
                {
                    int contador = mapa.get(palavra); // Obtem contagem atual
                    mapa.put(palavra, contador + 1); // Incrementa a contagem
                } else {
                    mapa.put(palavra, 1); // Adiciona uma nova palavra com
                }							// contagem de 1 ao mapa
            }

            criarVocabulario();
    }

    // Exibe conteudo do mapa
    private static void criarVocabulario() {
//        System.out.println(mapa.values());
//        System.out.println(mapa.keySet());
        
        ArrayList<String> arrayVocabulario = new ArrayList<String>();
        
        for (Map.Entry<String, Integer> entry : mapa.entrySet()) {
            String string = entry.getKey();
            Integer integer = entry.getValue();
            
            if (integer >= 5){
                arrayVocabulario.add(string);
            }
        }
        
        for (int i = 0; i < arrayVocabulario.size(); i++) {
            System.out.println(arrayVocabulario.get(i));
        }
    }
}