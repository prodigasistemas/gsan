package gcom.cadastro.atualizacaocadastral.validador;

import java.util.Date;

import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.cadastro.imovel.Imovel;

/**
 * 
 * Classe para validar se o imovel em questao pode ser atualizado
 * ou nao olhando para linha temporal
 * 
 * @author ewerttonbravo
 *
 */
public class ValidadorDataAlteracaoImovel extends ValidadorCommand {
	
	ControladorAtualizacaoCadastralLocal controlador;
	
	public ValidadorDataAlteracaoImovel(AtualizacaoCadastralImovel cadastroImovel, 
			ControladorAtualizacaoCadastralLocal controlador) {
		super(cadastroImovel);
		this.controlador = controlador;
	}
	
	@Override
	public void execute() throws Exception {
		Date dataDaUltimaAlteracaoDoImovel = controlador.pesquisarDataDaUltimaAlteracaoDoImovel(cadastroImovel.getMatricula());
		ImovelControleAtualizacaoCadastral imovelControle = controlador.pesquisarImovelControleAtualizacao(cadastroImovel.getMatricula());
		if (imovelControle != null && dataDaUltimaAlteracaoDoImovel != null) {
			if (dataDaUltimaAlteracaoDoImovel.compareTo(imovelControle.getDataGeracao()) > 0) {
				cadastroImovel.addMensagemErro("Imóvel foi alterado depois da geração da rota.");
			}
		}
	}

}
