package gcom.micromedicao.leitura;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * @author Rômulo Aurélio
 * @date 17/05/2007
 *
 */
public class FiltroLeituraFiscalizacao extends Filtro implements Serializable{
	
	/**
	 * @since 23/05/2007
	 */
	private static final long serialVersionUID = 1L;

	public FiltroLeituraFiscalizacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }	
    
    public FiltroLeituraFiscalizacao(){}
    
	
	public final static String ID = "id";
	
	public final static String NUMERO_LEITURA_EMPRESA = "numeroLeituraEmpresa";
	
	public final static String MEDICAO_HISTORICO = "medicaoHistorico";
	
	public final static String MEDICAO_HISTORICO_ID = "medicaoHistorico.id";
	
	public final static String LEITURA_ANORMALIDADE = "leituraAnormalidade";
	
	public final static String LEITURA_ANORMALIDADE_ID = "leituraAnormalidade.id";
	
	public final static String FUNCIONARIO = "funcionario";
	
	public final static String FUNCIONARIO_ID = "funcionario.id";
	
	

}
