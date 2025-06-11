// src/main/java/com/yourpackage/application/Program.java
package com.yourpackage.application;

import com.yourpackage.dao.DisciplinaDAO; // Importa a interface DAO
import com.yourpackage.dao.impl.DisciplinaDAOImp; // Importa a implementação DAO
import com.yourpackage.db.DB; // Importa o utilitário de banco de dados
import com.yourpackage.model.Disciplina; // Importa a entidade Disciplina

import java.sql.Connection; // Para a conexão com o banco
import java.util.List; // Para listas
import java.util.Scanner; // Para entrada do usuário

public class Program {

    public static void main(String[] args) {

        Connection conn = null;
        Scanner sc = null;

        try {
            conn = DB.getConnection(); // Obtém a conexão com o banco de dados
            DisciplinaDAO disciplinaDao = new DisciplinaDAOImp(conn); // Cria uma instância da implementação DAO

            sc = new Scanner(System.in); // Inicializa o scanner para entrada do usuário

            System.out.println("=== TESTE 1: inserir Disciplina ===");
            // Cria uma nova disciplina (o ID será gerado pelo banco)
            Disciplina newDisciplina = new Disciplina(0, "Desenvolvimento Web III", 80);
            disciplinaDao.insert(newDisciplina); // Insere a disciplina
            System.out.println("Nova disciplina inserida! Novo ID = " + newDisciplina.getIdDisciplina());

            System.out.println("\n=== TESTE 2: buscar Disciplina por ID ===");
            // Busca a disciplina recém-inserida pelo seu ID
            Disciplina disc = disciplinaDao.findById(newDisciplina.getIdDisciplina());
            System.out.println("Disciplina encontrada: " + disc);

            System.out.println("\n=== TESTE 3: atualizar Disciplina ===");
            disc.setNomeDisciplina("Desenvolvimento Web Avançado"); // Altera o nome da disciplina
            disc.setCargaHoraria(120); // Altera a carga horária
            disciplinaDao.update(disc); // Atualiza a disciplina no banco
            System.out.println("Atualização concluída! " + disc);

            System.out.println("\n=== TESTE 4: buscar todas as Disciplinas ===");
            List<Disciplina> list = disciplinaDao.findAll(); // Busca todas as disciplinas
            for (Disciplina d : list) {
                System.out.println(d); // Imprime cada disciplina
            }

            System.out.println("\n=== TESTE 5: excluir Disciplina ===");
            System.out.print("Digite o ID para o teste de exclusão: ");
            int idToDelete = sc.nextInt(); // Solicita o ID para exclusão
            disciplinaDao.deleteById(idToDelete); // Exclui a disciplina
            System.out.println("Exclusão concluída!");

        } catch (Exception e) {
            System.err.println("Ocorreu um erro: " + e.getMessage()); // Imprime qualquer erro
        } finally {
            DB.closeConnection(); // Garante que a conexão seja fechada
            if (sc != null) {
                sc.close(); // Garante que o scanner seja fechado
            }
        }
    }
}
