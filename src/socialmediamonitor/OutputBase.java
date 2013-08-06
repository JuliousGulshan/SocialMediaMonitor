/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialmediamonitor;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.process.DocumentPreprocessor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paulo
 */
public class OutputBase {

    public static String geraVocabulario(String texto) {
        DocumentPreprocessor dp = new DocumentPreprocessor(new StringReader(texto));

        List<String> tokens = new ArrayList<String>();
        for (List<HasWord> sentenceTokenized : dp) {
            for (HasWord w : sentenceTokenized) {
                tokens.add(w.toString());
                //  System.out.println(w.toString());
            }
        }

        String retorno = tokens.toString().replace(",", " ").toString();
        // System.out.println(aux);


//        aux[0] = dp.toString().replace(",", " ").toString();
//        aux[1] = aux[0].toString().replace("[", " ").toString();
//        aux[2] = aux[1].toString().replace("]", " ").toString();
//        aux[3] = aux[2].toString().replace("!", " ").toString();
//        aux[4] = aux[3].toString().replace(".", " ").toString();
//        aux[5] = aux[4].toString().replace("-", " ").toString();

        return retorno;
    }
    
    public static void salvaInfo(String aux) {

        FileOutputStream output;
        try {
            output = new FileOutputStream(new File("bagOfWordsOutput.txt"));
            PrintStream p = new PrintStream(output);
            try {
                output.write(aux.getBytes());
            } catch (IOException ex) {
                Logger.getLogger(Tokenizador.class.getName()).log(Level.SEVERE, null, ex);
            }
            p.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tokenizador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}