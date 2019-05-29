package com.dyun.basejava.web;

import com.dyun.basejava.Config;
import com.dyun.basejava.model.*;
import com.dyun.basejava.storage.SqlStorage;
import com.dyun.basejava.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = new SqlStorage(Config.get().getDbUrl(), Config.get().getDbUser(), Config.get().getDbPassword());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");

        Resume resume;
        boolean addFlg = (uuid == null || uuid.length() == 0);
        if (addFlg) {
            resume = new Resume(fullName);
            resume.setFullName(fullName);
        } else {
            resume = storage.get(uuid);
            resume.setFullName(fullName);
        }

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() > 0) {
                resume.setContact(type, value);
            } else {
                resume.getContacts().remove(type);
            }
        }

        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() > 0) {
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        resume.setSection(type, new TextSection(value));
                        break;
                    case ACHIVEMENT:
                    case QUALIFICATIONS:
                        resume.setSection(type, new ListSection(value.split("\\n")));
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        final String[] orgIds = request.getParameterValues(type + "OrganizationId");
                        final String[] orgNames = request.getParameterValues(type + "OrganizationName");
                        final String[] orgUrls = request.getParameterValues(type + "OrganizationUrl");
                        List<Organization> list = new ArrayList<>();
                        if (orgIds != null && orgIds.length > 0) {
                            for (int i = 0; i < orgIds.length; i++) {
                                final String[] posDateFrom = request.getParameterValues(type + orgIds[i] + "PositionFrom");
                                final String[] posDateTo = request.getParameterValues(type + orgIds[i] + "PositionTo");
                                final String[] posName = request.getParameterValues(type + orgIds[i] + "PositionName");
                                final String[] posDescription = request.getParameterValues(type + orgIds[i] + "PositionDescription");
                                List<Organization.OrganizationTimeEntry> listPos = new ArrayList<>();
                                for (int j = 0; j < posDateFrom.length; j++) {
                                    listPos.add(new Organization.OrganizationTimeEntry(LocalDate.parse(posDateFrom[j]), LocalDate.parse(posDateTo[j]), posName[j], posDescription[j]));
                                }
                                list.add(new Organization(new Link(orgNames[i], orgUrls[i]), listPos));
                                resume.setSection(type, new OrganizationSection(list));
                            }
                        } else {
                            list.clear();
                            resume.getSections().remove(type);
                        }
                        break;
                }
            } else {
                resume.getSections().remove(type);
            }
        }

        if (addFlg) {
            storage.save(resume);
        } else {
            storage.update(resume);
        }
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume resume;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "clear":
                storage.clear();
                response.sendRedirect("resume");
                return;
            case "view":
                resume = storage.get(uuid);
                break;
            case "add":
                resume = new Resume();
                break;
            case "edit":
                resume = storage.get(uuid);
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
