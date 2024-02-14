package com.emmanuel_yegon.expensemanager

import com.emmanuel_yegon.expensemanager.models.Category
import com.emmanuel_yegon.expensemanager.models.Expense
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration



val config = RealmConfiguration.Builder(
    schema = setOf(Expense::class,Category::class)
)
    .schemaVersion(2) // Sets the new schema version to 2
    .build()

val db: Realm = Realm.open(config)


