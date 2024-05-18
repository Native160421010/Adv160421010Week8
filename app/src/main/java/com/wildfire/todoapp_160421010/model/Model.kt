package com.wildfire.todoapp_160421010.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @ColumnInfo(name="title")
    var title:String,
    @ColumnInfo(name="notes")
    var notes:String,
    @ColumnInfo(name="priority")
    var priority:Int,
    @ColumnInfo(name="is_done")
    var is_done:Int = 0 // default is 0 a.k.a not done yet.

    // Integer (int) is used instead of boolean for is_done due to the following:
    // 1. Efficiency, int data type is much space efficient than boolean, perfect for optimizing
    //      memory usage and maximizing speed.
    // 2. Flexibility, sometimes the data state can be expanded further.
    //      For example:
    //      todo can include new state: "Do_on_date_later" where it can only be done on a certain,
    //      date and will only show up a day before said date. By using integer, the database can
    //      be expanded to include said new state and other data required.
    // 3. Compatibility, many old databases have int data type, but not boolean. So this app will-
    //      work with them.

) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
}
