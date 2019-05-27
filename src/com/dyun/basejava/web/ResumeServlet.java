package com.dyun.basejava.web;

import com.dyun.basejava.Config;
import com.dyun.basejava.model.ContactType;
import com.dyun.basejava.model.Resume;
import com.dyun.basejava.storage.SqlStorage;
import com.dyun.basejava.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {
    private static final Storage STORAGE = new SqlStorage(Config.get().getDbUrl(), Config.get().getDbUser(), Config.get().getDbPassword());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");

        Resume resume;
        resume = STORAGE.get(uuid);
        resume.setFullName(fullName);

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() > 0) {
                resume.setContact(type, value);
            } else {
                resume.getContacts().remove(type);
            }
        }
        STORAGE.update(resume);
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", STORAGE.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume resume = null;
        switch (action) {
            case "delete":
                STORAGE.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
                resume = STORAGE.get(uuid);
                break;
            case "add":
                break;
            case "edit":
                resume = STORAGE.get(uuid);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }
}
