package br.com.eduardomaxwell.testeappkotlin.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.eduardomaxwell.testeappkotlin.model.UserModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserModel)

    @Delete
    fun delete(user: UserModel)

    @Update
    suspend fun update(user: UserModel)

    @Query("SELECT * FROM user_tb")
    fun getAllUsers(): LiveData<List<UserModel>>

    @Query("SELECT * FROM user_tb WHERE uid = :uid")
    fun getUserBy(uid: Int) : LiveData<UserModel>


}