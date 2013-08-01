/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialmediamonitor;

import java.io.IOException;
import socialmediamonitor.dao.SocialMediaMonitorDAO;
import java.util.List;
/**
 *
 * @author afonso
 */
public class SocialMediaMonitor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        // CARREGA O VOCABULARIO
        String[] vocabulario = Loader.carregarVocabulario("vocabulario.txt");
        System.out.println(vocabulario);
        
        // CARREGA A BASE DE TREINAMENTO
        List<Message> baseTreinamento;
        baseTreinamento = Loader.carregarDocumentos("BaseTreinamento/PRO", "PRO");
        baseTreinamento.addAll(Loader.carregarDocumentos("BaseTreinamento/CONTRA", "CONTRA"));
        List<Double> idfResult;
        
        // TOKENIZA A BASE DE TREINAMENTO  E CALCULA TF
        for(Message message : baseTreinamento)
        {
            message.setMensagemTokenizada(Tokenizador.tokenizarPorPalavras(message.getMensagem()));
            TF_IDFUtils.calcularTF(message, vocabulario);
            // System.out.println(message.getMensagemTokenizada());	
        }
        
        // CALCULA O IDF
        idfResult = TF_IDFUtils.calcularIDF(baseTreinamento, vocabulario.length);
        
        // CALCULA O TF IDF
        TF_IDFUtils.calcularTF_IDF(baseTreinamento, idfResult);
        
        // GERA ARFF
        String[] valoresClasse = {"PRO", "CONTRA"};
        ARFF_Generator.gerarArffClassificacaoTF_IDF("./output.arff", baseTreinamento, valoresClasse, vocabulario);
        
        // TODO -> SUBMETER BASE DE TREINAMENTO AO WEKA
    }
}