package eurofunk.controllers

import eurofunk.models.IdModel
import eurofunk.models.UserModel
import eurofunk.services.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 *
 */
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@Controller
@RequestMapping(path = ["/user"])
class UserController {

    @Autowired
    private lateinit var userService: IUserService

    /**
     *
     */
    @PutMapping("/add")
    fun add(@RequestParam firstName: String, @RequestParam lastName: String): ResponseEntity<IdModel> {
        return try {
            val id = userService.add(firstName = firstName, lastName = lastName)
            ResponseEntity(IdModel(id = id), HttpStatus.OK)
        } catch (ex: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    /**
     *
     */
    @PostMapping("/update")
    fun update(
        @RequestParam id: String,
        @RequestParam firstName: String,
        @RequestParam lastName: String
    ): ResponseEntity<HttpStatus> {
        return try {
            userService.update(id = id, firstName = firstName, lastName = lastName)
            ResponseEntity(HttpStatus.OK)
        } catch (ex: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    /**
     *
     */
    @DeleteMapping("/delete")
    fun delete(@RequestParam id: String): ResponseEntity<HttpStatus> {
        return try {
            userService.delete(id = id)
            ResponseEntity(HttpStatus.OK)
        } catch (ex: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    /**
     *
     */
    @GetMapping("/findById")
    fun findById(@RequestParam id: String): ResponseEntity<UserModel> {
        return try {
            val model = userService.findById(id = id)
            ResponseEntity(model, HttpStatus.OK)
        } catch (ex: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    /**
     *
     */
    @GetMapping("/findAll")
    fun findAll(): ResponseEntity<List<UserModel>> {
        return try {
            val models = userService.findAll()
            ResponseEntity(models, HttpStatus.OK)
        } catch (ex: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}
