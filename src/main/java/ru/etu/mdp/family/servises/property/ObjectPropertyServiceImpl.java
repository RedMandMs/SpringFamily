package ru.etu.mdp.family.servises.property;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.etu.mdp.family.domain.ChangeForm;
import ru.etu.mdp.family.exeption.ApplicationErrors;
import ru.etu.mdp.family.exeption.ApplicationException;
import ru.etu.mdp.family.servises.ontology.OntologyService;

import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLObjectProperty;
import edu.stanford.smi.protegex.owl.model.RDFObject;
import edu.stanford.smi.protegex.owl.model.query.QueryResults;

/**
 * @author Vilgodskiy Sergey
 *
 *         Реализация сервиса для работы с объектными свойствами экземпляров
 */
@Service("objectPropertyService")
public class ObjectPropertyServiceImpl implements ObjectPropertyService {

    /**
     * Сервис для работы с онтологией
     */
    @Autowired
    private OntologyService ontologyService;

    /**
     * Получить значение поля
     *
     * @param nameIndividual
     *            имя экземпляра
     * @param nameProperty
     *            наименование свойства
     * @return значение свойства данного экземпляра
     * @throws ApplicationException
     *             ошибка считывания свойства экземпляра
     */
    @Override
    public List<OWLIndividual> getPropertyValue(String nameIndividual,
        String nameProperty) throws ApplicationException {
        ChangeForm changeForm = new ChangeForm();
        changeForm.setNameIndividual(nameIndividual);
        changeForm.setNameProperty(nameProperty);
        try {
            getNeсessaryData(changeForm);
            String sparql_text = "PREFIX f: <http://www.owl-ontologies.com/family.owl#> SELECT ?individual WHERE { f:"
                + nameIndividual + " f:" + nameProperty + " ?individual.}";
            QueryResults results = ontologyService.getOwlModel()
                .executeSPARQLQuery(sparql_text);
            List<OWLIndividual> result = new ArrayList<>();
            while (results.hasNext()) {
                Map map = results.next();
                RDFObject value = (RDFObject) map.get("individual");
                result.add(ontologyService.getOwlModel()
                    .getOWLIndividual(value.getBrowserText()));
            }
            return result;
        } catch (Exception ex) {
            throw new ApplicationException(ApplicationErrors.READING_PROPERTY_ERROR);
        }

    }

    /**
     * Получить все значения поля
     *
     * @param changePropertyForm
     *            форма изменения с необходимыми данными
     * @return список всех значений данного поля
     * @throws ApplicationException
     *             ошибка считывания свойства экземпляра
     */
    @Override
    @SuppressWarnings("unchecked")
    public Collection<OWLIndividual> getAllPropertyValues(ChangeForm changePropertyForm)
        throws ApplicationException {

        try {
            getNeсessaryData(changePropertyForm);
            return changePropertyForm.getIndividual()
                .getPropertyValues(changePropertyForm.getObjectProperty());
        } catch (Exception ex) {
            throw new ApplicationException(ApplicationErrors.READING_PROPERTY_ERROR);
        }

    }

    /**
     * Установить значение поля
     *
     * @param changePropertyForm
     *            форма изменения с необходимыми данными
     * @throws ApplicationException
     *             ошибка записи свойства экземпляра
     */
    @Override
    public void setPropertyValue(ChangeForm changePropertyForm)
        throws ApplicationException {

        try {
            getNeсessaryData(changePropertyForm);

            changePropertyForm.getIndividual().setPropertyValue(
                changePropertyForm.getObjectProperty(),
                changePropertyForm.getObjectPropertyValue());
        } catch (Exception e) {
            throw new ApplicationException(ApplicationErrors.RECORDING_PROPERTY_ERROR);
        }

        ontologyService.saveOntology();
    }

