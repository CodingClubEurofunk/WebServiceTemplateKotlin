package eurofunk.services

import eurofunk.models.UserModel

/**
 *
 */
interface IUserService {

    /**
     *
     */
    fun add(firstName: String, lastName: String): String

    /**
     *
     */
    fun update(id: String, firstName: String, lastName: String)

    /**
     *
     */
    fun delete(id: String)

    /**
     *
     */
    fun findById(id: String): UserModel?

    /**
     *
     */
    fun findAll(): List<UserModel>
}