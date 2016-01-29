package ru.etu.mdp.family.servises.individual;

import java.util.List;

import ru.etu.mdp.family.domain.ChangeForm;
import ru.etu.mdp.family.exeption.ApplicationException;

import edu.stanford.smi.protegex.owl.model.OWLIndividual;

/**
 * @author Vilgodskiy Sergey
 *
 *         Сервис для работы с эекземпляром базы знаний
 */
public interface IndividualService {

    /**
     * Получение экземпляра по имени
     *
     * @param changeForm
     *            форма с именем и классом экземпляра
     * @return экземпляр
     */
    OWLIndividual getIndividual(ChangeForm changeForm);

    /**
     * Получить все экземпляры по названию класса
     *
     * @param className
     *            название класса
     * @return список всех экземпляров класса
     * @throws Exception
     *             ошибка считывания данных
     */
    List<OWLIndividual> getAllIndividualByClassName(String className) throws Exception;

    /**
     * Получить всех людей базы знаний
     *
     * @return Все люди в базе знаний
     * @throws Exception
     *             ошибка получения данных
     */
    List<OWLIndividual> getAllHumans() throws Exception;

    /**
     * Получить всех людей женщин в системе
     *
     * @return Все женщины в базе знаний
     * @throws Exception
     *             ошибка получения данных
     */
    List<OWLIndividual> getAllWomen() throws Exception;

    /**
     * Получить всех людей мужчин в системе
     *
     * @return Все мужчины в базе знаний
     * @throws Exception
     *             ошибка получения данных
     */
    List<OWLIndividual> getAllMen() throws Exception;

    /**
     * Создание нового экземпляра класса
     *
     * @param changeForm
     *            форма изменения с необходимыми данными
     * @throws ApplicationException
     *             ошибка создания экземпляра
     */
    void createIndividual(ChangeForm changeForm) throws ApplicationException;

    /**
     * Удаление экземпляра класса
     *
     * @param changeForm
     *            форма изменения с необходимыми данными
     * @throws ApplicationException
     *             ошибка удаления экземпляра
     */
    void deleteIndividual(ChangeForm changeForm) throws ApplicationException;

    /**
     * Переименование экземпляра класса
     *
     * @param changeForm
     *            форма изменения с необходимыми данными
     * @throws ApplicationException
     *             ошибка удаления экземпляра
     */
    void renameIndividual(ChangeForm changeForm) throws ApplicationException;
}
