package ru.etu.mdp.family.servises.property;

import ru.etu.mdp.family.domain.ChangeForm;
import ru.etu.mdp.family.exeption.ApplicationException;

/**
 * @author Vilgodskiy Sergey
 *
 *         Сервис для работы с примитивными свойствами объекта базы знаний
 */
public interface DataTypePropertyService {

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
    Object getPropertyValue(String nameIndividual, String nameProperty)
        throws ApplicationException;

    /**
     * Удалить значение поля
     *
     * @param changePropertyForm
     *            форма изменения с необходимыми данными
     * @throws ApplicationException
     *             ошибка удаления данных
     */
    void deletePropertyValue(ChangeForm changePropertyForm) throws ApplicationException;

    /**
     * Установить значение поля
     *
     * @param changePropertyForm
     *            форма изменения с необходимыми данными
     * @throws ApplicationException
     *             ошибка изменения значения поля
     */
    void setPropertyValue(ChangeForm changePropertyForm) throws ApplicationException;

    /**
     * Получить тип поля
     *
     * @param nameProperty
     * @return
     */
    String getTypeProperty(String nameProperty);
}
