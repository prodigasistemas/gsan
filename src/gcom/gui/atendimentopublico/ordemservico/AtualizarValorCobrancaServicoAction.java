package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ServicoCobrancaValor;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 01/11/2006
 */
public class AtualizarValorCobrancaServicoAction extends GcomAction {

	/**
	 * [UC0393] Manter Valor de Cobrança do Serviço
	 * 
	 * Este caso de uso cria um filtro que será usado na pesquisa do Valor de
	 * Cobrança de Serviço
	 * 
	 * [SB0001] Atualizar Valor de Cobrança do Serviço
	 * 
	 * @author Rômulo Aurélio
	 * @date 01/11/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

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
		
		Date timeStampDB = (Date) sessao.getAttribute("timeStamp");
		
		Date dataVigenciaInicial = (Date) sessao.getAttribute("dataVigenciaInicial");
		
		Date dataVigenciaFinal = (Date) sessao.getAttribute("dataVigenciaFinal");

		AtualizarValorCobrancaServicoActionForm form = (AtualizarValorCobrancaServicoActionForm) actionForm;

		String idServicoCobrancaValor1 = (String) sessao.getAttribute("idServicoCobrancaValor");
		
		form.setIdServicoCobrancaValor(idServicoCobrancaValor1);
		
		ServicoCobrancaValor servicoCobrancaValor = (ServicoCobrancaValor) sessao
				.getAttribute("servicoCobrancaValor");

		String descricaoTipoServico = form.getNomeTipoServico();

		String indicadorMedido = form.getIndicadorMedido();
		
		String indicativoTipoSevicoEconomias = form.getIndicativoTipoSevicoEconomias();

		String tipoServico = form.getTipoServico();

		String perfilImovel = form.getPerfilImovel();

		String capacidadeHidrometro = form.getCapacidadeHidrometro();
		
		String idCategoria = form.getIdCategoria();
		
		String idSubCategoria = form.getIdSubCategoria();
		
		String quantidadeEconomiaInicial = form.getQuantidadeEconomiasInicial();
		
		String quantidadeEconomiaFinal = form.getQuantidadeEconomiasFinal();
		
		String indicadorGeracaoDebito = form.getIndicadorGeracaoDebito();
	
		String valorServico = form.getValorServico();
		
		if(valorServico!=null && !valorServico.equals("")){

			String valorSemPontos = valorServico.replace(".", "");
	
			valorServico = valorSemPontos.replace(",", ".");
		
		}else{
			valorServico = "0";
		}
		
		servicoCobrancaValor.setValor(new BigDecimal(valorServico));
		
		if(indicadorGeracaoDebito!=null && !indicadorGeracaoDebito.equals("")){
			servicoCobrancaValor.setIndicadorGeracaoDebito(new Short(indicadorGeracaoDebito));
		}

		// Seta no Objeto os dados do form
		ServicoTipo servicoTipo = new ServicoTipo();

		servicoTipo.setId(new Integer(tipoServico));

		servicoCobrancaValor.setServicoTipo(servicoTipo);
		
		//categoria
		Categoria categoria = new Categoria();
		
		if(idCategoria!=null && !idCategoria.equals(ConstantesSistema.NUMERO_NAO_INFORMADO+"")){
			categoria.setId(new Integer(idCategoria));
			
			servicoCobrancaValor.setCategoria(categoria);
		}else{
			servicoCobrancaValor.setCategoria(null);
		}
		
		// subCategoria
		Subcategoria subCategoria = new Subcategoria();
		
		if(idSubCategoria != null && !idSubCategoria.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			subCategoria.setId(new Integer(idSubCategoria));
			
			servicoCobrancaValor.setSubCategoria(subCategoria);
		}else{
			servicoCobrancaValor.setSubCategoria(null);
		}
		
		servicoCobrancaValor.setIndicadorConsideraEconomias(new Short(indicativoTipoSevicoEconomias));

		if(indicadorMedido != null && !indicadorMedido.equals("")){
			servicoCobrancaValor.setIndicadorMedido(new Short(indicadorMedido));
		}
		
		if(servicoCobrancaValor.getIndicadorConsideraEconomias().compareTo(ConstantesSistema.SIM)==0){
			// Quantidade Economia Inicial
			if(quantidadeEconomiaInicial != null && !quantidadeEconomiaInicial.equals("")){
				servicoCobrancaValor.setQuantidadeEconomiasInicial(new Integer(quantidadeEconomiaInicial));
			}
		
			// Quantidade Economia Final
			if(quantidadeEconomiaFinal != null && !quantidadeEconomiaFinal.equals("")){
				servicoCobrancaValor.setQuantidadeEconomiasFinal(new Integer(quantidadeEconomiaFinal));
			}
		}else{
			servicoCobrancaValor.setQuantidadeEconomiasInicial(null);
			servicoCobrancaValor.setQuantidadeEconomiasFinal(null);
		}
		
		
		// capacidade Hidrometro
		HidrometroCapacidade hidrometroCapacidade = new HidrometroCapacidade();
		
		if (capacidadeHidrometro != null
				&& !capacidadeHidrometro.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)){ 

			hidrometroCapacidade.setId(new Integer(capacidadeHidrometro));

			servicoCobrancaValor.setHidrometroCapacidade(hidrometroCapacidade);
		}else{
			servicoCobrancaValor.setHidrometroCapacidade(null);
		}

		// Perfil imovel
		ImovelPerfil imovelPerfil = new ImovelPerfil();
		
		if (perfilImovel != null
				&& !perfilImovel.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			imovelPerfil.setId(new Integer(perfilImovel));

			servicoCobrancaValor.setImovelPerfil(imovelPerfil);
		}
		
		// Vigência servico Cobranca Valor
		//[FS0007] – Validar data da vigência inicial
		if (form.getDataVigenciaInicial() != null && !form.getDataVigenciaInicial().equals("")){
			
			if (!Util.validarDiaMesAno(form.getDataVigenciaInicial())) {
				
				servicoCobrancaValor.setDataVigenciaInicial(Util.converteStringParaDate(form.getDataVigenciaInicial()));
				//[FS0008] – Validar data da vigência final
				if (!Util.validarDiaMesAno(form.getDataVigenciaFinal())) {
					
					servicoCobrancaValor.setDataVigenciaFinal(Util.converteStringParaDate(form.getDataVigenciaFinal()));
					
					if(Util.compararData(servicoCobrancaValor.getDataVigenciaInicial(),servicoCobrancaValor.getDataVigenciaFinal()) == 1){
						throw new ActionServletException("atencao.atencao.data_vigencia_final_menor");
					}
				}else{
					throw new ActionServletException("atencao.atencao.data_vigencia_final_invalida");
				}			
			}else{
				throw new ActionServletException("atencao.data_vigencia_inicial_invalida");
			}
		}else{
			
			servicoCobrancaValor.setDataVigenciaInicial(null);
			servicoCobrancaValor.setDataVigenciaFinal(null);
		}
		
		// FS0008 - Verificar pré-existência de vigência para o Serviço tipo
		Fachada.getInstancia().verificarExistenciaVigenciaDebito(form.getDataVigenciaInicial(), 
				form.getDataVigenciaFinal(), new Integer(form.getTipoServico()),
				new Integer("1"));
		
		
		/*	 
		 	-Colocar método no controlador 		 	
		 Data: 14/05/2010
		 Author: Hugo Amorim		 
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_VALOR_COBRANCA_SERVICO_ATUALIZAR, servicoCobrancaValor.getServicoTipo().getId(),
				servicoCobrancaValor.getServicoTipo().getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		
		registradorOperacao.registrarOperacao(servicoCobrancaValor);
		*/
		if(timeStampDB.compareTo(servicoCobrancaValor.getUltimaAlteracao()) != 0){
			throw new ActionServletException("atencao.atualizacao.timestamp");
		}
		
		if(dataVigenciaInicial.compareTo(servicoCobrancaValor.getDataVigenciaInicial()) != 0
				|| dataVigenciaFinal.compareTo(servicoCobrancaValor.getDataVigenciaFinal()) != 0){
			// FS0013 - Verificar pré-existência de vigência para o Serviço tipo
			if(fachada.validarVigenciaValorCobrancaServico(servicoCobrancaValor)){
				throw new ActionServletException("atencao.existe_valor_para_vigencia");
			}
		}	
		fachada.atualizarValorCobrancaServico(servicoCobrancaValor,usuarioLogado);

		montarPaginaSucesso(httpServletRequest, "Valor da Cobrança do Serviço "
				+ descricaoTipoServico + " atualizado com sucesso.",
				"Realizar outra Manutenção Valor da Cobrança do Serviço",
				"exibirFiltrarValorCobrancaServicoAction.do?menu=sim");

		return retorno;
	}
}
