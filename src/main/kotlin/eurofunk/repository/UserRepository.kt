package eurofunk.repository

import eurofunk.entities.UserEntity
import org.springframework.data.repository.CrudRepository

/**
 *
 */
interface UserRepository: CrudRepository<UserEntity, String>