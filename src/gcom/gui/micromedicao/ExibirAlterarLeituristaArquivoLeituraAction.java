package gcom.gui.micromedicao;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.ArquivoTextoRoteiroEmpresa;
import gcom.micromedicao.ArquivoTextoRoteiroEmpresaDivisao;
import gcom.micromedicao.FiltroArquivoTextoRoteiroEmpresa;
import gcom.micromedicao.FiltroArquivoTextoRoteiroEmpresaDivisao;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAlterarLeituristaArquivoLeituraAction extends GcomAction {

	
	@Override
	public ActionForward execute(ActionMapping map, 
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse arg3) throws Exception {
		
	//		Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();
		
        HttpSession sessao = httpServletRequest.getSession(false);
		
        AlterarLeituristaArquivoLeituraActionForm form = (AlterarLeituristaArquivoLeituraActionForm) actionForm;
		
       ActionForward retorno = map.findForward("AlterarLeituristaArquivoLeitura");
       
       Usuario usuarioLogado = (Usuario)sessao.getAttribute("usuarioLogado");

		if (httpServletRequest.getParameter("menu") != null) {
			
			form.setEmpresaID(""+usuarioLogado.getEmpresa().getId());
			form.setGrupoFaturamentoID("");
			form.setMesAno("");
			form.setArquivoID("");
			form.setLeitursitaID("");

		}
        
        // Permissao da empresa
        sessao.removeAttribute("permissao");
        if(usuarioLogado.getEmpresa().getIndicadorEmpresaPrincipal().equals(new Short("1"))){
            sessao.setAttribute("permissao", "1");
        }else{
            sessao.setAttribute("permissao", "2");
        }
				
		// Parte que passa as coleções da Empresa necessárias no jsp
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.ID);
		Collection colecaoEmpresa = fachada.pesquisar(
				filtroEmpresa, Empresa.class.getName());

		if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Empresa");
		}
		
		// Parte que passa as coleções da Grupo de Faturamento necessárias no jsp
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.ID);
		Collection colecaoFaturamentoGrupo = fachada.pesquisar(
				filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

		if (colecaoFaturamentoGrupo != null && !colecaoFaturamentoGrupo.isEmpty()) {
			sessao.setAttribute("colecaoFaturamentoGrupo", colecaoFaturamentoGrupo);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Grupo de Faturamento");
		}
		
		String teste = httpServletRequest.getParameter("teste");
		
		if(teste == null){
			teste = "2";
		}
		
		if(form.getEmpresaID()!=null && !form.getEmpresaID().equals("") && !form.getEmpresaID().equals("-1")
				&& form.getGrupoFaturamentoID() != null && !form.getGrupoFaturamentoID().equals("")
				&& !form.getGrupoFaturamentoID().equals("-1") && form.getMesAno() !=null &&
				Util.validarMesAno(form.getMesAno()) && !teste.equals("1")){
			
			FiltroArquivoTextoRoteiroEmpresa filtroArquivo = 
				new FiltroArquivoTextoRoteiroEmpresa(FiltroArquivoTextoRoteiroEmpresa.NUMERO_SEQUENCIA_LEITURA);
			filtroArquivo.adicionarCaminhoParaCarregamentoEntidade(FiltroArquivoTextoRoteiroEmpresa.ROTA);
			filtroArquivo.adicionarCaminhoParaCarregamentoEntidade(FiltroArquivoTextoRoteiroEmpresa.LOCALIDADE);
			
			filtroArquivo.adicionarParametro(new ParametroSimples(
					FiltroArquivoTextoRoteiroEmpresa.SITUACAO_TRANS_LEITURA_ID, SituacaoTransmissaoLeitura.DISPONIVEL));

			filtroArquivo.adicionarParametro(new ParametroSimples(
					FiltroArquivoTextoRoteiroEmpresa.EMPRESA, form.getEmpresaID()));
			
			filtroArquivo.adicionarParametro(new ParametroSimples(
					FiltroArquivoTextoRoteiroEmpresa.GRUPO_FATURAMENTO, form.getGrupoFaturamentoID()));
			
			filtroArquivo.adicionarParametro(new ParametroSimples(
					FiltroArquivoTextoRoteiroEmpresa.ANO_MES_REFERENCIA, 
					Util.formatarMesAnoComBarraParaAnoMes(form.getMesAno())));
			
			Collection colecao = fachada.pesquisar(filtroArquivo,ArquivoTextoRoteiroEmpresa.class.getName());
			
			if(colecao!=null && !colecao.isEmpty()){
				Collection colecaoArquivo = new ArrayList();
				Iterator it = colecao.iterator();
				while(it.hasNext()){
					ArquivoTextoRoteiroEmpresa arq = (ArquivoTextoRoteiroEmpresa)it.next();
					
					String descricao = arq.getNomeArquivo() +  " - Rota: " + arq.getRota().getCodigo();
					DadosRota dado = new DadosRota(arq.getId(),descricao);
					
					if ( form.getTipoArquivo().equals( "T" ) ){
						colecaoArquivo.add(dado);
					} else if ( form.getTipoArquivo().equals( "D" ) ){						
						if ( fachada.isRotaDividida( arq.getRota().getId() , arq.getAnoMesReferencia()) ){
							colecaoArquivo.add(dado);
						}
					} else {
						if ( !fachada.isRotaDividida( arq.getRota().getId(), arq.getAnoMesReferencia() ) ){
							colecaoArquivo.add(dado);
						}						
					}					
				}
				sessao.setAttribute("colecaoArquivo", colecaoArquivo);
				
				//Cria o filtro do leiturista
				FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
				//Adiciona os parametros de pesquisa: empresa e indicador de uso
				filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.EMPRESA_ID, form.getEmpresaID()));
				filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.INDICADOR_USO, ConstantesSistema.SIM));
				filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
				filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
				
				filtroLeiturista.setCampoOrderBy( FiltroLeiturista.FUNCIONARIO_NOME, FiltroLeiturista.CLIENTE_NOME );
				
				//Pesquisa dos leituristas
				Collection colecaoLeit = fachada.pesquisar(filtroLeiturista,Leiturista.class.getName());
				
				it = colecaoLeit.iterator();
				
				Collection colecaoLeiturista = new ArrayList();
				while(it.hasNext()){
					Leiturista leitu =(Leiturista) it.next();
					DadosLeiturista dadosLeiu = null;
					if(leitu.getFuncionario()!=null){
						dadosLeiu = new DadosLeiturista(leitu.getId(),leitu.getFuncionario().getNome());
					}else{
						dadosLeiu = new DadosLeiturista(leitu.getId(),leitu.getCliente().getNome());
					}
					colecaoLeiturista.add(dadosLeiu);
				}
			
				
				sessao.setAttribute("colecaoLeiturista", colecaoLeiturista);
			}else{
				
				
				throw new ActionServletException("atencao.nenhum_arquivo_texto_encontrado", "exibirAlterarLeituristaArquivoLeituraAction.do?teste=1",
					"Arquivo Texto");
								
			}
		}else{
			sessao.setAttribute("colecaoArquivo", null);
		}
		
		String arquivoID = form.getArquivoID();
		
		if ( httpServletRequest.getParameter( "pesquisar" ) != null && 
		      httpServletRequest.getParameter( "pesquisar" ).equals( "consultar" )	){
			arquivoID = "";
		}
		
		if ( arquivoID != null && !arquivoID.equals( "" ) && !arquivoID.equals( "-1" ) ){
			
			FiltroArquivoTextoRoteiroEmpresaDivisao filtroDivisao = new FiltroArquivoTextoRoteiroEmpresaDivisao();
			filtroDivisao.adicionarParametro( new ParametroSimples( FiltroArquivoTextoRoteiroEmpresaDivisao.ARQUIVO_TEXTO_ROTEIRO_EMPRESA_ID, arquivoID ) );
			Collection<ArquivoTextoRoteiroEmpresaDivisao> colDivisao = fachada.pesquisar( filtroDivisao, ArquivoTextoRoteiroEmpresaDivisao.class.getName() );
			Collection<DadosRota> colDadosDivisao = new ArrayList();
			
			for (ArquivoTextoRoteiroEmpresaDivisao divisao : colDivisao) {
				String descricao = divisao.getNomeArquivo() +  " - Seq.: " + divisao.getNumeroSequenciaArquivo();				
				DadosRota dadosRota = new DadosRota( divisao.getId(), descricao );
				
				colDadosDivisao.add( dadosRota );
			}
			
			if ( colDivisao != null && !colDivisao.isEmpty() ){
				sessao.setAttribute("colecaoArquivoDividido", colDadosDivisao);
			}
			
		} else {
			form.setArquivoID( "-1" );
			sessao.setAttribute("colecaoArquivoDividido", null);
		}
		
		return retorno;
	}
}
