package gcom.gui.micromedicao.hidrometro;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 * @created 5 de Setembro de 2005
 */
public class AtualizarHidrometroActionForm extends ActionForm {
 
	private static final long serialVersionUID = 1L;

	private String anoFabricacao;
	
	private String idHidrometro;

	private String dataAquisicao;

	private String faixaFinal;

	private String faixaInicial;

	private String fixo;

	private String idHidrometroCapacidade;

	private String idHidrometroClasseMetrologica;

	private String idHidrometroDiametro;

	private String idHidrometroTipo;

	private String idLocalArmazenagem;

	private String localArmazenagemDescricao;

	private String localArmazenagemDescricaoOrigem;

	private String localArmazenagemDescricaoDestino;

	private String idHidrometroMarca;

	private String idNumeroDigitosLeitura;

	private String indicadorMacromedidor;

	private String numeroHidrometro;

	private String[] hidrometroSelectID;

	private String dataMovimentacaoInicial;

	private String dataMovimentacaoFinal;

	private String horaMovimentacaoInicial;

	private String horaMovimentacaoFinal;

	private String motivoMovimentacao;

	private String localArmazenagemDestino;

	private String localArmazenagemOrigem;

	private String usuario;
	
	private String nomeUsuario;

	private String idHidrometroSituacao;
	
	private String idHidrometroRelojoaria;
	
	private String idImovel;
	
	private String vazaoTransicao;
	
	private String vazaoNominal;
	
	private String vazaoMinima;
	
	private String notaFiscal;
	
	private String tempoGarantiaAnos;

	// essa variável é usada na tela de manter do hidrometro
	// para verificar se o botão atualizarConjuntoHidrometro vai ficar
	// abilitado ou não.
	private String conjuntoHidrometro;

	/**
	 * Retorna o valor de anoFabricacao
	 * 
	 * @return O valor de anoFabricacao
	 */
	public String getAnoFabricacao() {
		return anoFabricacao;
	}

	/**
	 * Seta o valor de anoFabricacao
	 * 
	 * @param anoFabricacao
	 *            O novo valor de anoFabricacao
	 */
	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	/**
	 * Retorna o valor de dataAquisicao
	 * 
	 * @return O valor de dataAquisicao
	 */
	public String getDataAquisicao() {
		return dataAquisicao;
	}

