package ru.etu.mdp.family.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.etu.mdp.family.domain.OntologyForm;
import ru.etu.mdp.family.exeption.ApplicationException;
import ru.etu.mdp.family.servises.ontology.OntologyService;

/**
 *
 * @author Vilgodskiy Sergey
 *
 *         Контроллер для работы с онтологией
 *
 */
@Controller("/ontology/")
public class OntologyController {

    /**
     * Сервис для работы с онтологией
     */
    @Autowired
    private OntologyService ontologyService;

    /**
     * Создать пустую онтологию
     *
     * @param ontologyForm
     *            форма с именем и URI онтогии
     * @return редирект обратно на cтраницу
     * @throws ApplicationException
     *             ошибка создания онтологии
     */
    @RequestMapping(value = "/createEmptyOntology", method = RequestMethod.POST)
    public String createOntology(
        @ModelAttribute("ontologyForm") OntologyForm ontologyForm)
            throws ApplicationException {
        ontologyService.createEmptyOntology(ontologyForm);
        return "redirect:/";
    }

    /**
     * Считать онтологию по-умолчанию
     *
     * @return редирект обратно на cтраницу
     *
     * @throws ApplicationException
     *             ошибка считвания онтологии
     */
    @RequestMapping(value = "/readDefoultOntology", method = RequestMethod.POST)
    public String readDefoultOntology() throws ApplicationException {
        ontologyService.readDefoultOntology();
        return "redirect:/";
    }

    /**
     * Считать онтологию
     *
     * @param ontologyForm
     *            форма с путём до онтологии и URI
     * @return редирект обратно на cтраницу
     * @throws ApplicationException
     *             ошибка считывания онтологии
     */
    @RequestMapping(value = "/readOntology", method = RequestMethod.POST)
    public String readOntology(@ModelAttribute("OntologyForm") OntologyForm ontologyForm)
        throws ApplicationException {
        ontologyService.readOntology(ontologyForm);
        return "redirect:/";
    }

    /**
     * Сохранить онтологию (изменения онтологии)
     *
     * @return редирект обратно на cтраницу
     *
     * @throws ApplicationException
     *             ошибка сохранения онтологии
     */
    @RequestMapping(value = "/saveOntology", method = RequestMethod.POST)
    public String saveOntology() throws ApplicationException {
        ontologyService.saveOntology();
        return "redirect:/";
    }

    /**
     * Сохранить онтологию в новом файле
     *
     * @return редирект обратно на cтраницу
     *
     * @throws ApplicationException
     *             ошибка сохранения онтологии
     */
    @RequestMapping(value = "/saveInNewFile", method = RequestMethod.POST)
    public String saveInNewFile() throws ApplicationException {
        ontologyService.saveInNewFile();
        return "redirect:/";
    }

}
