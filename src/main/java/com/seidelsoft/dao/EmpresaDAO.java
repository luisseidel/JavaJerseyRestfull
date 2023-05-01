package com.seidelsoft.dao;

import com.seidelsoft.model.Empresa;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpresaDAO extends BaseDAO<Empresa> {

    public EmpresaDAO(String tableName) {
        super(tableName);
    }

    @Override
    public Empresa getById(Long id) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(selectById(id));
            ResultSet result = executeQueryForGet(stmt);

            return createRegister(result);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public List<Empresa> getByName(String name) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(selectByName("nome", name));
            ResultSet result = executeQueryForGet(stmt);

            return prepareListOf(result);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Empresa> getList() {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(getListQuery());
            ResultSet result = executeQueryForGet(stmt);

            return prepareListOf(result);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Empresa save(Empresa a) throws SQLException {
        try {
            Map<String, String> colVals = new HashMap<>();
            colVals.put("nome", a.getNome());
            colVals.put("endereco", a.getEndereco());

            ResultSet exists = verifyBeforeInsert(colVals);
            if (exists != null && exists.next()) {
                throw new SQLException("Registro já existe no banco de dados!");
            }

            if (exists != null && !exists.next()) {
                executeInsert(colVals);
                ResultSet result = executeQueryForGet(getConnection().prepareStatement(getMaxId()));
                while (result.next()) {
                    Long id = result.getLong("id");
                    return getById(id);
                }
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

        return null;
    }

    public Empresa update(Long id, Empresa e) throws SQLException, IllegalAccessException, NoSuchFieldException {
        if (id == null || id <= 0)
            throw new SQLException("Informe um id");

        Map<String, Object> colVals = new HashMap<>();

        for (Field field : e.getClass().getDeclaredFields()) {
            if (!field.getName().equalsIgnoreCase("id")) {
                field.setAccessible(true);
                colVals.put(field.getName(), field.get(e));
            }
        }

        executeUpdate(id, colVals);

        return getById(id);
    }

    @Override
    public void delete(Long id) throws SQLException {
        try {
            if (getById(id) == null)
                throw new SQLException("Registro não existe na base de dados!");
            executeDelete(id);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    protected List<Empresa> prepareListOf(ResultSet result) throws SQLException {
        List<Empresa> list = new ArrayList<>();

        while (result.next()) {
            Long id = result.getLong("id");
            String nome = result.getString("nome");
            String endereco = result.getString("endereco");

            list.add(new Empresa(id, nome, endereco));
        }

        return list;
    }

    private Empresa createRegister(ResultSet result) throws SQLException {
        while (result.next()) {
            Long id = result.getLong("id");
            String nome = result.getString("nome");
            String endereco = result.getString("endereco");

            return new Empresa(id, nome, endereco);
        }

        return null;
    }
}
