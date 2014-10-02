package gcom.micromedicao.leitura;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

public class FiltroLeituraAnormalidade extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroLeituraAnormalidade object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */

    public FiltroLeituraAnormalidade(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }	
    
    public FiltroLeituraAnormalidade(){}
	
    public final static String INDICADOR_USO = "indicadorUso";	
		
	// Colocado o ID por Felipe e por "" ,por enquanto, só para o
	// exibirDadosFaturamentoAction
	public final static String ID = "id";
	
	public final static String DESCRICAO = "descricao";
	
	public final static String INDICADOR_RELATIVO_HIDROMETRO = "indicadorRelativoHidrometro";
	
	public final static String INDICADOR_IMOVEL_SEM_HIDROMETRO = "indicadorImovelSemHidrometro";
	
	public final static String INDICADOR_USO_SISTEMA = "indicadorSistema";
	
	public final static String INDICADOR_PERDA_TARIFA_SOCIAL = "indicadorPerdaTarifaSocial";
	
	public final static String INDICADOR_EMISSAO_ORDEM_SERVICO = "indicadorEmissaoOrdemServico";
	
	public final static String ID_TIPO_SERVICO = "servicoTipo";
	
	public final static String ID_CONSUMO_A_COBRAR_SEM_LEITURA = "leituraAnormalidadeConsumoSemleitura.id";
	
	public final static String ID_CONSUMO_A_COBRAR_COM_LEITURA = "leituraAnormalidadeConsumoComleitura.id";
	
	public final static String ID_LEITURA_A_FATURAR_SEM_LEITURA = "leituraAnormalidadeLeituraSemleitura.id";
	
	public final static String ID_LEITURA_A_FATURAR_COM_LEITURA = "leituraAnormalidadeLeituraComleitura.id";
	
	public final static String NUMERO_VEZES_ANORMALIDADE_SUSPENDER_LEITURA = "numeroVezesSuspendeLeitura";
	
	public final static String NUMERO_MESES_LEITURA_SUSPENSA = "numeroMesesLeituraSuspensa";
	
	/**
	 * 
	 * Pamela Gatinho - 13/03/2012
	 * Campo que identifica se a anormalidade será usada ou
	 * não no sistema de leitura e impressão simultanea.
	 */
	public final static String INDICADOR_IMPRESSAO_SIMULTANEA = "indicadorImpressaoSimultanea";
}
