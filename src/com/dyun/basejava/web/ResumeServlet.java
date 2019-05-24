package com.dyun.basejava.web;

import com.dyun.basejava.Config;
import com.dyun.basejava.exception.NotExistStorageException;
import com.dyun.basejava.model.Resume;
import com.dyun.basejava.storage.SqlStorage;
import com.dyun.basejava.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private static final Storage STORAGE = new SqlStorage(Config.get().getDbUrl(), Config.get().getDbUser(), Config.get().getDbPassword());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        final String uuid = request.getParameter("uuid");

        if (uuid != null) {
            Resume resume = null;
            try {
                resume = STORAGE.get(uuid);
            } catch (NotExistStorageException e) {
                response.getWriter().write(e.getMessage());
            }
            if (resume != null) {
                List<Resume> list = new ArrayList<>(1);
                list.add(resume);
                response.getWriter().write(makeTable(list));
            }
        } else {
            response.getWriter().write(makeTable(STORAGE.getAllSorted()));
        }
    }

    private String makeTable(List<Resume> list) {
        StringBuilder result = new StringBuilder();
        result.append("<table><tr><th>UUID</th><th>Full Name</th></tr>");
        String tabFooter = "</table>";
        for (Resume resume : list) {
            result.append("<tr><td>" + resume.getUuid() + "</td><td>" + resume.getFullName() + "</td></tr>");
        }
        result.append("</table>");
        return result.toString();
    }
}
