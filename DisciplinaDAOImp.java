package com.yourpackage.dao.impl;

import com.yourpackage.dao.DisciplinaDAO; 
import com.yourpackage.db.DB; 
import com.yourpackage.db.DBException; 
import com.yourpackage.model.Disciplina; 

import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 
import java.util.ArrayList;
import java.util.List; 


public class DisciplinaDAOImp implements DisciplinaDAO {

    private Connection conn;

    public DisciplinaDAOImp(Connection conn) {
        this.conn = conn;
    }

    private Disciplina instantiateDisciplina(ResultSet rs) throws SQLException {
        Disciplina obj = new Disciplina();
        obj.setIdDisciplina(rs.getInt("iddisciplina")); 
        obj.setNomeDisciplina(rs.getString("nomedisciplina")); 
        obj.setCargaHoraria(rs.getInt("cargahoraria")); 
        return obj;
    }

    @Override
    public void insert(Disciplina obj) {
        PreparedStatement st = null; 
        try {
            st = conn.prepareStatement(
                "INSERT INTO disciplina (nomedisciplina, cargahoraria) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNomeDisciplina());
            st.setInt(2, obj.getCargaHoraria());

            int rowsAffected = st.executeUpdate(); 

            if (rowsAffected > 0) { 
                ResultSet rs = st.getGeneratedKeys(); 
                if (rs.next()) {
                    int id = rs.getInt(1); 
                    obj.setIdDisciplina(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DBException("Erro inesperado! Nenhuma linha afetada!"); 
        } catch (SQLException e) {
            throw new DBException(e.getMessage()); 
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Disciplina obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                "UPDATE disciplina SET nomedisciplina = ?, cargahoraria = ? WHERE iddisciplina = ?");

            st.setString(1, obj.getNomeDisciplina());
            st.setInt(2, obj.getCargaHoraria());
            st.setInt(3, obj.getIdDisciplina()); 

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM disciplina WHERE iddisciplina = ?");
            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) { 
                throw new DBException("ID não existe!");
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Disciplina findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                "SELECT * FROM disciplina WHERE iddisciplina = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) { 
                return instantiateDisciplina(rs); 
            }
            return null; 
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Disciplina> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            // Prepara a instrução SQL para buscar todas as disciplinas
            st = conn.prepareStatement(
                "SELECT * FROM disciplina ORDER BY nomedisciplina"); // Ordena por nome para melhor visualização
            rs = st.executeQuery();

            List<Disciplina> list = new ArrayList<>(); // Cria uma lista para armazenar as disciplinas
            while (rs.next()) { // Itera sobre os resultados
                list.add(instantiateDisciplina(rs)); // Adiciona cada disciplina à lista
            }
            return list; // Retorna a lista de disciplinas
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
