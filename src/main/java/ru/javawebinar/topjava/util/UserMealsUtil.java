package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ovik on 23.08.2016.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.JUNE, 30,10,0), "A", 500),
                new UserMeal(LocalDateTime.of(2015, Month.JUNE, 30,13,0), "B", 500),
                new UserMeal(LocalDateTime.of(2015, Month.JUNE, 30,20,0), "C", 500),
                new UserMeal(LocalDateTime.of(2015, Month.JUNE, 30,10,0), "A", 500),
                new UserMeal(LocalDateTime.of(2015, Month.JUNE, 30,13,0), "B", 500),
                new UserMeal(LocalDateTime.of(2015, Month.JUNE, 30,20,0), "C", 500)
        );
    }
}
