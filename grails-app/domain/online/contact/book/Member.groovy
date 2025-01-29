package online.contact.book

class Member {
    Integer id
    String firstName
    String lastName
    String email
    String password
    String memberType = GlobalConfig.USER_TYPE.REGULAR_MEMBER
    String identityHash
    Long identityHashLastUpdate
    Boolean isActive = true

    Date dateCreated
    Date lastUpdated

    static constraints = {
        email(email: false, nullable: false, unique: false, blank: false)
        password(blank: false)
        lastName(nullable: true)
        identityHash(nullable: true)
        identityHashLastUpdate(nullable: true)

    }

    def beforeInsert() {
        this.password = this.password.encodeAsMD5()
    }

    def afterUpate() {
        this.password = this.password.encodeAsMD5()
    }
}