	/**
	 * Seta o valor de dataAquisicao
	 * 
	 * @param dataAquisicao
	 *            O novo valor de dataAquisicao
	 */
	public void setDataAquisicao(String dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	/**
	 * Retorna o valor de faixaFinal
	 * 
	 * @return O valor de faixaFinal
	 */
	public String getFaixaFinal() {
		return faixaFinal;
	}

	/**
	 * Seta o valor de faixaFinal
	 * 
	 * @param faixaFinal
	 *            O novo valor de faixaFinal
	 */
	public void setFaixaFinal(String faixaFinal) {
		this.faixaFinal = faixaFinal;
	}

	/**
	 * Retorna o valor de faixaInicial
	 * 
	 * @return O valor de faixaInicial
	 */
	public String getFaixaInicial() {
		return faixaInicial;
	}

	/**
	 * Seta o valor de faixaInicial
	 * 
	 * @param faixaInicial
	 *            O novo valor de faixaInicial
	 */
	public void setFaixaInicial(String faixaInicial) {
		this.faixaInicial = faixaInicial;
	}

	/**
	 * Retorna o valor de fixo
	 * 
	 * @return O valor de fixo
	 */
	public String getFixo() {
		return fixo;
	}

	/**
	 * Seta o valor de fixo
	 * 
	 * @param fixo
	 *            O novo valor de fixo
	 */
	public void setFixo(String fixo) {
		this.fixo = fixo;
	}

	/**
	 * Retorna o valor de idHidrometroCapacidade
	 * 
	 * @return O valor de idHidrometroCapacidade
	 */
	public String getIdHidrometroCapacidade() {
		return idHidrometroCapacidade;
	}

	/**
	 * Seta o valor de idHidrometroCapacidade
	 * 
	 * @param idHidrometroCapacidade
	 *            O novo valor de idHidrometroCapacidade
	 */
	public void setIdHidrometroCapacidade(String idHidrometroCapacidade) {
		this.idHidrometroCapacidade = idHidrometroCapacidade;
	}

	/**
	 * Retorna o valor de idHidrometroClasseMetrologica
	 * 
	 * @return O valor de idHidrometroClasseMetrologica
	 */
	public String getIdHidrometroClasseMetrologica() {
		return idHidrometroClasseMetrologica;
	}

	/**
	 * Seta o valor de idHidrometroClasseMetrologica
	 * 
	 * @param idHidrometroClasseMetrologica
	 *            O novo valor de idHidrometroClasseMetrologica
	 */
	public void setIdHidrometroClasseMetrologica(
			String idHidrometroClasseMetrologica) {
		this.idHidrometroClasseMetrologica = idHidrometroClasseMetrologica;
	}

	/**
	 * Retorna o valor de idHidrometroDiametro
	 * 
	 * @return O valor de idHidrometroDiametro
	 */
	public String getIdHidrometroDiametro() {
		return idHidrometroDiametro;
	}

	/**
	 * Seta o valor de idHidrometroDiametro
	 * 
	 * @param idHidrometroDiametro
	 *            O novo valor de idHidrometroDiametro
	 */
	public void setIdHidrometroDiametro(String idHidrometroDiametro) {
		this.idHidrometroDiametro = idHidrometroDiametro;
	}

	/**
	 * Retorna o valor de idHidrometroTipo
	 * 
	 * @return O valor de idHidrometroTipo
	 */
	public String getIdHidrometroTipo() {
		return idHidrometroTipo;
	}

	/**
	 * Seta o valor de idHidrometroTipo
	 * 
	 * @param idHidrometroTipo
	 *            O novo valor de idHidrometroTipo
	 */
	public void setIdHidrometroTipo(String idHidrometroTipo) {
		this.idHidrometroTipo = idHidrometroTipo;
	}

	/**
	 * Retorna o valor de idLocalArmazenagem
	 * 
	 * @return O valor de idLocalArmazenagem
	 */
	public String getIdLocalArmazenagem() {
		return idLocalArmazenagem;
	}

	/**
	 * Seta o valor de idLocalArmazenagem
	 * 
	 * @param idLocalArmazenagem
	 *            O novo valor de idLocalArmazenagem
	 */
	public void setIdLocalArmazenagem(String idLocalArmazenagem) {
		this.idLocalArmazenagem = idLocalArmazenagem;
	}

	/**
	 * Retorna o valor de indicadorMacromedidor
	 * 
	 * @return O valor de indicadorMacromedidor
	 */
	public String getIndicadorMacromedidor() {
		return indicadorMacromedidor;
	}

	/**
	 * Seta o valor de indicadorMacromedidor
	 * 
	 * @param indicadorMacromedidor
	 *            O novo valor de indicadorMacromedidor
	 */
	public void setIndicadorMacromedidor(String indicadorMacromedidor) {
		this.indicadorMacromedidor = indicadorMacromedidor;
	}

	/**
	 * Retorna o valor de localArmazenagemDescricao
	 * 
	 * @return O valor de localArmazenagemDescricao
	 */
	public String getLocalArmazenagemDescricao() {
		return localArmazenagemDescricao;
	}

	/**
	 * Seta o valor de localArmazenagemDescricao
	 * 
	 * @param localArmazenagemDescricao
	 *            O novo valor de localArmazenagemDescricao
	 */
	public void setLocalArmazenagemDescricao(String localArmazenagemDescricao) {
		this.localArmazenagemDescricao = localArmazenagemDescricao;
	}

	/**
	 * Retorna o valor de idHidrometroMarca
	 * 
	 * @return O valor de idHidrometroMarca
	 */
	public String getIdHidrometroMarca() {
		return idHidrometroMarca;
	}

	/**
	 * Seta o valor de idHidrometroMarca
	 * 
	 * @param idHidrometroMarca
	 *            O novo valor de idHidrometroMarca
	 */
	public void setIdHidrometroMarca(String idHidrometroMarca) {
		this.idHidrometroMarca = idHidrometroMarca;
	}

	/**
	 * Retorna o valor de idNumeroDigitosLeitura
	 * 
	 * @return O valor de idNumeroDigitosLeitura
	 */
	public String getIdNumeroDigitosLeitura() {
		return idNumeroDigitosLeitura;
	}

	/**
	 * Seta o valor de idNumeroDigitosLeitura
	 * 
	 * @param idNumeroDigitosLeitura
	 *            O novo valor de idNumeroDigitosLeitura
	 */
	public void setIdNumeroDigitosLeitura(String idNumeroDigitosLeitura) {
		this.idNumeroDigitosLeitura = idNumeroDigitosLeitura;
	}

	/**
	 * Gets the numeroHidrometro attribute of the HidrometroActionForm object
	 * 
	 * @return The numeroHidrometro value
	 */
	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	/**
	 * Sets the numeroHidrometro attribute of the HidrometroActionForm object
	 * 
	 * @param numeroHidrometro
	 *            The new numeroHidrometro value
	 */
	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	public String[] getHidrometroSelectID() {
		return hidrometroSelectID;
	}

	public void setHidrometroSelectID(String[] hidrometroSelectID) {
		this.hidrometroSelectID = hidrometroSelectID;
	}

	public String getConjuntoHidrometro() {
		return conjuntoHidrometro;
	}

	public void setConjuntoHidrometro(String conjuntoHidrometro) {
		this.conjuntoHidrometro = conjuntoHidrometro;
	}

	public String getDataMovimentacaoFinal() {
		return dataMovimentacaoFinal;
	}

	public void setDataMovimentacaoFinal(String dataMovimentacaoFinal) {
		this.dataMovimentacaoFinal = dataMovimentacaoFinal;
	}

	public String getDataMovimentacaoInicial() {
		return dataMovimentacaoInicial;
	}

	public void setDataMovimentacaoInicial(String dataMovimentacaoInicial) {
		this.dataMovimentacaoInicial = dataMovimentacaoInicial;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getHoraMovimentacaoFinal() {
		return horaMovimentacaoFinal;
	}

	public void setHoraMovimentacaoFinal(String horaMovimentacaoFinal) {
		this.horaMovimentacaoFinal = horaMovimentacaoFinal;
	}

	public String getHoraMovimentacaoInicial() {
		return horaMovimentacaoInicial;
	}

	public void setHoraMovimentacaoInicial(String horaMovimentacaoInicial) {
		this.horaMovimentacaoInicial = horaMovimentacaoInicial;
	}

	public String getLocalArmazenagemDestino() {
		return localArmazenagemDestino;
	}

	public void setLocalArmazenagemDestino(String localArmazenagemDestino) {
		this.localArmazenagemDestino = localArmazenagemDestino;
	}

	public String getLocalArmazenagemOrigem() {
		return localArmazenagemOrigem;
	}

	public void setLocalArmazenagemOrigem(String localArmazenagemOrigem) {
		this.localArmazenagemOrigem = localArmazenagemOrigem;
	}

	public String getMotivoMovimentacao() {
		return motivoMovimentacao;
	}

	public void setMotivoMovimentacao(String motivoMovimentacao) {
		this.motivoMovimentacao = motivoMovimentacao;
	}

	public String getLocalArmazenagemDescricaoDestino() {
		return localArmazenagemDescricaoDestino;
	}

	public void setLocalArmazenagemDescricaoDestino(
			String localArmazenagemDescricaoDestino) {
		this.localArmazenagemDescricaoDestino = localArmazenagemDescricaoDestino;
	}

	public String getLocalArmazenagemDescricaoOrigem() {
		return localArmazenagemDescricaoOrigem;
	}

	public void setLocalArmazenagemDescricaoOrigem(
			String localArmazenagemDescricaoOrigem) {
		this.localArmazenagemDescricaoOrigem = localArmazenagemDescricaoOrigem;
	}

	public String getIdHidrometroSituacao() {
		return idHidrometroSituacao;
	}

	public void setIdHidrometroSituacao(String idHidrometroSituacao) {
		this.idHidrometroSituacao = idHidrometroSituacao;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 */
	
	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		// não usar pois vai dar problema com o filtrar
		/*anoFabricacao = null;

		dataAquisicao = null;

		faixaFinal = null;

		faixaInicial = null;

		fixo = null;

		idHidrometroCapacidade = null;

		idHidrometroClasseMetrologica = null;

		idHidrometroDiametro = null;

		idHidrometroTipo = null;

		idLocalArmazenagem = null;

		localArmazenagemDescricao = null;

		localArmazenagemDescricaoOrigem = null;

		localArmazenagemDescricaoDestino = null;

		idHidrometroMarca = null;

		idNumeroDigitosLeitura = null;

		indicadorMacromedidor = null;

		numeroHidrometro = null;

		hidrometroSelectID = null;

		dataMovimentacaoInicial = null;

		dataMovimentacaoFinal = null;

		horaMovimentacaoInicial = null;

		horaMovimentacaoFinal = null;

		motivoMovimentacao = null;

		localArmazenagemDestino = null;

		localArmazenagemOrigem = null;

		funcionario = null;
		
		nomeFuncionario = null;

		idHidrometroSituacao = null;*/

	}

	/**
	 * @return Retorna o campo idHidrometro.
	 */
	public String getIdHidrometro() {
		return idHidrometro;
	}

	/**
	 * @param idHidrometro O idHidrometro a ser setado.
	 */
	public void setIdHidrometro(String idHidrometro) {
		this.idHidrometro = idHidrometro;
	}

	/**
	 * @return Retorna o campo idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}

	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getIdHidrometroRelojoaria() {
		return idHidrometroRelojoaria;
	}

	public void setIdHidrometroRelojoaria(String idHidrometroRelojoaria) {
		this.idHidrometroRelojoaria = idHidrometroRelojoaria;
	}

	public String getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public String getTempoGarantiaAnos() {
		return tempoGarantiaAnos;
	}

	public void setTempoGarantiaAnos(String tempoGarantiaAnos) {
		this.tempoGarantiaAnos = tempoGarantiaAnos;
	}

	public String getVazaoMinima() {
		return vazaoMinima;
	}

	public void setVazaoMinima(String vazaoMinima) {
		this.vazaoMinima = vazaoMinima;
	}

	public String getVazaoNominal() {
		return vazaoNominal;
	}

	public void setVazaoNominal(String vazaoNominal) {
		this.vazaoNominal = vazaoNominal;
	}

	public String getVazaoTransicao() {
		return vazaoTransicao;
	}

	public void setVazaoTransicao(String vazaoTransicao) {
		this.vazaoTransicao = vazaoTransicao;
	}
	
	
}
