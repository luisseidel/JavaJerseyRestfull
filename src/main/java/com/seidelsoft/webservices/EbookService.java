package com.seidelsoft.webservices;

import com.google.gson.JsonObject;
import com.seidelsoft.dao.EbookDAO;
import com.seidelsoft.model.Ebook;
import com.seidelsoft.util.JsonUtil;

import java.sql.SQLException;
import java.util.List;

public class EbookService {

    private final EbookDAO dao;

    public EbookService() {
        this.dao = new EbookDAO("ebooks");
    }

    public Ebook getById(Long id) {
        return getDao().getById(id);
    }

    public List<Ebook> getByName(String name) {
        return getDao().getByName(name);
    }

    public Ebook save(Ebook obj) throws SQLException {
        return getDao().save(obj);
    }

    public Ebook update(Long id, Ebook obj) throws SQLException, IllegalAccessException, NoSuchFieldException {
        return getDao().update(id, obj);
    }

    public void delete(Long id) throws SQLException {
        getDao().delete(id);
    }

    public EbookDAO getDao() {
        return dao;
    }
}
