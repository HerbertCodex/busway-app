package ci.justapp.busway.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ci.justapp.busway.data.local.entities.CategoryEntity

/**
 * Data Access Object (DAO) for interacting with the 'categories' table in the database.
 * This interface provides methods for querying, inserting, updating, and deleting
 * [CategoryEntity] objects.
 */
@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories ORDER BY created_at DESC")
    fun getAllCategories(): PagingSource<Int, CategoryEntity>

    @Query("SELECT * FROM categories WHERE id = :id")
    suspend fun getCategoryById(id: String): CategoryEntity?

    @Insert
    suspend fun insertCategory(category: CategoryEntity)

    @Insert
    suspend fun insertCategories(vararg categories: CategoryEntity)

    @Update
    suspend fun updateCategory(category: CategoryEntity)

    @Update
    suspend fun updateCategories(vararg categories: CategoryEntity)

    @Delete
    suspend fun deleteCategory(category: CategoryEntity)

    @Delete
    suspend fun deleteCategories(vararg categories: CategoryEntity)
}