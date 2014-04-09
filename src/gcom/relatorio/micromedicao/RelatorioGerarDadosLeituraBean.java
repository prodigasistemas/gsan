package gcom.relatorio.micromedicao;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Rafael Corrêa
 * @created 08/08/2006
 */
/**
 * @author Administrador
 *
 */
public class RelatorioGerarDadosLeituraBean implements RelatorioBean {

	private String anoMes;
	
	private String grupo;
	
	private String localidade;
	
	private String dataPrevista;
	
	private String rota;
	
	private String leiturista;
	
	private String endereco;

	private String inscricao;

	private String nomeCliente;

	private String categoria;
	
	private String economias;
	
	private String situacaoLigacaoAgua;
	
	private String situacaoLigacaoEsgoto;
	
	private String grandeConsumidor;
	
	private String numeroHidrometro;
	
	private String media;
	
	private String sequencia;
	
	private String complementoSequenciaLeitura;
    
    private String matriculaImovel;

	
    public String getMatriculaImovel() {
        return matriculaImovel;
    }

    
    public void setMatriculaImovel(String matriculaImovel) {
        this.matriculaImovel = matriculaImovel;
    }

    public RelatorioGerarDadosLeituraBean(String anoMes, String grupo, String localidade,
			String dataPrevista, String rota, String leiturista,
			String endereco, String inscricao, String nomeCliente,
			String categoria, String economias, String situacaoLigacaoAgua,
			String situacaoLigacaoEsgoto, String grandeConsumidor,
			String numeroHidrometro, String media, String sequencia,
			String complementoSequenciaLeitura, String matriculaImovel ) {
		this.anoMes = anoMes;
		this.grupo = grupo;
		this.localidade = localidade;
		this.dataPrevista = dataPrevista;
		this.rota = rota;
		this.leiturista = leiturista;
		this.endereco = endereco;
		this.inscricao = inscricao;
		this.nomeCliente = nomeCliente;
		this.categoria = categoria;
		this.economias = economias;
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
		this.grandeConsumidor = grandeConsumidor;
		this.numeroHidrometro = numeroHidrometro;
		this.media = media;
		this.sequencia = sequencia;
		this.complementoSequenciaLeitura = complementoSequenciaLeitura;
        this.matriculaImovel = matriculaImovel;
	}
	
	public RelatorioGerarDadosLeituraBean() {
		
	}

	/**
	 * @return Retorna o campo categoria.
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return Retorna o campo complementoSequenciaLeitura.
	 */
	public String getComplementoSequenciaLeitura() {
		return complementoSequenciaLeitura;
	}

	/**
	 * @param complementoSequenciaLeitura O complementoSequenciaLeitura a ser setado.
	 */
	public void setComplementoSequenciaLeitura(String complementoSequenciaLeitura) {
		this.complementoSequenciaLeitura = complementoSequenciaLeitura;
	}

	/**
	 * @return Retorna o campo dataPrevista.
	 */
	public String getDataPrevista() {
		return dataPrevista;
	}

	/**
	 * @param dataPrevista O dataPrevista a ser setado.
	 */
	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	/**
	 * @return Retorna o campo economias.
	 */
	public String getEconomias() {
		return economias;
	}

	/**
	 * @param economias O economias a ser setado.
	 */
	public void setEconomias(String economias) {
		this.economias = economias;
	}

	/**
	 * @return Retorna o campo endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco O endereco a ser setado.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return Retorna o campo grandeConsumidor.
	 */
	public String getGrandeConsumidor() {
		return grandeConsumidor;
	}

	/**
	 * @param grandeConsumidor O grandeConsumidor a ser setado.
	 */
	public void setGrandeConsumidor(String grandeConsumidor) {
		this.grandeConsumidor = grandeConsumidor;
	}

	/**
	 * @return Retorna o campo grupo.
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * @param grupo O grupo a ser setado.
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	/**
	 * @return Retorna o campo hidrometro.
	 */
	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	/**
	 * @param hidrometro O hidrometro a ser setado.
	 */
	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	/**
	 * @return Retorna o campo inscricao.
	 */
	public String getInscricao() {
		return inscricao;
	}

	/**
	 * @param inscricao O inscricao a ser setado.
	 */
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	/**
	 * @return Retorna o campo leiturista.
	 */
	public String getLeiturista() {
		return leiturista;
	}

	/**
	 * @param leiturista O leiturista a ser setado.
	 */
	public void setLeiturista(String leiturista) {
		this.leiturista = leiturista;
	}

	/**
	 * @return Retorna o campo localidade.
	 */
	public String getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo media.
	 */
	public String getMedia() {
		return media;
	}

	/**
	 * @param media O media a ser setado.
	 */
	public void setMedia(String media) {
		this.media = media;
	}

	/**
	 * @return Retorna o campo nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente O nomeCliente a ser setado.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return Retorna o campo rota.
	 */
	public String getRota() {
		return rota;
	}

	/**
	 * @param rota O rota a ser setado.
	 */
	public void setRota(String rota) {
		this.rota = rota;
	}

	/**
	 * @return Retorna o campo sequencia.
	 */
	public String getSequencia() {
		return sequencia;
	}

	/**
	 * @param sequencia O sequencia a ser setado.
	 */
	public void setSequencia(String sequencia) {
		this.sequencia = sequencia;
	}

	/**
	 * @return Retorna o campo situacaoLigacaoAgua.
	 */
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	/**
	 * @param situacaoLigacaoAgua O situacaoLigacaoAgua a ser setado.
	 */
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	/**
	 * @return Retorna o campo situacaoLigacaoEsgoto.
	 */
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	/**
	 * @param situacaoLigacaoEsgoto O situacaoLigacaoEsgoto a ser setado.
	 */
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	/**
	 * @return Retorna o campo anoMes.
	 */
	public String getAnoMes() {
		return anoMes;
	}

	/**
	 * @param anoMes O anoMes a ser setado.
	 */
	public void setAnoMes(String anoMes) {
		this.anoMes = anoMes;
	}
	
	



}
