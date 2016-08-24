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
        List<UserMeal> mealList = UserMealsGenerator.generateUserMealList(5000000);

        getFilteredMealsWithExceeded(mealList, LocalTime.of(11, 0), LocalTime.of(14,0), 1300);
        getFilteredMealsWithExceededWithoutStream(mealList, LocalTime.of(11, 0), LocalTime.of(14,0), 1300);

    }

    public static List<UserMealWithExceed> getFilteredMealsWithExceededWithoutStream (List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<UserMealWithExceed> filteredList = new ArrayList<>();
        Map<LocalDate, Integer> dateCaloriesMap = new HashMap<>();

        long tStart = System.nanoTime();

        for (UserMeal userMeal : mealList) {
            if (dateCaloriesMap.containsKey(userMeal.toLocalDate())) {
                dateCaloriesMap.put(userMeal.toLocalDate(), dateCaloriesMap.get(userMeal.toLocalDate()) + userMeal.getCalories());
            }
            else {
                dateCaloriesMap.put(userMeal.toLocalDate(), userMeal.getCalories());
            }
        }
        mealList.stream().filter(userMeal -> userMeal.toLocalTime().isAfter(startTime) && userMeal.toLocalTime().isBefore(endTime)).forEach(userMeal -> {
            if (dateCaloriesMap.get(userMeal.toLocalDate()) > caloriesPerDay) {
                filteredList.add(new UserMealWithExceed(userMeal, true));
            } else {
                filteredList.add(new UserMealWithExceed(userMeal, false));
            }
        });

        long tEnd = System.nanoTime();
        System.out.println("Time for for streams: " + (tEnd - tStart));

        return filteredList;


    }

    public static List<UserMealWithExceed> getFilteredMealsWithExceeded (List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> filteredList = new ArrayList<>();
        Map<LocalDate, Integer> dateCaloriesMap = new HashMap<>();

        long tStart = System.nanoTime();

        mealList.
                stream().
                forEach(userMeal -> {
                    if (dateCaloriesMap.containsKey(userMeal.toLocalDate())) {
                        dateCaloriesMap.put(userMeal.toLocalDate(), dateCaloriesMap.get(userMeal.toLocalDate()) + userMeal.getCalories());
                    }
                    else {
                        dateCaloriesMap.put(userMeal.toLocalDate(), userMeal.getCalories());
                    }
                });

        mealList
                .stream()
                .filter(userMeal -> userMeal.toLocalTime().isAfter(startTime) && userMeal.toLocalTime().isBefore(endTime))
                .forEach(userMeal -> {
                    if (dateCaloriesMap.get(userMeal.toLocalDate()) > caloriesPerDay) {
                        filteredList.add(new UserMealWithExceed(userMeal, true));
                    } else {
                        filteredList.add(new UserMealWithExceed(userMeal, false));
                    }
                });

        long tEnd = System.nanoTime();
        System.out.println("Time for streams: " + (tEnd - tStart));
        return filteredList;
    }



}
