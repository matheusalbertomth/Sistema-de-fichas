package teste.bean;

import teste.dao.FichaDAO;
import teste.entidade.Ficha;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class FichaBean extends CrudBean<Ficha, FichaDAO>{

    private FichaDAO fichaDAO;
    
    @Override
    public FichaDAO getDao() {
        if(fichaDAO == null){
            fichaDAO = new FichaDAO();
        }
        return fichaDAO;
    }

    public Ficha criarNovaEntidade() {
        return new Ficha();
    }    
    
}