package com.arty.timespent.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserStatsDao {
    // Получаем статы. Flow позволит UI обновляться автоматически при изменениях.
    @Query("SELECT * FROM user_stats WHERE id = 0")
    fun getUserStats(): Flow<UserStats?>

    // Сохраняем или обновляем данные
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(stats: UserStats)
}