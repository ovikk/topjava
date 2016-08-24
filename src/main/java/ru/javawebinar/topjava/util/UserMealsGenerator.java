package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ovikdevil on 25.08.16.
 */
public class UserMealsGenerator {
    private static Random random = new Random();
    private static final String description = "TestDescription";

    public static List<UserMeal> generateUserMealList(int listLength) {
        List<UserMeal> userMealList = new ArrayList<>();
        for (int i = 0; i < listLength; i++) {
            userMealList.add(new UserMeal(
                    LocalDateTime.of(random.nextInt(2016), random.nextInt(12)+1, random.nextInt(28)+1, random.nextInt(24), random.nextInt(60), random.nextInt(60)),
                    description,
                    random.nextInt(2000)));
        }
        return userMealList;
    }

}
