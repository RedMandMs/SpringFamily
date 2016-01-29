package ru.etu.mdp.family.servises.property;

import java.util.Collection;
import java.util.List;

import ru.etu.mdp.family.domain.ChangeForm;
import ru.etu.mdp.family.exeption.ApplicationException;

import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLObjectProperty;

/**
 * @author Vilgodskiy Sergey
 *
 *         Сервис для работы с объектными свойствами экземпляров
 */
public interface ObjectPropertyService {

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
    List<OWLIndividual> getPropertyValue(String nameIndividual, String nameProperty)
        throws ApplicationException;

    /**
     * Получить все значения поля
     *
     * @param changePropertyForm
     *            форма изменения с необходимыми данными
     * @return список всех значений данного поля
     * @throws ApplicationException
     *             ошибка считывания свойства экземпляра
     */
    Collection<OWLIndividual> getAllPropertyValues(ChangeForm changePropertyForm)
        throws ApplicationException;

    /**
     * Установить значение поля
     *
     * @param changePropertyForm
     *            форма изменения с необходимыми данными
     * @throws ApplicationException
     *             ошибка записи свойства экземпляра
     */
    void setPropertyValue(ChangeForm changePropertyForm) throws ApplicationException;

    /**
     * Добавить одно значение - При мульти-значении
     *
     * @param changePropertyForm
     *            форма изменения с необходимыми данными
     * @throws ApplicationException
     *             ошибка добовления значения свойства
     */
    void addPropertyValue(ChangeForm changePropertyForm) throws ApplicationException;

    /**
     * Получить все объектные свойства экземпляра
     *
     * @param changePropertyForm
     *            форма изменения с необходимыми данными
     * @return список всех объектных свойств экземпляра
     * @throws ApplicationException
     *             ошибка считывания свойства экземпляра
     */
    Collection<OWLObjectProperty> getAllObjectProperties(ChangeForm changePropertyForm)
        throws ApplicationException;

    /**
     * Удалить одно значение - При мульти-значении
     *
     * @param changePropertyForm
     *            форма изменения с необходимыми данными
     * @throws ApplicationException
     *             ошибка удаления значения свойства
     */
    void removeOnePropertyValue(ChangeForm changePropertyForm)
        throws ApplicationException;

    /**
     * Удалить все значения - При мульти-значении
     *
     * @param changePropertyForm
     *            форма изменения с необходимыми данными
     * @throws ApplicationException
     *             ошибка удаления значения свойства
     */
    void clearPropertyValue(ChangeForm changePropertyForm) throws ApplicationException;
}
