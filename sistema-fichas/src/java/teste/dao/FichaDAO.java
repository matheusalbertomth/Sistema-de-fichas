package teste.dao;

import teste.entidade.Animal;
import teste.entidade.Ficha;
import teste.util.FabricaConexao;
import teste.util.exception.ErroSistema;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FichaDAO implements CrudDAO<Ficha>{

    @Override
    public void salvar(Ficha ficha) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            if(ficha.getId() == null){
                ps = conexao.prepareStatement("INSERT INTO ficha (dtCadastro,observacao,status) VALUES (?,?,?)");
            } else {
                ps = conexao.prepareStatement("update ficha set dtCadastro=?, observacao=?, status=? where id=?");
                ps.setInt(3, ficha.getId());
            }
            ps.setDate(1, new Date(ficha.getDtCadastro().getTime()));
            ps.setString(2, ficha.getObservacao());
            ps.setString(3, ficha.getStatus());
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar!", ex);
        }
    }

    @Override
    public void deletar(Ficha ficha) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps  = conexao.prepareStatement("delete from ficha where id = ?");
            ps.setInt(1, ficha.getId());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao deletar ficha!", ex);
        }
    }

    @Override
    public List<Ficha> buscar() throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("select * from ficha");
            ResultSet resultSet = ps.executeQuery();
            List<Ficha> fichas = new ArrayList<>();
            while(resultSet.next()){
                Ficha ficha = new Ficha();
                ficha.setId(resultSet.getInt("id"));
                ficha.setDtCadastro(resultSet.getDate("dtCadastro"));
                ficha.setObservacao(resultSet.getString("observacao"));
                ficha.setStatus(resultSet.getString("status"));
                fichas.add(ficha);
            }
            FabricaConexao.fecharConexao();
            return fichas;
            
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar os fichas!",ex);
        }
    }
    
}
