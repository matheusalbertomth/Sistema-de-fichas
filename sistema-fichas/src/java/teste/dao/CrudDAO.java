package teste.dao;

import teste.util.exception.ErroSistema;
import java.util.List;

public interface CrudDAO<E> {
    
    public void salvar(E entidade) throws ErroSistema;
    public void deletar(E entidade) throws ErroSistema;
    public List<E> buscar() throws ErroSistema;
    
}
