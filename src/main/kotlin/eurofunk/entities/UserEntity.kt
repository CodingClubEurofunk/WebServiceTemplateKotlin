package eurofunk.entities

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 *
 */
@Entity
@Table(name = "users")
class UserEntity {

    /**
     *
     */
    @Id
    @Column
    var id: String = UUID.randomUUID().toString()

    /**
     *
     */
    @Column
    var firstName: String = ""

    /**
     *
     */
    @Column
    var lastName: String = ""
}