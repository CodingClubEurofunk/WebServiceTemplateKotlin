package eurofunk.services

import eurofunk.entities.UserEntity
import eurofunk.models.UserModel
import eurofunk.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService: IUserService {

    @Autowired
    private lateinit var repositoryService: UserRepository

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    override fun add(firstName: String, lastName: String): String {
        logger.info("Adding new user. First Name '$firstName' | Last Name: '$lastName'")
        val entity = UserEntity()
        entity.firstName = firstName
        entity.lastName = lastName
        return repositoryService.save(entity).id
    }

    override fun update(id: String, firstName: String, lastName: String) {
        logger.info("Updating user by ID '$id'. First Name '$firstName' | Last Name: '$lastName'")
        val entityOptional = repositoryService.findById(id)
        if (entityOptional.isPresent) {
            val entity = entityOptional.get()
            entity.firstName = firstName
            entity.lastName = lastName
            repositoryService.save(entity)
        }
    }

    override fun delete(id: String) {
        logger.info("Deleting user by ID '$id'")
        repositoryService.deleteById(id)
    }

    override fun findById(id: String): UserModel? {
        logger.info("Get user by ID '$id'")
        repositoryService.findAll()
        val entityOptional = repositoryService.findById(id)
        return if (entityOptional.isPresent) {
            val entity = entityOptional.get()
            createModel(entity = entity)
        } else {
            null
        }
    }

    override fun findAll(): List<UserModel> {
        logger.info("Get all users")
        return repositoryService
            .findAll()
            .map { entity -> createModel(entity = entity) }
    }

    private fun createModel(entity: UserEntity): UserModel {
        val model = UserModel()
        model.id = entity.id
        model.firstName = entity.firstName
        model.lastName = entity.lastName
        return model
    }
}