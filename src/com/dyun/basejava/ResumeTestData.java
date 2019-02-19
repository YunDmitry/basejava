package com.dyun.basejava;

import com.dyun.basejava.model.*;

import java.time.LocalDate;

public class ResumeTestData {
    public static void main(String args[]) {
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

        resume.setSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));

        resume.setSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        ListSection achivementSection = new ListSection();
        achivementSection.addElement("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achivementSection.addElement("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achivementSection.addElement("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achivementSection.addElement("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achivementSection.addElement("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achivementSection.addElement("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        resume.setSection(SectionType.ACHIVEMENT, achivementSection);

        ListSection qualificationsSection = new ListSection();
        qualificationsSection.addElement("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualificationsSection.addElement("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualificationsSection.addElement("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
        qualificationsSection.addElement("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy, XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        qualificationsSection.addElement("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements)");
        qualificationsSection.addElement("Python: Django");
        qualificationsSection.addElement("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualificationsSection.addElement("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualificationsSection.addElement("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT");
        qualificationsSection.addElement("Инструменты: Maven + plugin development, Gradle, настройка Ngnix, администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer");
        qualificationsSection.addElement("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        qualificationsSection.addElement("Родной русский, английский \"upper intermediate\"");
        resume.setSection(SectionType.QUALIFICATIONS, qualificationsSection);

        TableSection experienceSection = new TableSection();
        experienceSection.addElement("Java Online Projects", LocalDate.of(2013, 10, 1), null, "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок.");
        experienceSection.addElement("Wrike", LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        experienceSection.addElement("RIT Center", LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 1), "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        experienceSection.addElement("Luxoft (Deutsche Bank)", LocalDate.of(2010, 12, 1), LocalDate.of(2012, 4, 1), "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        experienceSection.addElement("Yota", LocalDate.of(2008, 6, 1), LocalDate.of(2010, 12, 1), "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
        experienceSection.addElement("Enkata", LocalDate.of(2007, 3, 1), LocalDate.of(2008, 6, 1), "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        experienceSection.addElement("Siemens AG", LocalDate.of(2005, 1, 1), LocalDate.of(2007, 2, 1), "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        experienceSection.addElement("Alcatel", LocalDate.of(1997, 9, 1), LocalDate.of(2005, 1, 1), "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");
        resume.setSection(SectionType.EXPERIENCE, experienceSection);

        TableSection educationSection = new TableSection();
        educationSection.addElement("Coursera", LocalDate.of(2013, 3, 1), LocalDate.of(2013, 5, 1), "\"Functional Programming Principles in Scala\" by Martin Odersky", null);
        educationSection.addElement("Luxoft", LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4, 1), "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", null);
        educationSection.addElement("Siemens AG", LocalDate.of(2005, 1, 1), LocalDate.of(2005, 4, 1), "3 месяца обучения мобильным IN сетям (Берлин)", null);
        educationSection.addElement("Alcatel", LocalDate.of(1997, 9, 1), LocalDate.of(1998, 3, 1), "6 месяцев обучения цифровым телефонным сетям (Москва)", null);
        educationSection.addElement("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7, 1), "Аспирантура (программист С, С++)", null);
        educationSection.addElement(null, LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1), "Инженер (программист Fortran, C)", null);
        educationSection.addElement("Заочная физико-техническая школа при МФТИ", LocalDate.of(1984, 9, 1), LocalDate.of(1987, 6, 1), "Закончил с отличием", null);
        resume.setSection(SectionType.EDUCATION, educationSection);

        for (SectionType sectionType : SectionType.values()) {
            Section resumeSection = resume.getSection(sectionType);
            if (resumeSection != null) {
                System.out.println(sectionType.getTitle());
                resume.getSection(sectionType).print();
                System.out.println();
            }
        }
    }
}
