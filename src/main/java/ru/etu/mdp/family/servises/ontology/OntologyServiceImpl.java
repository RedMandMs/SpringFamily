package ru.etu.mdp.family.servises.ontology;

import org.springframework.stereotype.Service;

import ru.etu.mdp.family.domain.OntologyForm;
import ru.etu.mdp.family.exeption.ApplicationErrors;
import ru.etu.mdp.family.exeption.ApplicationException;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;

/**
 * @author Vilgodskiy Sergey
 *
 *         Реализация сервиса для работы с онтологией
 */
@Service("ontologyService")
public class OntologyServiceImpl implements OntologyService {

    /**
     * OWL-модель используемой онтологии
     */
    private static JenaOWLModel owlModel;

    /**
     * Создать пустую онтологию
     *
     * @param ontologyForm
     *            форма с именем и URI онтогии
     * @throws ApplicationException
     *             ошибка создания онтологии
     */
    @Override
    public void createEmptyOntology(OntologyForm ontologyForm)
        throws ApplicationException {

        String owlURI = ontologyForm.getUri();

        try {
            owlModel = ProtegeOWL.createJenaOWLModel();
        } catch (OntologyLoadException e) {
            throw new ApplicationException(ApplicationErrors.CREATING_ONTOLOGY_ERROR);
        }

        owlModel.getNamespaceManager().setDefaultNamespace(owlURI);

        saveOntology();
    }

    /**
     * Считать онтологию
     *
     * @param ontologyForm
     *            форма с путём до онтологии и URI
     * @throws ApplicationException
     *             ошибка считывания онтологии
     */
    @Override
    public void readOntology(OntologyForm ontologyForm) throws ApplicationException {
        try {
            owlModel = ProtegeOWL
                .createJenaOWLModelFromURI(ontologyForm.getUri().toString());
        } catch (OntologyLoadException e) {
            throw new ApplicationException(ApplicationErrors.READING_ONTOLOGY_ERROR);
        }
    }

    /**
     * Считать онтологию по-умолчанию
     *
     * @throws ApplicationException
     *             ошибка считвания онтологии
     */
    @Override
    public void readDefoultOntology() throws ApplicationException {
        try {
            owlModel = ProtegeOWL.createJenaOWLModelFromURI(owlFile.toURI().toString());
        } catch (OntologyLoadException e) {
            throw new ApplicationException(ApplicationErrors.READING_ONTOLOGY_ERROR);
        }
    }

    /**
     * Сохранить онтологию (изменения онтологии)
     *
     * @throws ApplicationException
     *             ошибка сохранения онтологии
     */
    @Override
    public void saveOntology() throws ApplicationException {
        try {
            owlModel.save(owlFile.toURI());
        } catch (Exception e) {
            throw new ApplicationException(ApplicationErrors.SAVING_ONTOLOGY_ERROR);
        }
    }

    /**
     * Сохранить онтологию в новом файле
     *
     * @throws ApplicationException
     *             ошибка сохранения онтологии
     */
    @Override
    public void saveInNewFile() throws ApplicationException {
        try {
            owlModel.save(newOwlFile.toURI());
        } catch (Exception e) {
            throw new ApplicationException(ApplicationErrors.SAVING_ONTOLOGY_ERROR);
        }
    }

    @Override
    public JenaOWLModel getOwlModel() {
        return owlModel;
    }

}
