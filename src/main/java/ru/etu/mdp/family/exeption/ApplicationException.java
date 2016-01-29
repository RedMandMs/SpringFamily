package ru.etu.mdp.family.exeption;

/**
 * @author Vilgodskiy Sergey
 *
 *         Класс ошибки приложения
 */
public class ApplicationException extends Exception {

    public ApplicationException(ApplicationErrors applicationError) {
        super(applicationError.getMessage());
    }

}
