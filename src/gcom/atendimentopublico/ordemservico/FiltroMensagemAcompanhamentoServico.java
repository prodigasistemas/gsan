package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;


/**
 * [UC12008] Cadastrar Mensagens de Acompanhamento de Serviços
 * 
 * @author Magno Gouveia
 * @since 11/08/2011
 */
public class FiltroMensagemAcompanhamentoServico extends Filtro {

	private static final long serialVersionUID = 1L;
	
	public static final String ID = "id";

    public final static String DESCRICAO = "descricao";

    public final static String INDICADOR_SITUACAO = "indicadorSituacao";
    
    public static final String ARQUIVO_TEXTO_ACOMPANHAMENTO_SERVICO = "arquivoTextoAcompanhamentoServico";
    
    public final static String ARQUIVO_TEXTO_ACOMPANHAMENTO_SERVICO_ID = "arquivoTextoAcompanhamentoServico.id";
    
    public static final String USUARIO = "usuario";
    
    public final static String USUARIO_ID = "usuario.id";

	public FiltroMensagemAcompanhamentoServico(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public FiltroMensagemAcompanhamentoServico() {

	}
}
