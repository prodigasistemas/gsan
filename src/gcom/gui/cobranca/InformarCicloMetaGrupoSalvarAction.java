package gcom.gui.cobranca;

import gcom.cobranca.CicloMeta;
import gcom.cobranca.CicloMetaGrupo;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.FiltroCicloMeta;
import gcom.cobranca.FiltroCicloMetaGrupo;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.InformarCicloMetaGrupoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC00] Informar metas do ciclo
 * 
 * @author Francisco do Nascimento
 * 
 */

public class InformarCicloMetaGrupoSalvarAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("telaSucesso");

		//CicloMetaGrupoActionForm cicloMetaForm = (CicloMetaGrupoActionForm) actionForm;
	
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = request.getSession(false);
		
		CicloMetaGrupoActionForm cicloMetaForm = (CicloMetaGrupoActionForm) actionForm;
		CicloMeta cicloMeta = (CicloMeta) sessao.getAttribute("cicloMeta");
		
		
		//se as metas foram regeradas
		if (sessao.getAttribute("metasRegeradas") != null){
			fachada.removerCicloMetaGrupo(new Integer(cicloMetaForm.getIdCicloMeta()));
			sessao.removeAttribute("metasRegeradas");
		}
		
		if (cicloMeta == null){
			if ((cicloMetaForm.getValorLimite() == null 
					|| cicloMetaForm.getValorLimite().equals(""))
					&& (cicloMetaForm.getMetaTotal() == null 
							|| cicloMetaForm.getMetaTotal().equals(""))){
				throw new ActionServletException(
					"atencao.informe_valor_ou_meta", null, "");
			}
		} if (cicloMeta != null){
			if ((cicloMetaForm.getValorLimite() == null 
					|| cicloMetaForm.getValorLimite().equals(""))
					&& (cicloMetaForm.getMetaTotal() == null 
							|| cicloMetaForm.getMetaTotal().equals(""))){
				throw new ActionServletException(
					"atencao.informe_valor_ou_meta", null, "");
			}
			
			if (cicloMeta.getId() == null){
				FiltroCicloMeta filtroCiclo = new FiltroCicloMeta();
				filtroCiclo.adicionarParametro(new ParametroSimples(FiltroCicloMeta.COBRANCA_ACAO_ID, 
					cicloMetaForm.getIdCobrancaAcao()));
				filtroCiclo.adicionarParametro(new ParametroSimples(FiltroCicloMeta.ANO_MES_REFERENCIA, 
						Util.formatarMesAnoComBarraParaAnoMes(cicloMetaForm.getAnoMesCobranca())));
				filtroCiclo.adicionarCaminhoParaCarregamentoEntidade(FiltroCicloMeta.COBRANCA_ACAO);

				Collection colecaoCicloMetas = fachada.pesquisar(
						filtroCiclo, CicloMeta.class.getName());
				
				if (colecaoCicloMetas != null && colecaoCicloMetas.size() > 0){
					throw new ActionServletException(
							"atencao.ciclo_meta_ano_mes_ja_existe", null, "");
				}
			}
		}
		
		Collection<InformarCicloMetaGrupoHelper> helpers = null;
		Collection colecaoCicloMetaGrupo = null;
		
		if (!cicloMetaForm.getIdCicloMeta().equals("") 
				&& !cicloMetaForm.getIdCicloMeta().equals("-1") ){
			FiltroCicloMetaGrupo filtroCicloMetaGrupo = new FiltroCicloMetaGrupo();
			filtroCicloMetaGrupo.adicionarParametro(new ParametroSimples(FiltroCicloMetaGrupo.CICLO_META_ID, 
				cicloMetaForm.getIdCicloMeta()));
			colecaoCicloMetaGrupo = fachada.pesquisar(
					filtroCicloMetaGrupo, CicloMetaGrupo.class.getName());
		}
		boolean distribuidas = false;
		if (colecaoCicloMetaGrupo == null || colecaoCicloMetaGrupo.isEmpty() 
				|| cicloMetaForm.getIdCicloMeta() == null || cicloMetaForm.getIdCicloMeta().equals("")){
				if (cicloMetaForm.getMetaTotal() != null 
						&& !cicloMetaForm.getMetaTotal().equals("")
						&& !cicloMetaForm.getMetaTotal().equals("0")){
					if (cicloMeta != null){
						cicloMeta.setMetaTotal(new Integer(cicloMetaForm.getMetaTotal()));
						fachada.distribuirMetasCiclo(cicloMeta);
						distribuidas = true;
					}else{
						throw new ActionServletException(
								"atencao.informe_valor_ou_meta", null, "");
					}
				}
		}
		
		helpers = (Collection<InformarCicloMetaGrupoHelper>) sessao.getAttribute("helpers");
		//Collection<InformarCicloMetaGrupoHelper> helpers = (Collection<InformarCicloMetaGrupoHelper>) sessao.getAttribute("helpers");
	
		
		/*if (helpers == null && cicloMeta == null){
			if (cicloMetaForm.getValorLimite() == null 
					|| cicloMetaForm.getValorLimite().equals("")){
				throw new ActionServletException(
					"atencao.informe_valor_ou_meta", null, "");
			}
		}*/
		
		
		Collection<InformarCicloMetaGrupoHelper> helpersLocalidade = new ArrayList<InformarCicloMetaGrupoHelper>();
		
		if (distribuidas == false){
			// Acumula numa coleção os helpers de todas as localidades
			if (helpers != null) {
				
				for (Iterator iter = helpers.iterator(); iter.hasNext();) {
					
					InformarCicloMetaGrupoHelper itemGerencia = (InformarCicloMetaGrupoHelper) iter.next();
					
					for (Iterator iter2 = itemGerencia.getSubItens().values().iterator(); iter2.hasNext();) {
						
						InformarCicloMetaGrupoHelper itemUneg = (InformarCicloMetaGrupoHelper) iter2.next();
						
						for (Iterator iter3 = itemUneg.getSubItens().values().iterator(); iter3
								.hasNext();) {
							
							InformarCicloMetaGrupoHelper itemLoc = (InformarCicloMetaGrupoHelper) iter3.next();
							  
							String nomeItem = itemGerencia.getTipoItem() + itemGerencia.getIdItem() + 
								itemUneg.getTipoItem() + itemUneg.getIdItem() +
								itemLoc.getTipoItem() + itemLoc.getIdItem(); 
							
							itemLoc.setMetaAtual(Integer.parseInt(
									request.getParameter(nomeItem)));
							
						}
						
						helpersLocalidade.addAll(itemUneg.getSubItens().values());
					}
					
				}
				
				FiltroCicloMeta filtroCiclo = new FiltroCicloMeta();
				filtroCiclo.adicionarParametro(new ParametroSimples(FiltroCicloMeta.COBRANCA_ACAO_ID, 
					cicloMetaForm.getIdCobrancaAcao()));
				filtroCiclo.adicionarParametro(new ParametroSimples(FiltroCicloMeta.ANO_MES_REFERENCIA, 
						Util.formatarMesAnoComBarraParaAnoMes(cicloMetaForm.getAnoMesCobranca())));
				filtroCiclo.adicionarCaminhoParaCarregamentoEntidade(FiltroCicloMeta.COBRANCA_ACAO);
	
				Collection colecaoCicloMetas = fachada.pesquisar(
						filtroCiclo, CicloMeta.class.getName());
				cicloMeta = (CicloMeta)Util.retonarObjetoDeColecao(colecaoCicloMetas);
				
				fachada.atualizarDistribuicaoMetasCicloGrupoLocalidade(cicloMeta, helpersLocalidade);
				
			}
		}
		
		//5.	Caso contrário o sistema permite que seja informado o valor
		//6.	Caso o valor não estivesse informado 
		//6.1.	O usuário pode informar o valor 
		if (cicloMeta == null && cicloMetaForm.getValorLimite() != null 
				&& !cicloMetaForm.getValorLimite().equals("")
				&& helpers == null){
			
			cicloMeta = new CicloMeta();
			cicloMeta.setAnoMesReferencia(Util.formatarMesAnoComBarraParaAnoMes(cicloMetaForm.getAnoMesCobranca()));
			cicloMeta.setUltimaAlteracao(new Date());
			
			FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
			filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.ID, 
				cicloMetaForm.getIdCobrancaAcao()));
			filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcao.COBRANCAO_CRITERIO);
			filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcao.LIGACAO_AGUA_SITUACAO);
			Collection colecaoCobrancaAcao = fachada.pesquisar(
					filtroCobrancaAcao, CobrancaAcao.class.getName());
			
			CobrancaAcao cobrancaAcao = (CobrancaAcao) Util.retonarObjetoDeColecao(
				colecaoCobrancaAcao);
			
			cicloMeta.setCobrancaAcao(cobrancaAcao);
			cicloMetaForm.setValorLimite(cicloMetaForm.getValorLimite().replace(".","").replace(",","."));
			if (new Integer(cicloMetaForm.getValorLimite()).intValue() > 0){
				cicloMeta.setValorLimite(new BigDecimal(cicloMetaForm.getValorLimite()));
				fachada.inserir(cicloMeta);
			}
			
		}else if (cicloMeta != null && cicloMetaForm.getValorLimite() != null 
				&& !cicloMetaForm.getValorLimite().equals("")){
			cicloMetaForm.setValorLimite(cicloMetaForm.getValorLimite().replace(".","").replace(",","."));
			
			cicloMeta.setValorLimite(new BigDecimal(cicloMetaForm.getValorLimite()));
			
			if (cicloMeta.getValorLimite().compareTo(new BigDecimal(0)) > 0){
				fachada.atualizar(cicloMeta);
			}
		}
		
        request.setAttribute("caminhoFuncionalidade","exibirInformarCicloMetaGrupoAction.do?menu=sim");
		request.setAttribute("labelPaginaSucesso"," Informar Metas de outra Ação de Cobrança ");
		request.setAttribute("mensagemPaginaSucesso","Metas/Valor Limite da Ação de Cobrança " + cicloMeta.getCobrancaAcao().getDescricaoCobrancaAcao() 
				+ " do Ciclo " + Util.formatarAnoMesParaMesAno(cicloMeta.getAnoMesReferencia())  				 
				+ " atualizadas com sucesso. ");

		return retorno;

	}

}
