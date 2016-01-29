package ru.etu.mdp.family.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ru.etu.mdp.family.domain.ChangeForm;
import ru.etu.mdp.family.exeption.ApplicationException;
import ru.etu.mdp.family.servises.ontology.OntologyService;

/**
 *
 * @author Vilgodskiy Sergey
 *
 *         Контроллер главной страницы
 *
 */
@Controller("/")
public class MainController {

    /**
     * Сервис для работы с онтологией
     */
    @Autowired
    private OntologyService ontologyService;

    /**
     * апрос главной страницы
     *
     * @return главная страница
     * @throws ApplicationException
     *             ошибка получения онтологии
     */
    @RequestMapping("/")
    public ModelAndView home() throws ApplicationException {
        if (ontologyService.getOwlModel() == null) {
            ontologyService.readDefoultOntology();
        }
        ModelAndView modelAndView = new ModelAndView("index");
        ChangeForm changeForm = new ChangeForm();
        modelAndView.addObject("changeForm", changeForm);
        return modelAndView;
    }

}
