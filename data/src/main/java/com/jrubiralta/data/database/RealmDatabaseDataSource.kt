package com.jrubiralta.data.database

import android.util.Log
import com.jrubiralta.data.database.realm.RxRealm
import com.jrubiralta.data.extension.removeAll
import com.jrubiralta.data.extension.save
import com.jrubiralta.data.mapper.toModel
import com.jrubiralta.data.mapper.toVo
import com.jrubiralta.data.model.vo.TransactionVO
import com.jrubiralta.domain.model.Transaction
import io.reactivex.Maybe
import io.reactivex.Single
import io.realm.Realm

class RealmDatabaseDataSource : DatabaseDataSource {

    override fun saveTransactions(list: List<Transaction>): Single<Int> {
        var num = 0
        list.map {
            it.toVo().save()
            ++num
            Log.d("THREAD", Thread.currentThread().toString())
        }
        return Single.just(num)
    }

    override fun getTransactions(): Maybe<List<Transaction>> = RxRealm.getList {
        it.where(TransactionVO::class.java)
            .findAll()
    }
        .map { list -> list.map { transaction -> transaction.toModel() } }


    override fun clearAllDataBase(): Single<Boolean> {
        val realm = Realm.getDefaultInstance()
        realm.use { it1 ->
            it1.executeTransaction { it2 ->
                it2.where(TransactionVO::class.java).findAll().deleteAllFromRealm()
            }
        }
        return Single.just(true)
    }

    override fun getProductTransactions(sku: String): Maybe<List<Transaction>> = RxRealm.getList {
        it.where(TransactionVO::class.java)
            .equalTo("sku", sku)
            .findAll()
        }
        .map { list -> list.map { transaction -> transaction.toModel()}
    }
}
