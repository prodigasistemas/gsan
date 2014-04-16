package gcom.gui.atendimentopublico.hidrometro;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.cadastro.imovel.Imovel;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.Util;

import java.util.Date;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Ana Maria
 * @date 30/06/2006
 */
public class EfetuarInstalacaoHidrometroActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private String idOrdemServico;
	private String nomeOrdemServico;

	private String idLocalArmazenagem;
	private String localInstalacao;
	private String numeroHidrometro;
	private String protecao;
	private String situacaoCavalete;
	private String matriculaImovel;
	private String inscricaoImovel;
	private String clienteUsuario;
	private String cpfCnpjCliente;
	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	private String tipoMedicao;
	private String dataInstalacao;
	private String leituraInstalacao;
	private String numeroSelo;
	private String veioEncerrarOS;
	private String usuarioNaoEncontrado;
	private String numeroLacre;
	private String tipoPoco;
	
//	Dados da Geração do Débito
    private String idTipoDebito;
    private String descricaoTipoDebito;
    
    private String valorDebito;
    private String motivoNaoCobranca;
    private String percentualCobranca;
    private String quantidadeParcelas;
    private String valorParcelas;
    private String qtdeMaxParcelas;  
    
    /*
	 * Colocado por Raphael Rossiter em 20/04/2007
	 * [FS0013 - Alteração de Valor]
	 */
    private String alteracaoValor;
	
    /*
     * Colocado por Vivianne Sousa em 05/12/2007
     */
    private String indicadorTrocaProtecao;
    private String indicadorTrocaRegistro;
    
	public String getQtdeMaxParcelas() {
		return qtdeMaxParcelas;
	}

	public void setQtdeMaxParcelas(String qtdeMaxParcelas) {
		this.qtdeMaxParcelas = qtdeMaxParcelas;
	}

	public String getPercentualCobranca() {
		return percentualCobranca;
	}

	public void setPercentualCobranca(String percentualCobranca) {
		this.percentualCobranca = percentualCobranca;
	}

	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	public String getVeioEncerrarOS() {
		return veioEncerrarOS;
	}

	public void setVeioEncerrarOS(String veioEncerrarOS) {
		this.veioEncerrarOS = veioEncerrarOS;
	}

	/**
	 * @return Retorna o campo localInstalacao.
	 */
	public String getLocalInstalacao() {
		return localInstalacao;
	}

	/**
	 * @param localInstalacao a ser setado.
	 */
	public void setLocalInstalacao(String localInstalacao) {
		this.localInstalacao = localInstalacao;
	}

	/**
	 * @return Retorna o campo protecao.
	 */
	public String getProtecao() {
		return protecao;
	}

	/**
	 * @param protecao a ser setado.
	 */
	public void setProtecao(String protecao) {
		this.protecao = protecao;
	}

	/**
	 * @return Retorna o campo tipoCavalete.
	 */
	public String getSituacaoCavalete() {
		return situacaoCavalete;
	}

	/**
	 * @param tipoCavalete a ser setado.
	 */
	public void setSituacaoCavalete(String situacaoCavalete) {
		this.situacaoCavalete = situacaoCavalete;
	}

	/**
	 * @return Retorna o campo clienteUsuario.
	 */
	public String getClienteUsuario() {
		return clienteUsuario;
	}

	/**
	 * @param clienteUsuario a ser setado.
	 */
	public void setClienteUsuario(String clienteUsuario) {
		this.clienteUsuario = clienteUsuario;
	}

	/**
	 * @return Retorna o campo cpfCnpjCliente.
	 */
	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}

	/**
	 * @param cpfCnpjCliente a ser setado.
	 */
	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}

	/**
	 * @return Retorna o campo dataInstalacao.
	 */
	public String getDataInstalacao() {
		return dataInstalacao;
	}

	/**
	 * @param dataInstalacao a ser setado.
	 */
	public void setDataInstalacao(String dataInstalacao) {
		this.dataInstalacao = dataInstalacao;
	}

	/**
	 * @return Retorna o campo idOrdemServico.
	 */
	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	/**
	 * @param idOrdemServico a ser setado.
	 */
	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	/**
	 * @return Retorna o campo matriculaImovel.
	 */
	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	/**
	 * @param matriculaImovel a ser setado.
	 */
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	/**
	 * @return Retorna o campo situacaoLigacaoAgua.
	 */
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	/**
	 * @param situacaoLigacaoAgua a ser setado.
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
	 * @param situacaoLigacaoEsgoto a ser setado.
	 */
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	/**
	 * @return Retorna o campo inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	/**
	 * @param inscricaoImovel a ser setado.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	/**
	 * @return Retorna o campo nomeOrdemServico.
	 */
	public String getNomeOrdemServico() {
		return nomeOrdemServico;
	}

	/**
	 * @param nomeOrdemServico a ser setado.
	 */
	public void setNomeOrdemServico(String nomeOrdemServico) {
		this.nomeOrdemServico = nomeOrdemServico;
	}

	/**
	 * @return Retorna o campo tipoMedicao.
	 */
	public String getTipoMedicao() {
		return tipoMedicao;
	}

	/**
	 * @param tipoMedicao a ser setado.
	 */
	public void setTipoMedicao(String tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}

	/**
	 * @return Retorna o campo hidrometro.
	 */
	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	/**
	 * @param hidrometro a ser setado.
	 */
	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	/**
	 * @return Retorna o campo leituraInstalacao.
	 */
	public String getLeituraInstalacao() {
		return leituraInstalacao;
	}

	/**
	 * @param leituraInstalacao a ser setado.
	 */
	public void setLeituraInstalacao(String leituraInstalacao) {
		this.leituraInstalacao = leituraInstalacao;
	}
	/**
	 * @return Retorna o numeroSelo leituraInstalacao.
	 */
	
	public String getNumeroSelo() {
		return numeroSelo;
	}

	/**
	 * @param numeroSelo a ser setado.
	 */
	public void setNumeroSelo(String numeroSelo) {
		this.numeroSelo = numeroSelo;
	}	
	
	public HidrometroInstalacaoHistorico setFormValues(HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico) {
		
		/*
		 * Campos obrigatórios
		 */
		
		//data instalação
		hidrometroInstalacaoHistorico.setDataInstalacao(Util.converteStringParaDate(getDataInstalacao()));
		
		if (tipoMedicao.equals(""+MedicaoTipo.POCO)) {

			  Imovel imovel = new Imovel();
			  imovel.setId(new Integer(matriculaImovel));
							
			  hidrometroInstalacaoHistorico.setImovel(imovel);
			  hidrometroInstalacaoHistorico.setLigacaoAgua(null);
						
			} else if (tipoMedicao.equals(""+MedicaoTipo.LIGACAO_AGUA)) {

			  LigacaoAgua ligacaoAgua = new LigacaoAgua();
			  ligacaoAgua.setId(new Integer(matriculaImovel));
							
			  hidrometroInstalacaoHistorico.setLigacaoAgua(ligacaoAgua);
			  hidrometroInstalacaoHistorico.setImovel(null);
		    }
		
		//medição tipo
		MedicaoTipo medicaoTipo = new MedicaoTipo();
		medicaoTipo.setId(Integer.parseInt(getTipoMedicao()));
		hidrometroInstalacaoHistorico.setMedicaoTipo(medicaoTipo);
		//hidrômetro local instalação
		HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();
		hidrometroLocalInstalacao.setId(Integer.parseInt(getLocalInstalacao()));		
		hidrometroInstalacaoHistorico.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);
		//proteção
		HidrometroProtecao hidrometroProtecao = new HidrometroProtecao();
		hidrometroProtecao.setId(Integer.parseInt(getProtecao()));
		hidrometroInstalacaoHistorico.setHidrometroProtecao(hidrometroProtecao);
		//leitura instalação
		if(getLeituraInstalacao()!=null && !getLeituraInstalacao().trim().equals("")){
		  hidrometroInstalacaoHistorico.setNumeroLeituraInstalacao(Integer.parseInt(getLeituraInstalacao()));
		}else{
		  hidrometroInstalacaoHistorico.setNumeroLeituraInstalacao(0);	
		}
		//cavalete
		hidrometroInstalacaoHistorico.setIndicadorExistenciaCavalete(Short.parseShort(getSituacaoCavalete()));
		
		/*
		 * Campos opcionais 
		 */

		//data da retirada
		hidrometroInstalacaoHistorico.setDataRetirada(null);
		//leitura retirada
		hidrometroInstalacaoHistorico.setNumeroLeituraRetirada(null);
		//leitura corte
		hidrometroInstalacaoHistorico.setNumeroLeituraCorte(null);
		//leitura supressão
		hidrometroInstalacaoHistorico.setNumeroLeituraSupressao(null);
		//numero selo
		if (getNumeroSelo() != null && !getNumeroSelo().equals("")){
		  hidrometroInstalacaoHistorico.setNumeroSelo(getNumeroSelo());
		} else {
		  hidrometroInstalacaoHistorico.setNumeroSelo(null);
		}
		//tipo de rateio
		hidrometroInstalacaoHistorico.setRateioTipo(null);
		hidrometroInstalacaoHistorico.setDataImplantacaoSistema(new Date());
		//indicador instalação substituição
		hidrometroInstalacaoHistorico.setIndicadorInstalcaoSubstituicao(new Short("1"));		
		//data última alteração
		hidrometroInstalacaoHistorico.setUltimaAlteracao(new Date());
        
        if(getIndicadorTrocaProtecao()!= null){
            hidrometroInstalacaoHistorico.setIndicadorTrocaProtecao(new Short(getIndicadorTrocaProtecao()));
        }
        if(getIndicadorTrocaRegistro()!= null){
            hidrometroInstalacaoHistorico.setIndicadorTrocaRegistro(new Short(getIndicadorTrocaRegistro()));
        }
        
        // Numero do lacre
        if ( getNumeroLacre() != null ){
        	hidrometroInstalacaoHistorico.setNumeroLacre( getNumeroLacre() );
        }	
		
		return hidrometroInstalacaoHistorico;
	}

	public String getDescricaoTipoDebito() {
		return descricaoTipoDebito;
	}

	public void setDescricaoTipoDebito(String descricaoTipoDebito) {
		this.descricaoTipoDebito = descricaoTipoDebito;
	}

	public String getIdTipoDebito() {
		return idTipoDebito;
	}

	public void setIdTipoDebito(String idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}

	public String getMotivoNaoCobranca() {
		return motivoNaoCobranca;
	}

	public void setMotivoNaoCobranca(String motivoNaoCobranca) {
		this.motivoNaoCobranca = motivoNaoCobranca;
	}

	public String getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(String quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public String getValorParcelas() {
		return valorParcelas;
	}

	public void setValorParcelas(String valorParcelas) {
		this.valorParcelas = valorParcelas;
	}

	public String getAlteracaoValor() {
		return alteracaoValor;
	}

	public void setAlteracaoValor(String alteracaoValor) {
		this.alteracaoValor = alteracaoValor;
	}

    public String getIndicadorTrocaProtecao() {
        return indicadorTrocaProtecao;
    }

    public void setIndicadorTrocaProtecao(String indicadorTrocaProtecao) {
        this.indicadorTrocaProtecao = indicadorTrocaProtecao;
    }

    public String getIndicadorTrocaRegistro() {
        return indicadorTrocaRegistro;
    }

    public void setIndicadorTrocaRegistro(String indicadorTrocaRegistro) {
        this.indicadorTrocaRegistro = indicadorTrocaRegistro;
    }

	public String getNumeroLacre() {
		return numeroLacre;
	}

	public void setNumeroLacre(String numeroLacre) {
		this.numeroLacre = numeroLacre;
	}

	public String getUsuarioNaoEncontrado() {
		return usuarioNaoEncontrado;
	}

	public void setUsuarioNaoEncontrado(String usuarioNaoEncontrado) {
		this.usuarioNaoEncontrado = usuarioNaoEncontrado;
	}

	/**
	 * @return Retorna o campo idLocalArmazenagem.
	 */
	public String getIdLocalArmazenagem() {
		return idLocalArmazenagem;
	}

	/**
	 * @param idLocalArmazenagem O idLocalArmazenagem a ser setado.
	 */
	public void setIdLocalArmazenagem(String idLocalArmazenagem) {
		this.idLocalArmazenagem = idLocalArmazenagem;
	}

	public String getTipoPoco() {
		return tipoPoco;
	}

	public void setTipoPoco(String tipoPoco) {
		this.tipoPoco = tipoPoco;
	}
	
	

}
