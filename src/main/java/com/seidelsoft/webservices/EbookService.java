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

    public Ebook save(Ebook obj) throws SQLException {
        return getDao().save(obj);
    }

    public List<Ebook> getList() {
        return getDao().getList();
    }

    public Ebook createAuthor(Long id, String body) {
        Ebook author = null;
        if (id != null) {
            author = getById(id);
        }
        if (author == null) {
            author = new Ebook(id);
        }

        if (body != null) {
            JsonObject jsonObject = JsonUtil.toJsonObject(body, JsonObject.class);
            String nome = jsonObject.get("nome").toString().replace("\"", "");
            String editora = jsonObject.get("editora").toString().replace("\"", "");

            author.setNome(nome);
            author.setEditora(editora);
        }

        return author;
    }

    public void delete(Long id) throws SQLException {
        getDao().delete(id);
    }

    public EbookDAO getDao() {
        return dao;
    }
}
