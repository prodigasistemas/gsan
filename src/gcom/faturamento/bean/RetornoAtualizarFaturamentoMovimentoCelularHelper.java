package gcom.faturamento.bean;

/**
 * 
 * [UC0811] Processar Requisições do Dispositivo Móvel.
 * 
 * Helper com os campos necessários ao retorno do 
 * ControladorFaturamento.atualizarFaturamentoMovimentoCelular
 * 
 * @author bruno
 * @date 01/07/2010
 *
 */
public class RetornoAtualizarFaturamentoMovimentoCelularHelper {
    
    private byte[] relatorioConsistenciaProcessamento;
    private String mensagemComunicacaoServidorCelular;
    private boolean indicadorSucessoAtualizacao;
        
    public String getMensagemComunicacaoServidorCelular() {
        return mensagemComunicacaoServidorCelular;
    }
    public void setMensagemComunicacaoServidorCelular(
            String mensagemComunicacaoServidorCelular) {
        this.mensagemComunicacaoServidorCelular = mensagemComunicacaoServidorCelular;
    }
    public byte[] getRelatorioConsistenciaProcessamento() {
        return relatorioConsistenciaProcessamento;
    }
    public void setRelatorioConsistenciaProcessamento(
            byte[] relatorioConsistenciaProcessamento) {
        this.relatorioConsistenciaProcessamento = relatorioConsistenciaProcessamento;
    }
	public boolean getIndicadorSucessoAtualizacao() {
		return indicadorSucessoAtualizacao;
	}
	public void setIndicadorSucessoAtualizacao(boolean indicadorSucessoAtualizacao) {
		this.indicadorSucessoAtualizacao = indicadorSucessoAtualizacao;
	}

}
