package gcom.integracao;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;



public interface ControladorIntegracaoLocal extends javax.ejb.EJBLocalObject {
	public void enviarMovimentoExportacaoFirma(); 
	public void receberMovimentoExportacaoFirma();
	public Integer gerarOS(Usuario usuario, OrdemServico ordemServico) throws ControladorException;
	public Object[] pesquisarHorarioProcessoIntegracaoUPA() throws ControladorException; 
}
