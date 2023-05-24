package com.dam.hoopsdynasty.data.repository

import com.dam.hoopsdynasty.data.dao.ManagerDao
import com.dam.hoopsdynasty.data.model.Manager

    class ManagerRepository(private val managerDao: ManagerDao) {
        suspend fun insertManager(manager: Manager) {
            managerDao.insertManager(manager)
        }

        suspend fun getManager(): Manager? {
            return managerDao.getManager()
        }

        suspend fun updateManager(manager: Manager) {
            managerDao.updateManager(manager)
        }

        //singleton pattern
        companion object {
            @Volatile
            private var instance: ManagerRepository? = null

            fun getInstance(managerDao: ManagerDao): ManagerRepository {
                return instance ?: synchronized(this) {
                    instance ?: ManagerRepository(managerDao).also { instance = it }
                }
            }
        }
    }
