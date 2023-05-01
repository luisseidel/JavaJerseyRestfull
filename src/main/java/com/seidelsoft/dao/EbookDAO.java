package com.seidelsoft.dao;

import com.seidelsoft.model.Ebook;
import com.seidelsoft.webservices.Response;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EbookDAO extends BaseDAO<Ebook> {

    public EbookDAO(String tableName) {
        super(tableName);
    }

    @Override
    public Ebook getById(Long id) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(selectById(id));
            ResultSet result = executeQueryForGet(stmt);

            return createRegister(result);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public List<Ebook> getByName(String name) {
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
    public List<Ebook> getList() {
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
    public Ebook save(Ebook a) throws SQLException {
        try {
            Map<String, String> colVals = new HashMap<>();
            colVals.put("nome", a.getNome());
            colVals.put("editora", a.getEditora());

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

    public Ebook update(Long id, Ebook e) throws SQLException, IllegalAccessException, NoSuchFieldException {
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
    protected List<Ebook> prepareListOf(ResultSet result) throws SQLException {
        List<Ebook> list = new ArrayList<>();

        while (result.next()) {
            Long id = result.getLong("id");
            String nome = result.getString("nome");
            String editora = result.getString("editora");

            list.add(new Ebook(id, nome, editora));
        }

        return list;
    }

    private Ebook createRegister(ResultSet result) throws SQLException {
        while (result.next()) {
            Long id = result.getLong("id");
            String nome = result.getString("nome");
            String editora = result.getString("editora");

            return new Ebook(id, nome, editora);
        }

        return null;
    }
}
