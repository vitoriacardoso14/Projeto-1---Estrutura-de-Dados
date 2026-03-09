public class Chamado {
    private String id, bairro, descricao;
	private char nivelUrgencia [1, 5]; 
    public enum Status(ABERTO, EM_ATENDIMENTO, FINALIZADO);
    
    public Chamado(){ 
		this("00000", "Nenhum", "Nenhum", (int) 2, "ABERTO");
	} 
	public Chamado(String id,
                       String bairro,
					   String descricao, 
					   char niverUrgencia,
					   Status status){ 
		this.id = id;
		this.bairro = bairro; 
		this.descricao = descricao;
		this.nivelUrgencia = niverUrgencia; 
		this.status = status;
	}

	public String getId(){
    	return id;
  	}
	public String getBairro(){
		return bairro;
	}
	public String getDescricao(){
		return descricao;
	}
  	public Char getNivelUrgencia(){
    	return nivelUrgencia;
 	}
  	public Status getStatus(){
		return status;
	}
 
  	public void setId(String id){
    		this.id = id;
  	}
  	public void setBairro(String bairro){
		this.bairro = bairro;
	}
  	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
  	public void setNivelUrgencia(Char NivelUrgencia){
    		this.nivelUrgencia = nivelUrgencia;
  	}
  	public void setStatus(Status status){
		this.idade = idade;
	}

  	public String dados() {
  		String dadosChamado = "ID: " + getId() +
  								  ", Bairro: " + getBarro() +
  								  ", Descrição: " + getDescricao() +
  								  ", Nivel de Urgência: " + getNivelUrgencia() +
  								  ", Status: " + getStatus() +
  		return dadosChamado;
  	}
}