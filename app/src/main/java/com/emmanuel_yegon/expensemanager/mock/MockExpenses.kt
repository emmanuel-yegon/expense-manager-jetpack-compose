package com.emmanuel_yegon.expensemanager.mock

import androidx.compose.ui.graphics.Color
import com.emmanuel_yegon.expensemanager.models.Category
import com.emmanuel_yegon.expensemanager.models.Expense
import com.emmanuel_yegon.expensemanager.models.Recurrence
import io.github.serpro69.kfaker.Faker
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit


val faker = Faker()

val mockCategories =
    listOf(
        Category(
            "Groceries", Color(
                faker.random.nextInt(0, 255),
                faker.random.nextInt(0, 255),
                faker.random.nextInt(0, 255)
            )
        ),
        Category(
            "Bills", Color(
                faker.random.nextInt(0, 255),
                faker.random.nextInt(0, 255),
                faker.random.nextInt(0, 255)
            )
        ),
        Category(
            "Subscriptions", Color(
                faker.random.nextInt(0, 255),
                faker.random.nextInt(0, 255),
                faker.random.nextInt(0, 255)
            )
        ),
        Category(
            "Take out", Color(
                faker.random.nextInt(0, 255),
                faker.random.nextInt(0, 255),
                faker.random.nextInt(0, 255)
            )
        ),
    )

val mockExpenses: List<Expense> = List(30) { index ->
    Expense(
        id = index,
        amount = faker.random.nextInt(min = 1, max = 999).toDouble() + faker.random.nextDouble(),
        date = LocalDateTime.now()
            .minus(faker.random.nextInt(min = 300, max = 345600).toLong(), ChronoUnit.SECONDS),
        recurrence = faker.random.randomValue(
            listOf(
                Recurrence.None,
                Recurrence.Daily,
                Recurrence.Weekly,
                Recurrence.Monthly,
                Recurrence.Yearly
            )
        ),
        note = faker.australia.animals(),
        category = faker.random.randomValue(
            mockCategories
        )
    )

}

