package com.dyun.basejava;

import com.dyun.basejava.model.*;
import com.dyun.basejava.util.DateUtil;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");
        System.out.println(resume.getFullName() + "\n");

        resume.setContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.setContact(ContactType.SKYPE, "grigory.kislin");
        resume.setContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.setContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.setContact(ContactType.SITE, "hsttp://gkislin.ru/");
        for (ContactType contactType : ContactType.values()) {
            System.out.println(resume.getContact(contactType));
        }
        System.out.println();

        resume.setSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по " +
                "Java Web и Enterprise технологиям"));

        resume.setSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, " +
                "креативность, инициативность. Пурист кода и архитектуры."));

        List<String> achivements = new ArrayList<>();
        achivements.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное " +
                "взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achivements.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achivements.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция " +
                "с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: " +
                "Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, " +
                "интеграция CIFS/SMB java сервера.");
        achivements.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, " +
                "Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achivements.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов " +
                "(SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии " +
                "через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга " +
                "системы по JMX (Jython/ Django).");
        achivements.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, " +
                "Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        resume.setSection(SectionType.ACHIVEMENT, new ListSection(achivements));

        List<String> qualifications = new ArrayList<>();
        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, " +
                "SQLite, MS SQL, HSQLDB");
        qualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy, XML/XSD/XSLT, SQL, " +
                "C/C++, Unix shell scripts");
        qualifications.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring " +
                "(MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), " +
                "Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements)");
        qualifications.add("Python: Django");
        qualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualifications.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualifications.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, " +
                "DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, " +
                "OAuth1, OAuth2, JWT");
        qualifications.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix, администрирование " +
                "Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, " +
                "pgBouncer");
        qualifications.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, " +
                "архитектурных шаблонов, UML, функционального программирования");
        qualifications.add("Родной русский, английский \"upper intermediate\"");
        resume.setSection(SectionType.QUALIFICATIONS, new ListSection(qualifications));

        List<Organization> experience = new ArrayList<>();
        List<OrganizationTimeEntry> experienceTimeEntries = new ArrayList<>();
        experienceTimeEntries.add(new OrganizationTimeEntry(
                DateUtil.of(2013, Month.of(10)),
                null,
                "Автор проекта",
                "Создание, организация и проведение Java онлайн проектов и стажировок."));
        experience.add(new Organization("Java Online Projects", "http://javaops.ru/", experienceTimeEntries));
        experienceTimeEntries = new ArrayList<>();
        experienceTimeEntries.add(new OrganizationTimeEntry(
                DateUtil.of(2014, Month.of(10)),
                DateUtil.of(2016, Month.of(1)),
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами " +
                        "Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная " +
                        "аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
        experience.add(new Organization("Wrike", "https://www.wrike.com/", experienceTimeEntries));
        experienceTimeEntries = new ArrayList<>();
        experienceTimeEntries.add(new OrganizationTimeEntry(
                DateUtil.of(2012, Month.of(4)),
                DateUtil.of(2014, Month.of(10)),
                "Java архитектор",
                "Организация процесса разработки системы ERP для разных окружений: релизная " +
                        "политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), " +
                        "конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. " +
                        "Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения " +
                        "(почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера " +
                        "документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, " +
                        "Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, " +
                        "PL/Python"));
        experience.add(new Organization("RIT Center", null, experienceTimeEntries));
        experienceTimeEntries = new ArrayList<>();
        experienceTimeEntries.add(new OrganizationTimeEntry(
                DateUtil.of(2010, Month.of(12)),
                DateUtil.of(2012, Month.of(4)),
                "Ведущий программист",
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, " +
                        "SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения " +
                        "для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, " +
                        "Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."));
        experience.add(new Organization("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/", experienceTimeEntries));
        experienceTimeEntries = new ArrayList<>();
        experienceTimeEntries.add(new OrganizationTimeEntry(
                DateUtil.of(2008, Month.of(6)),
                DateUtil.of(2010, Month.of(12)),
                "Ведущий специалист",
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" " +
                        "(GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация " +
                        "администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, " +
                        "Django, ExtJS)"));
        experience.add(new Organization("Yota", "https://www.yota.ru/", experienceTimeEntries));
        experienceTimeEntries = new ArrayList<>();
        experienceTimeEntries.add(new OrganizationTimeEntry(
                DateUtil.of(2007, Month.of(3)),
                DateUtil.of(2008, Month.of(6)),
                "Разработчик ПО",
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, " +
                        "JMS) частей кластерного J2EE приложения (OLAP, Data mining)."));
        experience.add(new Organization("Enkata", "http://enkata.com/", experienceTimeEntries));
        experienceTimeEntries = new ArrayList<>();
        experienceTimeEntries.add(new OrganizationTimeEntry(
                DateUtil.of(2005, Month.of(1)),
                DateUtil.of(2007, Month.of(2)),
                "Разработчик ПО",
                "Разработка информационной модели, проектирование интерфейсов, реализация и " +
                        "отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."));
        experience.add(new Organization("Siemens AG", "https://www.siemens.com/ru/ru/home.html", experienceTimeEntries));
        experienceTimeEntries = new ArrayList<>();
        experienceTimeEntries.add(new OrganizationTimeEntry(
                DateUtil.of(1997, Month.of(9)),
                DateUtil.of(2005, Month.of(1)),
                "Инженер по аппаратному и программному тестированию",
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."));
        experience.add(new Organization("Alcatel", "http://www.alcatel.ru/", experienceTimeEntries));

        resume.setSection(SectionType.EXPERIENCE, new OrganizationSection(experience));

        List<Organization> education = new ArrayList<>();
        List<OrganizationTimeEntry> educationTimeEntries = new ArrayList<>();
        educationTimeEntries.add(new OrganizationTimeEntry(
                DateUtil.of(2013, Month.of(3)),
                DateUtil.of(2013, Month.of(5)),
                "\"Functional Programming Principles in Scala\" by Martin Odersky",
                null));
        education.add(new Organization("Coursera", "https://www.coursera.org/course/progfun", educationTimeEntries));
        educationTimeEntries = new ArrayList<>();
        educationTimeEntries.add(new OrganizationTimeEntry(
                DateUtil.of(2011, Month.of(3)),
                DateUtil.of(2011, Month.of(4)),
                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                null));
        education.add(new Organization("Luxoft", "http://www.luxoft-training.ru/training/catalog/" +
                "course.html?ID=22366", educationTimeEntries));
        educationTimeEntries = new ArrayList<>();
        educationTimeEntries.add(new OrganizationTimeEntry(
                DateUtil.of(2005, Month.of(1)),
                DateUtil.of(2005, Month.of(4)),
                "3 месяца обучения мобильным IN сетям (Берлин)",
                null));
        education.add(new Organization("Siemens AG", "http://www.siemens.ru/", educationTimeEntries));
        educationTimeEntries = new ArrayList<>();
        educationTimeEntries.add(new OrganizationTimeEntry(
                DateUtil.of(1997, Month.of(9)),
                DateUtil.of(1998, Month.of(3)),
                "6 месяцев обучения цифровым телефонным сетям (Москва)",
                null));
        education.add(new Organization("Alcatel", "http://www.alcatel.ru/", educationTimeEntries));
        educationTimeEntries = new ArrayList<>();
        educationTimeEntries.add(new OrganizationTimeEntry(
                DateUtil.of(1993, Month.of(9)),
                DateUtil.of(1996, Month.of(7)),
                "Аспирантура (программист С, С++)",
                null));
        educationTimeEntries.add(new OrganizationTimeEntry(
                DateUtil.of(1987, Month.of(9)),
                DateUtil.of(1993, Month.of(7)),
                "Инженер (программист Fortran, C)",
                null));
        education.add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных " +
                "технологий, механики и оптики", "http://www.ifmo.ru/", educationTimeEntries));
        educationTimeEntries = new ArrayList<>();
        educationTimeEntries.add(new OrganizationTimeEntry(
                DateUtil.of(1984, Month.of(9)),
                DateUtil.of(1987, Month.of(6)),
                "Закончил с отличием",
                null));
        education.add(new Organization("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/",
                educationTimeEntries));
        resume.setSection(SectionType.EDUCATION, new OrganizationSection(education));

        for (SectionType sectionType : SectionType.values()) {
            Section resumeSection = resume.getSection(sectionType);
            if (resumeSection != null) {
                System.out.println(sectionType.getTitle());
                System.out.println(resume.getSection(sectionType).toString());
                System.out.println();
            }
        }
    }
}