    /**
     * Добавить одно значение - При мульти-значении
     *
     * @param changePropertyForm
     *            форма изменения с необходимыми данными
     * @throws ApplicationException
     *             ошибка добовления значения свойства
     */
    @Override
    public void addPropertyValue(ChangeForm changePropertyForm)
        throws ApplicationException {

        try {
            getNeсessaryData(changePropertyForm);

            changePropertyForm.getIndividual().addPropertyValue(
                changePropertyForm.getObjectProperty(),
                changePropertyForm.getObjectPropertyValue());
        } catch (Exception e) {
            throw new ApplicationException(ApplicationErrors.ADDING_PROPERTY_ERROR);
        }

        ontologyService.saveOntology();
    }

    /**
     * Получить все объектные свойства экземпляра
     *
     * @param changePropertyForm
     *            форма изменения с необходимыми данными
     * @return список всех объектных свойств экземпляра
     * @throws ApplicationException
     *             ошибка считывания свойства экземпляра
     */
    @Override
    @SuppressWarnings("unchecked")
    public Collection<OWLObjectProperty> getAllObjectProperties(
        ChangeForm changePropertyForm) throws ApplicationException {

        try {
            getNeсessaryData(changePropertyForm);

            Collection<OWLObjectProperty> result = changePropertyForm.getIndividual()
                .getRDFType().getUnionDomainProperties();
            return result;
        } catch (Exception e) {
            throw new ApplicationException(ApplicationErrors.READING_PROPERTY_ERROR);
        }

    }

    /**
     * Удалить одно значение - При мульти-значении
     *
     * @param changePropertyForm
     *            форма изменения с необходимыми данными
     * @throws ApplicationException
     *             ошибка удаления значения свойства
     */
    @Override
    public void removeOnePropertyValue(ChangeForm changePropertyForm)
        throws ApplicationException {

        try {
            getNeсessaryData(changePropertyForm);

            changePropertyForm.getIndividual().removePropertyValue(
                changePropertyForm.getObjectProperty(),
                changePropertyForm.getObjectPropertyValue());
        } catch (Exception e) {
            throw new ApplicationException(ApplicationErrors.REMOVING_PROPERTY_ERROR);
        }

        ontologyService.saveOntology();
    }

    /**
     * Удалить все значения - При мульти-значении
     *
     * @param changePropertyForm
     *            форма изменения с необходимыми данными
     * @throws ApplicationException
     *             ошибка удаления значения свойства
     */
    @Override
    public void clearPropertyValue(ChangeForm changePropertyForm)
        throws ApplicationException {

        try {
            getNeсessaryData(changePropertyForm);

            changePropertyForm.getIndividual()
                .setPropertyValue(changePropertyForm.getObjectProperty(), null);
        } catch (Exception e) {
            throw new ApplicationException(ApplicationErrors.REMOVING_PROPERTY_ERROR);
        }

        ontologyService.saveOntology();
    }

    /**
     * Подгрузка необходимых данных для выполнения операции изменением полей
     *
     * @param changePropertyForm
     *            форма с минимальным достаточным набором данных
     */
    private void getNeсessaryData(ChangeForm changePropertyForm) {

        if (changePropertyForm.getNameProperty() != null
            && !changePropertyForm.getNameProperty().isEmpty()) {
            changePropertyForm
                .setObjectProperty(ontologyService.getOwlModel().getOWLObjectProperty(
                    OntologyService.OWL_URI + changePropertyForm.getNameProperty()));
        }

        if (changePropertyForm.getNameIndividual() != null
            && !changePropertyForm.getNameIndividual().isEmpty()) {
            changePropertyForm
                .setIndividual(ontologyService.getOwlModel().getOWLIndividual(
                    OntologyService.OWL_URI + changePropertyForm.getNameIndividual()));
        }

        if (changePropertyForm.getNewValue() != null
            && !changePropertyForm.getNewValue().isEmpty()) {
            changePropertyForm
                .setObjectPropertyValue(ontologyService.getOwlModel().getOWLIndividual(
                    OntologyService.OWL_URI + changePropertyForm.getNewValue()));
        }

    }

}
