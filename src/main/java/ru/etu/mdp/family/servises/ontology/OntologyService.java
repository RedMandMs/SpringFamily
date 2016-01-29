package ru.etu.mdp.family.servises.ontology;

import java.io.File;

import ru.etu.mdp.family.domain.OntologyForm;
import ru.etu.mdp.family.exeption.ApplicationException;

import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;

/**
 *
 * @author Vilgodskiy Sergey
 *
 *         Сервис для работы с онтологией
 *
 */
public interface OntologyService {

    /**
     * Имя файла онтологии (по-умолчанию)
     */
    String OWL_FILE_NAME = "family.owl";

    /**
     * URI онтологии (по-умолчанию)
     */
    String OWL_URI = "http://www.owl-ontologies.com/family.owl#";

    /**
     * Файл с онтологией
     */
    File owlFile = new File(OWL_FILE_NAME);

    /**
     * Имя новой онтологии (для инструкции "Сохранить как..")
     */
    String newOwlFileName = "newOntology.owl";

    /**
     * Новый файл с онтологией (для инструкции "Сохранить как..")
     */
    File newOwlFile = new File(newOwlFileName);

    /**
     * Создать пустую онтологию
     *
     * @param ontologyForm
     *            форма с именем и URI онтогии
     * @throws ApplicationException
     *             ошибка создания онтологии
     */
    void createEmptyOntology(OntologyForm ontologyForm) throws ApplicationException;

    /**
     * Считать онтологию
     *
     * @param ontologyForm
     *            форма с путём до онтологии и URI
     * @throws ApplicationException
     *             ошибка считывания онтологии
     */
    void readOntology(OntologyForm ontologyForm) throws ApplicationException;

    /**
     * Считать онтологию по-умолчанию
     *
     * @throws ApplicationException
     *             ошибка считвания онтологии
     */
    void readDefoultOntology() throws ApplicationException;

    /**
     * Сохранить онтологию (изменения онтологии)
     *
     * @throws ApplicationException
     *             ошибка сохранения онтологии
     */
    void saveOntology() throws ApplicationException;

    /**
     * Сохранить онтологию в новом файле
     *
     * @throws ApplicationException
     *             ошибка сохранения онтологии
     */
    void saveInNewFile() throws ApplicationException;

    /**
     * Получить модель онтологии
     * 
     * @return модель онтологии
     */
    JenaOWLModel getOwlModel();

}
