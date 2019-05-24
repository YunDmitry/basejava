package com.dyun.basejava.storage;

import com.dyun.basejava.Config;
import com.dyun.basejava.exception.ExistStorageException;
import com.dyun.basejava.exception.NotExistStorageException;
import com.dyun.basejava.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected static final String STORAGE_DIR = Config.get().getStorageDir();
    protected Storage storage;
    private static final String UUID1 = UUID.randomUUID().toString();
    private static final String UUID2 = UUID.randomUUID().toString();
    private static final String UUID3 = UUID.randomUUID().toString();
    private static final String UUID4 = UUID.randomUUID().toString();
    private static final Resume RESUME_1 = new Resume(UUID1, "Sidorov Igor");
    private static final Resume RESUME_2 = new Resume(UUID2, "Trophimov Ivan");
    private static final Resume RESUME_3 = new Resume(UUID3, "Ivanov Roman");
    private static final Resume RESUME_4 = new Resume(UUID4, "Romanova Svetlana");

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        RESUME_1.setContact(ContactType.PHONE, "+7(111) 111-1111");
        RESUME_1.setContact(ContactType.SKYPE, "Igor.Sidorov");
        RESUME_1.setContact(ContactType.EMAIL, "isidorov@yandex.ru");
        RESUME_1.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/isidorov");
        RESUME_1.setContact(ContactType.GITHUB, "https://github.com/isidorov");
        RESUME_1.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/111111");
        RESUME_1.setContact(ContactType.SITE, "https://isidorov.ru/");
        RESUME_1.setSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по " +
                "Java Web и Enterprise технологиям"));
        RESUME_1.setSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, " +
                "креативность, инициативность. Пурист кода и архитектуры."));
        RESUME_1.setSection(SectionType.ACHIVEMENT, new ListSection(
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
        RESUME_1.setSection(SectionType.QUALIFICATIONS, new ListSection(
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
        storage.save(RESUME_1);

        RESUME_3.setContact(ContactType.PHONE, "+7(333) 333-3333");
        RESUME_3.setContact(ContactType.SKYPE, "Roman.Ivanov");
        RESUME_3.setContact(ContactType.EMAIL, "rivanov@yandex.ru");
        RESUME_3.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/rivanov");
        RESUME_3.setContact(ContactType.GITHUB, "https://github.com/rivanov");
        RESUME_3.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/333333");
        RESUME_3.setContact(ContactType.SITE, "https://rivanov.ru/");
        storage.save(RESUME_3);

        RESUME_2.setContact(ContactType.PHONE, "+7(222) 222-2222");
        RESUME_2.setContact(ContactType.SKYPE, "Ivan.Trophimov");
        RESUME_2.setContact(ContactType.EMAIL, "itrophimov@yandex.ru");
        RESUME_2.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/itrophimov");
        RESUME_2.setContact(ContactType.GITHUB, "https://github.com/itrophimov");
        RESUME_2.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/222222");
        RESUME_2.setContact(ContactType.SITE, "https://itrophimov.ru/");
        storage.save(RESUME_2);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        assertEquals(RESUME_1, storage.get(UUID1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertEquals(4, storage.size());
        assertEquals(RESUME_4, storage.get(UUID4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_2);
    }

    @Test
    public void update() {
        Resume resumeNew = new Resume(UUID3, "New FullName");
        storage.update(resumeNew);
        assertEquals(resumeNew, storage.get(UUID3));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID1);
        assertEquals(2, storage.size());
        storage.get(UUID1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID4);
    }

    @Test
    public void getAllSorted() {
        assertEquals(new ArrayList<>(Arrays.asList(RESUME_3, RESUME_1, RESUME_2)), storage.getAllSorted());
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }
}