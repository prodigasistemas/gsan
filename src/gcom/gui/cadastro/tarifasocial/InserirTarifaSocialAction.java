package gcom.gui.cadastro.tarifasocial;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirTarifaSocialAction extends GcomAction {
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Instancia da Fachada
        Fachada fachada = Fachada.getInstancia();

        //Pega uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);
        
        RegistroAtendimento registroAtendimento = (RegistroAtendimento) sessao.getAttribute("ra");
        
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
        atendimentoMotivoEncerramento.setId(15);
        
        registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
        registroAtendimento.setDataEncerramento(new Date());
        
        RegistroAtendimentoUnidade registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
        registroAtendimentoUnidade.setRegistroAtendimento(registroAtendimento);
        registroAtendimentoUnidade.setUnidadeOrganizacional(usuarioLogado.getUnidadeOrganizacional());
        registroAtendimentoUnidade.setUsuario(usuarioLogado);
        AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
        atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
        registroAtendimentoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
        registroAtendimentoUnidade.setUltimaAlteracao(new Date());

        //Para apenas uma economia
        
        Collection colecaoTarifaSocialDadoEconomia = null;
        
        if (sessao
                .getAttribute("colecaoDadosTarifaSocial") != null) {
        	colecaoTarifaSocialDadoEconomia = (Collection) sessao
                .getAttribute("colecaoDadosTarifaSocial");
        } else {
        	colecaoTarifaSocialDadoEconomia = (Collection) sessao
            .getAttribute("colecaoTarifaSocialDadoEconomia");
        }
        
        ClienteImovel clienteImovel = (ClienteImovel) sessao
                .getAttribute("clienteImovel");

        //Para mais de uma economia
        Collection colecaoClienteImovelEconomia = (Collection) sessao
                .getAttribute("colecaoClienteImovelEconomia");

        //Imóvel que está sendo trabalhado
        Imovel imovelSessao = (Imovel) sessao.getAttribute("imovelTarifa");
        
        Integer idTarifaSocialDadoEconomiaExcluida = null; 
        	
        if (sessao.getAttribute("idTarifaSocialDadoEconomia") != null) {
        	idTarifaSocialDadoEconomiaExcluida = new Integer((String) sessao.getAttribute("idTarifaSocialDadoEconomia"));
        }
        
        Collection colecaoTarifaSocialRecadastrar = (Collection) sessao
			.getAttribute("colecaoTarifaSocialDadoEconomia");
        
        Imovel imovelAtualizar = (Imovel) sessao.getAttribute("imovelAtualizado");
        
        Collection colecaoImovelEconomiaAtualizar = (Collection) sessao.getAttribute("colecaoImovelEconomiaAtualizados");
        
        fachada.inserirTarifaSocial(imovelSessao, clienteImovel,
				registroAtendimento, registroAtendimentoUnidade, usuarioLogado,
				idTarifaSocialDadoEconomiaExcluida,
				colecaoTarifaSocialDadoEconomia, colecaoClienteImovelEconomia,
				colecaoTarifaSocialRecadastrar, imovelAtualizar,
				colecaoImovelEconomiaAtualizar);
        
        montarPaginaSucesso(httpServletRequest, "Imóvel de matrícula " + imovelSessao.getId()
                + " incluído na tarifa social com sucesso.", "Inserir outra tarifa social",
                "exibirInserirTarifaSocialAction.do?menu=sim");
        
        
        
        return retorno;
    }
}
