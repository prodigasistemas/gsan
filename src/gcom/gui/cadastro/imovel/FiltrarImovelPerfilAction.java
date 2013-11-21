package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Filtrar Imovel Perfil 
 * @author Wallace Thierre
 * Data: 01/10/2010
 *
 */
public class FiltrarImovelPerfilAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping, 
			ActionForm actionForm, HttpServletRequest httpServletRequest, 
			HttpServletResponse httpServletResponse){
			
		ActionForward retorno = actionMapping
			.findForward("exibirManterImovelPerfil");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltrarImovelPerfilActionForm filtrarImovelPerfilActionForm = (FiltrarImovelPerfilActionForm) actionForm;
		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		
		boolean peloMenosUmParametroInformado = false;
		
		String id = filtrarImovelPerfilActionForm.getId();
		String descricao = filtrarImovelPerfilActionForm.getDescricao();		
		String indicadorUso = filtrarImovelPerfilActionForm.getIndicadorUso();		
		String indicadorGeracaoAutomatica = filtrarImovelPerfilActionForm.getIndicadorGeracaoAutomatica();
		String indicadorInserirManterPerfil = filtrarImovelPerfilActionForm.getIndicadorInserirManterPerfil();
		String indicadorGerarDadosLeitura = filtrarImovelPerfilActionForm.getIndicadorGerarDadosLeitura();
		String indicadorBloquearRetificacao = filtrarImovelPerfilActionForm.getIndicadorBloquearRetificacao();
		String indicadorGrandeConsumidor = filtrarImovelPerfilActionForm.getIndicadorGrandeConsumidor();
		String indicadorBloquearDadosSocial = filtrarImovelPerfilActionForm.getIndicadorBloquearDadosSocial();
		String indicadorGeraDebitoSegundaViaConta = filtrarImovelPerfilActionForm.getIndicadorGeraDebitoSegundaViaConta();
		String tipoPesquisa = filtrarImovelPerfilActionForm.getTipoPesquisa();
		
		//Id
		if(id != null && !id.trim().equals("")){
			peloMenosUmParametroInformado = true;
			filtroImovelPerfil.adicionarParametro(new ParametroSimples
					(FiltroImovelPerfil.ID, id));			
			
		}
		
		
		//Descrição Imovel
		if(descricao != null && !descricao.trim().equalsIgnoreCase("")){
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroImovelPerfil
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroImovelPerfil.DESCRICAO, descricao));
			} else {

				filtroImovelPerfil.adicionarParametro(new ComparacaoTexto(
						FiltroImovelPerfil.DESCRICAO, descricao));
			}
		}
		
		//Indicador de Uso
		if(indicadorUso != null && !indicadorUso.trim().equals("")){
			peloMenosUmParametroInformado = true;
			filtroImovelPerfil.adicionarParametro(new ParametroSimples
					(FiltroImovelPerfil.INDICADOR_USO, indicadorUso));			
			
		}
		//Indicador Geração Automática
		if(indicadorGeracaoAutomatica != null && !indicadorGeracaoAutomatica.trim().equals("")){
			peloMenosUmParametroInformado = true;
			filtroImovelPerfil.adicionarParametro(new ParametroSimples
					(FiltroImovelPerfil.INDICADOR_GERACAO_AUTOMATICA, indicadorGeracaoAutomatica));			
		}
		//Indicador Inserir Manter Perfil
		if(indicadorInserirManterPerfil != null && !indicadorInserirManterPerfil.trim().equals("")){
			peloMenosUmParametroInformado = true;
			filtroImovelPerfil.adicionarParametro(new ParametroSimples
					(FiltroImovelPerfil.INDICADOR_INSERIR_MANTER_PERFIL, indicadorInserirManterPerfil));			
			
		}
		//Indicador Gerar Dados Leitura
		if(indicadorGerarDadosLeitura != null && !indicadorGerarDadosLeitura.trim().equals("")){
			peloMenosUmParametroInformado = true;
			filtroImovelPerfil.adicionarParametro(new ParametroSimples
					(FiltroImovelPerfil.INDICADOR_GERAR_DADOS_LEITURA, indicadorGerarDadosLeitura));			
			
		}
		//Indicador Bloquear Retificação
		if(indicadorBloquearRetificacao!= null && !indicadorBloquearRetificacao.trim().equals("")){
			peloMenosUmParametroInformado = true;
			filtroImovelPerfil.adicionarParametro(new ParametroSimples
					(FiltroImovelPerfil.INDICADOR_BLOQUEAR_RETIFICACAO, indicadorBloquearRetificacao));			
			
		}
		//Indicador Grande Consumidor
		if(indicadorGrandeConsumidor != null && !indicadorGrandeConsumidor.trim().equals("")){
			peloMenosUmParametroInformado = true;
			filtroImovelPerfil.adicionarParametro(new ParametroSimples
					(FiltroImovelPerfil.INDICADOR_GRANDE_CONSUMIDOR, indicadorGrandeConsumidor));			
			
		}
		//Indicador Bloquear Dados Sociais
		if(indicadorBloquearDadosSocial!= null && !indicadorBloquearDadosSocial.trim().equals("")){
			peloMenosUmParametroInformado = true;
			filtroImovelPerfil.adicionarParametro(new ParametroSimples
					(FiltroImovelPerfil.INDICADOR_BLOQUEAR_DADOS_SOCIAL, indicadorBloquearDadosSocial));			
			
		}
		//Indicador Geração Débito Segunda Via Conta
		if(indicadorGeraDebitoSegundaViaConta != null && !indicadorGeraDebitoSegundaViaConta.trim().equals("")){
			peloMenosUmParametroInformado = true;
			filtroImovelPerfil.adicionarParametro(new ParametroSimples
					(FiltroImovelPerfil.INDICADOR_GERA_DEBITO_SEGUNDA_VIA_CONTA, indicadorGeraDebitoSegundaViaConta));			
			
		}
		
		Collection colecaoImovelPerfil = null;
		if(peloMenosUmParametroInformado == true){
			
			colecaoImovelPerfil = fachada.pesquisar
				(filtroImovelPerfil, ImovelPerfil.class.getName());
		}
		
		//Filtragem sem parametros
		if(!peloMenosUmParametroInformado){
			colecaoImovelPerfil = fachada.pesquisar
			(filtroImovelPerfil, ImovelPerfil.class.getName());
		}
			
		
		//Verificar a existência de um Perfil Imovel
		if(colecaoImovelPerfil == null 
				|| colecaoImovelPerfil.isEmpty()){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null,"Imovel Perfil");
		}else {
			httpServletRequest.setAttribute("colecaoImovelPerfil",
					colecaoImovelPerfil);
			ImovelPerfil imovelPerfil= new ImovelPerfil();
			imovelPerfil = (ImovelPerfil) Util
					.retonarObjetoDeColecao(colecaoImovelPerfil);
			String idRegistroAtualizacao = imovelPerfil.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}
		
		sessao.setAttribute("filtroImovelPerfil", filtroImovelPerfil);
		
		httpServletRequest.setAttribute("filtroImovelPerfil",
				filtroImovelPerfil);	
		
		
		return retorno;
		
		
	}
	

}
