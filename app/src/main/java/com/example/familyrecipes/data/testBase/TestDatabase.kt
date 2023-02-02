package com.example.familyrecipes.data.testBase

import androidx.compose.runtime.mutableStateOf
import com.example.familyrecipes.data.models.Ingredient
import com.example.familyrecipes.data.models.MethodStep
import com.example.familyrecipes.data.models.Recipe
import java.time.LocalTime

object TestDatabase {

    private val ingredientList = mutableListOf(
        Ingredient(mutableStateOf("Pizza dough")),
        Ingredient(mutableStateOf("Tomato sauce")),
        Ingredient(mutableStateOf("Mozzarella cheese")),
        Ingredient(mutableStateOf("Toppings of your choice")),
        Ingredient(mutableStateOf("Olive oil")),
        Ingredient(mutableStateOf("Salt")),
        Ingredient(mutableStateOf("Dried or fresh herbs")),
    )

    private val stepList = mutableListOf(
        MethodStep(
            mutableStateOf(
                "Prepare the dough: Mix flour, yeast, salt, sugar, " +
                        "and water (or other liquids like olive oil or milk) to form a soft, elastic dough." +
                        " Let the dough rise for at least 1 hour or until it has doubled in size."
            )
        ),
        MethodStep(
            mutableStateOf(
                "Preheat the oven: Preheat your oven to the highest " +
                        "temperature it can reach (usually around 250°C to 300°C). If you have a pizza " +
                        "stone, place it in the oven to preheat as well."
            )
        ),
        MethodStep(
            mutableStateOf(
                "Roll out the dough: Dust your work surface with flour and " +
                        "use a rolling pin to roll out the dough to your desired thickness. Place the " +
                        "rolled-out dough onto a pizza peel or a sheet of baking paper."
            )
        ),
        MethodStep(
            mutableStateOf(
                "Add the sauce and toppings: Spoon the tomato sauce over the" +
                        " pizza dough, leaving a small border around the edges. Sprinkle the mozzarella " +
                        "cheese over the sauce, followed by your choice of toppings."
            )
        ),
        MethodStep(
            mutableStateOf(
                "Bake the pizza: Slide the pizza onto the preheated pizza " +
                        "stone or place it directly onto the oven rack. Bake for 8 to 12 minutes, or until " +
                        "the crust is golden brown and the cheese is melted and bubbly."
            )
        ),
        MethodStep(
            mutableStateOf(
                "Serve and enjoy: Remove the pizza from the oven and let it " +
                        "cool for a few minutes. Slice and serve hot."
            )
        ),


        )

    val myRecipeList = mutableListOf<Recipe>(
        Recipe(
            "Granny's cake",
            null,
            LocalTime.of(1, 20),
            3,
            ingredientList,
            stepList,
        ),
        Recipe(
            "Smoky braised brisket",
            null,
            LocalTime.of(0, 45),
            2,
            ingredientList,
            stepList,
        ),
        Recipe(
            "Sticky bourbon BBQ wings with blue cheese dip",
            null,
            LocalTime.of(2, 8),
            2,
            ingredientList,
            stepList,
        ),
        Recipe(
            "Corndogs",
            null,
            LocalTime.of(0, 20),
            4,
            ingredientList,
            stepList,
        ),
        Recipe(
            "Pecan pie with maple cream",
            null,
            LocalTime.of(1, 10),
            1,
            ingredientList,
            stepList,
        ),
        Recipe(
            "Beaf soup",
            null,
            LocalTime.of(1, 15),
            2,
            ingredientList,
            stepList,
        ),
        Recipe(
            "American pancakes",
            null,
            LocalTime.of(0, 50),
            3,
            ingredientList,
            stepList,
        ),

        Recipe(
            "Granny's cake",
            null,
            LocalTime.of(1, 20),
            3,
            ingredientList,
            stepList,
        ),
        Recipe(
            "Smoky braised brisket",
            null,
            LocalTime.of(0, 45),
            2,
            ingredientList,
            stepList,
        ),
        Recipe(
            "Sticky bourbon BBQ wings with blue cheese dip",
            null,
            LocalTime.of(2, 8),
            2,
            ingredientList,
            stepList,
        ),
        Recipe(
            "Corndogs",
            null,
            LocalTime.of(0, 20),
            4,
            ingredientList,
            stepList,
        ),
        Recipe(
            "Pecan pie with maple cream",
            null,
            LocalTime.of(1, 10),
            1,
            ingredientList,
            stepList,
        ),
        Recipe(
            "Beaf soup",
            null,
            LocalTime.of(1, 15),
            2,
            ingredientList,
            stepList,
        ),
        Recipe(
            "American pancakes",
            null,
            LocalTime.of(0, 50),
            3,
            ingredientList,
            stepList,
        ),
    )
}