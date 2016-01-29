package ru.etu.mdp.family.domain;

import edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty;
import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.OWLObjectProperty;

/**
 * @author Vilgodskiy Sergey
 *
 *         Форма для изменения данных онтологии
 *
 */
public class ChangeForm {

    /**
     * Имя экземпляра
     */
    private String nameIndividual;

    /**
     * аименование свойства
     */
    private String nameProperty;

    /**
     * Новое значение свойства
     */
    private String newValue;

    /**
     * Наименование класса экземпляра
     */
    private String individualClassName;

    /**
     * Класс экземпляра
     */
    private OWLNamedClass individualClass;

    /**
     * Экземпляр
     */
    private OWLIndividual individual;

    /**
     * Свойство класса
     */
    private OWLObjectProperty objectProperty;

    /**
     * Объектное свойство класса
     */
    private OWLIndividual objectPropertyValue;

    /**
     * Примитивное свойство класса
     */
    private OWLDatatypeProperty dataTypeProperty;

    /**
     * Значение примитивного свойства
     */
    private Object dataTypePropertyValue;

    /**
     * Геттеры и сеттеры
     */
    public String getNameIndividual() {
        return nameIndividual;
    }

    public void setNameIndividual(String nameIndividual) {
        this.nameIndividual = nameIndividual;
    }

    public String getNameProperty() {
        return nameProperty;
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty = nameProperty;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public OWLIndividual getIndividual() {
        return individual;
    }

    public void setIndividual(OWLIndividual individual) {
        this.individual = individual;
    }

    public OWLObjectProperty getObjectProperty() {
        return objectProperty;
    }

    public void setObjectProperty(OWLObjectProperty objectProperty) {
        this.objectProperty = objectProperty;
    }

    public OWLIndividual getObjectPropertyValue() {
        return objectPropertyValue;
    }

    public void setObjectPropertyValue(OWLIndividual objectPropertyValue) {
        this.objectPropertyValue = objectPropertyValue;
    }

    public OWLDatatypeProperty getDataTypeProperty() {
        return dataTypeProperty;
    }

    public void setDataTypeProperty(OWLDatatypeProperty dataTypeProperty) {
        this.dataTypeProperty = dataTypeProperty;
    }

    public Object getDataTypePropertyValue() {
        return dataTypePropertyValue;
    }

    public void setDataTypePropertyValue(Object dataTypePropertyValue) {
        this.dataTypePropertyValue = dataTypePropertyValue;
    }

    public OWLNamedClass getIndividualClass() {
        return individualClass;
    }

    public void setIndividualClass(OWLNamedClass individualClass) {
        this.individualClass = individualClass;
    }

    public String getIndividualClassName() {
        return individualClassName;
    }

    public void setIndividualClassName(String individualClassName) {
        this.individualClassName = individualClassName;
    }

}
