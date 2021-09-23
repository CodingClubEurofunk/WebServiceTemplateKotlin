import eurofunk.Application
import eurofunk.services.IUserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

/**
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class ServiceTest {

    @Autowired
    private lateinit var userService: IUserService

    @Test
    fun testCrudlOperations() {
        var firstName = UUID.randomUUID().toString()
        var lastName = UUID.randomUUID().toString()

        // Add user
        val id = userService.add(firstName = firstName, lastName = lastName)
        // Check if user exists and if name matches
        checkData(id = id, firstName = firstName, lastName = lastName)

        // Assign new name
        firstName = UUID.randomUUID().toString()
        lastName = UUID.randomUUID().toString()

        // Update user with name
        userService.update(id = id, firstName = firstName, lastName = lastName)
        // Check if user exists and if new name matches
        checkData(id = id, firstName = firstName, lastName = lastName)

        // Check if only one user has been added
        var models = userService.findAll()
        assert(models.size == 1)

        // Delete user
        userService.delete(id = id)
        // Check that no user is saved anymore
        models = userService.findAll()
        assert(models.isEmpty())
    }

    private fun checkData(id: String, firstName: String, lastName: String) {
        val entity = userService.findById(id = id)
        assert(entity != null)
        assert(entity?.firstName == firstName)
        assert(entity?.lastName == lastName)
    }
}