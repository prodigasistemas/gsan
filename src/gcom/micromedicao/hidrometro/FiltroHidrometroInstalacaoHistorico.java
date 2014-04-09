package gcom.micromedicao.hidrometro;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroHidrometroInstalacaoHistorico extends Filtro implements
		Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroHidrometroInstalacaoHistorico(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Construtor da classe FiltroPavimentoCalcada
	 */
	public FiltroHidrometroInstalacaoHistorico() {
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	public final static String HIDROMETRO_ID = "hidrometro.id";

	public final static String IMOVEL_ID = "imovel.id";

	public final static String LIGACAO_AGUA_ID = "ligacaoAgua.id";
	
	public final static String LIGACAO_AGUA_SITUACAO_IMOVEL = "imovel.ligacaoAguaSituacao.id";
    
    public final static String LIGACAO_ESGOTO_SITUACAO_IMOVEL = "imovel.ligacaoEsgotoSituacao.id";
    
    public final static String HIDROMETRO_PROTECAO = "hidrometroProtecao";
    
    public final static String HIDROMETRO = "hidrometro";
    
    public final static String HIDROMETRO_LOCAL_INSTALACAO = "hidrometroLocalInstalacao";
    
    public final static String MEDICAO_TIPO= "medicaoTipo";
    
    public final static String RATEIO_TIPO= "rateioTipo";
    
    public final static String DATA_RETIRADA= "dataRetirada";

}
