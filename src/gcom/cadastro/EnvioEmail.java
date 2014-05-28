package gcom.cadastro;

import gcom.seguranca.acesso.Funcionalidade;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class EnvioEmail implements Serializable {

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String emailRemetente;

    /** persistent field */
    private String emailReceptor;

    /** persistent field */
    private String tituloMensagem;

    /** persistent field */
    private String corpoMensagem;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Funcionalidade funcionalidade;
    public final static Integer SPC_SERASA = 38;
    public final static Integer SPC_SERASA_MOV_RETORNO = 39;
    public final static Integer SUSPENDER_IMOVEL_EM_PROGRAMA_ESPECIAL_EMAIL = 40;
    public final static Integer GERAR_INTEGRACAO_PARA_CONTABILIDADE = 41;
    public final static Integer REGISTRAR_MOVIMENTO_CARTAO_CREDITO = 42;
    public final static Integer REGISTRAR_MOVIMENTO_CARTAO_CREDITO_COM_ERRO = 43;
    public final static Integer ENVIO_EMAIL_CONTA_PARA_CLIENTE = 44;
    public final static Integer INSERIR_CADASTRO_EMAIL_CLIENTE = 45;
    public final static Integer INSERIR_SOLICITACAO_ACESSO = 46;
    public final static Integer GERAR_TXT_OS_PRESTADORA_SERVICO = 48;
    public final static Integer PROCESSAR_ARQUIVO_TXT_ENCERRAMENTO_OS_COBRANCA = 49;
    public final static Integer PROCESSAR_ARQUIVO_TXT_ENCERRAMENTO_OS_COBRANCA_ERRO = 50;
    public final static Integer ENVIAR_QUESTIONARIO_SATISFACAO_CLIENTE = 51;
    public final static Integer GERAR_TXT_OS_CONTAS_PAGAS_PARCELADAS = 52;
    public final static Integer GERAR_TXT_OS_CONTAS_PAGAS_PARCELADAS_SEM_DADOS = 53;
    
    /** full constructor */
    public EnvioEmail(Integer id, String emailRemetente, String emailReceptor, String tituloMensagem, String corpoMensagem, Date ultimaAlteracao, Funcionalidade funcionalidade) {
        this.id = id;
        this.emailRemetente = emailRemetente;
        this.emailReceptor = emailReceptor;
        this.tituloMensagem = tituloMensagem;
        this.corpoMensagem = corpoMensagem;
        this.ultimaAlteracao = ultimaAlteracao;
        this.funcionalidade = funcionalidade;
    }


    public String getCorpoMensagem() {
		return corpoMensagem;
	}


	public void setCorpoMensagem(String corpoMensagem) {
		this.corpoMensagem = corpoMensagem;
	}


	public String getEmailReceptor() {
		return emailReceptor;
	}


	public void setEmailReceptor(String emailReceptor) {
		this.emailReceptor = emailReceptor;
	}


	public String getEmailRemetente() {
		return emailRemetente;
	}


	public void setEmailRemetente(String emailRemetente) {
		this.emailRemetente = emailRemetente;
	}


	public Funcionalidade getFuncionalidade() {
		return funcionalidade;
	}


	public void setFuncionalidade(Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTituloMensagem() {
		return tituloMensagem;
	}


	public void setTituloMensagem(String tituloMensagem) {
		this.tituloMensagem = tituloMensagem;
	}


	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}


	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}


	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}