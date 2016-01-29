package ru.etu.mdp.family.domain;

/**
 * @author Vilgodskiy Sergey
 *
 *         Форма для передачи данных по онтологии
 *
 */
public class OntologyForm {

    /**
     * URI Онтологии
     */
    private String uri;

    /**
     * Путь до онтологии
     */
    private String filePath;

    /**
     * Геттеры и сеттеры
     */
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
