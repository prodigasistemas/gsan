package gcom.gui.faturamento.debito;

import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.DebitoTipoVigencia;
import gcom.faturamento.debito.FiltroDebitoTipoVigencia;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 * 
 * @author Hugo Leonardo
 * @date 16/04/2010
 */
public class AtualizarDebitoTipoVigenciaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		AtualizarDebitoTipoVigenciaActionForm form = (AtualizarDebitoTipoVigenciaActionForm) actionForm;

		//Recebe os Parametros da sessao.
		Date timeStampDB = (Date) sessao.getAttribute("timeStamp");
		
	//	String idDebitoTipoVigencia1 = (String) sessao.getAttribute("idDebitoTipoVigencia");
		
		DebitoTipoVigencia debitoTipoVigencia = (DebitoTipoVigencia) sessao.getAttribute("debitoTipoVigencia");
		
	//	form.setIdDebitoTipoVigencia(idDebitoTipoVigencia1);

		String descricaoDebitoTipo = form.getNomeDebitoTipo();

		String debitoTipoSTR = form.getDebitoTipo();

		String valorServico = form.getValorDebito();

		debitoTipoVigencia.setValorDebito(Util.formatarMoedaRealparaBigDecimal(valorServico));

		// Seta no Objeto os dados do form

		DebitoTipo debitoTipo = new DebitoTipo();

		debitoTipo.setId(new Integer(debitoTipoSTR));

		debitoTipoVigencia.setDebitoTipo(debitoTipo);
		
		debitoTipoVigencia.setUltimaAlteracao(new Date());
		
		//
		// Verificar Atualização realizada por outro usuário
		//
		String idDebitoTipoVigencia = debitoTipoVigencia.getId().toString();
		
		FiltroDebitoTipoVigencia filtroDebitoTipoVigencia = new FiltroDebitoTipoVigencia();
		filtroDebitoTipoVigencia.adicionarParametro(new ParametroSimples(FiltroDebitoTipoVigencia.ID, idDebitoTipoVigencia));
		
		//  Recupera Debito Tipo Serviço
		Collection colecaoDebitoTipoVigencia = fachada.pesquisar(filtroDebitoTipoVigencia, DebitoTipoVigencia.class.getName());
		
		DebitoTipoVigencia debitoTipoVigenciaBD = (DebitoTipoVigencia) colecaoDebitoTipoVigencia.iterator().next();
		
		if ( !Util.formatarData(debitoTipoVigenciaBD.getDataVigenciaInicial()).equals(form.getDataVigenciaInicial() ) || 
				!Util.formatarData(debitoTipoVigenciaBD.getDataVigenciaFinal()).equals(form.getDataVigenciaFinal()) ) {
			
			// Vigência do valor do serviço
			//[FS0007] – Validar data da vigência inicial
			if (form.getDataVigenciaInicial() != null && !form.getDataVigenciaInicial().equals("")){
				
				if (!Util.validarDiaMesAno(form.getDataVigenciaInicial())) {
					
					debitoTipoVigencia.setDataVigenciaInicial(Util.converteStringParaDate(form.getDataVigenciaInicial()));
					//[FS0008] – Validar data da vigência final
					if (!Util.validarDiaMesAno(form.getDataVigenciaFinal())) {
						
						debitoTipoVigencia.setDataVigenciaFinal(Util.converteStringParaDate(form.getDataVigenciaFinal()));
						
						if(Util.compararData(debitoTipoVigencia.getDataVigenciaInicial(),debitoTipoVigencia.getDataVigenciaFinal()) == 1){
							throw new ActionServletException("atencao.atencao.data_vigencia_final_menor");
						}
					}else{
						throw new ActionServletException("atencao.atencao.data_vigencia_final_invalida");
					}			
				}else{
					throw new ActionServletException("atencao.data_vigencia_inicial_invalida");
				}
			}else{
				
				debitoTipoVigencia.setDataVigenciaInicial(null);
				debitoTipoVigencia.setDataVigenciaFinal(null);
				
			}
			// FS0008 - Verificar pré-existência de vigência para o débito tipo
			Fachada.getInstancia().verificarExistenciaVigenciaDebito(form.getDataVigenciaInicial(), 
					form.getDataVigenciaFinal(), new Integer(form.getDebitoTipo()), new Integer("1"));
		
		
		}	
		
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_ATUALIZAR_DEBITO_TIPO_VIGENCIA, debitoTipoVigencia.getDebitoTipo().getId(),
				debitoTipoVigencia.getDebitoTipo().getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		
		registradorOperacao.registrarOperacao(debitoTipoVigencia);
		
		
		if(timeStampDB.compareTo(debitoTipoVigenciaBD.getUltimaAlteracao()) != 0){
			throw new ActionServletException("atencao.atualizacao.timestamp");
		}
		
		fachada.atualizar(debitoTipoVigencia);

		montarPaginaSucesso(httpServletRequest, "O Debito Tipo Vigencia "
				+ descricaoDebitoTipo + " atualizado com sucesso.",
				"Realizar outra Manutenção Debito Tipo Vigencia",
				"exibirFiltrarDebitoTipoVigenciaAction.do?menu=sim");

		return retorno;
	}
}
