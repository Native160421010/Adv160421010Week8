
package com.wildfire.todoapp_160421010.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo:Todo)

    @Query("SELECT * FROM todo WHERE is_done=:isDone ORDER BY priority DESC")
    fun selectAllTodo(isDone:Int): List<Todo>

    @Query("SELECT * FROM todo WHERE uuid= :id")
    fun selectTodo(id:Int): Todo

    @Query("UPDATE todo SET title=:title, notes=:notes, priority=:priority WHERE uuid = :id")
    fun update(title:String, notes:String, priority:Int, id:Int)

    @Query("UPDATE todo SET is_done = 1 WHERE uuid = :id")
    fun updateTodo(id: Int)

//    @Delete
//    fun deleteTodo(todo:Todo)
}
