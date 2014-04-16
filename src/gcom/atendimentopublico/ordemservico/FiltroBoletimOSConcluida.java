package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Classe que representa o filtro do Boletim de Ordens de Servico Concluida
 * 
 * @author Ivan Sérgio
 * @data 09/04/2008
 * 
 */
public class FiltroBoletimOSConcluida extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe 
     */
    public FiltroBoletimOSConcluida() {
    }

    /**
     * Construtor da classe 
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroBoletimOSConcluida(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String ANO_MES_REFERENCIA_BOLETIM = "anoMesReferenciaBoletim";

    /**
     * Description of the Field
     */
    public final static String CODIGO_FISCALIZACAO = "codigoFiscalizacao";

    /**
     * Description of the Field
     */
    public final static String DATA_FISCALIZACAO = "dataFiscalizacao";
    
    /**
     * Description of the Field
     */
    public final static String DATA_ENCERRAMENTO_BOLETIM = "dataEncerramentoBoletim"; 
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_TROCA_PROTECAO_HIDROMETRO = "indicadorTrocaProtecaoHidrometro";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_TROCA_REGISTRO_HIDROMETRO = "indicadorTrocaRegistroHidrometro";
    
    /**
     * Description of the Field
     */
    public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";
    
    /**
     * Description of the Field
     */
    public final static String HIDROMETRO_LOCAL_INSTALACAO_ID = "hidrometroLocalInstalacao.id";
    public final static String HIDROMETRO_LOCAL_INSTALACAO = "hidrometroLocalInstalacao";
    
    /**
     * Description of the Field
     */
    public final static String LOCALIDADE_ID = "localidade.id";
    public final static String LOCALIDADE = "localidade";
    
    /**
     * Description of the Field
     */
    public final static String USUARIO_ID = "usuario.id";
    public final static String USUARIO = "usuario";
}
