package gcom.cadastro.atualizacaocadastral;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroArquivoTextoAtualizacaoCadastral extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    public FiltroArquivoTextoAtualizacaoCadastral(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public FiltroArquivoTextoAtualizacaoCadastral() {
    }

    public final static String ID = "id";
    public final static String DESCRICAO = "descricaoArquivo";
    public final static String LEITURISTA = "leiturista";
    public final static String LEITURISTA_ID = "leiturista.id";
    public final static String SITUACAO_TRANSMISSAO_LEITURA = "situacaoTransmissaoLeitura";
    public final static String SITUACAO_TRANSMISSAO_LEITURA_ID = "situacaoTransmissaoLeitura.id";
    public final static String LOCALIDADE = "localidade";
    public final static String LOCALIDADE_ID = "localidade.id";
    public final static String SETOR_COMERCIAL_CODIGO = "codigoSetorComercial";
    public final static String ROTA = "rota";
    public final static String ROTA_ID = "rota.id";
    public final static String ROTA_CODIGO = "rota.codigo";
}
