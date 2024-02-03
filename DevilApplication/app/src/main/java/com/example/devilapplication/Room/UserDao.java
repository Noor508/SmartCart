package com.example.devilapplication.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(Users user);

    @Query("SELECT * FROM users")
    LiveData<List<Users>> getAllUsers();

    @Update
    void update(Users users);

    @Query("DELETE FROM users WHERE id = :id")
    void delete(int id);

    @Query("DELETE FROM users")
    void deleteAll(); // Add this method for deleting all users
}
