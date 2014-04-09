package gcom.micromedicao;

import java.io.Serializable;


/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatorio de imoveis por Tipo de Consumo 
 *
 * @author Bruno Barros
 * @date 10/01/2008
 */
public class StringArquivoTextoLigacoesHidrometroHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String matriculaImovel;
	private String numeroHidrometro;
	private String localidazacaoHidrometro;
	private String capacidadeHidrometro;
	
	public String getCapacidadeHidrometro() {
		return capacidadeHidrometro;
	}
	public void setCapacidadeHidrometro(String capacidadeHidrometro) {
		this.capacidadeHidrometro = capacidadeHidrometro;
	}
	public String getLocalidazacaoHidrometro() {
		return localidazacaoHidrometro;
	}
	public void setLocalidazacaoHidrometro(String localidazacaoHidrometro) {
		this.localidazacaoHidrometro = localidazacaoHidrometro;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}
	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}
	
}
