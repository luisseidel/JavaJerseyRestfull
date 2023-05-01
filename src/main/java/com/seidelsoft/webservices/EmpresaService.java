package com.seidelsoft.webservices;

import com.seidelsoft.dao.EmpresaDAO;
import com.seidelsoft.model.Empresa;

import java.sql.SQLException;
import java.util.List;

public class EmpresaService {

    private final EmpresaDAO dao;

    public EmpresaService() {
        this.dao = new EmpresaDAO("empresas");
    }

    public Empresa getById(Long id) {
        return getDao().getById(id);
    }

    public List<Empresa> getByName(String name) {
        return getDao().getByName(name);
    }

    public Empresa save(Empresa obj) throws SQLException {
        return getDao().save(obj);
    }

    public Empresa update(Long id, Empresa obj) throws SQLException, IllegalAccessException, NoSuchFieldException {
        return getDao().update(id, obj);
    }

    public void delete(Long id) throws SQLException {
        getDao().delete(id);
    }

    public EmpresaDAO getDao() {
        return dao;
    }
}
