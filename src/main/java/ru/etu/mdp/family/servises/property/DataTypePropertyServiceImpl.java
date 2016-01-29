package ru.etu.mdp.family.servises.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.etu.mdp.family.domain.ChangeForm;
import ru.etu.mdp.family.exeption.ApplicationErrors;
import ru.etu.mdp.family.exeption.ApplicationException;
import ru.etu.mdp.family.servises.ontology.OntologyService;

import edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty;

/**
 * @author Vilgodskiy Sergey
 *
 *         Реализация сервиса для работы с примитивными свойствами объекта базы знаний
 */
@Service("dataTypePropertyService")
public class DataTypePropertyServiceImpl implements DataTypePropertyService {

    @Autowired
    private OntologyService ontologyService;

    /**
     * Получить значение поля
     *
     * @param nameIndividual
     *            имя экземпляра
     * @param nameProperty
     *            имя поля
     * @return значение поля
     * @throws ApplicationException
     *             ошибка получение данных
     */
    @Override
    public Object getPropertyValue(String nameIndividual, String nameProperty)
        throws ApplicationException {

        ChangeForm changePropertyForm = new ChangeForm();
        changePropertyForm.setNameIndividual(nameIndividual);
        changePropertyForm.setNameProperty(nameProperty);

        try {
            getNeсessaryData(changePropertyForm);
            Object propertyValue = changePropertyForm.getIndividual()
                .getPropertyValue(changePropertyForm.getDataTypeProperty());
            return propertyValue;
        } catch (Exception ex) {
            throw new ApplicationException(ApplicationErrors.READING_PROPERTY_ERROR);
        }

    }

    /**
     * Удалить значение поля
     *
     * @param changePropertyForm
     *            форма изменения с необходимыми данными
     * @throws ApplicationException
     *             ошибка удаления данных
     */
    @Override
    public void deletePropertyValue(ChangeForm changePropertyForm)
        throws ApplicationException {

        try {
            getNeсessaryData(changePropertyForm);

            changePropertyForm.getIndividual()
                .setPropertyValue(changePropertyForm.getDataTypeProperty(), null);
        } catch (Exception e) {
            throw new ApplicationException(ApplicationErrors.REMOVING_PROPERTY_ERROR);
        }

        ontologyService.saveOntology();
    }

    /**
     * Установить значение поля
     *
     * @param changePropertyForm
     *            форма изменения с необходимыми данными
     * @throws ApplicationException
     *             ошибка изменения значения поля
     */
    @Override
    public void setPropertyValue(ChangeForm changePropertyForm)
        throws ApplicationException {

        try {
            getNeсessaryData(changePropertyForm);

            changePropertyForm.getIndividual().setPropertyValue(
                changePropertyForm.getDataTypeProperty(),
                changePropertyForm.getDataTypePropertyValue());
        } catch (Exception e) {
            throw new ApplicationException(ApplicationErrors.RECORDING_PROPERTY_ERROR);
        }

        ontologyService.saveOntology();
    }

    /**
     * Получить тип поля
     *
     * @param nameProperty
     * @return
     */
    @Override
    public String getTypeProperty(String nameProperty) {

        OWLDatatypeProperty property = ontologyService.getOwlModel()
            .getOWLDatatypeProperty(OntologyService.OWL_URI + nameProperty);

        return property.getRange().getLocalName();

    }

    /**
     * Подгрузка необходимых данных для выполнения операции изменением полей
     *
     * @param changePropertyForm
     *            форма с минимальным достаточным набором данных
     * @throws ApplicationException
     *             ошибка получения данных
     */
    private void getNeсessaryData(ChangeForm changePropertyForm)
        throws ApplicationException {

        if (changePropertyForm.getNameProperty() != null
            && !changePropertyForm.getNameProperty().isEmpty()) {
            changePropertyForm
                .setDataTypeProperty(ontologyService.getOwlModel().getOWLDatatypeProperty(
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

            TypeProperty typeProperty = TypeProperty.findByLocalName(
                changePropertyForm.getDataTypeProperty().getRange().getLocalName());

            try {
                switch (typeProperty) {
                    case BOOLEAN:
                        changePropertyForm.setDataTypePropertyValue(
                            Boolean.valueOf(changePropertyForm.getNewValue()));
                        break;
                    case INTEGER:
                        changePropertyForm.setDataTypePropertyValue(
                            Integer.valueOf(changePropertyForm.getNewValue()));
                        break;
                    case STRING:
                        changePropertyForm
                            .setDataTypePropertyValue(changePropertyForm.getNewValue());
                        break;
                    case FLOAT:
                        changePropertyForm.setDataTypePropertyValue(
                            Float.valueOf(changePropertyForm.getNewValue()));
                        break;
                    default:
                        changePropertyForm
                            .setDataTypePropertyValue(changePropertyForm.getNewValue());
                        break;
                }
            } catch (NumberFormatException ex) {
                throw new ApplicationException(ApplicationErrors.INCORRECT_CONVERT);
            }
        }

    }

    public enum TypeProperty {
        BOOLEAN("boolean"), INTEGER("int"), STRING("string"), FLOAT("float");

        private final String localName;

        TypeProperty(String localName) {
            this.localName = localName;
        }

        public static TypeProperty findByLocalName(String localName) {
            for (TypeProperty typeProperty : values()) {
                if (typeProperty.localName.equals(localName)) {
                    return typeProperty;
                }
            }
            return null;
        }
    }
}
