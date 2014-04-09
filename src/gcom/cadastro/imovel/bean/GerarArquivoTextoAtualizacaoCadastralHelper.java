package gcom.cadastro.imovel.bean;

import java.io.Serializable;
import java.util.Collection;

/**
 * Esta classe tem a finalidade de encapsular as informações necessárias para inserir 
 * um arquivo texto atualizacao cadastral
 * 
 * @author Ana Maria
 * @date 22/09/2008
 */
public class GerarArquivoTextoAtualizacaoCadastralHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private String descricao;
	
	private Integer idLeiturista;
	
	private Integer idLocalidade;
	
	private Integer setorComercialCD;
	
	private Integer numeroQuadraInicial;
	
	private Integer numeroQuadraFinal;
	
	private Integer rotaCD;
	
	private Integer situacao;
	
	private Integer qtdImovel;
	
	private Collection colecaoImovel;
	
	
	public GerarArquivoTextoAtualizacaoCadastralHelper(){}


	/**
	 * @return Retorna o campo colecaoImovel.
	 */
	public Collection getColecaoImovel() {
		return colecaoImovel;
	}


	/**
	 * @param colecaoImovel O colecaoImovel a ser setado.
	 */
	public void setColecaoImovel(Collection colecaoImovel) {
		this.colecaoImovel = colecaoImovel;
	}


	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}


	/**
	 * @param descricao O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	/**
	 * @return Retorna o campo idLeiturista.
	 */
	public Integer getIdLeiturista() {
		return idLeiturista;
	}


	/**
	 * @param idLeiturista O idLeiturista a ser setado.
	 */
	public void setIdLeiturista(Integer idLeiturista) {
		this.idLeiturista = idLeiturista;
	}


	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public Integer getIdLocalidade() {
		return idLocalidade;
	}


	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}


	/**
	 * @return Retorna o campo numeroQuadraFinal.
	 */
	public Integer getNumeroQuadraFinal() {
		return numeroQuadraFinal;
	}


	/**
	 * @param numeroQuadraFinal O numeroQuadraFinal a ser setado.
	 */
	public void setNumeroQuadraFinal(Integer numeroQuadraFinal) {
		this.numeroQuadraFinal = numeroQuadraFinal;
	}


	/**
	 * @return Retorna o campo numeroQuadraInicial.
	 */
	public Integer getNumeroQuadraInicial() {
		return numeroQuadraInicial;
	}


	/**
	 * @param numeroQuadraInicial O numeroQuadraInicial a ser setado.
	 */
	public void setNumeroQuadraInicial(Integer numeroQuadraInicial) {
		this.numeroQuadraInicial = numeroQuadraInicial;
	}


	/**
	 * @return Retorna o campo qtdImovel.
	 */
	public Integer getQtdImovel() {
		return qtdImovel;
	}


	/**
	 * @param qtdImovel O qtdImovel a ser setado.
	 */
	public void setQtdImovel(Integer qtdImovel) {
		this.qtdImovel = qtdImovel;
	}


	/**
	 * @return Retorna o campo rotaCD.
	 */
	public Integer getRotaCD() {
		return rotaCD;
	}


	/**
	 * @param rotaCD O rotaCD a ser setado.
	 */
	public void setRotaCD(Integer rotaCD) {
		this.rotaCD = rotaCD;
	}


	/**
	 * @return Retorna o campo setorComercialCD.
	 */
	public Integer getSetorComercialCD() {
		return setorComercialCD;
	}


	/**
	 * @param setorComercialCD O setorComercialCD a ser setado.
	 */
	public void setSetorComercialCD(Integer setorComercialCD) {
		this.setorComercialCD = setorComercialCD;
	}


	/**
	 * @return Retorna o campo situacao.
	 */
	public Integer getSituacao() {
		return situacao;
	}


	/**
	 * @param situacao O situacao a ser setado.
	 */
	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}
	

}
