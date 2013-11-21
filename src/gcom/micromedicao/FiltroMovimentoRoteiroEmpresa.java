package gcom.micromedicao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroMovimentoRoteiroEmpresa extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final String ID = "id";
	
	public static final String EMPRESA = "empresa.id";	

    public static final String INDICADOR_USO = "indicadorUso";
    
    public static final String LOCALIDADE = "localidade";

    public static final String LOCALIDADE_ID = "localidade.id";
    
    public final static String LOCALIDADE_DESCRICAO = "localidade.descricao";

    public static final String LEITURISTA_ID = "rota.leiturista.id";
    
    public static final String LEITURISTA = "rota.leiturista";
    
    public static final String ROTA = "rota";
    
    public static final String ROTA_ID = "rota.id";
    
    public static final String ROTA_CODIGO = "codigoRota";
    
    public static final String ANO_MES_MOVIMENTO = "anoMesMovimento";
    
    public static final String TEMPO_LEITURA = "tempoLeitura";
    
    public static final String IMOVEL_ID = "imovel.id";
    
    public static final String IMOVEL = "imovel";
    
    public static final String FUNCIONARIO = "rota.leiturista.funcionario";
    
    public static final String CLIENTE = "rota.leiturista.cliente";
    
    public static final String MEDICAO_TIPO_ID = "medicaoTipo.id";
    
    public static final String SETOR_COMERCIAL_CODIGO = "codigoSetorComercial";
    
    public static final String FATURAMENTO_GRUPO = "faturamentoGrupo";
    
    public static final String IMOVEL_IMPRESSO = "";
    

    public FiltroMovimentoRoteiroEmpresa() {
    }

    public FiltroMovimentoRoteiroEmpresa(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}
