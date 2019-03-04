package com.dyun.basejava;

import com.dyun.basejava.model.*;

import java.time.Month;

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

        resume.setSection(SectionType.ACHIVEMENT, new ListSection(
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                        "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное " +
                        "взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                        "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция " +
                        "с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: " +
                        "Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, " +
                        "интеграция CIFS/SMB java сервера.",
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, " +
                        "Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов " +
                        "(SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии " +
                        "через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга " +
                        "системы по JMX (Jython/ Django).",
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, " +
                        "Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."));

        resume.setSection(SectionType.QUALIFICATIONS, new ListSection(
                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, " +
                        "SQLite, MS SQL, HSQLDB",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy, XML/XSD/XSLT, SQL, " +
                        "C/C++, Unix shell scripts",
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring " +
                        "(MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), " +
                        "Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements)",
                "Python: Django",
                "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js",
                "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka",
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, " +
                        "DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, " +
                        "OAuth1, OAuth2, JWT",
                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix, администрирование " +
                        "Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, " +
                        "pgBouncer",
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, " +
                        "архитектурных шаблонов, UML, функционального программирования",
                "Родной русский, английский \"upper intermediate\""));

        resume.setSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Java Online Projects", "http://javaops.ru/",
                                new Organization.OrganizationTimeEntry(
                                        2013, Month.of(10),
                                        "Автор проекта",
                                        "Создание, организация и проведение Java онлайн проектов и стажировок.")),
                        new Organization("Wrike", "https://www.wrike.com/",
                                new Organization.OrganizationTimeEntry(
                                        2014, Month.of(10),
                                        2016, Month.of(1),
                                        "Старший разработчик (backend)",
                                        "Проектирование и разработка онлайн платформы управления проектами " +
                                                "Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, " +
                                                "Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")),
                        new Organization("RIT Center", null,
                                new Organization.OrganizationTimeEntry(
                                        2012, Month.of(4),
                                        2014, Month.of(10),
                                        "Java архитектор",
                                        "Организация процесса разработки системы ERP для разных окружений: релизная " +
                                                "политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), " +
                                                "конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. " +
                                                "Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения " +
                                                "(почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера " +
                                                "документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, " +
                                                "Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, " +
                                                "PL/Python")),
                        new Organization("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/",
                                new Organization.OrganizationTimeEntry(
                                        2010, Month.of(12),
                                        2012, Month.of(4),
                                        "Ведущий программист",
                                        "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, " +
                                                "SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения " +
                                                "для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, " +
                                                "Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.")),

                        new Organization("Yota", "https://www.yota.ru/",
                                new Organization.OrganizationTimeEntry(
                                        2008, Month.of(6),
                                        2010, Month.of(12),
                                        "Ведущий специалист",
                                        "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" " +
                                                "(GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация " +
                                                "администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, " +
                                                "Django, ExtJS)")),
                        new Organization("Enkata", "http://enkata.com/",
                                new Organization.OrganizationTimeEntry(
                                        2007, Month.of(3),
                                        2008, Month.of(6),
                                        "Разработчик ПО",
                                        "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, " +
                                                "JMS) частей кластерного J2EE приложения (OLAP, Data mining).")),
                        new Organization("Siemens AG", "https://www.siemens.com/ru/ru/home.html",
                                new Organization.OrganizationTimeEntry(
                                        2005, Month.of(1),
                                        2007, Month.of(2),
                                        "Разработчик ПО",
                                        "Разработка информационной модели, проектирование интерфейсов, реализация и " +
                                                "отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).")),
                        new Organization("Alcatel", "http://www.alcatel.ru/",
                                new Organization.OrganizationTimeEntry(
                                        1997, Month.of(9),
                                        2005, Month.of(1),
                                        "Инженер по аппаратному и программному тестированию",
                                        "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."))));

        resume.setSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Coursera", "https://www.coursera.org/course/progfun",
                                new Organization.OrganizationTimeEntry(
                                        2013, Month.of(3),
                                        2013, Month.of(5),
                                        "\"Functional Programming Principles in Scala\" by Martin Odersky",
                                        null)),
                        new Organization("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                                new Organization.OrganizationTimeEntry(
                                        2011, Month.of(3),
                                        2011, Month.of(4),
                                        "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                                        null)),
                        new Organization("Siemens AG", "http://www.siemens.ru/",
                                new Organization.OrganizationTimeEntry(
                                        2005, Month.of(1),
                                        2005, Month.of(4),
                                        "3 месяца обучения мобильным IN сетям (Берлин)",
                                        null)),
                        new Organization("Alcatel", "http://www.alcatel.ru/",
                                new Organization.OrganizationTimeEntry(
                                        1997, Month.of(9),
                                        1998, Month.of(3),
                                        "6 месяцев обучения цифровым телефонным сетям (Москва)",
                                        null)),
                        new Organization("Санкт-Петербургский национальный исследовательский университет информационных " +
                                "технологий, механики и оптики", "http://www.ifmo.ru/",
                                new Organization.OrganizationTimeEntry(
                                        1993, Month.of(9),
                                        1996, Month.of(7),
                                        "Аспирантура (программист С, С++)",
                                        null),
                                new Organization.OrganizationTimeEntry(
                                        1987, Month.of(9),
                                        1993, Month.of(7),
                                        "Инженер (программист Fortran, C)",
                                        null)),
                        new Organization("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/",
                                new Organization.OrganizationTimeEntry(
                                        1984, Month.of(9),
                                        1987, Month.of(6),
                                        "Закончил с отличием",
                                        null))));

        for (SectionType sectionType : SectionType.values()) {
            Section resumeSection = resume.getSection(sectionType);
            if (resumeSection != null) {
                System.out.println(sectionType.getTitle());
                System.out.println(resume.getSection(sectionType).toString());
                System.out.println();
            }
        }

        //new PathStorage("C:\\Users\\dyun\\IdeaProjects\\basejava\\storage", new JsonStreamSerialization()).save(resume);
    }
}
