package ru.etu.mdp.family.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ru.etu.mdp.family.domain.ChangeForm;
import ru.etu.mdp.family.exeption.ApplicationException;
import ru.etu.mdp.family.servises.individual.IndividualService;
import ru.etu.mdp.family.servises.property.DataTypePropertyService;

import edu.stanford.smi.protegex.owl.model.OWLIndividual;

/**
 *
 * @author Vilgodskiy Sergey
 *
 *         Контроллер для работы с экземалярами онтологии
 *
 */
@Controller("/individualController/")
public class IndividualController {

    /**
     * Сервис для работы с экземплярами онтологии
     */
    @Autowired
    private IndividualService individualService;

    /**
     * Сервис для раюоты с свойствами экземаляра класса
     */
    @Autowired
    private DataTypePropertyService dataTypePropertyService;

    /**
     * Получить страинцу экземаляра
     * 
     * @param nameIndividual
     *            имя экземпляра
     * @return искомый экземпляр
     * @throws ApplicationException
     *             ошибка считывая экземпляра класса
     */
    @RequestMapping(value = "/getIndividual/", method = RequestMethod.POST)
    public ModelAndView getIndividual(
        @ModelAttribute("nameIndividual") String nameIndividual)
            throws ApplicationException {

        ModelAndView modelAndView = new ModelAndView("individual");
        ChangeForm changeForm = new ChangeForm();
        changeForm.setNameIndividual(nameIndividual);
        OWLIndividual individual = individualService.getIndividual(changeForm);
        modelAndView.addObject("individual", individual);
        return modelAndView;
    }

    /**
     * Получить страницу экземаляра
     * 
     * @param nameIndividual
     *            имя экземпляра
     * @return страница экземпляра
     * @throws ApplicationException
     *             ошибка считывая экземпляра класса
     */
    @RequestMapping(value = "/getIndividual/{nameIndividual}", method = RequestMethod.GET)
    public ModelAndView getIndividualByName(
        @PathVariable("nameIndividual") String nameIndividual)
            throws ApplicationException {
        ModelAndView modelAndView = getIndividual(nameIndividual);
        return modelAndView;
    }

    /**
     * Создать экземпляр онтологии
     * 
     * @param nameClass
     *            имя класса
     * @param nameIndividual
     *            имя экземпляра
     * @return редирект на главную страницу
     * @throws ApplicationException
     *             ошибка создания экземпляра класса
     */
    @RequestMapping(value = "/createIndividual/", method = RequestMethod.POST)
    public String createIndividual(@ModelAttribute("nameClass") String nameClass,
        @ModelAttribute("nameIndividual") String nameIndividual)
            throws ApplicationException {
        ChangeForm changeForm = new ChangeForm();
        changeForm.setIndividualClassName(nameClass);
        changeForm.setNameIndividual(nameIndividual);
        individualService.createIndividual(changeForm);
        ChangeForm changeForm2 = new ChangeForm();
        changeForm2.setNameIndividual(nameIndividual);
        changeForm2.setNameProperty("Sex");
        if (nameClass.equals("Man")) {
            changeForm2.setNewValue("male");
        } else {
            changeForm2.setNewValue("female");
        }
        dataTypePropertyService.setPropertyValue(changeForm2);
        return "redirect:/";
    }

    /**
     * Удаление экземпляра онтологии
     * 
     * @param nameIndividual
     *            имя экземпляра
     * @return редирект обратно на страницу
     * @throws ApplicationException
     *             ошибка удаления данных
     */
    @RequestMapping(value = "/deleteIndividual/", method = RequestMethod.POST)
    public String deleteIndividual(
        @ModelAttribute("nameIndividual") String nameIndividual)
            throws ApplicationException {
        ChangeForm changeForm = new ChangeForm();
        changeForm.setNameIndividual(nameIndividual);
        individualService.deleteIndividual(changeForm);

        return "redirect:/";
    }

    /**
     * Переименовать экземпляр онтологии
     * 
     * @param changeForm
     *            форма со старым и новым именем экземпляра
     * @return редирект обратно на страницу
     * @throws ApplicationException
     *             ошибка изменения данных
     */
    @RequestMapping(value = "/renameIndividual/", method = RequestMethod.POST)
    public String renameIndividual(@ModelAttribute("changeForm") ChangeForm changeForm)
        throws ApplicationException {

        individualService.renameIndividual(changeForm);

        return "redirect:/";
    }

}
