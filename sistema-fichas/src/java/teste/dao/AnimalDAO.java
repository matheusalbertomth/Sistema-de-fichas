package teste.dao;

import teste.entidade.Animal;
import teste.util.FabricaConexao;
import teste.util.exception.ErroSistema;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO implements CrudDAO<Animal>{
    
    @Override
    public void salvar(Animal animal) throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            if(animal.getId() == null){
                ps = conexao.prepareStatement("INSERT INTO `animal` (`nome`) VALUES (?)");
            } else {
                ps = conexao.prepareStatement("update animal set nome=? where id=?");
                ps.setInt(5, animal.getId());
            }
            ps.setString(1, animal.getNome());
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar!", ex);
        }
    }
    
    @Override
    public void deletar(Animal animal) throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps  = conexao.prepareStatement("delete from animal where id = ?");
            ps.setInt(1, animal.getId());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao deletar o animal cadastrado!", ex);
        }
    }
    
    @Override
    public List<Animal> buscar() throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("select * from animal");
            ResultSet resultSet = ps.executeQuery();
            List<Animal> animais = new ArrayList<>();
            while(resultSet.next()){
                Animal animal = new Animal();
                animal.setId(resultSet.getInt("id"));
                animal.setNome(resultSet.getString("nome"));
                animais.add(animal);
            }
            FabricaConexao.fecharConexao();
            return animais;
            
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar animais!",ex);
        }
    }
}
