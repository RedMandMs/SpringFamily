package ru.etu.mdp.family.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.etu.mdp.family.domain.ChangeForm;
import ru.etu.mdp.family.exeption.ApplicationException;
import ru.etu.mdp.family.servises.property.DataTypePropertyService;

/**
 *
 * @author Vilgodskiy Sergey
 *
 *         Контроллер для работы с свойствами экземпляра
 *
 */
@Controller("/dataTypeProperty/")
public class DataTypePropertyController {

    /**
     * Сервис для работы с свойствами экземпляров
     */
    @Autowired
    private DataTypePropertyService dataTypePropertyService;

    /**
     * Установить новое значение свойства
     *
     * @param nameIndividual
     *            имя экземпляра
     * @param nameProperty
     *            имя свойства
     * @param valueProperty
     *            новое значение свойства
     * @return адрес ридеректа
     * @throws ApplicationException
     *             ошибка записи данных
     */
    @RequestMapping(value = "/setDateTypeProperty/", method = RequestMethod.POST)
    public String setDataTypeProperty(
        @ModelAttribute("nameIndividual") String nameIndividual,
        @ModelAttribute("nameProperty") String nameProperty,
        @ModelAttribute("valueProperty") String valueProperty)
            throws ApplicationException {
        ChangeForm changeForm = new ChangeForm();
        changeForm.setNameIndividual(nameIndividual);
        changeForm.setNameProperty(nameProperty);
        changeForm.setNewValue(valueProperty);
        dataTypePropertyService.setPropertyValue(changeForm);
        return "redirect:/getIndividual/" + nameIndividual;

    }

    /**
     * Удалить значение свойства из экземпляра
     * 
     * @param changeForm
     *            форма с именем экземпляра и свойства
     * @return адрес ридеректа
     * @throws ApplicationException
     *             ошибка удаления данных
     */
    @RequestMapping(value = "/deleteDateTypeProperty", method = RequestMethod.POST)
    public String deleteDataTypeProperty(
        @ModelAttribute("changePropertyForm") ChangeForm changeForm)
            throws ApplicationException {
        dataTypePropertyService.deletePropertyValue(changeForm);
        return "redirect:/";
    }

}
