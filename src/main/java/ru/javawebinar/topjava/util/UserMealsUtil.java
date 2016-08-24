package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * Created by Ovik on 23.08.2016.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.JUNE, 30,10,0), "A", 500),
                new UserMeal(LocalDateTime.of(2015, Month.JUNE, 30,13,0), "B", 500),
                new UserMeal(LocalDateTime.of(2015, Month.JUNE, 30,20,0), "C", 500),

                new UserMeal(LocalDateTime.of(2015, Month.JUNE, 29,10,0), "A", 400),
                new UserMeal(LocalDateTime.of(2015, Month.JUNE, 29,13,0), "B", 400),
                new UserMeal(LocalDateTime.of(2015, Month.JUNE, 29,20,0), "C", 400)
        );

        System.out.println(getFilteredMealsWithExceeded(mealList, LocalTime.of(0, 0), LocalTime.of(23,0), 100));

    }

    public static List<UserMealWithExceed> getFilteredMealsWithExceeded (List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> filteredList = new ArrayList<>();
        Map<LocalDate, Integer> dateCaloriesMap = new HashMap<>();

        for (UserMeal userMeal :
                mealList) {
            if (dateCaloriesMap.containsKey(userMeal.toLocalDate())) {
                dateCaloriesMap.put(userMeal.toLocalDate(), dateCaloriesMap.get(userMeal.toLocalDate()) + userMeal.getCalories());
            }
            else {
                dateCaloriesMap.put(userMeal.toLocalDate(), userMeal.getCalories());
            }
        }

        mealList.
                stream().
                filter(userMeal -> userMeal.toLocalTime().isAfter(startTime) && userMeal.toLocalTime().isBefore(endTime)).
                forEach(userMeal -> {
                    if (dateCaloriesMap.get(userMeal.toLocalDate()) > caloriesPerDay) {
                        filteredList.add(new UserMealWithExceed(userMeal, true));
                    } else {
                        filteredList.add(new UserMealWithExceed(userMeal, false));
                    }
                });

        return filteredList;
    }



}
