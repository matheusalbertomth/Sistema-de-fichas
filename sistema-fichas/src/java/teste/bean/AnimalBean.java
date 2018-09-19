package teste.bean;

import teste.dao.AnimalDAO;
import teste.entidade.Animal;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AnimalBean extends CrudBean<Animal, AnimalDAO> {

    private AnimalDAO animalDAO;
    
    @Override
    public AnimalDAO getDao() {
        if(animalDAO == null){
            animalDAO = new AnimalDAO();
        }
        return animalDAO;
    }

    @Override
    public Animal criarNovaEntidade() {
        return new Animal();
    }

}
