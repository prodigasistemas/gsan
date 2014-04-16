package gcom.gui.faturamento.debito;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipoVigencia;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Inserção da vigência do Débito Tipo Vigência
 * 
 * @author Josenildo Neves
 * @date 22/02/2010
 */
public class ReplicarDebitoTipoVigenciaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);

		ReplicarDebitoTipoVigenciaActionForm replicarDebitoTipoVigenciaActionForm = (ReplicarDebitoTipoVigenciaActionForm) actionForm;

		//[SB0002] – Replicar os serviços existentes para uma nova vigência e valor- Replicar os débitos existentes para uma nova vigência e valor.		
		Collection<DebitoTipoVigencia> collDebitoTipoVigencia = this.setCollDebitoTipoVigencia(replicarDebitoTipoVigenciaActionForm);
		
		for (DebitoTipoVigencia debitoTipoVigencia : collDebitoTipoVigencia) {
			
			// FS0008 - Verificar pré-existência de vigência para o débito tipo
			String dataVigenciaInicial = Util.formatarData(debitoTipoVigencia.getDataVigenciaInicial());
			String dataVigenciaFinal = Util.formatarData(debitoTipoVigencia.getDataVigenciaFinal());
			Integer idDebitoTipo = debitoTipoVigencia.getDebitoTipo().getId();
			
			Fachada.getInstancia().verificarExistenciaVigenciaDebito(dataVigenciaInicial, 
					dataVigenciaFinal, idDebitoTipo, new Integer("2"));
		}
		
		for (DebitoTipoVigencia debitoTipoVigencia : collDebitoTipoVigencia) {
			
			// ------------ REGISTRAR TRANSAÇÃO ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_INSERIR_DEBITO_TIPO_VIGENCIA, debitoTipoVigencia.getDebitoTipo().getId(),
					debitoTipoVigencia.getDebitoTipo().getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			// ------------ REGISTRAR TRANSAÇÃO ----------------
			registradorOperacao.registrarOperacao(debitoTipoVigencia);
			
			Fachada.getInstancia().inserir(debitoTipoVigencia);
			
		}		

		// [FS0003 - Verificar sucesso da transação].
		montarPaginaSucesso(httpServletRequest, "Todas as vigências foram atualizadas com sucesso.",
				"Replicar Debito Tipo Vigencia",
				"exibirReplicarDebitoTipoVigenciaAction.do?menu=sim");
		return retorno;
	}

	/**
	 * Atualização da vigência e do valor na coleção de Débito Tipo Vigência
	 * 
	 * @author Josenildo Neves
	 * @date 22/02/2010
	 * 
	 * @param form
	 * @return collDebitoTipoVigencia
	 */
	
	private Collection<DebitoTipoVigencia> setCollDebitoTipoVigencia(
			ReplicarDebitoTipoVigenciaActionForm form) {
		
		Collection<DebitoTipoVigencia> collDebitoTipoVigencia = Fachada.getInstancia()
					.pesquisarDebitoTipoVigenciaUltimaVigenciaSelecionados(form.getIdRegistrosSelecionados());
		
		for (DebitoTipoVigencia debitoTipoVigencia : collDebitoTipoVigencia) {
			
			debitoTipoVigencia.setDataVigenciaInicial(Util.converteStringParaDate(form.getNovaDataVigenciaInicial()));
			
			debitoTipoVigencia.setDataVigenciaFinal(Util.converteStringParaDate(form.getNovaDataVigenciaFinal()));
			
			if(!form.getIndiceParaCorrecao().replace(",",".").equals("00.0000")){
				
				BigDecimal novoValor = null;
				BigDecimal indice = new BigDecimal(form.getIndiceParaCorrecao().replace(",","."));
	
				//divide por Cem pois o valor informado na tela é em percentagem "%".
				indice = indice.divide(new BigDecimal(100));
				
				novoValor = indice.multiply(debitoTipoVigencia.getValorDebito());
				
				novoValor = novoValor.add(debitoTipoVigencia.getValorDebito());
				
				debitoTipoVigencia.setValorDebito(novoValor);
			
				debitoTipoVigencia.setUltimaAlteracao(new Date());
			}else{
				throw new ActionServletException("atencao.valor.índice.informado.igual.zero");
			}
		}
		
		return collDebitoTipoVigencia;
	}

}
