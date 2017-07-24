package br.com.rodrigo.OFP.facade;

import java.util.List;

import br.com.rodrigo.OFP.modelo.Transacao;

public class ExtratoFacade {
	
	
//	private static Boolean LadoDireitoDaTimeLine = true;

	
	
	
	public  List<Transacao>  ordenaLista(List<Transacao> transacoes){
		transacoes.sort((t1, t2) -> t1.getDatapagamento().compareTo(t2.getDatapagamento()));
		//		extratos  = extratos.entrySet().stream()
//	                .sorted((e1,e2)->e1.getKey().getDatapagamento().compareTo(e2.getKey().getDatapagamento()))
//	                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
//	                        (e1, e2) -> e1, LinkedHashMap::new));
		return transacoes;

	}

//	public Map<Transacao, Boolean> listaDeExtrato(){
//		extratos = new HashMap<Transacao, Boolean>();
//		for (Transacao transacao : transacoes) {
//			if (transacao instanceof Despesa) {
//				LadoDireitoDaTimeLine=false;
//			}else if (transacao instanceof Receita) {
//				LadoDireitoDaTimeLine=true;
//			}
//			extratos.put(transacao, LadoDireitoDaTimeLine);
//		}
//		ordenaLista();
//		
//		return extratos;
//		
//		
//	}
	

}
