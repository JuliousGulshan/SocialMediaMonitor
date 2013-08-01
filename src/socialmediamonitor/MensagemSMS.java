package socialmediamonitor;

import java.util.ArrayList;
import java.util.List;

public class MensagemSMS {

	private String mensagem;
	private String classe;
	private List<String> mensagemTokenizada = new ArrayList<String>();
	private List<Double> tf = new ArrayList<Double>();
    private List<Double> tfIdf = new ArrayList<Double>();
    //Lista para armazenar o total de vezes que cada termo do vocabulario aparece no documento
    //Esta lista apresenta o mesmo tamanho da "mensagemTokenizada" e os valores fazem referencia
    //aos tokens
    private List<Integer> totalVezesTermoVocOnDoc = new ArrayList<Integer>();
    
	public MensagemSMS(String mensagem, String classe){
		this.mensagem = mensagem;
		this.classe = classe;
	}
	
	public MensagemSMS(){}
	
	
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public List<Double> getTf() {
        return tf;
    }
    public void setTf(List<Double> tf) {
        this.tf = tf;
    }
    public List<Double> getTfIdf() {
        return tfIdf;
    }
    public void setTfIdf(List<Double> tfIdf) {
        this.tfIdf = tfIdf;
    }

    public String getTfIdfAsString(){
        String out = "";
        for(double d : tfIdf){
            out += d + ",";
        }

        if(out.equals("") == false){
            out = out.substring(0, out.length()-1);
        }

        return out;
    }

	public List<String> getMensagemTokenizada() {
		return mensagemTokenizada;
	}

	public void setMensagemTokenizada(List<String> mensagemTokenizada) {
		this.mensagemTokenizada = mensagemTokenizada;
	}
	
	public List<Integer> getTotalVezesTermoVocOnDoc() {
		return totalVezesTermoVocOnDoc;
	}
	public void setTotalVezesTermoVocOnDoc(List<Integer> totalVezesTermoVocOnDoc) {
		this.totalVezesTermoVocOnDoc = totalVezesTermoVocOnDoc;
	}

}
