package ru.etu.mdp.family.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.etu.mdp.family.domain.ChangeForm;
import ru.etu.mdp.family.exeption.ApplicationException;
import ru.etu.mdp.family.servises.property.ObjectPropertyService;

/**
 *
 * @author Vilgodskiy Sergey
 *
 *         Контроллер для работы с объектными свойствами экземпляров
 *
 */
@Controller("objectProperty/")
public class ObjectPropertyController {

    /**
     * Сервис для работы с объектными свойствами экземпляров
     */
    @Autowired
    private ObjectPropertyService objectPropertyService;

    /**
     * Установить объектное свойство экземпляра
     *
     * @param nameIndividual
     *            имя экземпляра
     * @param nameProperty
     *            имя свойства
     * @param valueProperty
     *            значение свойства
     * @return редирект на страницу экземпляра
     * @throws ApplicationException
     *             ошибказаписи данных
     */
    @RequestMapping(value = "/setObjectPropertyValue/", method = RequestMethod.POST)
    public String setObjectPropertyValue(
        @ModelAttribute("nameIndividual") String nameIndividual,
        @ModelAttribute("nameProperty") String nameProperty,
        @ModelAttribute("valueProperty") String valueProperty)
            throws ApplicationException {
        ChangeForm changeForm = new ChangeForm();
        changeForm.setNameIndividual(nameIndividual);
        changeForm.setNameProperty(nameProperty);
        changeForm.setNewValue(valueProperty);
        objectPropertyService.setPropertyValue(changeForm);
        return "redirect:/getIndividual/" + nameIndividual;
    }

    /**
     * Добавить одно значение - При мульти-значении
     *
     * @param changePropertyForm
     *            форма изменения с необходимыми данными
     * @return редирект обратно на cтраницу
     * @throws ApplicationException
     *             ошибка записи данных
     */
    @RequestMapping(value = "/addObjectPropertyValue/", method = RequestMethod.POST)
    public String addObjectPropertyValue(
        @ModelAttribute("changePropertyForm") ChangeForm changePropertyForm)
            throws ApplicationException {
        objectPropertyService.addPropertyValue(changePropertyForm);
        return "redirect:/";
    }

    /**
     * Удалить одно значение - При мульти-значении
     *
     * @param changePropertyForm
     *            форма изменения с необходимыми данными
     * @return редирект обратно на cтраницу
     * @throws ApplicationException
     *             ошибка удаления значения свойства
     */
    @RequestMapping(value = "/removeOneObjectPropertyValue/", method = RequestMethod.POST)
    public String removeOneObjectPropertyValue(
        @ModelAttribute("changePropertyForm") ChangeForm changePropertyForm)
            throws ApplicationException {
        objectPropertyService.removeOnePropertyValue(changePropertyForm);
        return "redirect:/";
    }

    /**
     * Удалить все значения - При мульти-значении
     *
     * @param changePropertyForm
     *            форма изменения с необходимыми данными
     * @return редирект обратно на cтраницу
     * @throws ApplicationException
     *             ошибка удаления значения свойства
     */
    @RequestMapping(value = "/clearObjectPropertyValue/", method = RequestMethod.POST)
    public String clearObjectPropertyValue(
        @ModelAttribute("changePropertyForm") ChangeForm changePropertyForm)
            throws ApplicationException {
        objectPropertyService.clearPropertyValue(changePropertyForm);
        return "redirect:/";
    }
}
