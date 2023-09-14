package com.gogolook.wheresmoney.data

class CategoryRepositoryImpl(private val dao: CategoryDao) : CategoryRepository {
    override fun getAll(): List<Category> = dao.getAll()
    override fun get(id: Int): Category? = dao.get(id)
    override fun insert(category: Category): Long = dao.insert(category)
    override fun update(category: Category) = dao.update(category)
    override fun delete(category: Category) = dao.delete(category)
}